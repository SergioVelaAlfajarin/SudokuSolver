package sva.ss.solver.viewrsc;

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

    @Override
    public String toString() {
        return "Square{" +
                "posInGrid=" + squarePosition +
                '}';
    }
}
