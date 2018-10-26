package org.centrale.projet.objet;

import java.io.*;
import java.util.Random;
import java.util.ArrayList;
import java.util.StringTokenizer;

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
    public Archer(String nom, int ptMana, int pourcentageMag, int pourcentageResisMag, int degMag, double distAttMax, ArrayList<Objet> sac, ArrayList<Nourriture> bonusmalus, int pV, int pA, int pP, int dA, int ptP, Point2D pos, int nbFleches){
        super(nom, ptMana, pourcentageMag, pourcentageResisMag, degMag, distAttMax, sac, bonusmalus, pV, pA, pP, dA, ptP, pos);
        this.nbFleches=nbFleches;
    }

    /**
     * Constructeur de Archer qui construit un archer en fonction des attributs de l'archer mis en paramètre
     * @param a 
     */
    public Archer(Archer a) {
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
    
    
    /**
     * Construit un Archer à partir d'une ligne prise dans la sauvegarde
     * @param element ligne correspondant à l'archer dans la sauvegarde
     */
    public Archer(String element){
        super(element);
        //pour récupérer le nombre de flèches on créé un délimiteur 
        String delimiteurs = "*";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);
        tokenizer.nextToken();
        int nbreFleches = Integer.parseInt(tokenizer.nextToken());
        nbFleches=nbreFleches;
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
        if (c.getControle()==0 && this.getControle()==0) {
            System.out.println("Le "+this.getClass().getSimpleName() +" engage le combat contre le "+ c.getClass().getSimpleName());
        }
        else if (c.getControle()==1 && this.getControle()==0) {
            System.out.println("Le "+this.getClass().getSimpleName() +" engage le combat contre "+ ((Personnage)c).getNom());
        }
        else{
            
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

        //Combat à distance 
        else if ((this.getPos().distance(c.getPos()) >=1.42 )&&(this.getPos().distance(c.getPos()) <= this.getDistAttMax())&&((this.getNbFleches() > 0))){
            this.setNbFleches(this.getNbFleches()-1);
            Random lanceDe = new Random();
            int RandAtt = lanceDe.nextInt(101);
            if (RandAtt <= this.getPourcentageAtt()) {
                if(c.getControle()==0){
                    System.out.println("Attaque réussie! Le " +c.getClass().getSimpleName() +" perd "+this.getDegAtt()+" points de vie");
                    System.out.println();
                }
                else if(c.getControle()==1){
                    System.out.println("Attaque réussie! "+((Personnage)c).getNom()+" perd "+this.getDegAtt()+" points de vie");
                    System.out.println();
                }
                c.setPtVie(c.getPtVie()-this.getDegAtt());
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
    
    public void ramasser(Objet o, World w){
        if (o instanceof Mana){
            System.out.println("L'archer ne peut pas ramasser de Potion Mana");
        }
        else if (o instanceof NuageToxique){
            System.out.println("C'est un nuage Toxique!");
            ((NuageToxique) o).combattre(this);
        }
        else if(o instanceof Nourriture){
        this.getBonusMalus().add((Nourriture)o);
        int x = o.getPos().getX();
        int y = o.getPos().getY();
        w.getMatMonde()[x][y].setObjet(null);
        w.getlObjet().remove(o);
        this.effetNourriture((Nourriture)o,1);
    }
        else {
            getSac().add(o);
            int x = o.getPos().getX();
            int y = o.getPos().getY();
            w.getMatMonde()[x][y].setObjet(null);
            w.getlObjet().remove(o);
        }
    }
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write("*");
        writer.write(Integer.toString(nbFleches));
        
    }
    
    public String getAffichage(){        
        if(this.getControle()==0){
            return "Arc";          
        }
        else{
            return this.getNomjControle();
        }               
    }
}