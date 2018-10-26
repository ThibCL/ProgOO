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
       

       /*//nuage qui n'est pas sur un objet
        NuageToxique n1 = new NuageToxique("NuageToxique 0 3 9 67 0");
        n1.affiche();
       
        System.out.println();
        
        //nuage placé sur un objet de type Soin
        NuageToxique n2 = new NuageToxique("NuageToxique 6 9 9 70 1 [Soin 9 6 16]");
        n2.affiche();*/

    }   
}
