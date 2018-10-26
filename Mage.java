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
     * @param ptMana
     * @param pourcentageMag
     * @param pourcentageResisMag
     * @param degMag
     * @param distAttMax
     * @param sac
     * @param bonusmalus
     * @param pV
     * @param pA
     * @param pP
     * @param dA
     * @param ptP
     * @param pos 
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
        i = 2 + intAlea.nextInt(5);
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
     * @param c créature qu'il attaque
     */
    @Override
    public void combattre(Creature c) {
        if (c.getControle()==0 && this.getControle()==0) {
            System.out.println("Le "+this.getClass().getSimpleName() +" engage le combat contre le "+ c.getClass().getSimpleName());
        }
        else if (c.getControle()==1 && this.getControle()==0) {
            System.out.println("Le "+this.getClass().getSimpleName() +" engage le combat contre "+ ((Personnage)c).getNom());
        }
        if ((this.getPos().distance(c.getPos()) <= this.getDistAttMax()) && ((this.getPtMana() > 0))) {
            this.setPtMana(this.getPtMana() - 1);
            Random lanceDe = new Random();
            int RandAtt = lanceDe.nextInt(101);
            if (RandAtt <= this.getPourcentageMag()) {
                if(c.getControle()==0){
                    System.out.println("Attaque réussie! Le " +c.getClass().getSimpleName() +" perd "+this.getDegMag()+" points de vie");
                    System.out.println();
                }
                else if(c.getControle()==1){
                    System.out.println("Attaque réussie! "+((Personnage)c).getNom()+" perd "+this.getDegMag()+" points de vie");
                    System.out.println();
                }
                c.setPtVie(c.getPtVie() - this.getDegMag());
                
            } else {
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
    
    /**
     * Méthode qui permet de ramasser un objet dans un monde 
     * 
     * @param o objet à ramasser
     * @param w monde dans lequel le mage vit
     */
    @Override
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

    /**
     * Méthode permettant d'appliquer l'effet d'un bonus/malus mangé par le mage 
     * Il est le seul personnage à pouvoir augmenter / diminuer ses caractéristiques magiques 
     * @param n nourriture mangée
     * @param i entier permettant d'activer l'effet de la nourriture (quand il vaut 1) ou de la désactiver (quand il vaut -1) 
     * pour retrouver les caractéristiques initiales
     */
    public void effetnourriture(Nourriture n, int i) {
        super.effetNourriture(n,i);
            switch (n.getCaracteristique()) {
                case 7:
                    this.setPourcentageMag(this.getPourcentageMag() + n.getPtEffet() * i);
                    break;
                case 8:
                    this.setDegMag(this.getDegMag() + n.getPtEffet() * i);
                    break;

            }
        

    }
    
    /**
     * méthode permettant d'effectuer les effets des potions que le mage boit
     * @param o potion en question
     */
    @Override
    public void effetPotion(Objet o){
        super.effetPotion(o);
        if(o instanceof Mana){
            this.setPtMana(this.getPtMana()+((Mana) o).getPtRecup());
        }
        
    }
       
    /**
    * Méthode qui renvoie l'affichage correspondant au mage
    * @return Le string qui correspond à l'affichage du mage
    */
    @Override
    public String getAffichage(){
        if(this.getControle()==0){
            return "Mag";          
        }
        else{
            return this.getNomjControle();
        }
    }
}
