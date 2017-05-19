package com.sdz.model;
import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {

    private int posX = 0;
    private int posY = 0;

    private int x_min = 0;
    private int y_min = 0;
    private int hauteur = 0;
    private int longueur = 0;
    private int size_up = 50;

    private boolean rotate = false;
    private int resize = 0;
    private int nb_times_rotated = 0;
    private int nb_times_rized_up = 0;
    private int nb_times_rized_down = 0;


    private int x_panel = 0;
    private int y_panel = 0;
    private int h_panel = 0;
    private int w_panel = 0;

    private Color color = Color.blue;

    public Rectangle(int X, int Y, int Hauteur, int Longueur) {
        x_min = X;
        y_min = Y;

        setHauteur(Hauteur);
        setLongueur(Longueur);

        setPosX(0+hauteur/4);
        setPosY(0+longueur/4);
    }


    public void paintComponent (Graphics g) {
        double zoom = 0;
        double zoomWidth = 0;
        double zoomHeight = 0;
        double anchorx = 0;
        double anchory = 0;

        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(color);
        java.awt.Rectangle rect2 = new java.awt.Rectangle(posX, posY, hauteur, longueur);

        for (int i = 0; i < nb_times_rotated; i++){
            g2d.rotate(Math.toRadians(45), (hauteur+hauteur/2)/2, (longueur+longueur/2)/2);
        }

        if (rotate) {
            g2d.rotate(Math.toRadians(45), (hauteur+hauteur/2)/2, (longueur+longueur/2)/2);
            rotate = false;
            nb_times_rotated++;
        }

        for (int i = 0; i < nb_times_rized_up; i++){
            zoom = 1.1;
            zoomWidth = longueur * zoom;
            zoomHeight = hauteur * zoom;
            anchorx = (longueur - zoomWidth) / 2;
            anchory = (hauteur - zoomHeight) / 2;
            g2d.translate(anchorx, anchory);
            g2d.scale(zoom, zoom);
        }
        for (int i = 0; i < nb_times_rized_down; i++){
            zoom = 0.9;
            zoomWidth = longueur * zoom;
            zoomHeight = hauteur * zoom;
            anchorx = (longueur - zoomWidth) / 2;
            anchory = (hauteur - zoomHeight) / 2;
            g2d.translate(anchorx, anchory);
            g2d.scale(zoom, zoom);
        }
        if (resize > 0) {
            if (resize == 1) {
                zoom = 1.1;
                zoomWidth = longueur * zoom;
                zoomHeight = hauteur * zoom;
                anchorx = (longueur - zoomWidth) / 2;
                anchory = (hauteur - zoomHeight) / 2;
                g2d.translate(anchorx, anchory);
                g2d.scale(zoom, zoom);

                resize = 0;
                nb_times_rized_up++;
            } else if (resize == 2) {

                zoom = 0.9;
                zoomWidth = longueur * zoom;
                zoomHeight = hauteur * zoom;
                anchorx = (longueur - zoomWidth) / 2;
                anchory = (hauteur - zoomHeight) / 2;
                g2d.translate(anchorx, anchory);
                g2d.scale(zoom, zoom);

                resize = 0;
                nb_times_rized_down++;
            }
        }

        g2d.draw(rect2);
        g2d.fill(rect2);

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
        color = Color.blue;
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

    public void setSize_up(int data) {
        this.size_up = data;
    }

    public int getSize_up() {
        return size_up;
    }

    public void resize_draw (boolean up) {
        if (up) {
            resize = 1;
        } else {
            resize = 2;
        }
    }





    public void setX_panel(int data) {
        this.x_panel = data;
    }

    public int getX_panel() {
        return x_panel;
    }

    public void setY_panel(int data) {
        this.y_panel = data;
    }

    public int getY_panel() {
        return y_panel;
    }

    public void setH_panel(int data) {
        this.h_panel = data;
    }

    public int getH_panel() {
        return h_panel;
    }

    public void setW_panel(int data) {
        this.w_panel = data;
    }

    public int getW_panel() {
        return w_panel;
    }


    public void translate (int choice) {

        switch (choice) {
            case 1:
                //up
                y_panel = y_panel - 10;
                y_min = y_min - 10;
                break;
            case 2:
                //down
                y_panel = y_panel + 10;
                y_min = y_min + 10;
                break;
            case 3:
                //right
                x_panel = x_panel + 10;
                x_min = x_min + 10;
                break;
            case 4:
                //left
                x_panel = x_panel - 10;
                x_min = x_min - 10;
                break;
        }
    }


    public void shape_infos () {

        int aera = hauteur*longueur;
        System.out.println("\nAire de la forme : " + aera + " m^2");

        aera = longueur*2 + hauteur*2;
        System.out.println("Périmètre de la forme : " + aera + " m");

    }



}
