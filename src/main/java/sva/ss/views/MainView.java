package sva.ss.views;

import sva.ss.Main;
import sva.ss.solver.actualsolver.Solver;
import sva.ss.solver.viewrsc.Grid;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MainView {
    private final JFrame frame;
    private final JMenuBar menuBar;
    private final Grid grid;

    public MainView() {
        this.frame = new JFrame("Sudoku Solver");
        this.grid = new Grid();
        this.menuBar = new JMenuBar();

        initFrame();
        createMenuBar();
    }

    private void createMenuBar() {
        JMenu menu1 = new JMenu("Archivo");
        JMenu menu2 = new JMenu("Calcular");

        JMenuItem exitItem = new JMenuItem("Salir...");
        exitItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exit();
            }
        });

        JMenuItem calculateItem = new JMenuItem("Calcular sudoku...");
        calculateItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculateSudoku();
            }
        });

        menu1.add(exitItem);
        menuBar.add(menu1);

        menu2.add(calculateItem);
        menuBar.add(menu2);
    }

    private void initFrame() {
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Main.GRID_SIZE * 70, Main.GRID_SIZE * 70);
        frame.add(grid.panel);
        frame.setLocationRelativeTo(null);
    }

    private void calculateSudoku() {
        Solver solver = new Solver();
        var tablero = grid.generateGrid();
        solver.printBoard(tablero);
        boolean isPossible = solver.solve(tablero);
        if(!isPossible){
            System.out.println("no es posible");
        }else{
            solver.printBoard(tablero);
        }
    }

    public void exit(){
        this.hide();
        this.dispose();
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
