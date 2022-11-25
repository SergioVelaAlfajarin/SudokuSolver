package sva.ss.views.rsc;

import sva.ss.solver.Position;

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

        Arrays.stream(squares)
                .flatMap(Arrays::stream)
                .forEach(System.out::println);

        System.out.println("imprimir pos absolutas of cells");

        Position[][] pos = squares[2][2].getAbsolutePositionsOfCells();

        System.out.println(Arrays.deepToString(pos));
    }

    private void fillPanelWithSquares() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                Position pos = new Position(rows, columns, "Square");
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



        return holder;
    }
}
