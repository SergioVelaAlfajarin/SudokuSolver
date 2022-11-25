package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;

public class Cell {
    public final Position pos;
    public final JButton button;

    public Cell(Position pos){
        button = new JButton();
        this.pos = pos;

        button.setText(pos.toString());
    }
}
