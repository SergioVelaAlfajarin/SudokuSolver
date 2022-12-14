package sva.ss.views;

import sva.ss.Main;
import sva.ss.solver.actualsolver.Solver;
import sva.ss.solver.viewrsc.Cell;
import sva.ss.solver.viewrsc.Grid;


import javax.swing.*;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MainView {
    private final JFrame frame;
    private final JMenuBar menuBar;
    private final Grid grid;

    public MainView() {
        this.frame = new JFrame("Sudoku Solver");
        this.grid = new Grid();
        this.menuBar = new JMenuBar();

        createMenuBar();
        initFrame();
    }

    private void createMenuBar() {
        //salir -------------------------------------------
        JMenu exitMenu = new JMenu("Exit");
        exitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exit();
            }
        });

        //limpiar -------------------------------------------
        JMenu limpiarMenu = new JMenu("Clean");
        JMenuItem limpiarTableroItem = new JMenuItem("Clean all");
        limpiarTableroItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cleanGrid();
            }
        });
        limpiarMenu.add(limpiarTableroItem);

        JMenuItem limpiarSolucionesItem = new JMenuItem("Clean solved cells");
        limpiarSolucionesItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cleanSolved();
            }
        });
        limpiarMenu.add(limpiarSolucionesItem);

        //calcular -------------------------------------------
        JMenu calcularMenu = new JMenu("Calculate");
        calcularMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculateSudoku();
            }
        });

        //cambiar color -----------------------------------------------------------
        JMenu cambiaColorMenu = new JMenu("Change color");
        cambiaColorMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                changeColor();
            }
        });

        //ayuda menu ----------------------------------------------------
        JMenu ayudaMenu = new JMenu("Help");
        ayudaMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                mostrarAyuda();
            }
        });

        menuBar.add(exitMenu);
        menuBar.add(limpiarMenu);
        menuBar.add(calcularMenu);
        menuBar.add(cambiaColorMenu);
        menuBar.add(ayudaMenu);
    }

    private void initFrame() {
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Main.GRID_SIZE * 70, Main.GRID_SIZE * 70);
        frame.add(grid.panel);
        frame.setLocationRelativeTo(null);
    }

    private void mostrarAyuda() {
        JOptionPane.showMessageDialog(frame, """
                Left Click: add 1 to cell.
                Right Click: substract 1 to cell.
                Middle Click: reset cell to 0."""
        );
    }

    private void cleanGrid(){
        Arrays.stream(grid.getGridCells())
                .flatMap(Arrays::stream)
                .forEach(cell -> cell.setButtonText(""));
    }

    private void cleanSolved(){
        Arrays.stream(grid.getGridCells())
                .flatMap(Arrays::stream)
                .filter(Cell::isResolved)
                .forEach(cell -> cell.setButtonText(""));
    }

    private void changeColor(){
        Color color = JColorChooser.showDialog(
                frame,"Pick a color", null
        );
        grid.setSolvedCellColor(color);
    }

    private void calculateSudoku() {
        var board = grid.convertGridToBoard();

        Solver solver = new Solver();
        try{
            boolean isPossible = solver.solve(board);
            if(!isPossible){
                JOptionPane.showMessageDialog(frame, "This sudoku has no solution.");
            }else{
                grid.setCellsBySolvedBoard(board);
            }
        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(frame, "Solver timed out.\n" +
                    "Sudoku impossible to solve.");
        }
    }

    public void exit(){
        this.hide();
        this.dispose();
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
