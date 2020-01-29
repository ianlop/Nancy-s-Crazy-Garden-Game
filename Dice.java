/* Assignment 4 class: Dice
 * Written by: Ian Lopez 27296126
 * For Comp 248 Section FF - Fall 2018
 * ASSIGNMENT 4
 * 
 * This class is the dice class that when call, generates two dices and return the sum of the two
 * AND it sends the specific die that the user wants to see what value die 1 or 2 rolled.
 * A final to string to display as well
*/
package A4;

import java.util.Random;
public class Dice {
	
	Random RandNumber = new Random();
	
	//IOG: a.) 2 dice values
	
	private int dice1, dice2;
	
	//IOG: b.)Default constructor which sets the value of each die to zero
	
	public Dice(){
		this.dice1 = 0; //the two attributes
		this.dice2 = 0;	}
	
	//IOG: c.) Acsessor for each attribute
	
	public int getDice1(){
		return (this.dice1);
	}
	public int getDice2(){
		return (this.dice2);}
	
	
	//IOG: d.) a rollDice method which randomly assigns a number between 1 and 6
	
	public int rollDice(){
		this.dice1 = RandNumber.nextInt(6) + 1;
		this.dice2 = RandNumber.nextInt(6) + 1;
		
		return (this.dice1 + this.dice2);
	}
	

	//IOG e.) toString to return the content as a string
	public String toString() {
		return "Let's see who goes first: this dice rolled " + rollDice() + " (Die 1: " + this.dice1 + " Die 2: " + this.dice2 + ")";
		}
	
}