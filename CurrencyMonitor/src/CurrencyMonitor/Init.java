package CurrencyMonitor;

import java.util.Calendar;
import java.util.Timer;



public class Init {

	//This class just performs the scheduling job for two threads
	// 1. Comparing rates 2. Accept User input
	
	public static void main(String[] args) throws Exception  {
		
		
		try {
			Calendar date = Calendar.getInstance();

			//this to schedule the job of accepting user Input
			Timer userTimer = new Timer();
			userTimer.schedule(
				      new User(),
				      date.getTime(),
				      30000
				    );
			
			//this to schedule the job of comparing rates
			Calendar rateDate = Calendar.getInstance();
			rateDate.add(Calendar.MILLISECOND, 120000);
			Timer rateTimer = new Timer();
			rateTimer.schedule(
				      new CompareRates(),
				      rateDate.getTime(),
				      60000
				    );
			
		} catch (Exception e) {
			System.out.println("System Erorr !!! One of the thread failed to run ");
		}

		
	}

}
