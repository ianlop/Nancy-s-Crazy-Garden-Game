/*-------------------------------------------
 * Assignment 4 class: player
 * Written by: Ian Lopez 27296126
 * For Comp 248 Section FF - Fall 2018
 * ASSIGNMENT 4
 * 
 * This class stores and associates the relevant player object's
 * name (Player name) and calls to methods in garden class to modify "this"
 * player with coordinates sent.
 * 
 * also fetches status of garden.
 * 
*/
package A4;

public class Player {
	
	//1 Player object w/ two att
	public String name;
	private Garden garden;
	
	//2 Constructor to have parameters of string (name) and int for the size of garden.
	public Player(String name, int nGardenSize) {
		this.name = name;
		this.garden = new Garden(nGardenSize);
	}
	
	//3 acessor method for the name attribute
	
	public String getName(){
		return this.name;
	}
	
//4 following methods associated with garden class
	//i
	public int howManyFlowersPossible(){
		return this.garden.countPossibleFlowers();
	}
	//ii
	public int howManyTreesPossible(){
		return this.garden.countPossibleTrees();
	}
	//iii
	public char whatIsPlanted(int r,int c){
		return this.garden.getInLocation(r,c);
	}
	//iv
	public void plantTreeInGarden(int r, int c){
		this.garden.plantTree(r,c);
	}
	//v
	public void plantFlowerInGarden(int r, int c){
		this.garden.plantFlower(r,c);
	}
	//vi
	public void eatHere(int r,int c){
		this.garden.removeFlower(r,c);
	}
	//vii
	public boolean isGardenFull(){
		return this.garden.gardenFull();
	}
	//viii
	public void showGarden(){
		this.garden.printGarden();
	}
}
