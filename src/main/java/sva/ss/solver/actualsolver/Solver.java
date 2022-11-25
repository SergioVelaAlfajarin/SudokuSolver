package sva.ss.solver.actualsolver;

import sva.ss.Main;

public class Solver {
    public boolean solve(int[][] board) {
        for(int row = 0; row < Main.GRID_SIZE; row++){
            for (int column = 0; column < Main.GRID_SIZE; column++) {
                if(board[row][column] == 0){
                    for (int numberToTry = 1; numberToTry <= Main.GRID_SIZE ; numberToTry++) {
                        if(isValidPlacement(board,numberToTry,row,column)){
                            board[row][column] = numberToTry;
                            if(solve(board)){
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
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            if(board[row][i] == number){
                return true;
            }
        }
        return false;
    }

    private static boolean isNumberInColumn(int [][] board, int number, int col){
        for (int i = 0; i < Main.GRID_SIZE; i++) {
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

    public void printBoard(int[][] board) {
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            if(i % 3 == 0 && i != 0){
                System.out.println("---------------------");
            }
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                if(j % 3 == 0 && j != 0){
                    System.out.print("| ");
                }
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
