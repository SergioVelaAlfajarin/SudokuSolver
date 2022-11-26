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
import java.util.List;

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
        //salir -------------------------------------------
        JMenu exitMenu = new JMenu("Salir");
        exitMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                exit();
            }
        });

        //limpiar -------------------------------------------
        JMenu limpiarMenu = new JMenu("Limpiar");
        JMenuItem limpiarTableroItem = new JMenuItem("Limipar todo");
        limpiarTableroItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cleanGrid();
            }
        });
        limpiarMenu.add(limpiarTableroItem);

        JMenuItem limpiarSolucionesItem = new JMenuItem("Limpiar soluciones");
        limpiarSolucionesItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cleanSolved();
            }
        });
        limpiarMenu.add(limpiarSolucionesItem);

        //calcular -------------------------------------------
        JMenu calcularMenu = new JMenu("Calcular");
        calcularMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                calculateSudoku();
            }
        });

        //cambiar color -----------------------------------------------------------
        JMenu cambiaColorMenu = new JMenu("Cambiar color");
        cambiaColorMenu.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                changeColor();
            }
        });

        JMenu ayudaMenu = new JMenu("Ayuda");
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

    private void mostrarAyuda() {
        JOptionPane.showMessageDialog(frame, "Click izq: aumenta en 1 la celda.\nClick der: resta 1 a la celda.\nClick de la ruleta: resetea la celda a 0.");
    }

    private void initFrame() {
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(Main.GRID_SIZE * 70, Main.GRID_SIZE * 70);
        frame.add(grid.panel);
        frame.setLocationRelativeTo(null);
    }

    private void cleanGrid(){
        Arrays.stream(grid.getGridCells())
                .flatMap(Arrays::stream)
                .forEach(cell -> {
                    cell.setButtonText("");
                });
    }

    private void cleanSolved(){
        List<Cell> solvedCells = grid.getCellsSolved();

        for(Cell c: solvedCells){
            c.setButtonText("");
        }
    }

    private void genTestGrid(){

    }

    private void changeColor(){
        Color color = JColorChooser.showDialog(frame,"Selecciona un color", grid.getSolvedCellColor());
        grid.setSolvedCellColor(color);
    }

    private void calculateSudoku() {
        var tablero = grid.generateGrid();

        Solver solver = new Solver();

        solver.startSolver();
        try{
            boolean isPossible = solver.solve(tablero);
            if(!isPossible){
                JOptionPane.showMessageDialog(frame, "El sudoku es imposible de resolver.");
            }else{
                grid.setCellsBySolvedBoard(tablero);
            }
        }catch (RuntimeException e){
            JOptionPane.showMessageDialog(frame, "Tiempo maximo de espera superado.");
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
