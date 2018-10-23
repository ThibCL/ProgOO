package org.centrale.projet.objet;

import java.io.*;
import java.util.Scanner;

/**
 * Classe pour les tests effectués lors de la séance dédiée au TP6
 * @author Mathilde
 */
public class TestSeance6 {
    public static void main(String[] args) throws IOException{
        
        Partie p = new Partie();
        SauvegardePartie testi =p.lancerPartie();
        p.getW().tourDeJeu(testi);/*
        World w=new World(5,10,10);
        w.creeMondeAlea();
        w.afficheMat();*/
              
    }   
}
