import java.util.Queue;

public class CPU {
   public static int Time;
	public static void executeProcesses(Queue<Job> IOQueue) {
		int  number_of_Jobs = 0;
		int interrupt_Probability;
		int InterruptType;
		while (!Hardware.RAM.isEmpty()) {
                    
			Job job = Hardware.RAM.poll();
                        
                       while(job.getPcb().getState()!="TERMINATED") {
                        
			job.getPcb().setState("READY");
			number_of_Jobs++;
			while (job.getPcb().getJobExecutionTime() > job.getPcb().getProgramCounter()) {
                        
				job.getPcb().setState("RUNNING");
				job.getPcb().incrementPC();
				Clock.incrementTime();
			    interrupt_Probability = (int) (Math.random() * 100 + 1);
				InterruptType = job.checkInterrupt(interrupt_Probability); // check for interrupt
				if (InterruptType == -1) { // which is IO request
                             
                                 
					job.getPcb().setState("INTERRUPTED");
					job.set_CPU_B(false);
					
					IOQueue.add(job);
					job.ISR(job, -1);
					Hardware.RAM.add(job);
					break;
				}
				if (InterruptType == 0) { //other type of interrupt
                                    Time++;
					job.getPcb().setState("INTERRUPTED");
					job.ISR(job, 0);
               
					
				} else {  //there is no interrupt
                                    Time++;
					job.getPcb().setState("RUNNING");
					job.ISR(job, 1);
					
				}
			}
		 job.checkTerminate((int) (Math.random() * 100 + 1));	
		job.getPcb().setState("TERMINATED");
                        
		}
                      
                }
		if (Hardware.RAM.isEmpty()) {
                    Time++;
			Hardware.RAM.add(Hardware.HardDisk.poll());
		}
	}
}
