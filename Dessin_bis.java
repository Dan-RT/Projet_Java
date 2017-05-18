package com.sdz.model;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

/**
 * Created by Daniel on 18/05/2017.
 */
public class Dessin_bis {

    /*      Liste image        */

    private ArrayList<JPanel> liste_Image = new ArrayList<JPanel>();
    private JButton next_bouton = new JButton("Contenu suivant");
    CardLayout cl = new CardLayout();
    //JPanel content = new JPanel();
    //Liste des noms de nos conteneurs pour la pile de cartes
    String[] listContent = {"CARD_1", "CARD_2", "CARD_3"};


    public Fenetre(){
        this.setTitle("CardLayout");
        this.setSize(300, 120);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);

        //On crée trois conteneurs de couleur différente
        JPanel card1 = new JPanel();
        card1.setBackground(Color.blue);
        JPanel card2 = new JPanel();
        card2.setBackground(Color.red);
        JPanel card3 = new JPanel();
        card3.setBackground(Color.green);

        JPanel boutonPane = new JPanel();
        JButton bouton = new JButton("Contenu suivant");

        //Définition de l'action du bouton
        bouton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                //Via cette instruction, on passe au prochain conteneur de la pile
                cl.next(content);
            }
        });

        JButton bouton2 = new JButton("Contenu par indice");
        //Définition de l'action du bouton2
        bouton2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent event){
                if(++indice > 2)
                    indice = 0;
                //Via cette instruction, on passe au conteneur correspondant au nom fourni en paramètre
                cl.show(content, listContent[indice]);
            }
        });

        boutonPane.add(bouton);
        boutonPane.add(bouton2);
        //On définit le layout
        content.setLayout(cl);
        //On ajoute les cartes à la pile avec un nom pour les retrouver
        content.add(card1, listContent[0]);
        content.add(card2, listContent[1]);
        content.add(card3, listContent[2]);

        this.getContentPane().add(boutonPane, BorderLayout.NORTH);
        this.getContentPane().add(content, BorderLayout.CENTER);
        this.setVisible(true);
    }

}












//On crée trois conteneurs de couleur différente

        for (int i = 0; i < 3; i++) {
        liste_Image.add(new Dessin.ImagePanel());
        if (i == 0) {
        liste_Image.get(0).setBackground(Color.green);
        } else if (i == 1) {
        liste_Image.get(0).setBackground(Color.blue);
        } else {
        liste_Image.get(0).setBackground(Color.yellow);
        }
        }

        next_bouton.addActionListener(new ActionListener(){
public void actionPerformed(ActionEvent event){
        //Via cette instruction, on passe au prochain conteneur de la pile
        cl.next(pan_dessin);
        }
        });

        //On définit le layout
        pan_dessin.setLayout(cl);

        //On ajoute les cartes à la pile avec un nom pour les retrouver
        for (int i = 0; i < 3; i++) {
        pan_dessin.add(liste_Image.get(i), listContent[i]);
        }
