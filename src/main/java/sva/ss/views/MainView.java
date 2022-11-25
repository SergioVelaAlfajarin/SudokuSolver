package sva.ss.views;

import sva.ss.Main;
import sva.ss.solver.viewrsc.Grid;

import javax.swing.*;

public class MainView {
    private final JFrame frame;
    private final Grid grid;

    public MainView() {
        this.frame = new JFrame("Sudoku Solver");
        this.grid = new Grid();

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Main.GRID_SIZE * 70, Main.GRID_SIZE * 70);
        frame.add(grid.panel);
        frame.setLocationRelativeTo(null);
    }

    public Grid getGrid(){
        return grid;
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
