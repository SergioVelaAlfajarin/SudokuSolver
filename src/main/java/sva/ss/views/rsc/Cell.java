package sva.ss.views.rsc;

import sva.ss.solver.Position;

import javax.swing.*;

public class Cell {
    public final Position posInSquare;
    public final JButton button;

    public Cell(Position pos){
        button = new JButton();
        this.posInSquare = pos;

        button.setText(pos.toString());
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.addActionListener(bt -> System.out.println(this));
    }

    public void incrementNumber(){
        int num;
        try{
            num = Integer.parseInt(button.getText());
        }catch (NumberFormatException e){
            num = 0;
        }
        num++;
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
