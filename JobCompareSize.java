import java.util.Comparator;
//sort the process in term of size;
public class JobCompareSize implements Comparator<Job>{ // this class use to sort the process in term of size;
	
	 public int compare(Job first_Job, Job second_Job) {
		if(first_Job.getPcb().getJobSize()< second_Job.getPcb().getJobSize())
			return -1;
		else if (first_Job.getPcb().getJobSize()>second_Job.getPcb().getJobSize())
			return 1;
		return 0;
	}
	 
}

