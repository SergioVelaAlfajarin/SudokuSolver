package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell {
    public final Position posInSquare;
    public final Position posInGrid;
    public final JButton button;

    public Cell(Position posInSquare, Position posInGrid){
        this.button = new JButton();
        this.posInSquare = posInSquare;
        this.posInGrid = posInGrid;

        button.setText("");
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    decreaseNumber();
                }else if (SwingUtilities.isLeftMouseButton(e)) {
                    incrementNumber();
                }
            }
        });
    }

    public void incrementNumber(){
        int num;
        try{
            num = Integer.parseInt(button.getText());
        }catch (NumberFormatException e){
            num = 0;
        }
        num++;
        if(num == 0){
            button.setText("");
            return;
        }
        if(num >= 10){
            button.setText("");
            return;
        }
        button.setText(String.valueOf(num));
    }

    public void decreaseNumber(){
        int num;
        try{
            num = Integer.parseInt(button.getText());
        }catch (NumberFormatException e){
            num = 0;
        }
        num--;
        if(num == 0){
            button.setText("");
            return;
        }
        if(num < 0){
            button.setText("9");
            return;
        }
        button.setText(String.valueOf(num));
    }

    public int getNumber() {
        return Integer.parseInt(button.getText());
    }

    @Override
    public String toString() {
        return "Cell{" +
                "posInSquare=" + posInSquare +
                '}';
    }
}
