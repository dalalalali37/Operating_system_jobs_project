import java.util.UUID; //  immutable universally unique identifier (UUID). A UUID represents a 128-bit value.

public class PCB {

	//1. Job ID (JID): a unique identification number for the job. 
   private String job_ID; 
   private String State;
	private int JobSize;
   private int IOR_Time;
   private int Waiting_Time;
   private int Job_Execution_Time;
	private int Program_Counter;

	public PCB(int job_Size,int IR_time, int W_time, int job_execution_time, String state) {
		JobSize = job_Size;
      IOR_Time = IR_time;
      Waiting_Time = W_time;
		Job_Execution_Time = job_execution_time;
		State = state;
		//we did not understand this
      job_ID = UUID.randomUUID().toString(); //Returns a String object representing this UUID.

	}

	public void incrementPC() {
		if (Job_Execution_Time >= Program_Counter) { // does 1 time unit === one instruction ?
			Program_Counter++;
		}
	}


	public int getJobSize() {
		return JobSize;
	}

	public void setJobSize(int js) {
		JobSize = js;
	}

	public int getJobExecutionTime() {
		return Job_Execution_Time;
	}

	public void setJobExecutionTime(int jet) {
		Job_Execution_Time = jet;
	}
        
  	public int getIRTTime() {
		return IOR_Time;
	}

	public void setIRTTime(int irt) {
		IOR_Time = irt;
	}
        
        	public int getWTTime() {
		return Waiting_Time;
	}

	public void setWTTime(int wt) {
		Waiting_Time = wt;
	}
   
   public String getID() {
		return job_ID;
	}

	public void setID(String jid) {
		job_ID = jid;
	}

        
        
	public String getState() {
		return State;
	}

	public void setState(String state) {
		State = state;
	}

	public int getProgramCounter() {
		return Program_Counter;
	}

	public void setProgramCounter(int pc) {
		Program_Counter = pc;
	}

}
