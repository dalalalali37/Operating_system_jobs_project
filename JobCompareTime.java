import java.util.Comparator;
// sort the process in term of expected time
public class JobCompareTime implements Comparator<Job>{ // this class use to sort the process in term of expected time
  
	public int compare(Job j1, Job j2) {
		 if(j1.getPcb().getJobExecutionTime()<j2.getPcb().getJobExecutionTime())
			 return -1;
		 else if(j1.getPcb().getJobExecutionTime()>j2.getPcb().getJobExecutionTime())
			 return 1;
		 return 0;
  }
}
