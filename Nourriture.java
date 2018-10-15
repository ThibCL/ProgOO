
package org.centrale.projet.objet;

import java.util.Random;

/**
 *
 * @author Thibault
 */
public class Nourriture extends Objet{
    
    
    /**
     *Atttibut qui précise la caractèristique impacté par le bonus/malus
     */
    private int caractéristique;
    /**
    * Indique le nombre de tour que l'objet fait effet
    */
    private int duree;
    
    /**
     * Nombre de point ajouté ou enlevé au personnage
     */
    private int pteffet;

    public Nourriture(int caractéristique, int duree, int pteffet, Point2D pos) {
        super(pos);
        this.caractéristique = caractéristique;
        this.duree = duree;
        this.pteffet = pteffet;
    }

    public Nourriture() {
        super();
        Random nbralea=new Random();
        this.caractéristique=nbralea.nextInt(9)+1;
        this.duree=3;//nbralea.nextInt(9)+1;
        this.pteffet=nbralea.nextInt(9)+1;
    }
    
    
    

    public int getPteffet() {
        return pteffet;
    }

    public void setPteffet(int pteffet) {
        this.pteffet = pteffet;
    }
    

    public int getCaractéristique() {
        return caractéristique;
    }

    public int getDuree() {
        return duree;
    }

    public void setCaractéristique(int caractéristique) {
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
