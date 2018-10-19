
package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 *
 * @author Thibault
 */
public class Nourriture extends Objet{
    
    
    /**
     *Atttibut qui précise la caractèristique impacté par le bonus/malus
     */
    private int caracteristique;
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

    public Nourriture(Point2D pos,int caracteristique, int duree, int pteffet, int etat) {
        super(pos);
        this.caracteristique = caracteristique;
        this.duree = duree;
        this.pteffet = pteffet;
        this.etat = etat;
    }

    

    public Nourriture() {
        super();
        Random nbralea=new Random();
        this.caracteristique=nbralea.nextInt(9)+1;
        this.duree=nbralea.nextInt(7)+3;
            switch(this.caracteristique){//a voir pour les malus
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
    

    public int getCaracteristique() {
        return caracteristique;
    }

    public int getDuree() {
        return duree;
    }

    public void setCaracteristique(int caracteristique) {
        this.caracteristique = caracteristique;
    }

    public void setDuree(int duree) {
        this.duree = duree;
    }
    
    /**
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){
        System.out.print("Nourriture: ");
        System.out.print("Augmente la caracteristique "+this.getCaracteristique());
        System.out.print(" de "+this.getPteffet()+" pt");
        System.out.println(" D'une duree de "+ this.getDuree()+" tour");
    }
    
    /**
    * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
    */
    public void caracteristiques(){
        
    }
    
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write(Integer.toString(this.caracteristique)+ " ");
        writer.write(Integer.toString(this.duree)+ " ");
         writer.write(Integer.toString(this.pteffet)+ " ");
        writer.write(Integer.toString(this.etat)+ " ");
       
        writer.newLine();
    }
}
