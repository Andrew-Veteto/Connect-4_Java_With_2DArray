import java.util.*;

public class Lab6 {

	//I made all of my variables public static so that I could get referance them in my methods without haveing to pass them through
	public static char board [][] = new char[7][8];
	//char[0][0] = bottom left
	public static boolean finished = false;
	public static boolean gameOver = false;
	public static char currentPlayer = 'X';
	public static int column = 0;
	public static int turns = 0;
	public static boolean valid = false;
	public static void main(String[] args) {
		
		SetBoard();

		//Just keeps the game going till players don't want to play anymore
		while(finished == false) {
			
			//Validating Column Input
			column = GetColumn();
			
			//Check column
			while(PlacePiece(column) != true) {
				column= GetColumn();			
			}

			//Displays the players Move
			DisplayBoard();
			
			//Checking if a player has won
			if(HasWon() == true){
				System.out.println(currentPlayer + " Has won!");
				//Checking if players want to play again
				if(PlayAgain() == false){
					break;
				}
			}
			
			//Adding turns - Seeing if out of turns
			turns++;
			if(turns == 42) {
				System.out.print("No one won.");
				break;
			}
			
			//Switches Players
			FlipPlayer();
	
		}	
	}
	
	public static boolean PlayAgain(){
		//Validating if the user wants to play again
		while(valid != true) {	
			Scanner input = new Scanner(System.in);
			System.out.print("Do you want to play again (y/n)? ");
			String again = input.nextLine();
			if(again.equals("n")) {
				valid = true;
				finished = true;
				System.out.print("Have a good day!");
				return false;
			}
			valid = true;
			NewGame();
			return true;
		}
		return false;
	}

	public static boolean HasWon(){

		//horizontal
		//The reason some of the for loops dont go all the way is to prevent going out of bounds
		for(int row = 0; row <=6; row++){
			for(int col = 0; col <=4; col++){
				if(board[row][col] == currentPlayer && 
				board[row][col+1] == currentPlayer &&
				board[row][col+2] == currentPlayer &&
				board[row][col+3] == currentPlayer )
				{
				return true;
				}
			}
		}

		//vertical
		for(int col = 0; col <=6; col++){
			for(int row = 0; row <=3; row++){
				if(board[row][col] == currentPlayer && 
				board[row+1][col] == currentPlayer &&
				board[row+2][col] == currentPlayer &&
				board[row+3][col] == currentPlayer )
				{
				return true;
				}
			}
		}

		//up slop
		for(int row = 0; row <=4; row++){
			for(int col = 0; col <=4; col++){
				if(board[row][col] == currentPlayer && 
				board[row+1][col+1] == currentPlayer &&
				board[row+2][col+2] == currentPlayer &&
				board[row+3][col+3] == currentPlayer )
				{
				return true;
				}
			}
		}

		//down slop
		for(int row = 0; row <=4; row++){
			for(int col = 7; col >=4; col--){
				if(board[row][col] == currentPlayer && 
				board[row+1][col-1] == currentPlayer &&
				board[row+2][col-2] == currentPlayer &&
				board[row+3][col-3] == currentPlayer )
				{
				return true;
				}
			}
		}

       return false;
	   }
	       
	public static void NewGame() {
		finished = false;
		valid = false;
		SetBoard();
		if(currentPlayer == 'X'){
			FlipPlayer();
		}
	}

	public static void FlipPlayer(){
		if(currentPlayer == 'X'){
			currentPlayer = 'O';
		}else
			currentPlayer = 'X';
	}
	
	public static boolean PlacePiece(int col){
		
		col--; //I do this because row 1 is technically row 0

		for(int row = 0; row <= 6; row++){

			//To check if a row is full all we are doing is going through each row and checking the top spot
			if (board[5][col] == 'X' || board[5][col] == 'O') {
				System.out.println("That column is full.");
				return false;
			}
			
			//If the conditional above isn't met we will look for the next spot in that column that is open
			if (board[row][col] == '.'){
				board[row][col] = currentPlayer;
				return true;
			}
			
		}
		
		return true;
	}

	public static int GetColumn(){

		boolean colValid = false;
		Scanner input = new Scanner(System.in);
		int col = 0;

		//This just goes untill a valid column number is entered with out using an expect
		while(colValid != true) {
				
			System.out.print("Please Enter in a column: ");
			col = input.nextInt();
			
			if(col > 7 || col < 1) {
				System.out.println("Please enter a valid column!");
			}else {
				colValid = true;
			}
			
		}


		return col;
	}

	public static void SetBoard(){
		//I set the board so that each spot has something in its place to check for. I tried ' ' but for some reason I was getting an error
		for(int row = 0; row <=6; row++){
			for(int col = 0; col <= 6; col++){
				board[col][row] = '.';
			}
		}
	}

	public static void DisplayBoard(){
		
		System.out.println("\t1\t2\t3\t4\t5\t6\t7"); //Shows num of column

			//I start at the top row because if we didn't peices would be at the top
			for (int row = 5; row >= 0; row--)
			{
				for (int col = 0; col < 7; col++)
				{
					System.out.print("\t" + board[row][col]);
				}
				System.out.println();
			}

	}

}