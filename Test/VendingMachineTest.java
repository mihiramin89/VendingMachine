import org.junit.Test;

public class VendingMachineTest {

	private VendingMachine mach;
	
	@Before
	public void setup()
	{
		mach = new VendingMachine();
	}

	/************************************************
	*
	* Test Cases for VendingMachine > EnterCoin
	* 
	**************************************************/
	@Test
	public void EnterPennyShouldNotIncreaseEnteredValueAmount()
	{
		assertEquals("0.0", mach.EnterCoin(0.01).toString());
	}

	@Test
	public void EnterNickelShouldIncreaseEnteredValueAmountByFive()
	{
		assertEquals("0.05", mach.EnterCoin(0.05).toString());
	}
	
	@Test
	public void EnterDimeShouldIncreaseEnteredValueAmountByTen()
	{
		assertEquals("0.1", mach.EnterCoin(0.10).toString());
	}
	
	@Test
	public void EnterQuarterShouldIncreaseEnteredValueAmountByTwentyFive()
	{
		assertEquals("0.25", mach.EnterCoin(0.25).toString());
	}

	@Test
	public void EnterNonCoinValueAmountShouldNotIncreaseEnteredAmount()
	{
		assertEquals("0.0", mach.EnterCoin(1.0).toString());
	}
}
