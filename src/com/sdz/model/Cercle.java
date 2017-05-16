package com.sdz.model;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Daniel on 14/05/2017.
 */
public class Cercle extends JPanel {

    private int posX = 0;
    private int posY = 0;

    private int x_min = 0;
    private int y_min = 0;
    private int hauteur = 0;
    private int longueur = 0;

    private Color color = Color.red;

    public Cercle(int X, int Y, int Hauteur, int Longueur) {
        x_min = X;
        y_min = Y;
        System.out.println("Create Cercle");
        setHauteur(Hauteur);
        setLongueur(Longueur);
    }

    public void paintComponent (Graphics g) {
        g.setColor(color);
        g.fillOval(posX, posY, 50, 50);
    }



    public boolean selected (int X, int Y) {

        if (X >= x_min && X <= x_min+hauteur/2) {
            if (Y >= y_min && Y <= y_min+longueur/2) {
                this.color = Color.green;
                this.repaint();
                return true;
            }
        }
        return false;
    }

    public void change_color () {
        color = Color.yellow;
        this.repaint();
    }

    public void reset_color () {
        color = Color.red;
        this.repaint();
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


    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int data) {
        this.hauteur = data;
    }

    public int getLongueur() {
        return longueur;
    }

    public void setLongueur(int data) {
        this.longueur = data;
    }


}
