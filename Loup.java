package org.centrale.projet.objet;

import java.util.Random;

/**
 * Cette classe représente les monstres de type Loup (sous-classe de Monstre)
 * @author Mathilde
 */
public class Loup extends Monstre implements Combattant {

/**
 * Constructeur de Loup qui prend en parametre tous les valeurs de tous les attributs de cette classe et de ses super-classes
 * @param pV
 * @param pA
 * @param pP
 * @param dA
 * @param ptP
 * @param p 
 */
    public Loup(int pV, int pA, int pP, int dA, int ptP, Point2D p){
        super(pV, pA, pP, dA, ptP, p);
    }

/**
 * Constructeur de Loup qui construit un loup en fonction des attributs du loup mis en paramètre
 * @param l 
 */
    public Loup(Loup l){
        super(l);
    }
    
/**
 * Constructeur de Loup par défault qui initialise les attributs à des valeurs choisies par défault. 
 */
    public Loup(){
        super();
        Random intAlea= new Random();
        int i = 20+intAlea.nextInt(15);
        setDegAtt(i);
    }
    
/**
 * Méthode permettant d'afficher un Loup
 */
    public void affiche(){
        System.out.println("C'est un monstre de type Loup :"+
                "; \nPoints de vie: "+getPtVie()+
                "; \nPourcentage d'attaque : "+getPourcentageAtt()+
                "; \nPourcentage parade : "+getPourcentagePar()+
                "; \nDegats d'attaque : "+getDegAtt()+
                "; \nPoints de parade : "+getPtPar()+
                ";");
        getPos().affiche();
    }
 

 /**
     * Méthode permettant de combattre une créature par un combat corps à corps (la créature attaquée
     * doit être sur une case adjacente au loup qui attaque)
     * 
     * @param c créature attaquée
     */
public void combattre(Creature c){
    
        //Combat corps à corps : on met 1.42 pour permettre d'attaquer les protagonistes sur les cases en diagonale
        if (this.getPos().distance(c.getPos()) <1.42 ){
            Random lanceDe = new Random();
            int RandAtt = lanceDe.nextInt(101);
            if (RandAtt <= this.getPourcentageAtt()) {
                System.out.println("Attaque réussie!");
                int RandDef = lanceDe.nextInt(101);
                if (RandDef > c.getPourcentagePar()){
                    System.out.println("Parade ratée! Le défenseur perd "+this.getDegAtt()+" points de vie");
                    c.setPtVie(c.getPtVie()-this.getDegAtt());
                }
                else {
                    int res = this.getDegAtt()-c.getPtPar();
                    if (res>0) {
                        System.out.println("Parade réussie! Le défenseur perd "+res +" points de vie");
                        c.setPtVie(c.getPtVie()-res);
                    }
                    else {
                        System.out.println("Parade réussie! Le défenseur ne reçoit aucun dégât");
                    }
                }
            }
            else {
                System.out.println("Attaque ratée!");
            }
        }
        if(c.getPtVie()<1){
                    System.out.println("Le défenseur est mort");
                }
    }

}