package sva.ss.solver.viewrsc;

import sva.ss.Main;

import javax.swing.*;
import java.awt.*;
import java.util.Arrays;

public class Grid {
    public final JPanel panel;
    public final Square[][] squares;

    public Grid() {
        this.panel = new JPanel(new GridLayout(3,3));
        this.squares = new Square[3][3];

        fillPanelWithSquares();
    }

    private void fillPanelWithSquares() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                Position pos = new Position(rows, columns);

                squares[rows][columns] = new Square(pos);

                panel.add(squares[rows][columns].panel);
            }
        }
    }

    public int[][] generateGrid(){
        final int[][] numsGrid = new int[9][9];
        final Cell[][] cells = getGridCells();

        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                numsGrid[i][j] = cells[i][j].getNumber();
            }
        }

        return numsGrid;
    }

    private Cell[][] getGridCells() {
        final Cell[][] holder = new Cell[9][9];

        for (int i = 0; i < Main.GRID_SIZE; i++) {
            for (int j = 0; j < Main.GRID_SIZE; j++) {
                holder[i][j] = getCellByAbsolutePos(new Position(i, j));
            }
        }

        return holder;
    }

    private Cell getCellByAbsolutePos(Position pos){
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
}
