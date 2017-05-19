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

    private ArrayList<Ellipse> liste_Ellipse = new ArrayList<Ellipse>();
    private ArrayList<Rectangle> liste_Rectangle = new ArrayList<Rectangle>();
    private Ellipse ellipse_selected = null;
    private Rectangle rectangle_selected = null;

    private JPanel pan_dessin = new JPanel();   //ce sur quoi on va venir coller chaque dessin

    private ImagePanel image = new ImagePanel();
    private Observateur dessin_observer;

    private Button ellipse_button = new Button("Ellipse");
    private Button rectangle_button = new Button("Rectangle");
    private Button circle_button = new Button("Circle");
    private Button button_line = new Button("Line");
    private Button rotate_button = new Button("Rotate");
    private Button erase_button = new Button("Erase");
    private Button erase_all_button = new Button("Clear All");

    private Button translate_right_button = new Button(">");
    private Button translate_left_button = new Button("<");
    private Button translate_up_button = new Button("Up");
    private Button translate_down_button = new Button("Down");

    private JLabel label = new JLabel("Infos");
    private JSlider slide = new JSlider();
    private int mouse_click_x = 100;
    private int mouse_click_y = 150;

    public Dessin () {

        pan_dessin.setBounds(0, 0, 800, 525);
        //pan_dessin.setBackground(Color.green);
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

        //translation.setBackground(Color.green);
        translation.setBounds(0, 525, 150, 150);


        /*   Boutons    */
        JPanel south = new JPanel();
        south.setBackground(Color.white);
        south.add(circle_button);
        south.add(ellipse_button);
        south.add(rectangle_button);
        south.add(button_line);
        south.add(erase_button);
        south.add(rotate_button);
        south.add(erase_all_button);
        south.add(label);
        south.add(slide);
        south.add(translation);
        south.setBounds(0, 525, 800, 200);
        pan_dessin.add(south);

        circle_button.addActionListener(new Circle_Listener());
        ellipse_button.addActionListener(new Ellipse_Listener());
        rectangle_button.addActionListener(new Rectangle_Listener());
        rotate_button.addActionListener(new Rotate_Listener());
        erase_button.addActionListener(new Erase_button_Listener());
        erase_all_button.addActionListener(new Erase_all_button_Listener());
        button_line.addActionListener(new Line_Listener());

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


    class Ellipse_Listener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected == null && rectangle_selected == null) {
                label.setText("Ellipse");

                System.out.println("Ajout ellipse!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Ellipse ellipse_tmp = new Ellipse(x, y, hauteur, longueur, true);
                //ellipse_tmp.setBackground(Color.cyan);
                ellipse_tmp.setOpaque(false);

                //ellipse_tmp.setOpaque(false);
                image.add(ellipse_tmp);
                //pan_dessin.add(ellipse_tmp);

                ellipse_tmp.setX_panel(x-hauteur/2);
                ellipse_tmp.setY_panel(y-longueur/2);
                ellipse_tmp.setH_panel(hauteur+hauteur/2);
                ellipse_tmp.setW_panel(longueur+longueur/2);

                ellipse_tmp.setBounds(x-hauteur/2, y-longueur/2, hauteur+hauteur/2, longueur+longueur/2);

                liste_Ellipse.add(ellipse_tmp);

                updateObservateur();
            }

        }


    }

    class Rectangle_Listener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected == null && rectangle_selected == null) {
                label.setText("Rectangle");

                System.out.println("Ajout Rectangle!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Rectangle rectangle_tmp = new Rectangle(x, y, hauteur, longueur);
                //ellipse_tmp.setBackground(Color.cyan);

                rectangle_tmp.setOpaque(false);
                image.add(rectangle_tmp);

                rectangle_tmp.setX_panel(x-hauteur/2);
                rectangle_tmp.setY_panel(y-longueur/2);
                rectangle_tmp.setH_panel(hauteur+hauteur/2);
                rectangle_tmp.setW_panel(longueur+longueur/2);

                rectangle_tmp.setBounds(x-hauteur/2, y-longueur/2, hauteur+hauteur/2, longueur+longueur/2);

                liste_Rectangle.add(rectangle_tmp);

                updateObservateur();
            }

        }
    }

    class Line_Listener implements ActionListener {
        //Classe écoutant notre second bouton
        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected == null && rectangle_selected == null) {
                label.setText("Rectangle");

                System.out.println("Ajout Rectangle!!");

                int hauteur = 200, longueur = 2;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Rectangle line_tmp = new Rectangle(x, y, hauteur, longueur);
                //ellipse_tmp.setBackground(Color.cyan);

                line_tmp.setOpaque(false);
                image.add(line_tmp);

                line_tmp.setX_panel(x-hauteur/2);
                line_tmp.setY_panel(y-longueur/2);
                line_tmp.setH_panel(hauteur+hauteur/2);
                line_tmp.setW_panel(longueur+longueur/2);

                line_tmp.setBounds(x-hauteur/2, y-longueur/2, 800, 600);

                liste_Rectangle.add(line_tmp);

                updateObservateur();
            }

        }
    }

    class Circle_Listener implements ActionListener {

        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected == null && rectangle_selected == null) {
                label.setText("Cercle");

                System.out.println("Ajout Cercle!!");

                int hauteur = 50, longueur = 50;
                int x = mouse_click_x - hauteur/2;
                int y = mouse_click_y - longueur/2;

                Ellipse circle_tmp = new Ellipse(x, y, hauteur, longueur, false);
                //ellipse_tmp.setBackground(Color.cyan);
                circle_tmp.setOpaque(false);

                //ellipse_tmp.setOpaque(false);
                image.add(circle_tmp);
                //pan_dessin.add(ellipse_tmp);

                circle_tmp.setX_panel(x-hauteur/2);
                circle_tmp.setY_panel(y-longueur/2);
                circle_tmp.setH_panel(hauteur+hauteur/2);
                circle_tmp.setW_panel(longueur+longueur/2);

                circle_tmp.setBounds(x-hauteur/2, y-longueur/2, hauteur+hauteur/2, longueur+longueur/2);

                liste_Ellipse.add(circle_tmp);

                updateObservateur();
            }

        }


    }


    class Rotate_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (ellipse_selected != null) {
                ellipse_selected.rotate_form();
            }
            if (rectangle_selected != null) {
                rectangle_selected.rotate_form();
            }
        }
    }

    class Erase_button_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            if (ellipse_selected != null) {
                image.remove(ellipse_selected);
                ellipse_selected = null;
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

            for (Ellipse tmp : liste_Ellipse) {
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

            //ellipse_selected.setSize_up(((JSlider)event.getSource()).getValue());

            if (ellipse_selected != null) {
                int delta = ellipse_selected.getSize_up() - value;
                ellipse_selected.setSize_up(value);
                System.out.println("Delta : " + delta);

                if (delta < 0) {

                    System.out.println("Agrandissement de : " + (-1)*delta);
                    System.out.println("X : " + ellipse_selected.getPosX());
                    System.out.println("Y : " + ellipse_selected.getPosY());

                    ellipse_selected.resize_draw(true);

                } else {
                    System.out.println("Diminution de : " + (-1)*delta);
                    ellipse_selected.resize_draw(false);
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

            if (ellipse_selected != null) {
                ellipse_selected.translate(1);
                ellipse_selected.setBounds(ellipse_selected.getX_panel(), ellipse_selected.getY_panel(), ellipse_selected.getH_panel(), ellipse_selected.getW_panel());
            }
            if (rectangle_selected != null) {
                rectangle_selected.translate(1);
                rectangle_selected.setBounds(rectangle_selected.getX_panel(), rectangle_selected.getY_panel(), rectangle_selected.getH_panel(), rectangle_selected.getW_panel());
            }
            updateObservateur();

        }
    }

    class Translate_down_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected != null) {
                ellipse_selected.translate(2);
                ellipse_selected.setBounds(ellipse_selected.getX_panel(), ellipse_selected.getY_panel(), ellipse_selected.getH_panel(), ellipse_selected.getW_panel());
            }
            if (rectangle_selected != null) {
                rectangle_selected.translate(2);
                rectangle_selected.setBounds(rectangle_selected.getX_panel(), rectangle_selected.getY_panel(), rectangle_selected.getH_panel(), rectangle_selected.getW_panel());
            }
            updateObservateur();
        }
    }

    class Translate_right_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected != null) {
                ellipse_selected.translate(3);
                ellipse_selected.setBounds(ellipse_selected.getX_panel(), ellipse_selected.getY_panel(), ellipse_selected.getH_panel(), ellipse_selected.getW_panel());
            }
            if (rectangle_selected != null) {
                rectangle_selected.translate(3);
                rectangle_selected.setBounds(rectangle_selected.getX_panel(), rectangle_selected.getY_panel(), rectangle_selected.getH_panel(), rectangle_selected.getW_panel());
            }
            updateObservateur();
        }
    }

    class Translate_left_Listener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {

            if (ellipse_selected != null) {
                ellipse_selected.translate(4);
                ellipse_selected.setBounds(ellipse_selected.getX_panel(), ellipse_selected.getY_panel(), ellipse_selected.getH_panel(), ellipse_selected.getW_panel());
            }
            if (rectangle_selected != null) {
                rectangle_selected.translate(4);
                rectangle_selected.setBounds(rectangle_selected.getX_panel(), rectangle_selected.getY_panel(), rectangle_selected.getH_panel(), rectangle_selected.getW_panel());
            }
            updateObservateur();
        }
    }




    public void mouseClicked(MouseEvent event) {
        //Méthode appelée lors du clic de souris
        if (event.getY() > 100) {
            if (ellipse_selected == null && rectangle_selected == null) {
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
        if (ellipse_selected == null && rectangle_selected == null) {
            for (Ellipse tmp : liste_Ellipse) {
                if (tmp.selected(X, Y)) {
                    slide.setValue(12);
                    ellipse_selected = tmp;
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
            ellipse_selected = null;
            rectangle_selected = null;
            return false;
        } else {
            if (ellipse_selected != null) {
                ellipse_selected.reset_color();
                ellipse_selected = null;
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


