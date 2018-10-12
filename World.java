package org.centrale.projet.objet;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.Objects;

/**
 *
 * @author Thibault
 */
public class World {
    
    /**
     * Liste des créatures dans le monde 
     */
    private ArrayList<Creature> lCrea = new ArrayList<>();
    
    /**
     * Liste des objets dans le monde 
     */
    private ArrayList<Objet> lObjet = new ArrayList<>();
    /**
     * Taille du monde
     */
    private final int taille=10;
    /**
     * Matrice des positions des différentes entitées dans le monde
     */
    private int[][] matMonde= new int[taille][taille];
    /**
     * Liste des joueurs dans le monde
     */
    private ArrayList<Joueur> lJoueur = new ArrayList<>();
    

    
    /**
     * Constructeur qui place un nombre aléatoire de chaque type de personnage
     */
    public World(int nbr) {
        Random nbralea = new Random();
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Archer arch=new Archer();
            this.ajouterCrea(arch);
        }
        for(int i=0;i<nbralea.nextInt(nbr);i++)
        {
            Paysan pays=new Paysan();
            this.ajouterCrea(pays);
        }
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Guerrier guer=new Guerrier();
            this.ajouterCrea(guer);
        }
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Mage mge=new Mage();
            this.ajouterCrea(mge);
        }
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Lapin lap=new Lapin();
            this.ajouterCrea(lap);
        }
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Loup lp=new Loup();
            this.ajouterCrea(lp);
        }
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Soin vdk=new Soin();
            this.ajouterObjet(vdk);
        }
        for(int k=0;k<nbralea.nextInt(nbr);k++)
        {
            Mana rhm=new Mana();
            this.ajouterObjet(rhm);
        }
        //la matrice sera remplie lors de l'appel de creeMondeAlea()
        
    }

    public ArrayList<Joueur> getlJoueur() {
        return lJoueur;
    }    
    
    public ArrayList<Creature> getlCrea() {
        return lCrea;
    }

    public ArrayList<Objet> getlObjet() {
        return lObjet;
    }

    public int getTaille() {
        return taille;
    }

    public int[][] getMatMonde() {
        return matMonde;
    }

    public void setMatMonde(int[][] matMonde) {
        this.matMonde = matMonde;
    }

    public void setlCrea(ArrayList<Creature> lCreature) {
        this.lCrea = lCrea;
    }


    public void setlObjet(ArrayList<Objet> lObjet) {
        this.lObjet = lObjet;
    }
    public void setlJoueur(ArrayList<Joueur> lJoueur) {
        this.lJoueur = lJoueur;
    }

    
    /**
     * Méthode pour ajouter des personnages au tableau des personnages 
     * @param crea personnage à ajouter au monde et donc au tableau des personnages
     */
    public void ajouterCrea(Creature crea){
        this.lCrea.add(crea);
    }
    /**
     * Méthode pour ajouter des objets au tableau des objets
     * @param o objet à ajouter au tableau des objets
     */
    public void ajouterObjet(Objet o){
        this.lObjet.add(o);
    }
    
    /**
     * Méthode vérifiant si une Creature est bien a une distance inférieure à la distance minimale à laquelle doivent être placés les différentes Créatures 
     * @param distMin distance minimale entre deux créatures à la création du monde
     * @param pt point où l'on souhaite placer la Creature 
     * @return vrai si elle peut etre placée au point entrer
     */
    public boolean distanceMonde(int distMin, Point2D pt){
        boolean ok = true;
        for (Creature c : this.getlCrea()){
            if (pt.distance(c.getPos())<distMin){
                ok=false;
            }
            if (ok==false)break;
        }
        return ok;
    }
    
    public void creeMondeAlea(){ 
        Random posAlea = new Random();
        for (Creature c : this.getlCrea()){
            int x = posAlea.nextInt(this.taille);
            int y= posAlea.nextInt(this.taille);
            Point2D pt= new Point2D(x,y);
            while (!(distanceMonde(3,pt))){
                x = posAlea.nextInt(this.taille);
                y= posAlea.nextInt(this.taille);
                pt= new Point2D(x,y);
            }
            c.setPos(pt);
            getMatMonde()[x][y]=1;
            
            }           
        for (Objet o : this.getlObjet()){
            int x = posAlea.nextInt(this.taille);
            int y= posAlea.nextInt(this.taille);
            if (getMatMonde()[x][y]>0){
                x = posAlea.nextInt(this.taille);
                y= posAlea.nextInt(this.taille);
            }
            else {
                o.getPos().setPosition(x,y);
                getMatMonde()[x][y]=2;
            }  
    }
}
    /**
     * méthode qui affiche tous les creatures et objets qu'il y a dans un monde
     */
    public void affiche(){
        for(int j=0; j<this.lCrea.size(); j++){
            this.lCrea.get(j).affiche();
        }
        for(int j=0; j<this.lObjet.size(); j++){
            this.lObjet.get(j).affiche();
        }
    }
    
    public void affichemat(){
        for(int i=0; i<taille;i++){
            for(int j=0; j<taille;j++){
                System.out.print(this.matMonde[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }
/**
 * méthode qui définit le tour de jeu et qui fait jouer les joueurs un par un avant de faire joueur les autres entitées
 * Le joueur à le choix de se déplacer ou de combattre 
 * Si on veut arrêter de jouer il faut rentrer quit entre deux tour de jeu
 */
    public void tourDeJeu(){
        Scanner sc = new Scanner(System.in);
        System.out.println("si vous ne voulez pas jouer tapez quit");
        String choix= sc.next();
        while(Objects.equals(choix,"quit")==false){
            System.out.println("Nouveau tour");
            for (Joueur j : this.getlJoueur()){
                System.out.println("C'est à "+ j.getNomJoueur() + " de jouer Veux tu Combattre ou te Deplacer?");
                choix=sc.next();
                switch(choix){
                    case "Combattre": 
                        j.combattreperso(this);
                        break;
                    case "Deplacer":
                        j.deplaceperso(this);
                        break;
                
                }
                j.getPerso().affiche();
                this.affichemat();
            }
            //faire joueur les autres entitees sur le terrain
            for(Creature c: this.getlCrea() ){
                if(c.getControle()==0){
                    c.deplacer(this, 0, 0);
                }
            }
            this.affichemat();
            System.out.println("Si vous voulez arrêter de jouer entrez quit");
            choix=sc.next();
        }
        
    }
/**
 * Méthoe qui rajoute un joueur à la liste de joueur du monde et qui ajoute le personnage que précise le joueur 
 * dans la liste des créatures 
 */
    public void creationJoueur(){
        System.out.println("Quelle est votre nom?");
        Scanner saisie = new Scanner(System.in); 
        String nmj = saisie.next();
        Joueur j= new Joueur();
        j.choisirperso();
        j.setNomJoueur(nmj);
        this.lJoueur.add(j);
        this.ajouterCrea(j.getPerso());
        
    }
    
}
