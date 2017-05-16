package com.sdz.observer;

import com.sdz.model.Dessin;
import com.sdz.model.Image;

import javax.swing.*;
import java.util.ArrayList;


public interface Bouton_Observable {
    public void addObservateur(Bouton_Observateur obs);
    public void updateObservateur();
    public void delObservateur();
}
