package com.sdz.model;

import javax.swing.*;
import java.awt.*;


import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

public class Image extends JPanel {

    private int posX = -50;
    private int posY = -50;

    public void paintComponent(Graphics g) {
        /*
        // On décide d'une couleur de fond pour notre rectangle
        g.setColor(Color.white);
        // On dessine celui-ci afin qu'il prenne tout la surface
        g.fillRect(0, 0, this.getWidth(), this.getHeight());
        // On redéfinit une couleur pour notre rond */
        g.setColor(Color.red);
        // On le dessine aux coordonnées souhaitées
        g.fillRect(posX, posY, 50, 50);
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





/* public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

*/