package com.sdz.model;

import com.sdz.observer.Observable;
import com.sdz.observer.Observateur;
import com.sdz.observer.Bouton_Observateur;
import com.sdz.model.Rectangle;
import com.sdz.model.Cercle;
import com.sdz.vue.Fenetre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Dessin implements Observable  {

    private ArrayList<Image> liste_Image = new ArrayList<Image>();
    private Cercle image_test = new Cercle();
    private JPanel pan_dessin = new JPanel();
    private Observateur dessin_observer;


    private Button bouton_1 = new Button("Rectangle");
    private Button bouton_2 = new Button("Cercle");
    private JLabel label = new JLabel("Le JLabel");


    public Dessin () {

        pan_dessin.setBounds(0, 0, 500, 400);
        pan_dessin.setBackground(Color.white);
        pan_dessin.setLayout(null);

        image_test.setBounds(0, 0, 500, 400);


        pan_dessin.add(image_test);

        //pan_dessin.add(image_test, BorderLayout.NORTH);
        System.out.println("Dessin()");


        /*   Boutons    */
        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.add(bouton_1);
        south.add(bouton_2);
        south.add(label);
        south.setBounds(100, 400, 500, 200);
        pan_dessin.add(south);

       /* bouton_1.addActionListener(new Bouton_1_Listener());
        bouton_2.addActionListener(new Bouton_2_Listener());
*/

    }

    public void go() {
        // Les coordonnées de départ de notre rond
        int x = image_test.getPosX(), y = image_test.getPosY();
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
            if (x > image_test.getWidth() - 50)
                backX = true;
            // Idem pour l'axe y
            if (y < 1)
                backY = false;
            if (y > image_test.getHeight() - 50)
                backY = true;

            // Si on avance, on incrémente la coordonnée
            // backX est un booléen, donc !backX revient à écrire
            // if (backX == false)
            if (!backX)
                image_test.setPosX(++x);
                // Sinon, on décrémente
            else
                image_test.setPosX(--x);
            // Idem pour l'axe Y
            if (!backY)
                image_test.setPosY(++y);
            else
                image_test.setPosY(--y);

            // On redessine notre Panneau
            image_test.repaint();
            // Comme on dit : la pause s'impose ! Ici, trois millièmes de seconde
            try {
                Thread.sleep(3);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            updateObservateur();
        }
    }

    //Ajoute un observateur à la liste
    public void addObservateur(Observateur obs) {
        this.dessin_observer = obs;
    }
    //Retire tous les observateurs de la liste
    public void delObservateur() {

    }

    //Avertit les observateurs que l'objet observable a changé
    //et invoque la méthode update() de chaque observateur
    public void updateObservateur() {
        this.dessin_observer.update(pan_dessin);
    }


    //Classe écoutant notre premier bouton
    class Bouton_1_Listener implements ActionListener {


        public void actionPerformed(ActionEvent arg0) {
            label.setText("Rectangle");
        }
        /*
        Observable
        private boolean pushed = false;
        private Observateur bouton_observer;

        public void addObservateur(Observateur obs) {
            this.bouton_observer = obs;
        }

        public void delObservateur() {

        }


        public void updateObservateur() {
            //this.bouton_observer.update(pushed);
        }

        public void actionPerformed(ActionEvent arg0) {
            pushed = true;
            updateObservateur();
            pushed = false;
        }*/
    }

    //Classe écoutant notre second bouton
    class Bouton_2_Listener implements ActionListener {
        //Redéfinition de la méthode actionPerformed()
        public void actionPerformed(ActionEvent e) {
            label.setText("Cercle");
        }

    }


}
