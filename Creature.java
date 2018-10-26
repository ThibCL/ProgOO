package org.centrale.projet.objet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Cette classe represente la super classe de toute les creatures qui vont être créées telle que les monstres ou personnages
 * @author Mathilde
 */
public abstract class Creature extends ElementDeJeu implements Deplacable {
  
    
/**
 * Represente le nombre de point de vie de la créature 
 */
    private int ptVie;
    
/**
 * Pourcentage de chance de toucher un adversaire en attaque
 */
    private int pourcentageAtt;
/**
 * POurcentage de chance de parer une attaque
 */
    private int pourcentagePar;
    
/**
 * Nombre de dégats engendré pas une attaque
 */
    private int degAtt;
    
/**
 * J'en ai aucune idée de ce que c'est
 */
    private int ptPar; 
/**
 * Coordonnée de la position du personnage
 */
    private Point2D pos;
/**
 * permet de savoir si un joueur le controle ou pas. Est égal à 1 si un joueur le controle
 */
    private int controle;
    
/**
 * Nom du joueur qui le controle
 */
    
    private String nomjControle;
    
    
/**
 * Constucteur de Creature qui prend en paramètre la valeur de tous les attributs de cette classe
 * @param pV
 * @param pA
 * @param pP
 * @param dA
 * @param ptP
 * @param pos 
 */
    public Creature(int pV, int pA, int pP, int dA, int ptP, Point2D pos){
        ptVie=pV;
        pourcentageAtt=pA;
        pourcentagePar=pP;
        degAtt=dA;
        ptPar=ptP;
        this.pos=pos;
        this.controle=0;
    }
    
    
/**
 * Constructeur de Crerature qui construit une créature en fonction des attributs de la créature mise en paramètre
 * @param c 
 */
    public Creature(Creature c){
        ptVie=c.ptVie;
        pourcentageAtt=c.pourcentageAtt;
        pourcentagePar=c.pourcentagePar;
        degAtt=c.degAtt;
        ptPar=c.ptPar;
        pos=new Point2D(c.pos);
        this.controle=c.controle;
    }
    
/**
 * Constructeur de Creature par défault qui initialise les attributs à des valeurs choisies par défault. A modifier par la suite pour mettre
 * des valeurs aléatoires
 */
    public Creature(){
        ptVie=20;
        pourcentageAtt=50;
        pourcentagePar=50;
        degAtt=20;
        ptPar=10;
        pos=new Point2D(0,0);
        this.controle=0;
    }
    
    public int getPtVie(){
        return ptVie;
    }
    
    public void setPtVie(int value){
        ptVie=value;
    }

    public void setControle(int controle) {
        this.controle = controle;
    }

    public int getControle() {
        return controle;
    }
    
    public int getPourcentageAtt(){
        return pourcentageAtt;
    }
    
    public void setPourcentageAtt(int value){
        pourcentageAtt=value;
    }
    
    public int getPourcentagePar(){
        return pourcentagePar;
    }
    
    public void setPourcentagePar(int value){
        pourcentagePar=value;
    }
    
    public int getDegAtt(){
        return degAtt;
    }
    
    public void setDegAtt(int value){
        degAtt=value;
    }
    
    public int getPtPar(){
        return ptPar;
    }
    
    public void setPtPar(int value){
        ptPar=value;
    }
    
    public Point2D getPos(){
        return pos;
    }
    
    public void setPos(Point2D p){
        pos=p;
    }

    
    public void setNomjControle(String nomjcontrole){
        this.nomjControle = nomjcontrole;
        
    }

    public String getNomjControle() {
        return nomjControle;
    }

    /**
     * Méthode qui permet de vérifier s'il y a une place libre autour de la créature 
     * @param w monde dans lequel se trouve la creature
     * @return verif qui retourne faux s'il n'y apas de place autour pour se deplacer et vrai s'il y a de la place autour pour se deplacer
     */
    public boolean deplPossible(World w){
        boolean verif=false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.getPos().getX()+i-1>-1 && this.getPos().getX()+i-1<w.getHauteur()-1 && this.getPos().getY()+j-1>-1 && this.getPos().getY()+j-1<w.getLargeur()-1){
                    if(w.getMatMonde()[this.getPos().getX()+i-1][this.getPos().getY()+j-1].getCreature()==null){
                        verif=true;
                    }
                }
            }
        }
        return verif;
    }
    
    
    /**
     * Méthode qui permet de déplacer une créature sur une des cases adjacentes vide, prend deux entiers i,j qui valent 1,0,-1 en parametre 
     * et qui verifie s'l y a une case dispo autour si c'est le cas vérifie si le dépmacement rentré en parmètre est possible sinon place
     * la créature au hasard sur une case adjacente.
     * Modifie aussi la matrice du monde une fois le perso deplacé
     * @param w monde dans lequel se trouve la créature
     * @param i Entier pour se déplacer de -1,0,1 en X
     * @param j Entier pour se déplacer de -1,0,1 en Y
     */
    @Override
    public void deplacer(World w,int i,int j){
        if(deplPossible(w)==false){
            System.out.println("La creature ne peut pas se déplacer");
            
        }
        else {
            Random posAlea= new Random();
            while (this.getPos().getX()+i<0 || this.getPos().getX()+i>w.getHauteur()-1 || 
                    this.getPos().getY()+j<0 || this.getPos().getY()+j>w.getLargeur()-1 || 
                    w.getMatMonde()[this.pos.getX()+i][this.pos.getY()+j].getCreature()!=null){
                i=posAlea.nextInt(3)-1;
                j=posAlea.nextInt(3)-1;
            }

            w.getMatMonde()[this.pos.getX()][this.pos.getY()].setCreature(null);
            pos.translate(i, j);
            w.getMatMonde()[this.pos.getX()][this.pos.getY()].setCreature(this);
            Objet o = w.getMatMonde()[this.pos.getX()][this.pos.getY()].getObjet();
            //Si la créature se déplace sur un nuage toxique celui-ci l'attaque
            if (o instanceof NuageToxique){
                ((NuageToxique) o).combattre(this);
                if(this.getPtVie()<0){
                    w.getlCrea().remove(this);
                }
            }
            else if (this instanceof Personnage){
                
                if (o != null){
                    if (this.controle == 1){
                        Scanner scan = new Scanner(System.in);
                        System.out.print("Voulez vous ramasser l'objet suivant ? (o/n) : ");
                        o.affiche();
                        String reponse=scan.next();
                        switch(reponse){
                            case "o": 
                                ((Personnage)this).ramasser(o,w);
                                System.out.println("L'objet est dans votre sac !");
                                break;
                            default :
                                System.out.println("L'objet n'est pas ramassé");
                                break;
                        }
                    }
                    else {
                        ((Personnage)this).ramasser(o,w);
                    }
            }
        }
    }
    }
/**
 * Méthode qui permet d'afficher tous les atttributs de la créature
 */
    
    public abstract void affiche();
    
    public abstract Creature creaAttaquables (World w);
    

}