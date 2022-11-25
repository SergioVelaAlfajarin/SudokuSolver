package sva.ss;

import sva.ss.views.MainView;

import java.util.Arrays;

public class Main {
	private static final int GRID_SIZE = 9;

	public static void main(String[] args) {
		//oldMain();

		MainView mv = new MainView();
		mv.show();
	}

	private static void oldMain() {
		final int[][] BOARD = {
				{0,0,0   ,0,2,0  ,4,0,0},
				{0,8,4   ,0,0,0  ,6,0,3},
				{1,7,0   ,0,0,3  ,0,0,9},

				{0,0,0   ,0,6,0  ,7,8,0},
				{0,4,9   ,3,7,2  ,5,1,0},
				{7,5,6   ,4,0,8  ,3,9,0},

				{0,0,1   ,0,0,0  ,0,0,7},
				{0,0,0   ,1,0,4  ,9,0,0},
				{4,6,0   ,2,9,0  ,0,0,0},
		};

		var isSolved = solveBoard(BOARD);

		if(isSolved){
			System.out.println("Solved.");
			printBoard(BOARD);
		}else{
			System.out.println("Impossible to Solve");
		}
	}

	private static void printBoard(int[][] board) {
		for (int i = 0; i < GRID_SIZE; i++) {
			if(i % 3 == 0 && i != 0){
				System.out.println("---------------------");
			}
			for (int j = 0; j < GRID_SIZE; j++) {
				if(j % 3 == 0 && j != 0){
					System.out.print("| ");
				}
				System.out.print(board[i][j] + " ");
			}
			System.out.println();
		}
	}

	private static boolean solveBoard(int [][] board){
		for(int row = 0; row < GRID_SIZE; row++){
			for (int column = 0; column < GRID_SIZE; column++) {
				if(board[row][column] == 0){
					for (int numberToTry = 1; numberToTry <= GRID_SIZE ; numberToTry++) {
						if(isValidPlacement(board,numberToTry,row,column)){
							board[row][column] = numberToTry;
							if(solveBoard(board)){
								return true;
							}else{
								board[row][column] = 0;
							}
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private static boolean isNumberInRow(int [][] board, int number, int row){
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[row][i] == number){
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInColumn(int [][] board, int number, int col){
		for (int i = 0; i < GRID_SIZE; i++) {
			if(board[i][col] == number){
				return true;
			}
		}
		return false;
	}

	private static boolean isNumberInBox(int [][] board, int number, int row, int col){
		int localBoxRow = row - row % 3; //each box is 3 rows and 3 columns
		int localBoxCol = col - col % 3;

		for (int i = localBoxRow; i < localBoxRow + 3; i++) {
			for (int j = localBoxCol; j < localBoxCol + 3; j++) {
				if(board[i][j] == number){
					return true;
				}
			}
		}

		return false;
	}

	private static boolean isValidPlacement(int [][] board, int number, int row, int col){
		return !isNumberInRow(board,number,row) &&
				!isNumberInColumn(board,number,col) &&
				!isNumberInBox(board,number,row,col);
	}
}