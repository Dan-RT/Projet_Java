package com.sdz.vue;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.*;

import com.sdz.model.Dessin;
import com.sdz.model.Horloge;
import com.sdz.model.Image;
import com.sdz.observer.Observable;
import com.sdz.observer.Observateur;
import sun.security.krb5.internal.crypto.Des;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Fenetre extends JFrame {

    private Image pan = new Image();
    private Dessin test_dessin = new Dessin();

    //private Image tmp_image = new Image();
    //private Container container;
    //private ArrayList<Dessin> liste_dessin = new ArrayList<Dessin>();

    public Fenetre(){

        this.setSize(500, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        this.test_dessin.addObservateur(new Observateur() {
            public void update(JPanel pan_dessin) {
                System.out.println("Fenetre/update");
                getContentPane().add(pan_dessin, BorderLayout.CENTER);
                setVisible(true);
            }
        });

        this.setVisible(true);
        test_dessin.initial_print();
        //test_dessin.go();
        //go();

    }

    public static void main(String[] args){
        Fenetre fen = new Fenetre();
    }
}





























    /*public void go() {
        // Les coordonnées de départ de notre rond
        int x = pan.getPosX(), y = pan.getPosY();
        // Le booléen pour savoir si l'on recule ou non sur l'axe x
        boolean backX = false;
        // Le booléen pour savoir si l'on recule ou non sur l'axe y
        boolean backY = false;

        // Dans cet exemple, j'utilise une boucle while
        // Vous verrez qu'elle fonctionne très bien
        while (true) {
            // Si la coordonnée x est inférieure à 1, on avance
            if (x < 1)
                backX = false;
            // Si la coordonnée x est supérieure à la taille du Panneau moins la taille du rond, on recule
            if (x > pan.getWidth() - 50)
                backX = true;
            // Idem pour l'axe y
            if (y < 1)
                backY = false;
            if (y > pan.getHeight() - 50)
                backY = true;

            // Si on avance, on incrémente la coordonnée
            // backX est un booléen, donc !backX revient à écrire
            // if (backX == false)
            if (!backX)
                pan.setPosX(++x);
                // Sinon, on décrémente
            else
                pan.setPosX(--x);
            // Idem pour l'axe Y
            if (!backY)
                pan.setPosY(++y);
            else
                pan.setPosY(--y);

            // On redessine notre Panneau
            pan.repaint();
            // Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
*/