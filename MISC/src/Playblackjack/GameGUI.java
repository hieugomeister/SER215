package Playblackjack;


import java.io.File;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.applet.*;
import java.io.*;

@SuppressWarnings({ "serial", "unused" })
public class GameGUI extends JPanel
{
	  //Panel is a simplest container class.
	  //It includes components and other panels. 
	  //It extends Container and implements to Accessible.
	  
	  JPanel topPanel = new JPanel();
	  JPanel DLR_CardPanel = new JPanel();
	  JPanel PLR_cardPanel = new JPanel();
	  JTextPane winlosebox = new JTextPane();
	  JButton hitbutton = new JButton();
	  JButton dealbutton = new JButton();
	  JButton staybutton = new JButton();
	  JButton playagainbutton = new JButton();
	  JButton quitbutton = new JButton();
	  JLabel dealerlabel = new JLabel();
	  JLabel playerlabel = new JLabel();

	  Playershand E_Dealer = new Playershand();    //dealer's cards
	  Playershand player = new Playershand();      //player's cards
	  Blackjack game = new Blackjack(E_Dealer,player); 

	  /*************************************************************
		These are the labels to represent the cards
	  *************************************************************/
	  JLabel playercard1;
	  JLabel playercard2;
	  JLabel playercardhit;
	  JLabel dealercard0;
	  JLabel dealercard2;
	  JLabel dealercard1;
	  JLabel dealercardhit;

	  /*************************************************************
		 Default Constructor
	  *************************************************************/
	  public GameGUI () 
	  {
		
		topPanel.setBackground(new Color(240, 240, 240));         //Panel color
		DLR_CardPanel.setBackground(new Color(240, 240, 240));    //dealer's zone color
		PLR_cardPanel.setBackground(new Color(240, 240, 240));    //player's zone color
		
		topPanel.setLayout(new FlowLayout());
		winlosebox.setText(" ");
		winlosebox.setFont(new java.awt.Font("Arial Bold", 1, 20));
		dealbutton.setText("  Deal");
		dealbutton.addActionListener(new dealbutton());
		hitbutton.setText("  Hit");
		hitbutton.addActionListener(new hitbutton());
		hitbutton.setEnabled(false);
		staybutton.setText("  Stay");
		staybutton.addActionListener(new staybutton());   
		staybutton.setEnabled(false);
		playagainbutton.setText("  Play Again");
		playagainbutton.addActionListener(new playagainbutton());
		playagainbutton.setEnabled(false);
		quitbutton.setText("  Quit Game");
		quitbutton.addActionListener(new quitbutton());
		quitbutton.setEnabled(true); 
		
		dealerlabel.setText("  E_Dealer:  ");
		playerlabel.setText("  Player:  ");

		topPanel.add(winlosebox);
		topPanel.add(dealbutton);
		topPanel.add(hitbutton);
		topPanel.add(staybutton);
		topPanel.add(playagainbutton);
		topPanel.add(quitbutton);
		PLR_cardPanel.add(playerlabel);
		DLR_CardPanel.add(dealerlabel);
		
		setLayout(new BorderLayout());
		add(topPanel,BorderLayout.NORTH);
		add(DLR_CardPanel,BorderLayout.CENTER);
		add(PLR_cardPanel,BorderLayout.SOUTH);
		
		ImageIcon imagex = new ImageIcon("C:\\SUMMERCLASS\\sundevil.jpg");
		JLabel ImLabel = new JLabel("", imagex, JLabel.CENTER);		
		topPanel.add(ImLabel, BorderLayout.CENTER );		
	  }//end blackjackGUI 

	  /*************************************************************
		 This method shows the screen
	  *************************************************************/
	  public void display() 
	  {
		JFrame myFrame = new JFrame("SER215 Project - GUI Driven blackjack Game");
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		myFrame.setContentPane(this);
		myFrame.setPreferredSize(new Dimension(550,550));

		//Display the window.
		myFrame.pack();
		myFrame.setVisible(true);
		
	  }//end display

	/*************************************************************
	   DealButton
	   This is the first button to click.
	   It starts the game.
	   @param e Deal button pressed
	*************************************************************/
	class dealbutton implements ActionListener 
	{ 
	  public void actionPerformed(ActionEvent e) 
	  {
		
		
		DLR_CardPanel.add(dealerlabel);
		PLR_cardPanel.add(playerlabel);

	   /**
		  (1) Get dealer and player cards from Playershand
		  (2) Get the image associated with that random card
		  (3) Puts them on the screen.
	   */
		
		dealercard0 = new JLabel(new ImageIcon("back.jpg"));

		game.dealInitialCards();

		//to iterate set and get current dealer cards
		Card DLR_Card = null;
		Iterator<Card> DLR_Scan = (E_Dealer.ThePlayershand).iterator();
		int count = 0;
		while (DLR_Scan.hasNext())
		{
			DLR_Card = DLR_Scan.next();
			if(count==0)
			  dealercard1 = new JLabel(DLR_Card.getimage());
			else 
			  dealercard2 = new JLabel(DLR_Card.getimage());
			  
			count++;
		}

		//to iterate set and get current player cards
		Iterator<Card> pscan = (player.ThePlayershand).iterator();
		count = 0;
		while (pscan.hasNext())
		{
		   Card pcard = pscan.next();
		   if(count==0)
			 playercard1 = new JLabel(pcard.getimage());
		   else
			 playercard2 = new JLabel(pcard.getimage());
	   
		   count++;
		}
	 
		DLR_CardPanel.add(dealercard0);
		DLR_CardPanel.add(dealercard2);
	 
		PLR_cardPanel.add(playercard1);
		PLR_cardPanel.add(playercard2);
		
		dealerlabel.setText("  E_Dealer:  "+ DLR_Card.getvalue());
		playerlabel.setText("  Player:  " + game.PlayershandValue(player));
		
		hitbutton.setEnabled(true);
		staybutton.setEnabled(true);
		dealbutton.setEnabled(false);

		 if(game.blackjack())     
		 {
			new Playsound("C:\\SUMMERCLASS\\cackle6.wav").setVisible(false);			
			hitbutton.setEnabled(false);
			staybutton.setEnabled(false);
			dealbutton.setEnabled(false);
			playagainbutton.setEnabled(true);
			playerlabel.setForeground(Color.red);
		    playerlabel.setText("Player: Ah ha! Got cha!");
			winlosebox.setForeground(Color.blue);
			winlosebox.setText(" - blackjack - Yeah!");				
						
		 }

		add(DLR_CardPanel,BorderLayout.CENTER);
		add(PLR_cardPanel,BorderLayout.SOUTH);

	  }
	}//end dealbutton

	/*************************************************************
	   HitButton
		every time the player wants another card,
		he/she clicks on this button until Playershand 
		value is over 21.
	   @param e Hit button pressed
	*************************************************************/
	class hitbutton implements ActionListener 
	{ 
	  public void actionPerformed(ActionEvent e) 
	  {
		
		Card hitcard = game.hit(player);
		
		playercardhit = new JLabel(hitcard.getimage());
		PLR_cardPanel.add(playercardhit);
		PLR_cardPanel.repaint();
	 
		if(game.bust(player))     
		{
		  new Playsound("C:\\SUMMERCLASS\\donkey2.wav").setVisible(false);		  
		  playerlabel.setForeground(Color.red);
		  playerlabel.setText("Player: Damn It!");
		  winlosebox.setForeground(Color.red);
		  winlosebox.setText("Bust");		  
		}

		playerlabel.setText("  Player:   " + game.PlayershandValue(player));
		if(game.bust(player))     
		{	
			playerlabel.setForeground(Color.red);
		    playerlabel.setText("Player: Damn It!");
			hitbutton.setEnabled(false);
			dealbutton.setEnabled(false);
			staybutton.setEnabled(false);
			playagainbutton.setEnabled(true);
		}

	  }
	}//end hitbutton

	/*************************************************************
	   StayButton
		dealer:
 		       must hit on 16 or lower. 
		       determines the winner, 
		player:
 		       wins if under 21 and above dealer. 
		Tie goes to dealer.
	   @param e Stay button pressed
	*************************************************************/
	class staybutton implements ActionListener 
	{ 
	  public void actionPerformed(ActionEvent e) 
	  {				
		DLR_CardPanel.remove(dealercard0);
		DLR_CardPanel.add(dealercard1);

		E_Dealer = game.dealerPlays();
		DLR_CardPanel.removeAll();
		DLR_CardPanel.add(dealerlabel);
		dealerlabel.setText(" " + dealerlabel.getText());  
		
		//iterate through cards and re-display
		Card dhitcard = null;
		Iterator<Card> scan = (E_Dealer.ThePlayershand).iterator(); //Data structure - Iterator 
		while (scan.hasNext())
		{
			dhitcard = scan.next();
			dealercardhit = new JLabel(dhitcard.getimage());
			DLR_CardPanel.add(dealercardhit);
		}
		
		dealerlabel.setText("E_Dealer: " + game.PlayershandValue(E_Dealer));
		playerlabel.setText("Player: " + game.PlayershandValue(player));
		winlosebox.setForeground(Color.blue);
		winlosebox.setText(game.winner());
		
		if(game.winner() == "Push")
		{
			new Playsound("C:\\SUMMERCLASS\\horseneigh1.wav").setVisible(false);
			playerlabel.setText("Player: So unfair...");			
		}
		else
		if(game.winner() == "Win")
		{
			new Playsound("C:\\SUMMERCLASS\\witch.wav").setVisible(false);			
			playerlabel.setForeground(Color.blue);
			playerlabel.setText("Player: Haha!");			
		}
		else
		if(game.winner() == "Lose")
		{
			new Playsound("C:\\SUMMERCLASS\\SingleCow.wav").setVisible(false);
			//ps3.setVisible(false);
			playerlabel.setForeground(Color.blue);
			playerlabel.setText("Player: Wh..What? Nooo!");
			//new Playsound("C:\\SUMMERCLASS\\SingleCow.wav").setVisible(false);			
		}
		
		hitbutton.setEnabled(false);
		staybutton.setEnabled(false);
		
		playagainbutton.setEnabled(true);

	  }
	}//end staybutton

	/*************************************************************
	   PlayAgainButton
		resets screen
	   @param e Play Again button pressed
	*************************************************************/
	class playagainbutton implements ActionListener 
	{ 
	  public void actionPerformed(ActionEvent e) 
	  {
		//Default settings for playagain
		dealerlabel.setText("E_Dealer: ");
		playerlabel.setText("Player: ");
		winlosebox.setText(""); 
		
		//Starting over
		E_Dealer = new Playershand();
		player = new Playershand();
		game = new Blackjack(E_Dealer, player);
		DLR_CardPanel.removeAll();
		PLR_cardPanel.removeAll();
		winlosebox.setForeground(Color.black);
		hitbutton.setEnabled(false);
		staybutton.setEnabled(false);
		playagainbutton.setEnabled(false);
		dealbutton.setEnabled(true);

	  }
	}//end playagainbutton
	
	class quitbutton implements ActionListener 
	{ 
	  public void actionPerformed(ActionEvent e) 
	  {

		System.exit(0); //Just quit to OS

	  }
	}//end quitbutton
	
}//end blackjackGUI
