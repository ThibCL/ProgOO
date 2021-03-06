package org.centrale.projet.objet;

import java.util.Random;
import java.util.ArrayList;

/**
 * Cette classe représente les personnages de type guerrier (sous-classe de
 * Personnage)
 *
 * @author Mathilde
 */
public class Guerrier extends Personnage implements Combattant {

    /**
     * Constucteur de Guerrier qui prend en parametre la valeur de tous les
     * attributs de cette classe et ses super-classes
     *
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
    public Guerrier(String nom, int ptMana, int pourcentageMag, int pourcentageResisMag, int degMag, double distAttMax, ArrayList<Objet> sac, ArrayList<Nourriture> bonusmalus, int pV, int pA, int pP, int dA, int ptP, Point2D pos) {
        super(nom, ptMana, pourcentageMag, pourcentageResisMag, degMag, distAttMax, sac, bonusmalus, pV, pA, pP, dA, ptP, pos);
    }

    /**
     * Constructeur de Guerrier qui construit un guerrier en fonction des
     * attributs du guerrier mis en paramètre
     *
     * @param g
     */
    public Guerrier(Guerrier g) {
        super(g);
    }

    /**
     * Constructeur de Guerrier par défault qui initialise les attributs à des
     * valeurs choisies par défault. A modifier par la suite pour mettre des
     * valeurs aléatoires
     */
    public Guerrier() {
        super();
        Random intAlea = new Random();
        int i = 50 + intAlea.nextInt(25);
        setPourcentageAtt(i);
        i = 20 + intAlea.nextInt(25);
        setDegAtt(i);
        i = 25 + intAlea.nextInt(15);
        setPourcentageResisMag(35);
    }
    
    /**
     * Constructeur de Guerrier à partir d'une ligne de la sauvegarde
     * @param element ligne de la sauvegarde comportant les caractéristiques du guerrier à créer
     */
    public Guerrier(String element){
        super(element);
    }

    /**
     * Méthode permettant d'afficher un Guerrier
     */
    @Override
    public void affiche() {
        System.out.println("C'est un personnage de type Guerrier :");
        System.out.println("Nom : " + getNom()
                + "; \nPoints de vie: " + getPtVie()
                + "; \nPoints Mana : " + getPtMana()
                + "; \nPourcentage de magie : " + getPourcentageMag()
                + "; \nPourcentage de résistance à la magie : " + getPourcentageResisMag()
                + "; \nDegré de magie : " + getDegMag()
                + "; \nDistance maximale d'attaque : " + getDistAttMax()
                + "; \nPourcentage d'attaque : " + getPourcentageAtt()
                + "; \nPourcentage parade : " + getPourcentagePar()
                + "; \nDégats d'attaque : " + getDegAtt()
                + "; \nPoints de parade : " + getPtPar()
                + ";");
        getPos().affiche();
    }

    /**
     * Méthode permettant de combattre une créature par un combat corps à corps
     * (la créature attaquée doit être sur une case adjacente à la créature qui
     * attaque.
     *
     * @param c créature attaquée
     */
    @Override
    public void combattre(Creature c) {

        //Combat corps à corps : on met 1.42 pour permettre d'attaquer les protagonistes sur les cases en diagonale
        if (c.getControle()==0 && this.getControle()==0) {
            System.out.println("Le "+this.getClass().getSimpleName() +" engage le combat contre le "+ c.getClass().getSimpleName());
        }
        else if (c.getControle()==1 && this.getControle()==0) {
            System.out.println("Le "+this.getClass().getSimpleName() +" engage le combat contre "+ ((Personnage)c).getNom());
        }
        
        
        if (this.getPos().distance(c.getPos()) < 1.42 ){
            Random lanceDe = new Random();
            int RandAtt = lanceDe.nextInt(101);
            if (RandAtt <= this.getPourcentageAtt()) {
                System.out.println("Attaque réussie!");
                int RandDef = lanceDe.nextInt(101);
                if (RandDef > c.getPourcentagePar()){
                    if (c.getControle()==0) {
                        System.out.println("Parade ratée! Le "+c.getClass().getSimpleName() +" perd "+this.getDegAtt()+" points de vie");
                        System.out.println();
                    }
                    else if(c.getControle()==1){
                        System.out.println("Parade ratée!" +((Personnage)c).getNom()+" perd "+this.getDegAtt()+" points de vie");
                        System.out.println();
                    }   
                    c.setPtVie(c.getPtVie()-this.getDegAtt());
                }
                else {
                    int res = this.getDegAtt()-c.getPtPar();
                    if (res>0) {
                        if(c.getControle()==0){
                            System.out.println("Parade réussie! Le " +c.getClass().getSimpleName() +" perd "+res +" points de vie");
                            System.out.println();
                        }
                        else if(c.getControle()==1){
                            System.out.println("Parade réussie! "+((Personnage)c).getNom()+" perd "+res +" points de vie");
                            System.out.println();
                        }
                        c.setPtVie(c.getPtVie()-res);
                    }
                    else {
                        if(c.getControle()==0){
                            System.out.println("Parade réussie! Le "+c.getClass().getSimpleName() +" ne reçois aucun dégats");
                            System.out.println();
                        }
                        else if(c.getControle()==1){
                            System.out.println("Parade réussie!"+ ((Personnage)c).getNom()+" ne reçoit aucun dégats");
                            System.out.println();
                        }
                    }
                }
            }
            else {
                System.out.println("Attaque ratée!");
                System.out.println();
            }
        }
        if (c.getPtVie() < 1 && c.getControle()==0) {
            System.out.println("Le "+c.getClass().getSimpleName() +" est mort");
            System.out.println();
        }
        else if(c.getPtVie() < 1 && c.getControle()==1){
            System.out.println(((Personnage)c).getNom()+" est mort");
            System.out.println();
        }
    }

    @Override
    public void ramasser(Objet o, World w) {
        if (o instanceof Mana) {
            System.out.println("Le Guerrier ne peut pas ramasser de Potion Mana");
        } else if (o instanceof NuageToxique) {
            System.out.println("C'est un nuage Toxique!");
            ((NuageToxique) o).combattre(this);
        } else if (o instanceof Nourriture) {
            this.getBonusMalus().add((Nourriture) o);
            int x = o.getPos().getX();
            int y = o.getPos().getY();
            w.getMatMonde()[x][y].setObjet(null);
            w.getlObjet().remove(o);
        } else {
            getSac().add(o);
            int x = o.getPos().getX();
            int y = o.getPos().getY();
            w.getMatMonde()[x][y].setObjet(null);
            w.getlObjet().remove(o);
        }
    }
    /**
    * Méthode qui renvoie l'affichage correspondant au guerrier
    * @return Le string qui correspond à l'affichage du guerrier
    */
    @Override
    public String getAffichage(){        
        if(this.getControle()==0){
            return "Gue";          
        }
        else{
            return this.getNomjControle();
        }               
    }
}
