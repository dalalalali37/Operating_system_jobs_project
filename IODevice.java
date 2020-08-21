public class IODevice {
	
private static String IOD_State;

 
 	public static void set_IO_State(String iO_State) {
		IOD_State = iO_State;
	                               }
                                  
  public static String get_IO_State() {
		return IOD_State;
	                               }

//constructure
	public IODevice(String state) {
		IODevice.IOD_State = state;
                                	}
   
	//1.5. A busy I/O device may generate an interrupt to signal its job completion and its availability
//with a probability 20%. In this case, the process currently occupying it returns to the Ready
//Queue, and the process at the I/O Queue head is assigned the device. 
//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx
	
   
   public static boolean IO_Termination(int D_Terimante) {
		boolean device_is_Not_Terminated = true;
		if(80 <= D_Terimante) {
			set_IO_State("Device Is Terminated");
			device_is_Not_Terminated = false;
		}
		else
			set_IO_State("Device Is Not Terminated");
		return true;
	}
	//xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx   we deleted this methos
   /////////////// is it deleted???? if not how did you change it in class job ??
	/* public static void IO_ISR(Job job) {
		 int waitingTime = 0;
		boolean DState = false;
		job.setCPUBound(false);
		while(!DState) {
			DState = IO_Termination((int) (Math.random() * 100 + 1));
			waitingTime++;
		}
	} */ 
}