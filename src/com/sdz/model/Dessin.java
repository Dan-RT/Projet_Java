package com.sdz.model;

import com.sdz.observer.Bouton_Observateur;
import com.sdz.observer.Observable;
import com.sdz.observer.Observateur;

import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;


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

    private ArrayList<Cercle> liste_Cercle = new ArrayList<Cercle>();
    private ArrayList<Rectangle> liste_Rectangle = new ArrayList<Rectangle>();
    private Cercle cercle_selected = null;
    private Rectangle rectangle_selected = null;

    private JPanel pan_dessin = new JPanel();   //ce sur quoi on va venir coller chaque dessin

    //private JPanel  = new JPanel();
    private ImagePanel image = new ImagePanel();
    private Observateur dessin_observer;

    private Button bouton_1 = new Button("Ellipse");
    private Button bouton_2 = new Button("Rectangle");
    private Button button_line = new Button("Line");
    private Button change_color_button = new Button("Rotate");
    private Button erase_button = new Button("Erase");
    private Button erase_all_button = new Button("Clear All");

    private Button translate_right_button = new Button(">");
    private Button translate_left_button = new Button("<");
    private Button translate_up_button = new Button("Up");
    private Button translate_down_button = new Button("Down");

    private JLabel label = new JLabel("Le JLabel");
    private JSlider slide = new JSlider();





    private int mouse_click_x = 100;
    private int mouse_click_y = 150;

    public Dessin () {

        pan_dessin.setBounds(0, 0, 800, 525);
        pan_dessin.setBackground(Color.green);
        pan_dessin.setLayout(null);


        image.setBounds(0, 0, 800, 525);
        image.setLayout(null);
        pan_dessin.add(image);

        slide.setMaximum(25);
        slide.setMinimum(0);
        slide.setValue(12);
        slide.setPaintTicks(true);
        slide.setPaintLabels(true);

        slide.addChangeListener(new Slider_Listener());

        System.out.println("Dessin()");


        /*   Translation    */
        JPanel translation = new JPanel();


        translation.add(translate_up_button, BorderLayout.NORTH);
        translation.add(translate_down_button, BorderLayout.SOUTH);
        translation.add(translate_left_button, BorderLayout.WEST);
        translation.add(translate_right_button, BorderLayout.EAST);

        translation.setBackground(Color.green);
        translation.setBounds(0, 525, 150, 150);



        /*   Boutons    */
        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.add(bouton_1);
        south.add(bouton_2);
        south.add(button_line);
        south.add(erase_button);
        south.add(change_color_button);
        south.add(erase_all_button);
        south.add(label);
        south.add(slide);
        south.add(translation);
        south.setBounds(0, 525, 800, 200);
        pan_dessin.add(south);

        bouton_1.addActionListener(new Cercle_Listener());
        bouton_2.addActionListener(new Rectangle_Listener());
        change_color_button.addActionListener(new Rotate_Listener());
        erase_button.addActionListener(new Erase_button_Listener());
        erase_all_button.addActionListener(new Erase_all_button_Listener());

        translate_up_button.addActionListener(new Translate_up_Listener());
        translate_down_button.addActionListener(new Translate_down_Listener());
        translate_right_button.addActionListener(new Translate_right_Listener());
        translate_left_button.addActionListener(new Translate_left_Listener());

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

                //cercle_tmp.setOpaque(false);
                image.add(cercle_tmp);
                //pan_dessin.add(cercle_tmp);

                cercle_tmp.setX_panel(x-hauteur/2);
                cercle_tmp.setY_panel(y-longueur/2);
                cercle_tmp.setH_panel(hauteur+hauteur/2);
                cercle_tmp.setW_panel(longueur+longueur/2);

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

    class Line_Listener implements ActionListener {

        //Classe écoutant notre second bouton
        public void actionPerformed(ActionEvent arg0) {

            if (cercle_selected == null && rectangle_selected == null) {
                label.setText("Rectangle");

                System.out.println("Ajout Rectangle!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Line rectangle_tmp = new Line (x, y, hauteur, longueur);
                //cercle_tmp.setBackground(Color.cyan);

                rectangle_tmp.setOpaque(false);
                image.add(rectangle_tmp);
                rectangle_tmp.setBounds(x-hauteur/2, y-longueur/2, hauteur+hauteur/2, longueur+longueur/2);


                updateObservateur();
            }

        }
    }

    class Rotate_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (cercle_selected != null) {
                cercle_selected.reset_color();
                cercle_selected.rotate_form();
            }
            if (rectangle_selected != null) {
                rectangle_selected.reset_color();
                rectangle_selected.rotate_form();
            }
        }
    }

    class Erase_button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (cercle_selected != null) {
                image.remove(cercle_selected);
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

    class Slider_Listener implements ChangeListener {

        public void stateChanged(ChangeEvent event){


            label.setText("Valeur actuelle : " + ((JSlider)event.getSource()).getValue());
            int value = ((JSlider)event.getSource()).getValue();

            System.out.println("Valeur : " + value);

            //cercle_selected.setSize_up(((JSlider)event.getSource()).getValue());

            if (cercle_selected != null) {
                int delta = cercle_selected.getSize_up() - value;
                cercle_selected.setSize_up(value);
                System.out.println("Delta : " + delta);

                if (delta < 0) {

                    System.out.println("Agrandissement de : " + (-1)*delta);
                    System.out.println("X : " + cercle_selected.getPosX());
                    System.out.println("Y : " + cercle_selected.getPosY());

                    cercle_selected.resize_draw(true);

                } else {
                    System.out.println("Diminution de : " + (-1)*delta);$
                    cercle_selected.resize_draw(false);
                }

                updateObservateur();
            }
            if (rectangle_selected != null) {
                int delta = rectangle_selected.getSize_up() - value;
                rectangle_selected.setSize_up(value);
                System.out.println("Delta : " + delta);


                if (delta < 0) {

                    System.out.println("Agrandissement de : " + (-1)*delta);
                    System.out.println("X : " + rectangle_selected.getPosX());
                    System.out.println("Y : " + rectangle_selected.getPosY());

                    rectangle_selected.resize_draw(true);

                } else {
                    System.out.println("Diminution de : " + (-1)*delta);

                    rectangle_selected.resize_draw(false);
                }
                updateObservateur();
            }
        }

    }


    class Translate_up_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for (Cercle tmp : liste_Cercle) {
                tmp.translate(1);
                tmp.setBounds(tmp.getX_panel(), tmp.getY_panel(), tmp.getH_panel(), tmp.getW_panel());
            }
            for (Rectangle tmp : liste_Rectangle) {

            }
            updateObservateur();
        }
    }

    class Translate_down_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for (Cercle tmp : liste_Cercle) {
                tmp.translate(2);
                tmp.setBounds(tmp.getX_panel(), tmp.getY_panel(), tmp.getH_panel(), tmp.getW_panel());
            }
            for (Rectangle tmp : liste_Rectangle) {

            }
            updateObservateur();
        }
    }

    class Translate_right_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for (Cercle tmp : liste_Cercle) {
                tmp.translate(3);
                tmp.setBounds(tmp.getX_panel(), tmp.getY_panel(), tmp.getH_panel(), tmp.getW_panel());
            }
            for (Rectangle tmp : liste_Rectangle) {

            }
            updateObservateur();
        }
    }

    class Translate_left_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            for (Cercle tmp : liste_Cercle) {
                tmp.translate(4);
                tmp.setBounds(tmp.getX_panel(), tmp.getY_panel(), tmp.getH_panel(), tmp.getW_panel());
            }
            for (Rectangle tmp : liste_Rectangle) {

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
                    slide.setValue(12);
                    cercle_selected = tmp;
                    return true;
                }
            }
            for (Rectangle tmp : liste_Rectangle) {
                if (tmp.selected(X, Y)) {

                    slide.setValue(12);
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


