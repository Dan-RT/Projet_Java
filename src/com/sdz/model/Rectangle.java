package com.sdz.model;
import javax.swing.*;
import java.awt.*;

public class Rectangle extends JPanel {

    private int posX = 200;
    private int posY = 70;

    /*private int height = 0;
    private int width = 0;
    private Color color = Color.red;*/

    public Rectangle (int X, int Y) {
        setPosX(X);
        setPosY(Y);
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


    /*
    public int getHeight() {
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

*/

}
