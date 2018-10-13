
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author Thibault
 */
public class Nourriture extends Objet{
    
    /**
     * 
     */
    private int quantite;
    /**
     *Atttibut qui précise la caractèristique impacté par le bonus/malus
     */
    private String caractéristique;
    /**
    * Indique le nombre de tour que l'objet fait effet
    */
    private int duree;
    
    
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

    public String getCaractéristique() {
        return caractéristique;
    }

    public int getDuree() {
        return duree;
    }

    public void setCaractéristique(String caractéristique) {
        this.caractéristique = caractéristique;
    }

    public void setDuree(int duree) {
        this.duree = duree;
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
