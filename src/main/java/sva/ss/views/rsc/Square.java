package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;
import java.awt.*;

public class Square {
    public final Position squarePosition;
    public final JPanel panel;
    public final Cell[][] cells;

    public Square(Position pos) {
        panel = new JPanel(new GridLayout(3,3));
        cells = new Cell[3][3];
        this.squarePosition = pos;

        fillPanelWithCells();

        panel.setBorder(
                BorderFactory.createLineBorder(Color.BLACK)
        );
    }

    private void fillPanelWithCells() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                Position cellPosInSquare = new Position(rows, columns);
                Position cellPosInGrid = new Position(
                        rows + (this.squarePosition.row() * 3),
                        columns + (this.squarePosition.col() * 3)
                );
                cells[rows][columns] = new Cell(cellPosInSquare, cellPosInGrid);
                panel.add(cells[rows][columns].button);
            }
        }
    }



    public Cell getCellByPos(int row, int col){
        return getCellByPos(new Position(row, col));
    }

    public Cell getCellByPos(Position pos){
        try{
            return cells[pos.row()][pos.col()];
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            return null;
        }
    }

    @Override
    public String toString() {
        return "Square{" +
                "posInGrid=" + squarePosition +
                '}';
    }
}
