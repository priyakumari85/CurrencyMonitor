package CurrencyMonitor;

import java.io.IOException;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

public class CompareRates extends TimerTask{
	
	public void run() {
		System.out.println("\nStarting Comparison");
		Connection conn = new Connection("http://rates.fxcm.com/RatesXML");
		try {
			 conn.OpenConnection();
		} catch (IOException e) {
			System.out.println("Error connecting to URL");
			e.printStackTrace();
		}
	  
	
		ReadXML readXml = new ReadXML();
		
		try {
			readXml.CreateCurrPairList();
			CompareAndNotify();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {	
			e.printStackTrace();
		}
		System.out.println("\nFinished Comparison");
	  }
	
	  private void CompareAndNotify(){
		  ArrayList<Currency> currList = ReadXML.xmlDoc.currPairList;
		  if (User.userInList.size()>0) {
			for (int j = 0; j < User.userInList.size(); j++) {
				for (int i = 0; i < currList.size(); i++) {
					if (currList.get(i).Symbol
							.equals(User.userInList.get(j)._symbol)
							&& currList.get(i).Bid >= User.userInList.get(j)._targetValue) {
						System.out.println("Target Met for "
								+ User.userInList.get(j)._symbol + ":"
								+ User.userInList.get(j)._targetValue);
					}
				}
			}
		}
		else{
			  System.out.println("\nNo target found");
		  }
	  }

}
