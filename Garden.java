/*-------------------------------------------
 * Assignment 4 class: Garden
 * Written by: Ian Lopez 27296126
 * For Comp 248 Section FF - Fall 2018
 * ASSIGNMENT 4
 * 
 * The garden class is the user's nxn board. This class is called to construct
 * ,initialize and modify the player object garden.
 * The garden accepts chars and implements them in the array of characters that make
 * up the nxn garden.
 * 
*/
package A4;

public class Garden {
	private char [][] garden;
	//a.) Garden object has 1 attribute: 'garden' a 2d array representing the garden
	
	
	//b.) default garden (4x4 when user presses 0
	public  Garden(){
		this.garden = new char[4][4];
		initializeGarden();
	}
	
	//c when user wants to build his/her own board
	
	public Garden(int size){
		this.garden = new char[size][size];
		initializeGarden();
	}
	
	//d 
	private void initializeGarden(){
		for (int index = 0; index < garden.length; index++ ){
			for(int space = 0; space < garden.length; space++){
				this.garden[index][space] = '-';
			}
		}
	}
	//e
	public char getInLocation(int r, int c){
		return this.garden[r][c];
	}
	//f
	public void plantFlower(int r, int c){
		this.garden[r][c] = 'f';
	}
	//g
	public void plantTree(int r,int c){
		this.garden[r][c] = 't';
		this.garden[r+1][c] = 't';
		this.garden[r][c+1] = 't';
		this.garden[r+1][c+1] = 't';
	}
	//h
	public void removeFlower(int r,int c){
		this.garden[r][c] = '-';
	}
	
	//i
	
		public int countPossibleTrees(){
		int possible = 0; //counts how many spaces can plant a tree
		for (int r = 0; r < garden.length-1; r++ ){
			for(int c = 0; c < garden.length-1; c++){
				if(this.garden[r][c] == '-'&&
						this.garden[r+1][c] == '-' &&
						this.garden[r][c+1] == '-'&&
						this.garden[r+1][c+1] == '-'){	
					possible++;
				}
			}
		}
		return possible;
	}
	//j	
		public int countPossibleFlowers(){
			int possible = 0; //how many spaces can plant a flower
			for (int r = 0; r < garden.length; r++ ){
				for(int c = 0; c < garden.length; c++){
					if(this.garden[r][c] == '-'){	
						possible++;
					}
				}
			}
			return possible;
		}
	//k	
		public boolean gardenFull(){
			//checks if garden is full W/ the length of the nxn board squared 
			//and squared of the current board.
			double sqrBool = Math.pow(this.garden.length,2);
			int counter=0;
			for(int i =0; i < this.garden.length; i++){
				for (int j = 0; j< this.garden.length; j++){
					if(this.garden[i][j] != '-'){
						counter++;
					}
				}
			}
			return (counter == sqrBool);
		}
		
	//acessor

		public char[][] getGarden(){
		return this.garden;
		}
	
	
	//L 
		public void printGarden(){
			System.out.print(" | ");
			for (int i = 0; i < garden.length; i++){
				System.out.print(i + " ");
			}
			System.out.println();
		for(int i=0;i<garden.length;i++){
			for(int j=0;j<garden.length;j++){
				 //if col == 0
				if (j ==0){
					System.out.print(i+ "| ");
				}
				System.out.print(garden[i][j]+" ");}
			System.out.println();
		}
	}
	
	
	
}
