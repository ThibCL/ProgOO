package org.centrale.projet.objet;

import java.util.Random;
import java.util.ArrayList;

/**
 * Cette classe représente les personnages de type Mage (sous-classe de
 * Personnage) ; C'est un personnage magique
 *
 * @author Mathilde
 */
public class Mage extends Personnage implements Combattant {

    /**
     * Constructeur de Mage qui prend en parametre toutes les valeurs des
     * attributs a initialiser
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
    public Mage(String nom, int ptMana, int pourcentageMag, int pourcentageResisMag, int degMag, double distAttMax, ArrayList<Objet> sac, ArrayList<Nourriture> bonusmalus, int pV, int pA, int pP, int dA, int ptP, Point2D pos) {    
        super(nom, ptMana, pourcentageMag, pourcentageResisMag, degMag, distAttMax, sac, bonusmalus, pV, pA, pP, dA, ptP, pos);
    }

    /**
     * Constructeur de Mage qui initialise les attributs a partir des attributs
     * du Mage mis en parametre
     *
     * @param m
     */
    public Mage(Mage m) {
        super(m);
    }

    /**
     * Constructeur de Mage par default qui initialise les attributs à des
     * valeurs choisies par default Le mage ne peut attaquer qu'avec de la magie
     */
    public Mage() {
        super();
        Random intAlea = new Random();
        int i = 15 + intAlea.nextInt(15);
        setPtMana(i);
        i = 40 + intAlea.nextInt(30);
        setPourcentageMag(i);
        i = intAlea.nextInt(15);
        setPourcentageAtt(i);
        i = intAlea.nextInt(8);
        setDegAtt(i); //le Mage se bat très mal sans sa Magie
        i = 15 + intAlea.nextInt(15);
        setDegMag(i);
        i = 10 + intAlea.nextInt(10); //s'il réussit sa défense, il use de sa magie et se défend très bien
        setPtPar(i);
        i = 45 + intAlea.nextInt(20);
        setPourcentageResisMag(i);
        i = 1 + intAlea.nextInt(5);
        setDistAttMax(i);
    }
    
    /**
     * Constructeur de Mage à partir d'une ligne de la sauvegarde
     * @param element ligne de la sauvegarde comportant les caractéristiques du mage à créer
     */
    public Mage(String element){
        super(element);
    }

    /**
     * Méthode permettant d'afficher le mage
     */
    @Override
    public void affiche() {
        System.out.println("C'est un personnage de type Mage :"
                + "\nNom : " + getNom()
                + "; \nPoints de vie: " + getPtVie()
                + "; \nPoints Mana : " + getPtMana()
                + "; \nPourcentage de magie : " + getPourcentageMag()
                + "; \nPourcentage de résistance à la magie : " + getPourcentageResisMag()
                + "; \nDegats de magie : " + getDegMag()
                + "; \nDistance maximale d'attaque : " + getDistAttMax()
                + "; \nPourcentage d'attaque : " + getPourcentageAtt()
                + "; \nPourcentage parade : " + getPourcentagePar()
                + "; \nDegats d'attaque : " + getDegAtt()
                + "; \nPoints de parade : " + getPtPar()
                + ";");
        getPos().affiche();
    }

    /**
     * Méthode peremtttant au mage d'attaquer. Il utilise la magie et peut
     * atteindre toutes les créatures à moins de distMax de lui.
     *
     * @param c
     */
    public void combattre(Creature c) {
        if ((this.getPos().distance(c.getPos()) <= this.getDistAttMax()) && ((this.getPtMana() > 0))) {
            this.setPtMana(this.getPtMana() - 1);
            Random lanceDe = new Random();
            int RandAtt = lanceDe.nextInt(101);
            if (RandAtt <= this.getPourcentageMag()) {
                System.out.println("Attaque réussie! Le défenseur perd " + this.getDegMag() + " points de vie");
                c.setPtVie(c.getPtVie() - this.getDegMag());
                
            } else {
                System.out.println("Attaque ratée!");
            }
        }
        if(c.getPtVie()<1){
                    System.out.println("Le défenseur est mort");
                }
    }
    /**
     * Méthode qui permet de ramasser un objet dans un mondequit
     * 
     * @param o
     * @param w 
     */
    public void ramasser(Objet o, World w) {
        if (o instanceof NuageToxique) {
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

    public void effetnourriture(Nourriture n, int i) {
        super.effetNourriture(n,i);

        

            switch (n.getCaracteristique()) {
                case 8:
                    this.setPourcentageMag(this.getPourcentageMag() + n.getPteffet() * i);
                    break;
                case 9:
                    this.setDegMag(this.getDegMag() + n.getPteffet() * i);
                    break;

            }
        

    }
    
    public void effetPotion(Objet o){
        super.effetPotion(o);
        if(o instanceof Mana){
            this.setPtMana(this.getPtMana()+((Mana) o).getPtRecup());
        }
        
    }
    

    
    
    public String getAffichage(){
        if(this.getControle()==0){
            return "Mag";          
        }
        else{
            return this.getNomjControle();
        }
    }
}
