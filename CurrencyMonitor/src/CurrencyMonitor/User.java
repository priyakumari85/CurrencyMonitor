package CurrencyMonitor;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TimerTask;


public class User extends TimerTask{
	public static ArrayList<UserInput> userInList = new ArrayList<UserInput>();
	Scanner scanner = new Scanner(System.in); 
	
	
	 volatile boolean keepRunning = true;

	 // Run function is scheduled to run every min to see if user wants to change Currency pair
	    public void run() {
	        
	            	CurrManage();          
	    }

	//Logic to add Currency Pair
	public void AddCurrPair(){

		String cont = "y";
		String currpair="";
		double targetVal=0;
		int foundIndex = -1;
		
		while(cont.equals("y") || cont.equals("Y")) {	
			
			currpair = AcceptSymVal();
			targetVal= AcceptTarVal();
			
			if (userInList.size()>0) {
				foundIndex = FindCurrpairInList(currpair);
				if (foundIndex > -1) {
					System.out.println("\nADD MSG: Item:" + currpair + " already in list. \nGo back to main menu and select option 2 to edit");
				} else {
					UserInput ur = new UserInput(currpair.toUpperCase(),
							targetVal);
					userInList.add(ur);
					System.out.println("\nADD MSG: Item:" + currpair + " added to list");
				}
			}
			else {
				UserInput ur = new UserInput(currpair.toUpperCase(),
						targetVal);
				userInList.add(ur);
				System.out.println("\nADD MSG: Item:" + currpair + " added to list");
			}
			cont= AcceptContVal(1);	
		}
	}
	
	//Logic to remove Currency Pair
	public void RemoveCurrPair(String currName){
		int foundIndex=-1;
		foundIndex=FindCurrpairInList(currName);
		if(foundIndex>-1){
						  userInList.remove(foundIndex);
						  System.out.println("\nDELETE MSG: Item removed");
		}
		else if(foundIndex==-1){
			System.out.println("\nDELETE MSG: No Currency pair : " + currName + "in the user list");
		}
		
	}
	
	//Logic to change target rate ofexisting Currency Pair
	public void EditCurrPair(String currName,double newTarget){

		int foundIndex=-1;
		foundIndex=FindCurrpairInList(currName);
		if(foundIndex>-1){
			userInList.get(foundIndex)._targetValue = newTarget;
			System.out.println("\nEDIT MSG: Item edit complete");
		}
		else if(foundIndex==-1){
			System.out.println("\nEDIT MSG: No Currency pair : " + currName + "in the user list");
		}
	}
	
	//Function to regulate add,delete and remove Curency Pair
	public void CurrManage(){
		String input="";
		input=AcceptYNVal();

		if(input.equals("y") || input.equals("Y")){
			int menuSel=Acceptmenuitem();

			if(menuSel == 1) {
				AddCurrPair();
			}
			else if(menuSel == 2){
				String cont = "y";
				double targetVal=0;
				String currpair="";
				
				while (cont.equals("y") || cont.equals("Y")) {
					currpair = AcceptSymVal();
					targetVal = AcceptTarVal();
					EditCurrPair(currpair,targetVal);
					cont = AcceptContVal(2);
				}
			}
			else if(menuSel == 3){
				String cont = "y";
				String currpair= "";
				while (cont.equals("y") || cont.equals("Y")) {
					currpair= AcceptSymVal();
					RemoveCurrPair(currpair);
					cont= AcceptContVal(3);
				}
			}
			
		}
	}
	
	//Accept Target value
	private double AcceptTarVal(){
		
		double targetVal=0;
		System.out.println("\nEnter New Target Value");
		while (!scanner.hasNextDouble())
		{
		    System.out.println("Invalid input\n Type the double-type number:");
		    scanner.next();
		}
		targetVal = scanner.nextDouble();
		scanner.nextLine();
		return targetVal;
	}
	
	//Accept Currency Pair Symbol
	private String AcceptSymVal(){
		String currpair="";
		try {
			System.out.println("\nEnter Currency Pair Symbol");
			currpair = scanner.nextLine();
		} catch (Exception e) {
			System.out.println("\nNot in proper format. Enter String.\nWait for next prompt !!!!!!"); 
		}
		return currpair;
	}
	
	// ITEM LEVEL -Asks user if they want to continue or not in adding or editing item
	private String AcceptContVal(int type){
		String cont = "y";
		String input="";
		
		if(type==1){
			System.out.println("\n------ADD MENU------- \nDo you want to add another pair (Y/N)");
		}
		if(type==2){
			System.out.println("\n------EDIT MENU-------\nDo you want to edit target value of another pair (Y/N)");
		}
		if(type==3){
			System.out.println("\n------DELETE MENU-------\nDo you want to remove another pair (Y/N)");
		}
		
		
		boolean validMenuIn2=false; 
		do{
			try {
				input = scanner.nextLine();	
				cont = input;
				if(input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N")){
					validMenuIn2=true;
				   }
				else{
				       System.out.println("\nNot in proper format. Enter 'Y' for Yes or 'N' for No");
				     }
			} catch (Exception e) {
				validMenuIn2=true;
				System.out.println("\nNot in proper format. Enter 'Y' for Yes or 'N' for No.\nWait for next prompt !!!!!!");
			}
		}while(!validMenuIn2);
		return cont;
	}

	// LIST LEVEL - Asks user if they want to continue or not in adding or editing list
	private String AcceptYNVal(){
		String input="";
		 boolean validInput=false; 
		   do{
				try {
						System.out.println("\nDo you want to add or edit Currency Pair List (Y\\N)");
						input = scanner.nextLine();
						if(input.equals("y") || input.equals("Y") || input.equals("n") || input.equals("N")){
						       validInput=true;
						   }
						else{
						       System.out.println("\nNot in proper format. Enter 'Y' for Yes or 'N' for No");
						     }
					} catch (Exception e1) {
						validInput=true;
						System.out.println("\nNot in proper format. Enter 'Y' for Yes or 'N' for No.\nWait for next prompt !!!!!!");
					}
		    }while(!validInput);
		   return input;
	}

	//Logic to operate menu items
	private int Acceptmenuitem(){
		int input=0;
		boolean validMenuIn=false; 
		do{
				try {
					System.out.println("----MAIN MENU----- \nYou want to \n1.Add \n2.Edit \n3.Remove. \nType 1 for add, Type 2 for edit and type 3 for remove");
					input = scanner.nextInt();
					scanner.nextLine();
					if(input==1 || input==2 || input==3){
						validMenuIn=true;
					   }
					else{
					       System.out.println("\nPlease enter valid Interger value");
					     }
				} catch (Exception e1) {
					validMenuIn=true;
					System.out.println("\nPlease enter valid Interger value.\nWait for next prompt !!!!!!");
				}
		}while(!validMenuIn);
		return input;
	}

	// Searches Currecny Pair in the list. Used in Add,Edit and Delete functions
	private int FindCurrpairInList(String currName){
		int index = -1;
		boolean found = false;
		if(userInList.size()>0){
			 for(int j=0; j< User.userInList.size(); j++){
					  if(userInList.get(j)._symbol.equals(currName.toUpperCase())){
						  index = j;
						  found = true;
						  break;
					  } 
			  }
			 if(found==false){
					System.out.println("\nItem doesnt exist in list.");
				}
		}
		else {
			System.out.println("\nUser has not created any currency pair value.try adding one !");
		}
		
		return index;
		
	}
}
