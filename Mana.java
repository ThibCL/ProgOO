package org.centrale.projet.objet;

import java.util.Random;

/**
 * Cette classe est une Potion de type Mana, c'est un objet permettant de redonner les points Mana aux créatures du monde l'utilisant
 * @author Thibault
 */
public class Mana extends Potion{
    
    public Mana(Point2D pos, int ptRecup){
        super(pos,ptRecup);
    }
    public Mana(){
        super();
    }
    
    /**
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){

        System.out.println("Potion de Mana ("+getPtRecup()+" pts Mana)");
    }
    
        /**
     * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
     */
    public void caracteristiques(){
        System.out.println("Cette potion permet de récupérer "+getPtRecup()+" points de Mana");
    }
}
