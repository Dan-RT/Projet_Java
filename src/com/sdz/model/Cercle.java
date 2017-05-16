package com.sdz.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 14/05/2017.
 */
public class Cercle extends JPanel {

    private int posX = 100;
    private int posY = 100;

    private Color color = Color.red;

    public Cercle(int X, int Y) {
        setPosX(X);
        setPosY(Y);
    }

    public void paintComponent (Graphics g) {

        g.setColor(color);
        g.fillOval(posX, posY, 50, 50);

    }

    public int getPosX() {
        return posX;
    }

    public void setPosX(int posX) {
        this.posX = posX;
    }

    public int getPosY() {
        return posY;
    }

    public void setPosY(int posY) {
        this.posY = posY;
    }


}
