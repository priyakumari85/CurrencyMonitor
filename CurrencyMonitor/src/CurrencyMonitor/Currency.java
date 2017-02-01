package CurrencyMonitor;



// This class is the template for each Node in the XML
// This has same fields like in the XML
//<Rate Symbol="EURUSD">
//<Bid>1.14442</Bid>
//<Ask>1.14571</Ask>
//<High>1.14607</High>
//<Low>1.13448</Low>
//<Direction>1</Direction>
//<Last>16:59:09</Last>
//</Rate>

public class Currency {
	String Symbol;
	double Bid;
	double Ask;
	double High;
	double Low;
	int Direction;
	String Last; 

}
