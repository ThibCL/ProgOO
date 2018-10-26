
package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

/**
 *
 * @author Thibault
 */
public class Nourriture extends Objet{
    
    
    /**
     *Atttibut qui précise la caractèristique impacté par le bonus/malus
     * 1 correspond à PourcentageAtt ; 
     * 2 correspond à PourcentagePar ; 
     * 3 correspond à DegAtt ; 
     * 4 correspond à PtPar ;
     * 5 correspond à PourcentageResisMag ; 
     * 6 correspond à DistAttMax ;
     * 7 correspond à PourcentageMag ; 
     * 8 correspond à DegMag ; 
     */
    private int caracteristique;
    
    /**
    * Indique le nombre de tour que l'objet fait effet
    */
    private int duree;
    
    /**
     * Nombre de point ajouté ou enlevé au personnage
     */
    private int ptEffet;
    /**
     * Represente l'etat activé (1) ou non (0)de la nourriture
     */
    private int etat;

    public Nourriture(Point2D pos, int caracteristique, int duree, int ptEffet, int etat) {
        super(pos);
        this.caracteristique = caracteristique;
        this.duree = duree;
        this.ptEffet = ptEffet;
        this.etat = etat;
    }
    

    public Nourriture() {
        super();
        Random nbralea=new Random();
        this.caracteristique=nbralea.nextInt(9)+1;
        this.duree=nbralea.nextInt(7)+3;
            switch(this.caracteristique){//a voir pour les malus
                case 1: case 2: case 5: case 6: case 8:
                    this.ptEffet=nbralea.nextInt(11)+5;
                    break;
                case 3: case 4: case 9:
                    this.ptEffet=nbralea.nextInt(6)+1;
                    break;
                case 7:
                    this.ptEffet=nbralea.nextInt(3)+1;
                    break;
            }
        int i=nbralea.nextInt(2);
        if (i==0){
            ptEffet=-ptEffet;
        }
        this.etat=0;
    }
    
    /**
     * Constructeur de Nourriture à partir d'une ligne de la sauvegarde
     * @param element ligne de la sauvegarde comportant les caractéristiques de la nourriture à créer
     */
    public Nourriture(String element){
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);
        tokenizer.nextToken();//on passe le type de l'objet
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        setPos(new Point2D(x,y));
        caracteristique=Integer.parseInt(tokenizer.nextToken());
        duree= Integer.parseInt(tokenizer.nextToken());
        ptEffet=Integer.parseInt(tokenizer.nextToken());
        etat=Integer.parseInt(tokenizer.nextToken());
    }

    public int getEtat() {
        return etat;
    }

    public void setEtat(int etat) {
        this.etat = etat;
    }

    public int getPtEffet() {
        return ptEffet;
    }

    public void setPtEffet(int ptEffet) {
        this.ptEffet = ptEffet;
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
     * Méthode permettant d'afficher la nourriture de manière globale (ne révèle pas son effet bonus ou malus) 
     */
    public void affiche(){
        System.out.print("Nourriture: ");
        System.out.print("Modifie la caracteristique ");
        this.caracteristiques();
        System.out.println();
    }
    
    /**
    * Méthode permettant d'afficher l'effet de la nourriture ramassée
    **/
    public void afficheEffet(){
        System.out.print("Nourriture: ");
        if (getPtEffet()<0){
            System.out.print("Malus! Diminue la caracteristique ");
        }
        else {
            System.out.print("Bonus! Augmente la caracteristique ");
        }
        this.caracteristiques();
        System.out.print(" de "+this.getPtEffet()+" point(s)");
        System.out.println(" pour une durée de "+ this.getDuree()+" tour(s)");
    }
    
    /**
    * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
    */
    public void caracteristiques(){
        switch (getCaracteristique()){
            case(1):
                System.out.print("Pourcentage d'Attaque");
                break;
            case(2):
                System.out.print("Pourcentage de Parade");
                break;
            case(3):
                System.out.print("Dégats d'Attaque");
                break;
            case(4):
                System.out.print("Points de Parade");
                break;
            case(5):
                System.out.print("Pourcentage de résitance à la magie");
                break;
            case(6):
                System.out.print("Distance maximale d'attaque");
                break;
            case(7):
                System.out.print("Pourcentage de Magie");
                break;
            case(8):
                System.out.print("Dégâts de Magie");
                break;
        }
    }
    
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write(Integer.toString(this.caracteristique)+ " ");
        writer.write(Integer.toString(this.duree)+ " ");
         writer.write(Integer.toString(this.ptEffet)+ " ");
        writer.write(Integer.toString(this.etat)+ "");
       
        
    }
    
    
    public String getAffichage(){        
        return "Nou";               
    }
}
