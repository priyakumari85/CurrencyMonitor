package CurrencyMonitor;
import java.net.*;
import java.io.*;



// This class performs the operation to cnnect to URL
// It is class in CompareRates class
public class Connection {

	String URL;
	public static URLConnection urlConn ;
	
	Connection(String url) {
	    this.URL = url;
	  }

	
	public  void OpenConnection() throws IOException{
		URL xmlURL;
		try {
			xmlURL = new URL(this.URL);
			urlConn = xmlURL.openConnection();
			urlConn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
			urlConn.connect();
		} catch (MalformedURLException e) {
			System.out.println("Error Connecting to URL");
		}

	}

}
