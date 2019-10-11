package connect4;

import java.util.Scanner;

public class Connect4 {
	
	public static int num_rows = 6;
	public static int num_cols = 9;
	
	public static int[][] board = new int[num_rows][num_cols];
	
	public static boolean gameOver = false;

	public static int player = 1;
	
	public static int winningNumber = 5;
	
	public static void main (String args[]) {
		Scanner scanner = new Scanner(System.in);
		
		while (!gameOver) {
			// play game
			displayBoard();
			System.out.print("Player " + player + ", enter number 1-9: ");
			int colIndex = scanner.nextInt();
			
			if (colIndex < 1 || colIndex > 9) {
				System.out.print("Player " + player + ", enter number 1-9: ");
				colIndex = scanner.nextInt();
			}
			
			// colIndex - 1 as player enters number from 1-9
			// not from 0-8
			boolean validMove = updateBoard(colIndex - 1);
			
			while (!validMove) {
				System.out.print("Can't place a piece there! Try another move: ");
				colIndex = scanner.nextInt();
				validMove = updateBoard(colIndex - 1);
			}
			
			if (!gameOver) {
				if (player % 2 == 0) player = 0;
				player += 1;
			}
			
			System.out.println();
			
		}
		
		System.out.println("Congratulations player " + player + ", you won!");
		displayBoard();
	}
	
	public static void displayBoard() {
		for (int row = 0; row < num_rows; row++ ) {
			for (int col = 0; col < num_cols; col++ ) {
				System.out.print(board[row][col]);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	public static boolean updateBoard(int colIndex) {
		int i;
		// check for last free element
		for (i = num_rows - 1; i >= 0; i--) {
			if (board[i][colIndex] == 0) {
				board[i][colIndex] = player;
				checkForWinner(i, colIndex);
				break;
			}
		}
		
		 if (i < 0) return false;
		 return true;
	}
	
	public static void checkForWinner(int row, int col) {
		int count = 0;
		
		// Horizontal check
		for (int i = 0; i < num_cols; i++) {
		    if (board[row][i] == player) count++;
		    else count = 0;

		    if (count >= winningNumber) gameOver = true;
		}
		
		count = 0;
		
		// Vertical check
		for (int i = num_rows - 1; i >= 0; i--) {
		    if (board[i][col] == player) count++;
		    else count = 0;

		    if (count >= winningNumber) gameOver = true;
		}
		
		count = 0;
		
		int rowStart, colStart = 0;
		
		// Diagonal check - top left to bottom right
		// iterating over rows
		for (rowStart = 0; rowStart <= num_rows - winningNumber; rowStart++) {
			for (int i = rowStart, j = colStart; i < num_rows && j < num_cols; i++, j++) {
				if (board[i][j] == player) count++;
			    else count = 0;
	
			    if (count >= winningNumber) gameOver = true;
			}

		}
		
		rowStart = 0;
		
		// Diagonal check - top left to bottom right
		// iterating over columns
		for (colStart = 1; colStart <= num_cols - winningNumber; colStart++) {
			for (int i = rowStart, j = colStart; i < num_rows && j < num_cols; i++, j++) {
				if (board[i][j] == player) count++;
			    else count = 0;
	
			    if (count >= winningNumber) gameOver = true;
			}

		}
		
		// Diagonal check - top right to bottom left
		// iterating over rows
		
		colStart = num_cols - 1;
		
		for (rowStart = 0; rowStart <= num_rows - winningNumber; rowStart++) {
			for (int i = rowStart, j = colStart; i < num_rows && j >= 0; i++, j--) {
				if (board[i][j] == player) count++;
			    else count = 0;
	
			    if (count >= winningNumber) gameOver = true;
			}

		}
		
		rowStart = 0;
		
		// Diagonal check - top right to bottom left
		// iterating over columns
		for (colStart = num_cols - 2; colStart >= winningNumber - 1; colStart--) {
			for (int i = rowStart, j = colStart; i < num_rows && j >= 0; i++, j--) {
				if (board[i][j] == player) count++;
			    else count = 0;
	
			    if (count >= winningNumber) gameOver = true;
			}

		}
	}
	
}
