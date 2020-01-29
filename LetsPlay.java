/*-------------------------------------------
 * Assignment 4 Driver class: LetsPlay
 * Written by: Ian Lopez 27296126
 * For Comp 248 Section FF - Fall 2018
 * ASSIGNMENT 4
 * 
 * In this portion of the assignment (Driver class) this is where the main method is stored
 * that:
 * 	- Explains the rules of the game
 * 	- Asks users for:
 * 		1.) Size of board (nxn garden)
 * 		2.) number of players
 * 		3.) name of players
 * 	- There are a few do whiles loops to accept the correct inputs required for 
 * the game to work properly here and throughout the program.
 * 
 * - Once the user has inputed the initial variable values
 * the game begins.
 * The game is simple. A player is given a nxn board
 * and needs to "fill" up their spaces in their board through turns. A player is determined to go first if he/she rolls
 * the highest value of dice amongst his/her friends.
 * When that is determined that player rolls again and depending on what die they get they either "plant"
 * a flower or a tree in their nxn garden (board). Some times a
 * certain dice value creates exceptions to keep the game interesting.
 * A player wins the moment their garden is full and the game (program) finishes.
 *  The game is under the biggest do while loop at the bottom that calls to other classes in this package.
 *  I will elaborate in the other classes what they do individually.
 *  
 *  There are a few objects created here that send arguments to methods with specific signatures
 * 
*/
package A4;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Random;


public class LetsPlay {
	static Scanner keyboard = new Scanner(System.in);
	
	public static void main(String args[]){
		final int standard = 4; //default board size
		int nGardenSize, GardenPlayers; //nGardenSize for garden board size , GardenPlayers for object array
		boolean hasSpace;//player name cannot contain spaces
		String name;//the player's name
		
		System.out.println("\t \t -------****-------****-------****-------****-----****----- \t \t");
		System.out.println("\t \t \t Welcome to Crazy Nancy's Garden Game! \t \t \t");
		System.out.println("\t \t -------****-------****-------****-------****-----****----- \t \t");
		System.out.println();
		System.out.println("To win this game you need some luck with the dice and a bit of strategy." + 
					"\nYour garden is an NxN lot. You can plant flowers or trees. A flower takes one spot. A" + "\n"
							+ "tree takes 4 spots (2x2)." + "\n"
							+ "You roll the dice and based on the outcome you get to plant a pre-set number of trees" 
							+ "\nand flowers.");
		System.out.println();
		System.out.println("Rolls and their outcome:" + "\n---------------------");
		System.out.println(" 3: plan a tree (2x2) and a flower (1x1)" + "\n 6: plant 2 flowers (2 times 1x1)"
				+ "\n 12: plant 2 trees (2 times 2x2)" + "\n 5 or 10: the rabbit will eat something that you have planted - might be a flower or");
		System.out.println("part of a tree(1x1)");
		System.out.println(" Any other EVEN rolls: plant a tree (2x2)");
		System.out.println(" Any other ODD rolls: plant a flower (1x1)");
		System.out.println();
		System.out.println("Minimum number of players: 2.");
		System.out.println("Minimum garden size: 3x3.");
		System.out.println("You can only plant in empty locations. To plant a tree you give the top left");
		System.out.println("coordinates of the 2x2 space" + "\nand I will check to make sure all 4 locations are free.");
		System.out.println("Okay .. Let's start the game! May the best gardener win!!!");
		System.out.println("\n");
		System.out.println("The default garden size is a 4x4 square. You can use this default board size or");
		System.out.println("change the size.");
		System.out.println("----------------------------------------------------------");
		System.out.println();
		System.out.print("Enter 0 to use default garden size or" + 
				" -1 to enter your own size: ");
		do{
			nGardenSize = keyboard.nextInt();					//user garden size input
			if (nGardenSize !=-1 && nGardenSize !=0){
				System.out.println("Sorry but " + nGardenSize + " is not a legal"
						+ " choice. Enter your choice: ");
			}
		}while(nGardenSize != 0 && nGardenSize !=-1);
		
		if (nGardenSize == 0){
			nGardenSize = standard;
		}
		else {
			System.out.println("What size board would you like? (minimum size 3): ");
			do{
				nGardenSize = keyboard.nextInt();
				if (nGardenSize < 3){
					System.out.println("BOARD MUST BE A SIZE MIN OF 3! " + "Try again: ");
				}
			}while(nGardenSize < 3);
		}
		//the board size is either 0 (AKA 4) or what the user has inputted that is not < 3 nGardenSize = userinput
		do{
			System.out.println("How many gardeners will there be (players[minimum 2, max 10])? ");
					GardenPlayers = keyboard.nextInt();
			if (GardenPlayers < 2 || GardenPlayers > 10){
				System.out.println("**Sorry but " + GardenPlayers + " is not a legal number of players");
			}
		}while(GardenPlayers < 2 || GardenPlayers > 10);
		
		//Array of objects for players
		Player[] Players = new Player[GardenPlayers];
		
		//asks for the names of the players with no spaces
		for(int i = 0; i < Players.length; i++){
			System.out.println("Name of player (No spaces)" + (i+1) +": ");
			do{
				hasSpace = false;
				name = keyboard.nextLine();
				for(int j = 0; j < name.length(); j++){
					if(Character.isWhitespace(name.charAt(j))){ //checks for spaces
						hasSpace = true;
						System.out.println("Try again (No spaces):");
		                break;
					}
				}
			}while( name.isEmpty() || hasSpace == true);
			//player array of objects needs to take parameter
			//player name string, garden NxN size
			Players[i] = new Player(name, nGardenSize); //individually create the player object
			System.out.println();
		}
		//Portion of game that determines who goes first by rolling dice
		System.out.println("Let's see who goes first...");
		
		Dice dices = new Dice();
		
		int[] WhoGoesWhen = new int[GardenPlayers];
		boolean sameRoll;//if two or more players roll the same number this loops again
		do{
		sameRoll = false;
		for(int i=0; i < WhoGoesWhen.length; i++){
			WhoGoesWhen[i] = dices.rollDice();
			System.out.println(Players[i].getName() + " rolled a " + WhoGoesWhen[i] );
		}
		for (int i = 0; i < WhoGoesWhen.length; i++){
			for(int j=i+1; j < WhoGoesWhen.length; j++){
				if(WhoGoesWhen[i]==WhoGoesWhen[j]){
					sameRoll = true;
					System.out.println("We will start over as " + WhoGoesWhen[j]
							+ " was rolled by " + Players[i].getName()
							+ " as well\n");
					break;
				}	
				else continue;
			}
			if (sameRoll == true) break;
		}
		}while(sameRoll==true);
		
		
		//After there have been no duplicate rolls, the person with the most
		//dice value goes first and so on and so forth
		for(int i=0; i<WhoGoesWhen.length; i++) {
			for(int j=0; j<WhoGoesWhen.length - i - 1; j++) {
				if(WhoGoesWhen[j] < WhoGoesWhen[j + 1]) { // swap if a[j] < a[j + 1]
					int tmp = WhoGoesWhen[j];
					Player tmp2 = Players[j];
					WhoGoesWhen[j]= WhoGoesWhen[j + 1];
					Players[j] = Players[j+1];
					WhoGoesWhen[j + 1] = tmp;
					Players[j+1] = tmp2;
					}
							}
		}
		System.out.println();
		System.out.println(Arrays.toString(WhoGoesWhen) + "\n"); 
		for(int i = 0; i < Players.length; i++){
			System.out.println(Players[i].getName() + " will go " + (i + 1) + "\n");
		}
		
		//The array has been sorted in order
		//0 < nGardenPlayers
		
		int player= 0;									//where the game begins
		boolean isTheGardenFull;
		int winnerint = 0, rounds=0;
		do{
			isTheGardenFull = false;
			int GardenRow,GardenCol;
			int DiceThePlayerRolled = dices.rollDice();
			System.out.println(Players[player].getName() + " you rolled "
					+ DiceThePlayerRolled + " (Die 1 :" + dices.getDice1() 
					+ " Die 2: " + dices.getDice2() + ")");
			if(DiceThePlayerRolled == 3){
				System.out.println("You must plant a tree (2x2) and a flower (1x1)"); //you will notice the many types of else statements that will prompt the user to enter something depending on what they roll from here on out
				Players[player].showGarden(); //status of their garden
				System.out.println();
				
				if(Players[player].howManyTreesPossible() == 0){
					System.out.println("** Sorry no room left to plant a tree");
				}
				
				else {
					System.out.println("You have " + Players[player].howManyTreesPossible()
						+ " places to do this");
					System.out.println("Enter the coordinates as row column" 
							+ ". Please do not enter the last row and column to add a tree!"); //these statements repeat through here on out as well as I do not want the user to go out of bounds
					System.out.print("Row: ");
					do{
					GardenRow = keyboard.nextInt();
						if (GardenRow == (nGardenSize-1))
							System.out.println("Please do not enter the last row to add a tree! Go again " + 
						"\nRow: ");
					}while (GardenRow == (nGardenSize-1));
					System.out.println();
					System.out.print("Column: ");
					do{
					GardenCol = keyboard.nextInt();
					if (GardenCol == (nGardenSize-1))
						System.out.println("Please do not enter the last column to add a tree! Go again " + 
					"\nCol: ");
				}while (GardenCol == (nGardenSize-1));
					System.out.println();
					Players[player].plantTreeInGarden(GardenRow,GardenCol);
					Players[player].showGarden(); //status of their garden
				}
				
				if(Players[player].howManyFlowersPossible() == 0){
					winnerint = player;
					isTheGardenFull = true;
				}
				else{
				System.out.println("You must plant a flower (1x1)");
				System.out.println("You have " + Players[player].howManyFlowersPossible()
							+ " places to do this");
				System.out.println("Enter the coordinates as row column");
				System.out.print("Row: ");
				GardenRow = keyboard.nextInt();
				System.out.println();
				System.out.print("Column: ");
				GardenCol = keyboard.nextInt();
				System.out.println();
				Players[player].plantFlowerInGarden(GardenRow,GardenCol);
				if(Players[player].isGardenFull() == true){
					winnerint = player;
					isTheGardenFull = true;
				}
				}
				player++;
			}
			else if (DiceThePlayerRolled == 6){
				System.out.println("You must plant 2 flowers (1x1)");
				Players[player].showGarden(); //status of their garden
				System.out.println();
				System.out.println("You must plant the first flower (1x1)");
				System.out.println("You have " + Players[player].howManyFlowersPossible()
							+ " places to do this");
				System.out.println("Enter the coordinates as (row, column)");
				System.out.print("Row: ");
				GardenRow = keyboard.nextInt();
				System.out.println();
				System.out.print("Column: ");
				GardenCol = keyboard.nextInt();
				System.out.println();
				Players[player].plantFlowerInGarden(GardenRow,GardenCol);
				if(Players[player].isGardenFull() == true){
					winnerint = player;
					isTheGardenFull = true;}
				else{
					Players[player].showGarden(); //status of their garden
					System.out.println();
					System.out.println("You must plant the second flower (1x1)");
					System.out.println("You have " + Players[player].howManyFlowersPossible()
								+ " places to do this");
					System.out.println("Enter the coordinates as row column");
					System.out.print("Row: ");
					GardenRow = keyboard.nextInt();
					System.out.println();
					System.out.print("Column: ");
					GardenCol = keyboard.nextInt();
					System.out.println();
					Players[player].plantFlowerInGarden(GardenRow,GardenCol);
					if(Players[player].isGardenFull() == true){
						winnerint = player;
						isTheGardenFull = true;}
					}
				player++;
			}
			else if (DiceThePlayerRolled == 12){
				System.out.println("You must plant two trees 2x(2x2)");
				Players[player].showGarden(); //status of their garden
				System.out.println();
				
				if(Players[player].howManyTreesPossible() == 0){
					System.out.println("** Sorry no room left to plant a tree");
				}
				
				else {
					System.out.println("Plant the first tree");
					System.out.println("You have " + Players[player].howManyTreesPossible()
						+ " places to do this");
					System.out.println("Enter the coordinates as row column" 
							+ ". Please do not enter the last row to add a tree!");
					System.out.print("Row: ");
					do{
					GardenRow = keyboard.nextInt();
						if (GardenRow == (nGardenSize-1))
							System.out.println("Please do not enter the last column to add a tree! Go again " + 
						"\nRow: ");
					}while (GardenRow == (nGardenSize-1));
					System.out.println();
					System.out.print("Column: ");
					do{
					GardenCol = keyboard.nextInt();
					if (GardenCol == (nGardenSize-1))
						System.out.println("Please do not enter the last column to add a tree! Go again " + 
					"\nCol: ");
					}while (GardenCol == (nGardenSize-1));
					Players[player].plantTreeInGarden(GardenRow,GardenCol);
					if(Players[player].isGardenFull() == true){
						winnerint = player;
						isTheGardenFull = true;
					}
					if(Players[player].howManyTreesPossible() == 0 && isTheGardenFull == false){
						System.out.println("** Sorry no room left to plant the second tree");
					}
					else {
						Players[player].showGarden();
						System.out.println();
						System.out.println("Plant the second tree");
						System.out.println("You have " + Players[player].howManyTreesPossible()
								+ " places to do this");
							System.out.println("Enter the coordinates as row column" 
									+ ". Please do not enter the last row and column to add a tree!");
							System.out.print("Row: ");
							do{
							GardenRow = keyboard.nextInt();
								if (GardenRow == (nGardenSize-1))
									System.out.println("Please do not enter the last row to add a tree! Go again " + 
								"\nRow: ");
							}while (GardenRow == (nGardenSize-1));
							System.out.println();
							System.out.print("Column: ");
							do{
							GardenCol = keyboard.nextInt();
							if (GardenCol == (nGardenSize-1))
								System.out.println("Please do not enter the last column to add a tree! Go again " + 
							"\nCol: ");
							}while (GardenCol == (nGardenSize-1));
						Players[player].plantTreeInGarden(GardenRow,GardenCol);
						if(Players[player].isGardenFull() == true){
							winnerint = player;
							isTheGardenFull = true;
						}
					}
				}

				player++;
			}
			else if(DiceThePlayerRolled == 5 || DiceThePlayerRolled == 10){
				Random RandNum = new Random();
				int rowRabbitRan = RandNum.nextInt(nGardenSize), colRabbitRan = RandNum.nextInt(nGardenSize);
				System.out.println("The rabbit ate whatever was planted in location "  
						+ "(" +rowRabbitRan + ", " + colRabbitRan +")." );
				Players[player].showGarden(); //status of their garden
				System.out.println();
				Players[player].eatHere(rowRabbitRan,colRabbitRan);
				player++;
				}
			else if(DiceThePlayerRolled == 7 || DiceThePlayerRolled == 9 ||  
					DiceThePlayerRolled == 11){
				System.out.println("You must plant a flower (1x1)");
				Players[player].showGarden(); //status of their garden
				System.out.println();
				System.out.println("You have " + Players[player].howManyFlowersPossible()
							+ " places to do this");
				System.out.println("Enter the coordinates as row column");
				System.out.print("Row: ");
				GardenRow = keyboard.nextInt();
				System.out.println();
				System.out.print("Column: ");
				GardenCol = keyboard.nextInt();
				System.out.println();
				Players[player].plantFlowerInGarden(GardenRow,GardenCol);
				if(Players[player].isGardenFull() == true){
					winnerint = player;
					isTheGardenFull = true;
				}
				player++;
				}
			else{ //rest of even numbers
					System.out.println("You must plant a tree (2x2)");
					Players[player].showGarden(); //status of their garden
					System.out.println();
				if(Players[player].howManyTreesPossible() == 0){
					System.out.println("** Sorry no room left to plant a tree - You miss a turn");
					}
				else {
					System.out.println("You have " + Players[player].howManyTreesPossible()
						+ " places to do this");
					System.out.println("Enter the coordinates as row column" 
							+ ". Please do not enter the last row and column to add a tree!");
					System.out.print("Row: ");
					do{
					GardenRow = keyboard.nextInt();
						if (GardenRow == (nGardenSize-1))
							System.out.println("Please do not enter the last row to add a tree! Go again " + 
						"\nRow: ");
					}while (GardenRow == (nGardenSize-1));
					System.out.println();
					System.out.print("Column: ");
					do{
					GardenCol = keyboard.nextInt();
					if (GardenCol == (nGardenSize-1))
						System.out.println("Please do not enter the last column to add a tree! Go again " + 
					"\nCol: ");
					}while (GardenCol == (nGardenSize-1));
					System.out.println();
					Players[player].plantTreeInGarden(GardenRow,GardenCol);
					if(Players[player].isGardenFull() == true){
						winnerint = player;
						isTheGardenFull = true;
					}
					}
				player++;
					}
			++rounds;
			if(isTheGardenFull == true){
				System.out.println("FINAL RESULTS");
					System.out.println("--------------------");
					System.out.println("Here are the gardens after " + rounds +" rounds.");
				for(int i = 0; i < Players.length; i++){
					System.out.println("Player: " + Players[i].getName() + "'s garden: ");
					System.out.println();
					Players[i].showGarden();
					System.out.println();
				}
				System.out.println("And the winner is ....." + Players[winnerint].getName() +"!!!!!");
				System.out.println("What a beautiful garden you have." 
						+ "\n\nHope you had fun!!!!");
			}
			
			if (player >= (Players.length)){
				player = 0;
			}
		}while(isTheGardenFull == false);
		
		
		
	}	
}		
	

