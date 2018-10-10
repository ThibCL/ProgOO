package org.centrale.projet.objet;
import java.util.Random;

/**
 * Cette classe représente les personnages de type guerrier (sous-classe de Personnage)
 * @author Mathilde
 */
public class Guerrier extends Personnage implements Combattant {

/**
 * Constucteur de Guerrier qui prend en parametre la valeur de tous les attributs de cette classe et ses super-classes
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
    public Guerrier(String nom, int pV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, int ptP, Point2D p){
        super(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, ptP,  p);
    }
    
/**
 * Constructeur de Guerrier qui construit un guerrier en fonction des attributs du guerrier mis en paramètre
 * @param g 
 */
    public Guerrier(Guerrier g){
        super(g);
    }

/**
 * Constructeur de Guerrier par défault qui initialise les attributs à des valeurs choisies par défault. A modifier par la suite pour mettre
 * des valeurs aléatoires
 */
    public Guerrier(){
        super();
    }
    
/**
 * Méthode permettant d'afficher un Guerrier
 */
public void affiche(){
    System.out.println("C'est un personnage de type Guerrier :");
    System.out.println("Nom : "+getNom() +
                "; \nPoints de vie: "+getPtVie()+
                "; \nPoints Mana : "+getPtMana()+
                "; \nPourcentage de magie : "+getPourcentageMag()+
                "; \nPourcentage de résistance à la magie : "+getPourcentageResisMag()+
                "; \nDegré de magie : "+getDegMag()+
                "; \nDistance maximale d'attaque : "+getDistAttMax()+
                "; \nPourcentage d'attaque : "+getPourcentageAtt()+
                "; \nPourcentage parade : "+getPourcentagePar()+
                "; \nDégats d'attaque : "+getDegAtt()+
                "; \nPoints de parade : "+getPtPar()+
                ";");
        getPos().affiche();
}
    
 /**
     * Méthode permettant de combattre une créature par un combat corps à corps (la créature attaquée
     * doit être sur une case adjacente à la créature qui attaque. 
     * @param c créature attaquée
     */

public void combattre(Creature c){
        if (this.getPos().distance(c.getPos()) == 1 ){
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
    }

}