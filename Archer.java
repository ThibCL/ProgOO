package org.centrale.projet.objet;

import java.util.Random;
import java.util.ArrayList;

/**
 * Cette classe représente les personnages de type archer (sous-classe de Personnage)
 * test pour github avec le nouveau nom de dossier 
 * @author Mathilde
 * 
 */
public class Archer extends Personnage implements Combattant {
    
    /**
     * Représente le nombre de flèches que détient l'archer 
     */
    private int nbFleches;
    
    /**
     * Constucteur de Archer qui prend en parametre la valeur de tous les attributs de cette classe et de ses super-classes
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
     * @param nbF 
     */
    public Archer (String nom, int pV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, int distMax, int ptP, ArrayList<Objet> s, Point2D p, int nbF){
        super(nom, pV, ptM, pA, pP, pM, rM, dA, dM, distMax, ptP, s, p);
        nbFleches=nbF;
    }
    
    /**
     * Constructeur de Archer qui construit un archer en fonction des attributs de l'archer mis en paramètre
     * @param a 
     */
    public Archer(Archer a){
        super(a);
        nbFleches=a.nbFleches;
    }
    
/**
 * Constructeur de Archer par défault qui initialise les attributs à des valeurs choisies par défault. A modifier par la suite pour mettre
 * des valeurs aléatoires
 */
    public Archer(){
        super();
        Random intAlea= new Random();
        int i = 10+intAlea.nextInt(20);
        setPourcentagePar(i);
        i = 5+intAlea.nextInt(5);
        setPtPar(i);
        i = 1+intAlea.nextInt(5);
        setDistAttMax(i);
        nbFleches=10;
    }
    
    public int getNbFleches(){
        return nbFleches;
    }
    
    public void setNbFleches(int fleches){
        nbFleches=fleches;
    }
    
/**
 * Méthode qui permet d'afficher tous les atttributs de l'archer
 */
    public void affiche(){
        System.out.println("C'est un personnage de type Archer :");
        System.out.println("Nom : "+getNom() +
                "; \nPoints de vie: "+getPtVie()+
                "; \nPourcentage de résistance à la magie : "+getPourcentageResisMag()+
                "; \nDistance maximale d'attaque : "+getDistAttMax()+
                "; \nPourcentage d'attaque : "+getPourcentageAtt()+
                "; \nPourcentage parade : "+getPourcentagePar()+
                "; \nDegats d'attaque : "+getDegAtt()+
                "; \nPoints de parade : "+getPtPar()+
                "; \nNombre de flèches : "+nbFleches+
                ";");
        getPos().affiche();
    }
    
    /**
     * Méthode permettant à l'archer de combattre une créature. L'archer peut combattre à distance ou non 
     * et inflige alors des dégats si il réussi son attaque et que la créature est à portée atteignable
     * @param c 
     */
    public void combattre(Creature c){
        
        //Combat corps à corps : on met 1.42 pour permettre d'attaquer les protagonistes sur les cases en diagonale
        if (this.getPos().distance(c.getPos()) < 1.42 ){
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

        //Combat à distance 
        else if ((this.getPos().distance(c.getPos()) >1.42 )&&(this.getPos().distance(c.getPos()) < this.getDistAttMax())&&((this.getNbFleches() > 0))){
            this.setNbFleches(this.getNbFleches()-1);
            Random lanceDe = new Random();
            int RandAtt = lanceDe.nextInt(101);
            if (RandAtt <= this.getPourcentageAtt()) {
                System.out.println("Attaque réussie! Le défenseur perd "+this.getDegAtt()+" points de vie");
                c.setPtVie(c.getPtVie()-this.getDegAtt());
            }
            else {
                System.out.println("Attaque ratée!");
            }
        }
    }
    
    public void ramasser(Objet o, World w){
        if (o instanceof Mana){
            System.out.println("L'archer ne peut pas ramasser de Potion Mana");
        }
        else if (o instanceof NuageToxique){
            System.out.println("C'est un nuage Toxique!");
        }
        else {
            getSac().add(o);
            int x = o.getPos().getX();
            int y = o.getPos().getY();
            w.getMatMonde()[x][y].setObjet(null);
            w.getlObjet().remove(o);
        }
    }
}