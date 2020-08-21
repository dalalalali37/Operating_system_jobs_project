import java.util.LinkedList;
import java.util.Queue;
import java.util.PriorityQueue;
import java.io.FileWriter;
import java.util.Comparator;

public class OperatingSystem {

//2. Average/Minimum/Maximum job size in KB.
static int Maximum_Value=0;
static int Minimum_Value=999999999;
static int extall=0 ;/// i didn't change this !!!!!!!!!!

// store all generated processes
public static Queue<Job> jobs_Queue = new LinkedList<Job>();

// this method will write the generated programs into text file
public static void Writting_Jobs_To_File(Queue<Job> jobs) {

try {
FileWriter File_Writer = new FileWriter("Job.TXT");
for (Job job : jobs) {
File_Writer.write(" ID : " + job.getPcb().getID() + "\n"+ " Size : " + job.getPcb().getJobSize() + " KB "+ "\n"+ 
                                     " Execuation Time : " + job.getPcb().getJobExecutionTime() + " UT " + "\n"+ 
                                            " IRT : " + 0 +" UT "+ " WT : "+ 0 +" UT "+ " State : " + job.getPcb().getState() + "\n");
	
extall=extall+job.getPcb().getJobExecutionTime() ;  

    int Job_Size=job.getPcb().getJobSize();
                
      if(Maximum_Value < Job_Size){
	  Maximum_Value = Job_Size;
	}
      if(Minimum_Value > Job_Size){
	  Minimum_Value = Job_Size;
	}}
File_Writer.close(); } 
catch (Exception e) {
System.out.println(e.getMessage());
}}

//generate jobs with random size and execution time
	
	public static Queue<Job> initiate_Jobs(int wanted_programs) {
          if (wanted_programs > 0) {
         
			int Max_Size = 256; // KB   EMR between 16 KB and 256 KB
			int Min_Size = 16; 
         
         //3. Expected memory requirement (EMR): the memory (in KB) needed by the program. The EMR is
//generated randomly with a uniform distributed between 16 KB and 256
			int Min_Time = 16;
         int Max_Time = 512; 
			
         int Time_Range = Max_Time - Min_Time;
			int Size_Range = Max_Size - Min_Size;
			
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx

			// we will store the jobs in this queue
         // for (int i = 0; i < amount; i++) {
			for (int i = 1; i <= wanted_programs; i++) {
				int Job_Size = (int) (Math.random() * Size_Range) + Min_Size; // random size for the job
            int Job_Execution_Time = (int) (Math.random() *Time_Range) + Min_Time; // random execution time for the job
				int Waiting_Time = (int) (Math.random() * Time_Range) + Min_Time; // random execution time for the job
            int IOR_Time = (int) (Math.random() * Time_Range) + Min_Time; // random execution time for the job
            int Interrupt_probability = (int) (Math.random() * 100 + 1);
				if (Hardware.Check_Space(Job_Size)) { // if there is space in the memory
					Job job = new Job(
                                                Job_Size,
                                                Waiting_Time,
                                                IOR_Time,
                                                Job_Execution_Time,
                                                "NEW",
                                                "NotDetirmined",
                                                
				 Interrupt_probability,
                                                        false);
				job.checkInterrupt(Interrupt_probability);
				jobs_Queue.add(job);
				} else {
				break; // stop creating new jobs
				}

			}
			return jobs_Queue;

		} else {
			System.out.println("No program created");
			return null;
		}

	}
//2. Process scheduling. The operating system maintains a single ready queue and a single I/O
//queue. CPU Scheduler follows the First-Come, First-Served (FCFS) scheduling algorithm policy.

public static Queue<Job> Order_Process_By_SmallestSize(Queue<Job> jobs) { // here we will order the process the smallest
																	                 	// size we be first
Comparator<Job> Job_Comparator_S = new JobCompareSize();
PriorityQueue<Job> P_Queue = new PriorityQueue<Job>(jobs.size(), Job_Comparator_S);// this will order the process in
																					// queue
// here repeated store to because we need it in shorterRemainingtime first

for (Job job : jobs) {
P_Queue.add(job);

}
return P_Queue;
}

//2.Process scheduling. The operating system maintains a single ready queue and a single I/O
//queue. CPU Scheduler follows the First-Come, First-Served (FCFS) scheduling algorithm policy.
// here all the process will be chosen in order in term of jobExecututionTime() then it will go to RAM

public static void Shorter_Time_First() {//

Comparator<Job> Job_Comparator_T = new JobCompareTime();
PriorityQueue<Job> P_Queue = new PriorityQueue<Job>(jobs_Queue.size(), Job_Comparator_T);
for (Job job : Hardware.RAM) {
P_Queue.add(job);

}
		
for (Job job : P_Queue) {
Hardware.RAM.add(job);

}
		

}

public static void main(String[] args) {
	
		Queue<Job> jobs_Queue = initiate_Jobs(500000000);//any number but not less than 100
		Writting_Jobs_To_File(jobs_Queue);
		Queue<Job> IO_Queue = new LinkedList<Job>();
		Hardware.HardDisk_Jobs(Order_Process_By_SmallestSize(jobs_Queue));

		while (!Hardware.HardDisk.isEmpty()) {
		Hardware.Load_Process();
		Shorter_Time_First();
		CPU.executeProcesses(IO_Queue);
		}
		// double Normally= CPU.countNormalTerminiation;
		//double Abnomrally = CPU.contAbnormalTerminiation;
		
      System.out.println("The max of job size: " + Maximum_Value +" Kb ");
      System.out.println("The min of job size : " + Minimum_Value +" Kb ");
      System.out.println("The number of jobs completed their execution normally:  " +  Job.interrupt_normally_c );
		System.out.println("The number of jobs completed their execution abnormally: " +Job.interrupt_abnormally_c);  ///Hardware.NumOfProcess
		System.out.println("The Number of jobs serviced by I/O device: "+ Job.io);
      System.out.println("cpu utilization: "+Clock.currentTime+" }" +(  extall));//Clock.currentTime                
      System.out.println("I/O device utilization: "+ Job.waiting_Time);
                
///Hardware.NumOfProcess
//Process terminates abnormally 5% Job.inorm
//Process terminates normally 10% Job.ianorm
//IO request 20% Job.io
		try {	
            FileWriter File_Writer_R = new FileWriter("Result1.TXT");
				File_Writer_R.write("The max of job size: " + Maximum_Value +" Kb " +
                                             "The min of job size: " + Minimum_Value +" Kb "+
                                             "The number of jobs completed their execution normally:  " +  Job.interrupt_normally_c+ 
                                             "The number of jobs completed their execution abnormally: " +Job.interrupt_abnormally_c+
                                            "The Number of jobs serviced by I/O device: "+ Job.io+      
                                               "\n");
		   
File_Writer_R.close();
}// end try 

catch (Exception e) {
System.out.println(e.getMessage());
}

}
}