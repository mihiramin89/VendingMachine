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
}
