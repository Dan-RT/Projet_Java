package com.sdz.model;

import javax.swing.*;
import java.awt.*;
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

    private Color color = Color.red;

    public Cercle(int X, int Y, int Hauteur, int Longueur) {
        x_min = X;
        y_min = Y;
        System.out.println("Create Cercle");
        setHauteur(Hauteur);
        setLongueur(Longueur);
    }

    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
        java.awt.Rectangle rect2 = new java.awt.Rectangle(posX, posY, 50, 50);

        if (rotate) {
            g2d.rotate(Math.toRadians(45), hauteur/2, longueur/2);

            rotate = false;
        }

        g2d.draw(rect2);
        g2d.fill(rect2);
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


/*

    public void paintIcon(Component c, Graphics g, int x, int y)
    {
        Graphics2D g2 = (Graphics2D)g.create();

        int cWidth = icon.getIconWidth() / 2;
        int cHeight = icon.getIconHeight() / 2;
        int xAdjustment = (icon.getIconWidth() % 2) == 0 ? 0 : -1;
        int yAdjustment = (icon.getIconHeight() % 2) == 0 ? 0 : -1;

        if (rotate == Rotate.DOWN)
        {
            g2.translate(x + cHeight, y + cWidth);
            g2.rotate( Math.toRadians( 90 ) );
            icon.paintIcon(c, g2,  -cWidth, yAdjustment - cHeight);
        }
        else if (rotate == Rotate.UP)
        {
            g2.translate(x + cHeight, y + cWidth);
            g2.rotate( Math.toRadians( -90 ) );
            icon.paintIcon(c, g2,  xAdjustment - cWidth, -cHeight);
        }
        else if (rotate == Rotate.UPSIDE_DOWN)
        {
            g2.translate(x + cWidth, y + cHeight);
            g2.rotate( Math.toRadians( 180 ) );
            icon.paintIcon(c, g2, xAdjustment - cWidth, yAdjustment - cHeight);
        }
        else if (rotate == Rotate.ABOUT_CENTER)
        {
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setClip(x, y, getIconWidth(), getIconHeight());
            g2.translate((getIconWidth() - icon.getIconWidth()) / 2, (getIconHeight() - icon.getIconHeight()) / 2);
            g2.rotate(Math.toRadians(degrees), x + cWidth, y + cHeight);
            icon.paintIcon(c, g2, x, y);
        }

        g2.dispose();
    }
*/


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
