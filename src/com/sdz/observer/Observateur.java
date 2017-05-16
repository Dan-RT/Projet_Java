package com.sdz.observer;

import com.sdz.model.Dessin;
import com.sdz.model.Image;

import javax.swing.*;
import java.util.ArrayList;


public interface Observateur {
    public void update(JPanel pan_dessin);
}

