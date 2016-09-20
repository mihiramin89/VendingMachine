/**
 * Created by mihiramin on 9/10/16.
 */
public class VendingMachine {

    private Double change;
	private Integer colaInventory;
	private Integer candyInventory;
	private Integer chipInventory;
	private ArrayList<Double> VenderChange;// vendor's collection stores: { total, nickel count, dime count, quarter count }
	private Double enteredAmount;// users collection stores: { total, nickel count, dime count, quarter count }
	
	//constructor
	public VendingMachine()
	{
		change = 0.0;
		colaInventory = 10;
		candyInventory = 10;
		chipInventory = 10;
		VenderChange = new ArrayList<Double>();
		//default vendor collection to $5.50.
		VenderChange.add(5.50);
		VenderChange.add(10.0);//nickels
		VenderChange.add(10.0);//dimes
		VenderChange.add(10.0);//quarters
		
		enteredAmount = 0.0;
	}

	// This function accepts user inputted coins.
	// only accepts nickels, dimes & quarters. All
	// other values are ignored and immediately added
	// to the coin return.
	public Double EnterCoin(Double value)
	{
		
		//only accept nickels, dimes, quarters.
		if(value == .05 || value == 0.10 || value == 0.25)
		{
			if(value == 0.05)
			{
				VenderChange.set(1, (VenderChange.get(1)+1));
			}else if(value == 0.10)
			{
				VenderChange.set(2, (VenderChange.get(2)+1));
			}else
			{
				VenderChange.set(2, (VenderChange.get(2)+1));
			}
			enteredAmount = enteredAmount + (value);
			change = change + value;//keep change up to date with the entered amount for case where no item is purchased.
		}else if(value == 0.01) //penny is entered. so add it to the coin return.
		{
			change = change + 0.01;
		}
		
		VenderChange.set(0,(VenderChange.get(0) + enteredAmount));
		return enteredAmount;
	}

	//This function returns a true if the machine has enough change available to give the user.
	// if it does not have enough change saved up it will return false.
	public Boolean CanMakeChange(Double amount) 
	{
		boolean havechangeavailable = false;
		//determine number of quarters,dimes,nickels
		int changeneeded = (int) (amount*100);//make it easier to determine the change if dealing with integers.
		int quarternumber = changeneeded/25;
		changeneeded = changeneeded - (quarternumber *25);
		int dimenumber = changeneeded/10;
		changeneeded = changeneeded - (dimenumber * 10);
		int nickelnumber = changeneeded/5;
		changeneeded = changeneeded -(nickelnumber *5);
		if(changeneeded == 0 && VenderChange.get(1) >= quarternumber && VenderChange.get(2) >= dimenumber && VenderChange.get(3) >= nickelnumber)
		{
			havechangeavailable = true;
		}

		return havechangeavailable;
		//set change to that amount.
	}

	// this function returns a string that either says Thank you, sold out, or the price of the item.
	public String SelectProduct(ProductEnum prod)
	{
		StringBuilder output = new StringBuilder();
		
		switch (prod)
		{
			case CANDY : //candy 0.65
				if(enteredAmount >= 0.65 && candyInventory > 0)
				{
					if(enteredAmount > 0.65)
					{
						if(CanMakeChange(enteredAmount - 0.65))
						{
							candyInventory--;
							enteredAmount = enteredAmount - 0.65;
							output.append("THANK YOU");
						}
					}else
					{
						candyInventory--;
						enteredAmount = enteredAmount - 0.65;
						output.append("THANK YOU");
					}
					
				}else if(candyInventory == 0 )
				{
					output.append("SOLD OUT");
				}else
				{
					output.append("Price: 0.65");
				}
				break;
			case CHIPS : // chips 0.50
				if(enteredAmount >= 0.50 && chipInventory > 0)
				{
					if(enteredAmount > 0.50)
					{
						if(CanMakeChange(enteredAmount - 0.50))
						{
							chipInventory--;
							enteredAmount = enteredAmount - 0.50;
							output.append("THANK YOU");
						}
					}else
					{
						chipInventory--;
						enteredAmount = enteredAmount - 0.50;
						output.append("THANK YOU");
					}
					
				}else if(chipInventory == 0 )
				{
					output.append("SOLD OUT");
				}else
				{
					output.append("Price: 0.50");
				}
				
				break;
			case COLA : //cola 1.00
				if(enteredAmount >= 1.00 && colaInventory > 0)
				{
					if(enteredAmount > 1.00)
					{
						if(CanMakeChange(enteredAmount - 1.00))
						{
							colaInventory--;
							enteredAmount = enteredAmount - 1.00;
							output.append("THANK YOU");
						}
					}else
					{
						colaInventory--;
						enteredAmount = enteredAmount - 1.00;
						output.append("THANK YOU");
					}
					
				}else if(colaInventory == 0 )
				{
					output.append("SOLD OUT");
				}else
				{
					output.append("Price: 1.00");
				}
				break;
		}
		change = enteredAmount;
		return output.toString();
	}

	//gives the change back to the user.
	//returns the amount not spent.
	public Double ReturnCoins()
	{
		Double amount = change;
		change = 0.0;
		enteredAmount = 0.0;
		return amount;
	}
}
