package Playblackjack;

//This is nothing more than an executer
//It starts the GameGUI which subsequently
//gives control to the entire game

public class Playblackjack 
{

	public static void main(String[] args)
	{
	   GameGUI mainframe = new GameGUI();
		 //frame is an instance of GameGUI class
		 //Default constructor used 
	   
		 mainframe.display();
		 //call the display method to display
		 //the only GUI with control buttons
		 //and other graphical components.
		 
		 //This is all the necessary steps
		 //Subsequent processes are handled by other classes.
	}
}