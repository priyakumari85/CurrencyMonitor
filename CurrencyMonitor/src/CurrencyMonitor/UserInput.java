package CurrencyMonitor;


public class UserInput {
	String _symbol;
	double _targetValue;
	
	// Template for user input. User can put ArrayList of UserInput
	UserInput(String symbol,double targetValue){
		this._symbol = symbol;
		this._targetValue = targetValue;
	}
	
}
