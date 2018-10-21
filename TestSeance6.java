package org.centrale.projet.objet;

import java.io.IOException;

/**
 * Classe pour les tests effectués lors de la séance dédiée au TP6
 * @author Mathilde
 */
public class TestSeance6 {
    public static void main(String[] args) throws IOException{
        World w = new World(2,10,10);
        w.creeMondeAlea();
        w.affiche();
        SauvegardePartie s= new SauvegardePartie("Sauvegarde");
        s.sauvegarderPartie(w);
        ChargementPartie partie = new ChargementPartie("Sauvegarde.txt");
        World w2 = partie.chargerPartie();
        w2.affiche();
    }   
}
