package sva.ss.solver.viewrsc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Cell {
    public final Position posInGrid;
    public final JButton button;
    private boolean resolved; //si es una celda cuyo texto a sido generado (true) || o introducido por el user (false)

    public Cell(Position posInGrid){
        this.button = new JButton();
        this.posInGrid = posInGrid;

        button.setText("");
        button.setBorderPainted(true);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                cellClicked(e);
            }
        });
    }

    private void cellClicked(MouseEvent e) {
        setResolved(false);
        setButtonTextColor(Color.BLACK);
        if (SwingUtilities.isRightMouseButton(e)) {
            decreaseNumber();
        }else if (SwingUtilities.isLeftMouseButton(e)) {
            incrementNumber();
        }else if(SwingUtilities.isMiddleMouseButton(e)){
            setButtonText("");
        }
    }

    public void incrementNumber(){
        int num = getNumber() + 1;
        if(num == 0){
            setButtonText("");
            return;
        }
        if(num >= 10){
            setButtonText("");
            return;
        }
        setButtonText(num);
    }

    public void decreaseNumber(){
        int num = getNumber() - 1;
        if(num == 0){
            setButtonText("");
            return;
        }
        if(num < 0){
            setButtonText(9);
            return;
        }
        setButtonText(num);
    }

    public int getNumber() {
        try{
            return Integer.parseInt(button.getText());
        }catch (NumberFormatException e){
            return 0;
        }
    }


    public void setButtonTextColor(Color color){
        button.setForeground(color);
    }

    public void setButtonText(String num){
        button.setText(num);
    }

    public void setButtonText(int num){
        setButtonText(String.valueOf(num));
    }


    public void setResolved(boolean b){
        resolved = b;
    }

    public boolean isResolved() {
        return resolved;
    }
}
