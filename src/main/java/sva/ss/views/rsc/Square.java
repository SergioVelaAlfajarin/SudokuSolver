package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;
import java.awt.*;

public class Square {
    public final Position pos;
    public final JPanel panel;
    public final Cell[][] cells;

    public Square(Position pos) {
        panel = new JPanel(new GridLayout(3,3));
        cells = new Cell[3][3];
        this.pos = pos;

        fillPanelWithCells();

        panel.setBorder(
                BorderFactory.createLineBorder(Color.BLACK)
        );
    }

    private void fillPanelWithCells() {
        for (int rows = 0; rows < 3; rows++) {
            for (int columns = 0; columns < 3; columns++) {
                Position pos = new Position(rows, columns, "Cell");
                cells[rows][columns] = new Cell(pos);
                panel.add(cells[rows][columns].button);
            }
        }
    }
}
