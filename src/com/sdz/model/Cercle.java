package com.sdz.model;

import javax.swing.*;
import java.awt.*;

import java.awt.geom.Ellipse2D;
import java.awt.Rectangle;

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

    private boolean rotate = false;
    private int nb_times_rotated = 0;

    private Color color = Color.red;

    public Cercle(int X, int Y, int Hauteur, int Longueur) {
        x_min = X;
        y_min = Y;

        System.out.println("Create Cercle");
        setHauteur(Hauteur);
        setLongueur(Longueur);

        setPosX(0+hauteur/4);
        setPosY(0+longueur/4);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        Ellipse2D ellispe_tmp = new Ellipse2D.Double(posX, posY, hauteur, longueur-10);

        g2d.setColor(color);


        //java.awt.Rectangle rect2 = new java.awt.Rectangle(posX, posY, hauteur, longueur);


        for (int i = 0; i < nb_times_rotated; i++){
            g2d.rotate(Math.toRadians(45), (hauteur+hauteur/2)/2, (longueur+longueur/2)/2);

        }


        if (rotate) {
            g2d.rotate(Math.toRadians(45), (hauteur+hauteur/2)/2, (longueur+longueur/2)/2);
            rotate = false;
            nb_times_rotated++;
        }

        g2d.fill(ellispe_tmp);
        g2d.draw(ellispe_tmp);
        //g2d.draw(rect2);
        //g2d.fill(rect2);
    }



    public boolean selected (int X, int Y) {

        int x_max = x_min+hauteur/2;
        int y_max = y_min+longueur/2;

        if (X >= x_min && X <= x_max) {
            if (Y >= y_min && Y <= y_max) {
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


    public void rotate_form () {
        rotate = true;
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
