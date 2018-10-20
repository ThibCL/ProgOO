package org.centrale.projet.objet;

/**
 * Classe pour les tests effectués lors de la séance dédiée au TP6
 * @author Mathilde
 */
public class TestSeance6 {
    public static void main(String[] args){
        ChargementPartie partie = new ChargementPartie("Sauvegarde-WoE2.txt");
        World w = partie.chargerPartie();
     
    }   
}
