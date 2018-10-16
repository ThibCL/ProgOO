
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
    /**
     * Represente l'etat activé ou non de la nourriture
     */
    private int etat;

    public Nourriture(int caractéristique, int duree, int pteffet, int etat, Point2D pos) {
        super(pos);
        this.caractéristique = caractéristique;
        this.duree = duree;
        this.pteffet = pteffet;
        this.etat = etat;
    }

    

    public Nourriture() {
        super();
        Random nbralea=new Random();
        this.caractéristique=nbralea.nextInt(9)+1;
        this.duree=nbralea.nextInt(7)+3;
            switch(this.caractéristique){//a voir pour les malus
                case 1: case 2: case 5: case 6: case 8:
                    this.pteffet=nbralea.nextInt(11)+5;
                case 3: case 4: case 9:
                    this.pteffet=nbralea.nextInt(6)+1;
                case 7:
                    this.pteffet=nbralea.nextInt(3)+1;
            }
        this.etat=0;
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
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
        System.out.print("Nourriture: ");
        System.out.print("Augmente la caractéristique "+this.getCaractéristique());
        System.out.print(" de "+this.getPteffet()+" pt");
        System.out.println(" D'une duree de "+ this.getDuree()+" tour");
    }
    
    /**
    * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
    */
    public void caracteristiques(){
        
    }
    
}
