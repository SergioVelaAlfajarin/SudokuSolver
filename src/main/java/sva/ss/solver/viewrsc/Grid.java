package sva.ss.solver.viewrsc;

import sva.ss.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.List;

public class Grid {
    public final JPanel panel;
    public final Square[][] squares;

    private Color solvedCellColor;
    private Cell[][] cells = null;

    public Grid() {
        this.panel = new JPanel(new GridLayout(3,3));
        this.squares = new Square[3][3];

        fillPanelWithSquares();
    }

    private void fillPanelWithSquares() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Position squarePos = new Position(row, col);
                squares[row][col] = new Square(squarePos);
                panel.add(squares[row][col].panel);
            }
        }
    }

    public Cell[][] getGridCells() { //si esta vacio
        if(cells != null){
            return cells;
        }
        Cell[][] cellsHolder = new Cell[9][9];
        for (int i = 0; i < Main.GRID_SIZE; i++) {
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                cellsHolder[i][j] = getCellByAbsolutePos(new Position(i, j));
            }
        }
        return cellsHolder;
    }

    public int[][] convertGridToBoard(){
        if(cells == null){
            cells = getGridCells();
        }
        final int[][] numsGrid = new int[9][9];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                numsGrid[i][j] = cells[i][j].getNumber();
            }
        }
        return numsGrid;
    }

    private Cell getCellByAbsolutePos(Position pos){ //wtf?
        if(cells == null){
            return Arrays.stream(squares)
                    .flatMap(Arrays::stream)
                    .flatMap(sq ->
                            Arrays.stream(sq.cells)
                                    .flatMap(Arrays::stream)
                    )
                    .filter(cell -> cell.posInGrid.equals(pos))
                    .findFirst()
                    .orElseThrow();
        }
        return Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(c -> c.posInGrid.equals(pos))
                .findFirst()
                .orElseThrow();
    }

    public void setCellsBySolvedBoard(int[][] board) {
        if (cells == null) {
            cells = getGridCells();
        }
        if(solvedCellColor == null){
            solvedCellColor = Color.GREEN;
        }

        for (int rows = 0; rows < board.length; rows++) {
            for (int cols = 0; cols < board[rows].length; cols++) {
                Cell cell = cells[rows][cols];

                if(cell.getNumber() == 0){
                    cell.setButtonText(board[rows][cols]);
                    cell.setButtonTextColor(solvedCellColor);
                    cell.setResolved(true);
                }
            }
        }
    }

    public void setSolvedCellColor(Color solvedCellColor) {
        this.solvedCellColor = solvedCellColor;

        Arrays.stream(cells)
                .flatMap(Arrays::stream)
                .filter(Cell::isResolved)
                .forEach(cell -> cell.setButtonTextColor(solvedCellColor));
    }
}
