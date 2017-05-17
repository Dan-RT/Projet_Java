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
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Dessin implements Observable, MouseListener  {


    //private ArrayList<JPanel> liste_Image = new ArrayList<JPanel>();
    private ArrayList<Cercle> liste_Cercle = new ArrayList<Cercle>();
    private ArrayList<Rectangle> liste_Rectangle = new ArrayList<Rectangle>();
    private Cercle cercle_selected = null;
    private Rectangle rectangle_selected = null;

    private JPanel pan_dessin = new JPanel();   //ce sur quoi on va venir coller chaque dessin

    //private JPanel  = new JPanel();
    private ImagePanel image = new ImagePanel();
    private Observateur dessin_observer;

    private Button bouton_1 = new Button("Cercle");
    private Button bouton_2 = new Button("Rectangle");
    private Button change_color_button = new Button("Rotate");
    private Button erase_button = new Button("Erase");
    private Button erase_all_button = new Button("Clear All");
    private JLabel label = new JLabel("Le JLabel");


    private int mouse_click_x = 100;
    private int mouse_click_y = 150;

    public Dessin () {

        pan_dessin.setBounds(0, 0, 800, 525);
        pan_dessin.setBackground(Color.green);
        pan_dessin.setLayout(null);
        image.setBounds(0, 0, 800, 525);
        image.setLayout(null);
        pan_dessin.add(image);




        System.out.println("Dessin()");


        /*   Boutons    */
        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.add(bouton_1);
        south.add(bouton_2);
        south.add(erase_button);
        south.add(change_color_button);
        south.add(erase_all_button);
        south.add(label);
        south.setBounds(0, 525, 800, 200);
        pan_dessin.add(south);

        bouton_1.addActionListener(new Cercle_Listener());
        bouton_2.addActionListener(new Rectangle_Listener());
        change_color_button.addActionListener(new Change_color_button_Listener());
        erase_button.addActionListener(new Erase_button_Listener());
        erase_all_button.addActionListener(new Erase_all_button_Listener());

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


    class Cercle_Listener implements ActionListener {
        //Classe écoutant notre premier bouton

        public void actionPerformed(ActionEvent arg0) {

            if (cercle_selected == null && rectangle_selected == null) {
                label.setText("Cercle");

                System.out.println("Ajout cercle!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Cercle cercle_tmp = new Cercle(x, y, hauteur, longueur);
                //cercle_tmp.setBackground(Color.cyan);

                cercle_tmp.setOpaque(false);
                image.add(cercle_tmp);
                //pan_dessin.add(cercle_tmp);
                cercle_tmp.setBounds(x-hauteur/2, y-longueur/2, hauteur+hauteur/2, longueur+longueur/2);

                liste_Cercle.add(cercle_tmp);

                updateObservateur();
            }

        }

        private boolean pushed = false;
        private Bouton_Observateur bouton_observer;

    }

    class Rectangle_Listener implements ActionListener {
        //Classe écoutant notre second bouton
        public void actionPerformed(ActionEvent arg0) {

            if (cercle_selected == null && rectangle_selected == null) {
                label.setText("Rectangle");

                System.out.println("Ajout Rectangle!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Rectangle rectangle_tmp = new Rectangle(x, y, hauteur, longueur);
                //cercle_tmp.setBackground(Color.cyan);

                rectangle_tmp.setOpaque(false);
                image.add(rectangle_tmp);
                rectangle_tmp.setBounds(x-hauteur/2, y-longueur/2, hauteur+hauteur/2, longueur+longueur/2);

                liste_Rectangle.add(rectangle_tmp);

                updateObservateur();
            }

        }
    }

    class Change_color_button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (cercle_selected != null) {
                cercle_selected.reset_color();
                cercle_selected.rotate_form();
                cercle_selected = null;
            }
            if (rectangle_selected != null) {
                rectangle_selected.reset_color();
                rectangle_selected.rotate_form();
                rectangle_selected = null;

            }
        }
    }

    class Erase_button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (cercle_selected != null) {
                image.remove(cercle_selected);
                //cercle_selected.rotate_form();
                cercle_selected = null;
                updateObservateur();
            }
            if (rectangle_selected != null) {
                image.remove(rectangle_selected);
                rectangle_selected = null;
                updateObservateur();
            }
        }
    }

    class Erase_all_button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for (Cercle tmp : liste_Cercle) {
                image.remove(tmp);
            }
            for (Rectangle tmp : liste_Rectangle) {
                image.remove(tmp);
            }
            updateObservateur();
        }
    }




    public void mouseClicked(MouseEvent event) {
        //Méthode appelée lors du clic de souris
        if (event.getY() > 100) {
            if (cercle_selected == null && rectangle_selected == null) {
                mouse_click_x = event.getX();
                mouse_click_y = event.getY();
                label.setText("X : " + mouse_click_x + " Y : " + mouse_click_y);

                form_selection(mouse_click_x, mouse_click_y);
                updateObservateur();
            } else {
                form_selection(mouse_click_x, mouse_click_y);
            }
        } else {
            System.out.println("Y : " + event.getY());
        }

    }


    public boolean form_selection (int X, int Y) {
        if (cercle_selected == null && rectangle_selected == null) {
            for (Cercle tmp : liste_Cercle) {
                if (tmp.selected(X, Y)) {
                    cercle_selected = tmp;
                    return true;
                }
            }
            for (Rectangle tmp : liste_Rectangle) {
                if (tmp.selected(X, Y)) {
                    rectangle_selected = tmp;
                    return true;
                }
            }
            cercle_selected = null;
            rectangle_selected = null;
            return false;
        } else {
            if (cercle_selected != null) {
                cercle_selected.reset_color();
                cercle_selected = null;
            }
            if (rectangle_selected != null) {
                rectangle_selected.reset_color();
                rectangle_selected = null;
            }
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

    public class ImagePanel extends JPanel{

        private BufferedImage mur;
        public ImagePanel() {
            try {
                mur = ImageIO.read(new File("images/mur2.png"));
            } catch (IOException ex) {
                Logger.getLogger(Panel.class.getName()).log(
                        Level.SEVERE, null, ex);
            }
        }

        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(mur, 0, 0, this);
            loadFont(g);
        }

    }

    private void loadFont(Graphics g) {

        try{


            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            Font font = Font.createFont(Font.TRUETYPE_FONT,new File("Ro.ttf"));
            ge.registerFont(font);
            font = font.deriveFont(Font.TRUETYPE_FONT,60);
            Graphics2D g2d = (Graphics2D) g.create();

            g2d.setColor(Color.black);
            g2d.setFont(font);
            g2d.drawString(" Dessine moi un mouton...", 50,60);
            font = font.deriveFont(Font.TRUETYPE_FONT,40);
            g2d.setColor(Color.black);
            g2d.setFont(font);


        }catch(IOException e){

        }catch(FontFormatException e){

        }catch(IllegalArgumentException e){

        }
    }
}


