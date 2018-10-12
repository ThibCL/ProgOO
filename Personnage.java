package org.centrale.projet.objet;

/**
 * Classe qui est une super classe de tous les personnages possibles tels que les archers, les paysans... 
 * (sous-classe de Creature)
 * @author Mathilde
 */
public abstract class Personnage extends Creature {
 
/**
 * Represente le nom du personnage
 */
    private String nom;   
    
/**
 * Represente le nombre de points de mana du personnage pour les personnages Magiques 
 */
    private int ptMana;
/**
 * Pourcentage d'attaque magique pour les personnages Magiques 
 */
    private int pourcentageMag;
    
/**
 * Pourcentage de résistence à la magie
 */
    private int pourcentageResisMag;
/**
 * Nombre de degats infligés par une attaque magique pour les personnages Magiques 
 */
    private int degMag;
/**
 * Portée maximale de l'attaque magique pour les personnages Magiques 
 */
    private double distAttMax;
    
/**
 * Constructeur de Personnage qui prend en parametre toutes les valeurs des attributs a initialiser
 * @param nom Nom du personnage créé
 * @param ptV Points de vie du personnage créé
 * @param ptM Points de mana du personnage créé
 * @param pA Points d'attaque du personnage créé
 * @param pP Points de parade du personnage créé
 * @param pM Points de magie du personnage créé
 * @param rM Resistance à la magie du personnage créé
 * @param dA Dégats d'attaque du personnage créé
 * @param dM Dégats de magie du personnage créé
 * @param distM Distance maximale d'attaque du personnage créé
 * @param ptP Points de parade du personnage créé
 * @param p Poisition 2D du personnage créé
 */
    
    public Personnage(String nom, int ptV, int ptM, int pA, int pP, int pM, int rM, int dA, int dM, double distM, int ptP, Point2D p){
        super(ptV, pA, pP, dA, ptP, p);
        this.nom=nom;
        ptMana=ptM;
        pourcentageMag=pM;
        pourcentageResisMag=rM;
        degMag=dM;
        distAttMax=distM;
    }
    
    
/**
 * Constructeur de personnage qui initialise les attributs a partir des attributs du personnage mis en parametre
 * @param perso 
 */
    public Personnage(Personnage perso){
        super(perso);
        nom=perso.nom;
        ptMana=perso.ptMana;
        pourcentageMag=perso.pourcentageMag;
        pourcentageResisMag=perso.pourcentageResisMag;
        degMag=perso.degMag;
        distAttMax=perso.distAttMax;
    }
    
    //A améliorer avec Random
/**
 * Constructeur de personnage par default qui initialise les attributs a des valeurs choisies par default
 */
    public Personnage(){
        super();
        ptMana=0;
        pourcentageMag=0;
        pourcentageResisMag=40;
        degMag=0;
        distAttMax=1.42;
    }
    
    public String getNom(){
        return nom;
    }
    
    public void setNom(String value){
        nom=value;
    }
    
    public int getPtMana(){
        return ptMana;
    }
    
    public void setPtMana(int value){
        ptMana=value;
    }
    
    public int getPourcentageMag(){
        return pourcentageMag;
    }
    
    public void setPourcentageMag(int value){
        pourcentageMag=value;
    }
    
    public int getPourcentageResisMag(){
        return pourcentageResisMag;
    }
    
    public void setPourcentageResisMag(int value){
        pourcentageResisMag=value;
    }
    
    public int getDegMag(){
        return degMag;
    }
    
    public void setDegMag(int value){
        degMag=value;
    }
    
    public double getDistAttMax(){
        return distAttMax;
    }
    
    public void setDistAttMax(int value){
        distAttMax=value;
    }
    
    public abstract void affiche();
    

    
}



