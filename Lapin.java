package org.centrale.projet.objet;

/**
 * Cette classe représente les monstres de type Lapin (sous-classe de Monstre)
 * @author Mathilde
 */
public class Lapin extends Monstre{

/**
 * Constructeur de Lapin qui prend en parametre tous les valeurs de tous les attributs de cette classe et de ses super-classes
 * @param pV
 * @param pA
 * @param pP
 * @param dA
 * @param ptP
 * @param p 
 */
    public Lapin(int pV, int pA, int pP, int dA, int ptP, Point2D p){
        super(pV, pA, pP, dA, ptP, p);
    }
    
/**
 * Constructeur de Lapin qui construit un lapin en fonction des attributs du lapin mis en paramètre
 * @param l 
 */
    public Lapin(Lapin l){
        super(l);
    }
    
/**
 * Constructeur de Lapin par défault qui initialise les attributs à des valeurs choisies par défault. 
 */
    public Lapin(){
        super();
    }

/**
 * Méthode permettant d'afficher un Lapin
 */
    public void affiche(){
        System.out.println("C'est un monstre de type Lapin :"+
                "; \nPoints de vie: "+getPtVie()+
                "; \nPourcentage d'attaque : "+getPourcentageAtt()+
                "; \nPourcentage parade : "+getPourcentagePar()+
                "; \nDegats d'attaque : "+getDegAtt()+
                "; \nPoints de parade : "+getPtPar()+
                ";");
        getPos().affiche();
    }
    
}