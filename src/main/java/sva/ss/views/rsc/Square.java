package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;
import java.awt.*;

public class Square {
    public final Position posInGrid;
    public final JPanel panel;
    public final Cell[][] cells;

    public Square(Position pos) {
        panel = new JPanel(new GridLayout(3,3));
        cells = new Cell[3][3];
        this.posInGrid = pos;

        fillPanelWithCells();

        panel.setBorder(
                BorderFactory.createLineBorder(Color.BLACK)
        );
    }

    private void fillPanelWithCells() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                Position pos = new Position(rows, columns, "cell");
                cells[rows][columns] = new Cell(pos);
                panel.add(cells[rows][columns].button);
            }
        }
    }

    public Position[][] getAbsolutePositionsOfCells(){
        Position[][] holder = new Position[3][3];
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Position cellsPos = cells[i][j].posInSquare;

                holder[i][j] = new Position(
                        cellsPos.row() * (posInGrid.row() == 0 ? 1 : posInGrid.row()),
                        cellsPos.col() * (posInGrid.col() == 0 ? 1 : posInGrid.col()),
                        "cell"
                );
            }
        }
        return holder;
    }

    @Override
    public String toString() {
        return "Square{" +
                "posInGrid=" + posInGrid +
                '}';
    }
}
