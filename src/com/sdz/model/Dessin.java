package com.sdz.model;

import com.sdz.observer.Bouton_Observateur;
import com.sdz.observer.Observable;
import com.sdz.observer.Observateur;
import com.sdz.observer.Bouton_Observable;
import com.sdz.model.Rectangle;
import com.sdz.model.Cercle;
import com.sdz.vue.Fenetre;


import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class Dessin implements Observable, MouseListener  {

    //private ArrayList<JPanel> liste_Image = new ArrayList<JPanel>();
    private ArrayList<Cercle> liste_Cercle = new ArrayList<Cercle>();
    private ArrayList<Rectangle> liste_Rectangle = new ArrayList<Rectangle>();
    private Cercle form_selected = null;

    private JPanel pan_dessin = new JPanel();   //ce sur quoi on va venir coller chaque dessin

    private JPanel image = new JPanel();
    private Observateur dessin_observer;

    private Button bouton_1 = new Button("Cercle");
    private Button bouton_2 = new Button("Rectangle");
    private Button bouton_3 = new Button("Effacer");
    private Button change_color_button = new Button("Jaune");
    private JLabel label = new JLabel("Le JLabel");

    //private Bouton_Observateur bouton_1_observer;
    //private Bouton_Observateur bouton_2_observer;

    private int mouse_click_x = 100;
    private int mouse_click_y = 150;

    public Dessin () {

        pan_dessin.setBounds(0, 0, 500, 400);
        pan_dessin.setBackground(Color.white);
        pan_dessin.setLayout(null);

        image.setLayout(null);
        image.setBounds(0, 0, 500, 400);
        pan_dessin.add(image);
        pan_dessin.setBackground(Color.green);

        System.out.println("Dessin()");


        /*   Boutons    */
        JPanel south = new JPanel();
        south.setBackground(Color.red);
        south.add(bouton_1);
        south.add(bouton_2);
        south.add(bouton_3);
        south.add(change_color_button);
        south.add(label);
        south.setBounds(0, 400, 500, 200);
        pan_dessin.add(south);

        bouton_1.addActionListener(new Bouton_1_Listener());
        bouton_2.addActionListener(new Bouton_2_Listener());
        bouton_3.addActionListener(new Bouton_3_Listener());
        change_color_button.addActionListener(new Change_color_button_Listener());

        image.addMouseListener(this);
    }

    public void initial_print() {
        updateObservateur();
    }



    public void addObservateur(Observateur obs) {
        this.dessin_observer = obs;
    }

    public void delObservateur() {

    }

    public void updateObservateur() {
        this.dessin_observer.update(pan_dessin);
    }


    class Bouton_1_Listener implements ActionListener /*, Bouton_Observable */ {
        //Classe écoutant notre premier bouton

        public void actionPerformed(ActionEvent arg0) {

            if (form_selected == null) {
                label.setText("Cercle");

                System.out.println("Ajout cercle!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Cercle cercle_tmp = new Cercle(x, y, hauteur, longueur);
                image.add(cercle_tmp);
                //pan_dessin.add(cercle_tmp);
                cercle_tmp.setBounds(x, y, hauteur, longueur);

                liste_Cercle.add(cercle_tmp);

                updateObservateur();
            }

        }

        private boolean pushed = false;
        private Bouton_Observateur bouton_observer;

    }

    //Classe écoutant notre second bouton
    class Bouton_2_Listener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

            if (form_selected == null) {
                label.setText("Rectangle");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;
                System.out.println("Ajout Rectange!!");

                Rectangle rectangle_tmp = new Rectangle(x, y, hauteur, longueur);
                image.add(rectangle_tmp);
                //pan_dessin.add(rectangle_tmp);

                rectangle_tmp.setBackground(Color.blue);
                rectangle_tmp.setBounds(x, y, hauteur, longueur);

                liste_Rectangle.add(rectangle_tmp);

                updateObservateur();
            }

        }
    }

    class Bouton_3_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            label.setText("Supprimé");

            image = new JPanel();
            //liste_Image.clear();
            //pan_dessin.remove(liste_Image.get(0));

            updateObservateur();
        }
    }

    class Change_color_button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (form_selected != null) {
                form_selected.rotate_form();
                form_selected.setBounds(mouse_click_x-38, mouse_click_y-38, 75, 75);
                form_selected.setBackground(Color.cyan);

            }
        }
    }


    //Méthode appelée lors du clic de souris
    public void mouseClicked(MouseEvent event) {

        if (form_selected == null) {
            mouse_click_x = event.getX();
            mouse_click_y = event.getY();
            label.setText("X : " + mouse_click_x + " Y : " + mouse_click_y);

            form_selection(mouse_click_x, mouse_click_y);
            updateObservateur();
        } else {
            form_selection(mouse_click_x, mouse_click_y);
        }

    }


    public boolean form_selection (int X, int Y) {
        if (form_selected == null) {
            for (Cercle tmp : liste_Cercle) {
                if (tmp.selected(X, Y)) {
                    form_selected = tmp;
                    return true;
                }
            }
            for (Rectangle tmp : liste_Rectangle) {
                if (tmp.selected(X, Y)) {
                    //form_selected = tmp;
                    return true;
                }
            }
            form_selected = null;
            return false;
        } else {
            form_selected.reset_color();
            form_selected = null;
            return false;
        }
    }


    //Méthode appelée lors du survol de la souris
    public void mouseEntered(MouseEvent event) { }

    //Méthode appelée lorsque la souris sort de la zone du bouton
    public void mouseExited(MouseEvent event) { }

    //Méthode appelée lorsque l'on presse le bouton gauche de la souris
    public void mousePressed(MouseEvent event) { }

    //Méthode appelée lorsque l'on relâche le clic de souris
    public void mouseReleased(MouseEvent event) { }

}










/*
        public void addObservateur(Bouton_Observateur obs) {
            this.bouton_observer = obs;
        }

        public void delObservateur() {

        }


        public void updateObservateur() {
            this.bouton_observer.update(pushed);
        }

        public void actionPerformed(ActionEvent arg0) {
            pushed = true;
            updateObservateur();
            pushed = false;
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

        */