package sva.ss.views;

import sva.ss.views.rsc.Grid;

import javax.swing.*;
import java.awt.*;

public class MainView {
    private static final int ROWS = 9;
    private static final int COLS = 9;

    private final JFrame frame;
    private final Grid grid;

    public MainView() { //TODO NOVA
        this.frame = new JFrame("Sudoku Solver");
        this.grid = initGrid();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(ROWS * 75,COLS * 75);
        frame.add(grid.panel);
        frame.setLocationRelativeTo(null);
    }

    private Grid initGrid() {
        return new Grid();
    }

    public void show(){
        frame.setVisible(true);
    }

    public void hide(){
        frame.setVisible(false);
    }

    public void dispose(){
        frame.dispose();
    }
}
