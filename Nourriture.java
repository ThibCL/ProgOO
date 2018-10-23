
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
     * 1 correspond à
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
                case 3: case 4: case 9:
                    this.ptEffet=nbralea.nextInt(6)+1;
                case 7:
                    this.ptEffet=nbralea.nextInt(3)+1;
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
    
    
    
    
    public int getPteffet() {
        return ptEffet;
    }

    public void setPteffet(int ptEffet) {
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
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){
        System.out.print("Nourriture: ");
        System.out.print("Augmente la caracteristique "+this.getCaracteristique());
        System.out.print(" de "+this.getPteffet()+" pt");
        System.out.println(" pour une duree de "+ this.getDuree()+" tour");
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
         writer.write(Integer.toString(this.ptEffet)+ " ");
        writer.write(Integer.toString(this.etat)+ "");
       
        
    }
    
    
    public String getAffichage(){        
        return "Nou";               
    }
}
