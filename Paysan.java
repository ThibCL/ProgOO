package org.centrale.projet.objet;

import java.util.Random;
import java.util.ArrayList;

/**
 * Cette classe permet de représenter un personnage de type Paysan (sous-classe de Personnage)
 * @author Mathilde
 */
public class Paysan extends Personnage {
    
/**
 * Constucteur de Paysan qui prend en parametre la valeur de tous les attributs de cette classe et de ses super-classes
 * @param nom
 * @param pV
 * @param ptM
 * @param pA
 * @param pP
 * @param pM
 * @param rM
 * @param dA
 * @param dM
 * @param distMax
 * @param ptP
 * @param s 
 * @param p 
 */
    public Paysan(String nom, int pV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, int ptP, ArrayList<Objet> s, Point2D p){
        super(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, ptP, s,  p);
    }
    
/**
 * Constructeur de Paysan qui construit un paysan en fonction des attributs du paysan mis en paramètre
 * @param p 
 */
    public Paysan(Paysan p){
        super(p);
    }
    
/**
 * Constructeur de Paysan par défault qui initialise les attributs à des valeurs choisies par défault
 */
    public Paysan(){
        super();
        Random intAlea= new Random();
        int i = 50+intAlea.nextInt(25);
        setPourcentageAtt(0); //le paysan ne peut pas attaquer 
        setDegAtt(0);
        i = 35+intAlea.nextInt(30); //le paysan ne sait pas trop se défendre
        setPourcentagePar(50);
        i = 5+intAlea.nextInt(15);
        setPtPar(i);
    }
    
    /**
     * Méthode permettant d'afficher un Paysan
     */
    public void affiche(){
        System.out.println("C'est un personnage de type Paysan :"
                +"\nNom : "+getNom() +
                "; \nPoints de vie: "+getPtVie()+              
                "; \nPourcentage de résistance à la magie : "+getPourcentageResisMag()+
                "; \nDistance maximale d'attaque : "+getDistAttMax()+
                "; \nPourcentage d'attaque : "+getPourcentageAtt()+
                "; \nPourcentage parade : "+getPourcentagePar()+
                "; \nDegats d'attaque : "+getDegAtt()+
                "; \nPoints de parade : "+getPtPar()+
                ";");
        getPos().affiche();
    }
    
}