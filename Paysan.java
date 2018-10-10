package org.centrale.projet.objet;

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
 * @param p 
 */
    public Paysan(String nom, int pV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, int ptP, Point2D p){
        super(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, ptP,  p);
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