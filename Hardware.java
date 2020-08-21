import java.util.LinkedList;
import java.util.Queue;

public class Hardware {


	final static int Hardisk_Size = 2 * 1024 * 1024; // to kb     //2. A hard disk with 2 GB available for user programs.
	final static int RAM_Size = 192 * 1024 ;           //3. A RAM with 192 MB available for user programs
   final static int user_Program_Space = Hardisk_Size ;  /// 5;
	
  
	public static Queue<Job> RAM = new LinkedList<Job>();
   public static int number_Of_Process = 0;
	public static Queue<Job> HardDisk = new LinkedList<Job>();
	public static int User_Programs_Size = 0;
   
	


//1. Job scheduling: The operating system maintains a single job queue. Job Scheduler follows the Smallest Storage Requirement (SSR) policy.
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx	
   public static void Load_Process() { // here we will use Smallest size process first to load the
											                                                                      	// process into RAM
		int JobsSize = 0;
		while (!HardDisk.isEmpty()) {
			
			if (JobsSize < RAM_Size) {
				Job job = HardDisk.poll(); //returns and removes the element at the front the container
				JobsSize += job.getPcb().getJobSize();
				RAM.add(job);
			} else break;
			
	}

}


public static void HardDisk_Jobs(Queue<Job> jobs) {

int Jobs_Size = 0;

while (!jobs.isEmpty()) {

Job job = jobs.poll(); //returns the job at the front of container
Jobs_Size= Jobs_Size + job.getPcb().getJobSize();
HardDisk.add(job);  //inserts the element 
number_Of_Process++;}

System.out.println("The total number of jobs processed: " + number_Of_Process);

if (number_Of_Process< 0) {
System.out.println("The average program size of all jobs: " + 0+" KB");} 
else {
System.out.println("The average program size of all jobs: " + User_Programs_Size / number_Of_Process +" KB"); }
	
}//end method


        // space that needed by process false if larger than memorySize
public static boolean Check_Space(int size) {
int memory_Size = Hardware.User_Programs_Size;
int mSize = memory_Size+size;

if (mSize> user_Program_Space) {
return false;} 
else {
Hardware.User_Programs_Size =User_Programs_Size + size;
return true;}
}

}