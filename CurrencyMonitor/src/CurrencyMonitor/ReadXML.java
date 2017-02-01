package CurrencyMonitor;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder; 
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class ReadXML {
	
	//function uses XML parser DocumentBuilder to parse XML and read the input from the link
	// in to an object of class XMLDocument
	
	public static XMLDocument xmlDoc;
	public void CreateCurrPairList() throws IOException, SAXException, ParserConfigurationException{
		try {	
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder(); 
			Document doc = db.parse(Connection.urlConn.getInputStream());
			
			xmlDoc = new XMLDocument();

	        xmlDoc.Rates = doc.getDocumentElement().getNodeName();
	         
	         
	        NodeList nList = doc.getElementsByTagName("Rate");
	         
	         for (int temp = 0; temp < nList.getLength(); temp++) {
	        	Currency curr = new Currency();
	            Node nNode = nList.item(temp);
	                      
	            
	            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
	               Element eElement = (Element) nNode;
	               curr.Symbol = eElement.getAttribute("Symbol").toUpperCase();

	               curr.Bid = Double.parseDouble(eElement.getElementsByTagName("Bid").item(0).getTextContent());
	               
	               curr.Ask = Double.parseDouble(eElement.getElementsByTagName("Ask").item(0).getTextContent());
	               
	               curr.High = Double.parseDouble(eElement.getElementsByTagName("High").item(0).getTextContent());
	               
	               curr.Low = Double.parseDouble(eElement.getElementsByTagName("Low").item(0).getTextContent());
	               
	               curr.Direction = Integer.parseInt(eElement.getElementsByTagName("Direction").item(0).getTextContent());
	               
				  curr.Last = eElement.getElementsByTagName("Last").item(0).getTextContent();
				  xmlDoc.currPairList.add(curr);
	            }
 
	         }
	      } catch (Exception e) {

	    	  System.out.println("Error reading XML from the URL");
	      }
	            
	}
}
