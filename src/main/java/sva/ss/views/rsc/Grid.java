package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;
import java.awt.*;

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
                Position pos = new Position(rows, columns, "Square");
                squares[rows][columns] = new Square(pos);
                panel.add(squares[rows][columns].panel);
            }
        }
    }
}
