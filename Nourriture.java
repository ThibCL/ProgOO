
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author Thibault
 */
public class Nourriture extends Objet{
    
    private int quantite;
    
    public Nourriture(Point2D pos, int q){
        super(pos);
        quantite=q;
    }
    
    public Nourriture(){
        super();
        Random intAlea= new Random();
        int i = 1+intAlea.nextInt(3);
        quantite=i;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public int getQuantite() {
        return quantite;
    }
    
    /**
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){
        System.out.println("Nourriture");
    }
    
    /**
    * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
    */
    public void caracteristiques(){
        
    }
}
