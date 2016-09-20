package Src;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class VendingMachineUI {

	private static JFrame Machine;
	private static JPanel panel;
	private static JTextArea DisplayArea;
	
	private static JPanel CoinPanel;
	//private static JButton PennyButton;
	private static JButton NickelButton;
	private static JButton QuarterButton;
	private static JButton DimeButton;
	private static JButton ReturnCoinsButton;
	
	private static JPanel ProductPanel;
	private static JButton ColaButton;
	private static JButton ChipButton;
	private static JButton CandyButton;
	
	private static JTextArea CoinReturnArea;
	
	
	private static VendingMachine brains;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		brains = new VendingMachine();
		generateUI();
	}

	public static void generateUI()
	{	
		JLabel label = new JLabel();
		panel = new JPanel();
		
		Machine = new JFrame("Vending Machine");
		Machine.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Machine.setSize(600, 350);
		Machine.setLayout(new BorderLayout());
		
		//create display panel 
		DisplayArea = new JTextArea(5,30);
		DisplayArea.setBackground(Color.BLACK);
		DisplayArea.setForeground(Color.WHITE);
		DisplayArea.setFont(new Font("Verdana", Font.BOLD, 17));
		DisplayArea.setText("Insert Coin");
		
		DisplayArea.setEditable(false);
		panel.add(DisplayArea,JPanel.TOP_ALIGNMENT);
		
		//create panel for coins
		CoinPanel = new JPanel();
		label.setText("Enter Coins: ");
		CoinPanel.add(label);
		NickelButton = new JButton("Nickel");
		NickelButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Nickel");
            }
        });
		DimeButton = new JButton("Dime");
		DimeButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Dime");
            }
        });
		QuarterButton = new JButton("Quarter");
		QuarterButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Quarter");
            }
        });
		ReturnCoinsButton = new JButton("Return Coins");
		ReturnCoinsButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Return Coins");
            }
        });
		CoinPanel.add(NickelButton);
		CoinPanel.add(DimeButton);
		CoinPanel.add(QuarterButton);
		CoinPanel.add(ReturnCoinsButton);
		panel.add(CoinPanel);
		
		//create panel for products
		ProductPanel = new JPanel();
		label = new JLabel();
		label.setText("Products: ");
		ColaButton = new JButton("Cola");
		ColaButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Cola");

            }
        });
		ChipButton = new JButton("Chip");
		ChipButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Chip");
            }
        });
		CandyButton = new JButton("Candy");
		CandyButton.addActionListener(new ActionListener() {
            
            public void actionPerformed(ActionEvent e)
            {
                ButtonAction("Candy");
            }
        });
		ProductPanel.add(label);
		ProductPanel.add(ColaButton);
		ProductPanel.add(ChipButton);
		ProductPanel.add(CandyButton);
		panel.add(ProductPanel);
		
		//create coin return panel 
		CoinReturnArea = new JTextArea(5,30);
		CoinReturnArea.setBackground(Color.BLACK);
		CoinReturnArea.setForeground(Color.WHITE);
		CoinReturnArea.setFont(new Font("Verdana", Font.BOLD, 17));
		CoinReturnArea.setText("0.00");
		CoinReturnArea.setEditable(false);
		panel.add(CoinReturnArea);
		Machine.add(panel);
		
		Machine.setVisible(true);//display UI
	}
	
	// do different things for each button action. 
	private static void ButtonAction(String buttonName)
	{
		Double change;
		String output;
		
		ActionListener taskPerformer = new ActionListener() {
	            public void actionPerformed(ActionEvent evt) {
	                //...Perform a task...
	            	DisplayArea.setText("Insert Coins");
	            }
	        };
		switch (buttonName)
		{
			case "Nickel" : // enter nickel.
				brains.EnterCoin(0.05);
				DisplayArea.setText(brains.RemainingBalance().toString());
				break;
			case "Dime" : 
				brains.EnterCoin(0.10);
				DisplayArea.setText(brains.RemainingBalance().toString());
				break;
			case "Quarter" :
				brains.EnterCoin(0.25);
				DisplayArea.setText(brains.RemainingBalance().toString());
				break;
			case "Return Coins" :
				change = brains.ReturnCoins();
				DisplayArea.setText("0.00");
				CoinReturnArea.setText(change.toString());
				break;
			case "Cola" :
				output = brains.SelectProduct(ProductEnum.COLA);
				if( output.equals("THANK YOU"))
				{
					DisplayArea.setText(output);
					change = brains.ReturnCoins();
					CoinReturnArea.setText(change.toString());
				}else
				{
					DisplayArea.setText(output);
			        Timer timer = new Timer(1000 ,taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
				}
				break;
			case "Chip" : 
				output = brains.SelectProduct(ProductEnum.CHIPS);
				if( output.equals("THANK YOU"))
				{
					DisplayArea.setText(output);
					change = brains.ReturnCoins();
					CoinReturnArea.setText(change.toString());
				}else
				{
					DisplayArea.setText(output);
			        Timer timer = new Timer(1000 ,taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
				}
				break;
			case "Candy" : 
				output = brains.SelectProduct(ProductEnum.CANDY);
				if( output.equals("THANK YOU"))
				{
					DisplayArea.setText(output);
					change = brains.ReturnCoins();
					CoinReturnArea.setText(change.toString());
				}else
				{
					DisplayArea.setText(output);
			        Timer timer = new Timer(1000 ,taskPerformer);
			        timer.setRepeats(false);
			        timer.start();
				}
				break;
		}
	}
}
