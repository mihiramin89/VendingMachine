import org.junit.Test;

import static org.junit.Assert.*;

import org.junit.Before;

import Code.ProductEnum;
import Code.VendingMachine;

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

	/************************************************
	*
	* Test Cases for VendingMachine > SelectProduct
	* 
	**************************************************/
	@Test
	public void SelectOneColaFromVendingMachine()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		assertEquals("THANK YOU", mach.SelectProduct(ProductEnum.COLA));
	}
	
	@Test
	public void SelectOneChipFromVendingMachine()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		assertEquals("THANK YOU", mach.SelectProduct(ProductEnum.CHIPS));
	}
	@Test
	public void SelectOneCandyFromVendingMachine()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.10);
		mach.EnterCoin(0.05);
		assertEquals("THANK YOU", mach.SelectProduct(ProductEnum.CANDY));
	}
	
	@Test
	public void SelectColaWithoutEnteringMoneyFromVendingMachine()
	{
		assertEquals("Price: 1.00", mach.SelectProduct(ProductEnum.COLA));
	}
	
	@Test
	public void SelectChipWithoutEnteringMoneyFromVendingMachine()
	{
		assertEquals("Price: 0.50", mach.SelectProduct(ProductEnum.CHIPS));
	}
	
	@Test
	public void SelectCandyWithoutEnteringMoneyFromVendingMachine()
	{
		assertEquals("Price: 0.65", mach.SelectProduct(ProductEnum.CANDY));
	}
	
	@Test
	public void SelectColasWhenSoldOutFromVendingMachine()
	{
		for(int i = 0; i < 10; i++)
		{
			mach.EnterCoin(0.25);
			mach.EnterCoin(0.25);
			mach.EnterCoin(0.25);
			mach.EnterCoin(0.25);
			mach.SelectProduct(ProductEnum.COLA);
		}
		assertEquals("SOLD OUT",mach.SelectProduct(ProductEnum.COLA));
	}
	
	@Test
	public void SelectChipsWhenSoldOutFromVendingMachine()
	{
		for(int i = 0; i < 10; i++)
		{
			mach.EnterCoin(0.25);
			mach.EnterCoin(0.25);
			mach.SelectProduct(ProductEnum.CHIPS);
		}
		assertEquals("SOLD OUT",mach.SelectProduct(ProductEnum.CHIPS));
	}
	
	@Test
	public void SelectCandyWhenSoldOutFromVendingMachine()
	{
		for(int i = 0; i < 10; i++)
		{
			mach.EnterCoin(0.25);
			mach.EnterCoin(0.25);
			mach.EnterCoin(0.10);
			mach.EnterCoin(0.05);
			mach.SelectProduct(ProductEnum.CANDY);
		}
		assertEquals("SOLD OUT",mach.SelectProduct(ProductEnum.CANDY));
	}

	/************************************************
	*
	* Test Cases for VendingMachine > CanMakeChange
	* 
	**************************************************/
	@Test
	public void CanMakeChangeForZeroReturnTrue() {
		assertEquals("true", mach.CanMakeChange(0.0).toString());
	}
	@Test
	public void CanMakeChangeForTenCentsReturnsTrue()
	{
		assertEquals("true",mach.CanMakeChange(0.10).toString());
	}
	@Test
	public void CanMakeChangeForNickelReturnsTrue()
	{
		assertEquals("true",mach.CanMakeChange(0.05).toString());
	}
	@Test
	public void CanMakeChangeForQuarterReturnsTrue()
	{
		assertEquals("true",mach.CanMakeChange(0.25).toString());
	}
	@Test
	public void CanMakeChangeForSixDollarsReturnsFalse()
	{
		assertEquals("false",mach.CanMakeChange(6.00).toString());
	}

	/************************************************
	*
	* Test Cases for VendingMachine > ReturnCoins
	* 
	**************************************************/
	@Test
	public void ReturnCoinsWhenOnlyEnteringAPennyReturnsAPenny()
	{
		mach.EnterCoin(0.01);
		assertEquals("0.01",mach.ReturnCoins().toString());
	}
	@Test
	public void ReturnCoinsWhenOnlyEnteringANickelReturnsANickel()
	{
		mach.EnterCoin(0.05);
		assertEquals("0.05",mach.ReturnCoins().toString());
	}
	@Test
	public void ReturnCoinsWhenOnlyEnteringADimeReturnsADime()
	{
		mach.EnterCoin(0.10);
		assertEquals("0.1",mach.ReturnCoins().toString());
	}
	@Test
	public void ReturnCoinsWhenOnlyEnteringAQuarterReturnsAQuarter()
	{
		mach.EnterCoin(0.25);
		assertEquals("0.25",mach.ReturnCoins().toString());
	}
	@Test
	public void ReturnCoinsWhenEnteringADollarAndBuyingAChipReturnsFiftyCents()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.SelectProduct(ProductEnum.CHIPS);
		assertEquals("0.5",mach.ReturnCoins().toString());
	}
	@Test
	public void ReturnCoinsWhenEnteringADollarAndBuyingACandyReturnsThirtyFiveCents()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.SelectProduct(ProductEnum.CANDY);
		assertEquals("0.35",mach.ReturnCoins().toString());
	}
	@Test
	public void ReturnCoinsWhenEnteringADollarAndBuyingAColaReturnsZeroCents()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.SelectProduct(ProductEnum.COLA);
		assertEquals("0.0",mach.ReturnCoins().toString());
	}

	/************************************************
	*
	* Test Cases for VendingMachine > RemainingBalance
	* 
	**************************************************/
	@Test
	public void ReturnAllCoinsEnteredWithoutBuyingAProduct()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		assertEquals("1.00",mach.RemainingBalance().toString());
	}

	@Test
	public void EnterOneDollarBuyChipsRemainingBalanceIsTwentyFive()
	{
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.EnterCoin(0.25);
		mach.SelectProduct(ProductEnum.CHIPS);
		assertEquals("0.5",mach.RemainingBalance().toString());
	}
}
