public class Job {

	private PCB pcb;
	private boolean CPU_b;
	private String interrupt_Type;
	private String termination_Type;
	private int Interrupt_probability;
	private int Termination_probability;
      
        public static int io_count1;
          public static int interrupt_normally_c;
          public static int interrupt_abnormally_c;
          public static int io;
          public static int Abnomrally;
        	public static int waiting_Time = 0;
    
//constucture    
    public Job(int job_Size,int Wait_time, int IR_time,int job_Execution_Time, String State, String Interrupt_Type, int Interrupt_prob ,boolean CPU_B) {
		  CPU_b = CPU_B;
		  interrupt_Type = Interrupt_Type;
		  Interrupt_probability = Interrupt_prob;
		pcb = new PCB(job_Size, Wait_time,IR_time,job_Execution_Time,  State);

	                                                                                      }

	public void checkTerminate(int Termination_probability) {
		  if (Termination_probability <= 10) {
               set_Termination_Type("Noramally");
			           interrupt_normally_c++;
                        return;             }
		
        if (Termination_probability <=15 ) {
			       set_Termination_Type("Abnormally");
			          interrupt_abnormally_c++;
                        return;             }
    
    
       if (Termination_probability <=35 ) {                              //XXXXXXXXXXXXXXXXXXXXXXX  //IO request 20%
			      set_Interrupt_Type("IORequist");
			            io++;
                        return;           }
       
       else
			set_Termination_Type("Other");
	
                                              }

	public int checkInterrupt(int Interrupt_probability) {
       int random = (int) (Math.random() * 100 + 1);
		    
          if (Interrupt_probability <= 20) {
              getPcb().setState("INTERRUPTED");
			
          if (random <= 20) {
				set_Interrupt_Type("IORequist"); // -1 IO request
				  return -1;    }
              
			set_Interrupt_Type("Other"); 
         			return 0; // 0 Other type of Interrupt

		                                       }
		  set_Interrupt_Type("None");
		           return 1;   //1 No Interrupt
	}



	public void ISR(Job job, int Interrupt_Type) {
		job.getPcb().setState("INTERRUPTED");
		if (Interrupt_Type == -1){    // IO request
            job.getPcb().setState("WAITING");
		boolean Device_State = false;
		job.set_CPU_B(false);
		while(!Device_State) {
			Device_State = IODevice.IO_Termination((int) (Math.random() * 100 + 1));
			waiting_Time++;
		}
                
                }
                
		else
			return;
	}

	public void set_Interrupt_Type(String interrupType) {
		interrupt_Type = interrupt_Type;
	}
   
  public void set_Termination_Type(String terminationType) {
		termination_Type = terminationType;
	}
	public PCB getPcb() {
		return pcb;
	}
   	
	public void set_CPU_B(boolean cPUBound) {
		CPU_b = cPUBound;
	}
/*public int get_Termination_Scoure() {
		return Termination_probability;
	}*/

	/*public void set_TerminationScoure(int terminationScoure) {
		Termination_probability = terminationScoure;
	}*/

	/*public boolean isCPUBound() {
		return CPU_size;
	}*/

  /*
	public String get_Interrupt_Type() {
		return interrupt_Type;
	}*/
   
/*
	public int get_Interrupt_Probability() {
		return Interrupt_probability;
	}*/

	 /*public void set_Interrupt_Probability(int interruptprob) {
		Interrupt_probability = interruptprob;
	} */

	/*public String getTermination_Type() {
		return termination_Type;
	}*/


}
