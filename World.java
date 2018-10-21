package org.centrale.projet.objet;

import java.io.IOException;
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
    
    
    private ArrayList<ElementDeJeu> lelem =new ArrayList<>();
    /**
     * Taille du monde
     */
    private int hauteur;
    private int largeur;
    /**
     * Matrice des positions des différentes entitées dans le monde
     */
    private Case[][] matMonde;
    /**
     * Liste des joueurs dans le monde
     */
    private ArrayList<Joueur> lJoueur = new ArrayList<>();
    
    
    /**
     * Constructeur du monde de hauteur h et de largeur l
     * @param h hauteur du monde
     * @param l largeur du monde
     */
    public World (int h, int l){
        hauteur=h;
        largeur=l;
        matMonde= new Case[hauteur][largeur];
        for (int k = 0; k < hauteur; k++) {
            for (int i = 0; i < largeur; i++) {
                this.matMonde[k][i] = new Case(new Point2D(k, i));
            }
        }
    }

    /**
     * Constructeur qui place un nombre aléatoire de chaque type de personnage
     *
     * @param nbr le nombre aléatoire sera choisi entre 0 et nbr
     */
    public World(int nbr, int h, int l) {
        Random nbralea = new Random();
        this.hauteur=h;
        this.largeur=l;
        this.matMonde= new Case[hauteur][largeur];
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Archer arch = new Archer();
            this.ajouterCrea(arch);
        }
        for (int i = 0; i < nbralea.nextInt(nbr); i++) {
            Paysan pays = new Paysan();
            this.ajouterCrea(pays);
        }
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Guerrier guer = new Guerrier();
            this.ajouterCrea(guer);
        }
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Mage mge = new Mage();
            this.ajouterCrea(mge);
        }
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Lapin lap = new Lapin();
            this.ajouterCrea(lap);
        }
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Loup lp = new Loup();
            this.ajouterCrea(lp);
        }
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Soin vdk = new Soin();
            this.ajouterObjet(vdk);
        }
        for (int k = 0; k<7/* nbralea.nextInt(nbr)*/; k++) {
            Mana rhm = new Mana();
            this.ajouterObjet(rhm);
        }
        for (int k = 0; k < 8/*nbralea.nextInt(nbr)*/; k++) {
            Nourriture burger = new Nourriture();
            this.ajouterObjet(burger);
        }
        //initialisation de la matrice par l'ajout de cases vides
        for (int li = 0; li < hauteur; li++) {
            for (int ti = 0; ti < largeur; ti++) {
                this.matMonde[li][ti] = new Case(new Point2D(li, ti));
            }
        }

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

    public int getHauteur() {
        return hauteur;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }



    public Case[][] getMatMonde() {
        return matMonde;
    }

    public void setMatMonde(Case[][] matMonde) {
        this.matMonde = matMonde;
    }

    public void setlCrea(ArrayList<Creature> lCreature) {
        this.lCrea = lCreature;
    }

    public void setlObjet(ArrayList<Objet> lObjet) {
        this.lObjet = lObjet;
    }

    public void setlJoueur(ArrayList<Joueur> lJoueur) {
        this.lJoueur = lJoueur;
    }

    /**
     * Méthode pour ajouter des personnages au tableau des personnages
     *
     * @param crea personnage à ajouter au monde et donc au tableau des
     * personnages
     */
    public void ajouterCrea(Creature crea) {
        this.lCrea.add(crea);
        this.lCrea.add(crea);
    }

    /**
     * Méthode pour ajouter des objets au tableau des objets
     *
     * @param o objet à ajouter au tableau des objets
     */
    public void ajouterObjet(Objet o) {
        this.lObjet.add(o);
    }

    /**
     * Méthode vérifiant si une Creature est bien a une distance inférieure à la
     * distance minimale à laquelle doivent être placés les différentes
     * Créatures
     *
     * @param distMin distance minimale entre deux créatures à la création du
     * monde
     * @param pt point où l'on souhaite placer la Creature
     * @return vrai si elle peut etre placée au point entrer
     */
    public boolean distanceMonde(int distMin, Point2D pt) {
        boolean ok = true;
        for (Creature c : this.getlCrea()) {
            if (pt.distance(c.getPos()) < distMin) {
                ok = false;
            }
            if (ok == false) {
                break;
            }
        }
        return ok;
    }

    public void creeMondeAlea() {
        Random posAlea = new Random();
        for (Creature c : this.getlCrea()) {
            int x = posAlea.nextInt(this.hauteur);
            int y = posAlea.nextInt(this.largeur);
            Point2D pt = new Point2D(x, y);
            while (!(distanceMonde(2, pt))) {
                x = posAlea.nextInt(this.hauteur);
                y = posAlea.nextInt(this.largeur);
                pt = new Point2D(x, y);
            }
            c.setPos(pt);
            getMatMonde()[x][y].setCreature(c);

        }
        for (Objet o : this.getlObjet()) {
            int x = posAlea.nextInt(this.hauteur);
            int y = posAlea.nextInt(this.largeur);
            if (getMatMonde()[x][y].getObjet() != null) {
                x = posAlea.nextInt(this.hauteur);
                y = posAlea.nextInt(this.largeur);
            } else {
                o.getPos().setPosition(x, y);
                getMatMonde()[x][y].setObjet(o);
            }
        }
    }

    /**
     * méthode qui affiche tous les creatures et objets qu'il y a dans un monde
     */
    public void affiche() {
        for (int j = 0; j < this.lCrea.size(); j++) {
            this.lCrea.get(j).affiche();
        }
        for (int j = 0; j < this.lObjet.size(); j++) {
            this.lObjet.get(j).affiche();
        }
    }

    public void affichemat() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                if (this.matMonde[i][j].getCreature() == null && this.matMonde[i][j].getObjet() == null) {
                    System.out.print(" [" + "." + "," + "." + "]");
                } else if (this.matMonde[i][j].getCreature() == null && this.matMonde[i][j].getObjet() != null) {
                    System.out.print(" [" + "." + "," + 2 + "]");
                } else if (this.matMonde[i][j].getCreature() != null && this.matMonde[i][j].getObjet() == null) {
                    System.out.print(" [" + 1 + "," + "." + "]");
                } else if (this.matMonde[i][j].getCreature() != null && this.matMonde[i][j].getObjet() != null) {
                    System.out.print(" [" + 1 + "," + 2 + "]");
                }
            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    /**
     * méthode qui définit le tour de jeu et qui fait jouer les joueurs un par
     * un avant de faire joueur les autres entitées Le joueur à le choix de se
     * déplacer ou de combattre Si on veut arrêter de jouer il faut rentrer quit
     * entre deux tour de jeu
     */
    public void tourDeJeu() throws IOException {
        SauvegardePartie testi=new SauvegardePartie("trere");
        Scanner sc = new Scanner(System.in);
        System.out.println("si vous ne voulez pas jouer tapez quit");
        String choix = sc.next();
        while (Objects.equals(choix, "quit") == false) {
            System.out.println("Nouveau tour");
            for (Joueur j : this.getlJoueur()) {
                System.out.println("C'est à " + j.getPerso().getNom()+ " de jouer. Veux tu Combattre ,te Deplacer,Manger ou Boire?");
                boolean choisi = false;
                while (choisi == false) {
                    choix = sc.next();
                    switch (choix) {
                        case "Combattre": case "c":
                            j.combattreperso(this);
                            choisi = true;
                            break;
                        case "Deplacer": case "d":
                            j.deplaceperso(this);
                            choisi = true;
                            break;
                        case "Manger": case "m":
                            j.mangerperso(this);
                            choisi=true;
                            break;
                        case "Boire": case "b":
                            j.boirePerso(this);
                            choisi=true;
                            break;
                        default:
                            System.out.println("Ce n'est pas une action possible : Entrer Combattre,Deplacer,Manger ou Boire :");
                            break;
                    }
                }
                j.getPerso().affiche();

            }
            //faire jouer les autres entitees sur le terrain
            int i=0;
            while(i<this.getlCrea().size()){
                Creature c=this.getlCrea().get(i);
            //for (Creature c : this.getlCrea()) {
                if (c.getControle() == 0 && c.getPtVie()>0) {
                    c.deplacer(this, 0, 0);
                }
                
                if (c instanceof Personnage) {
                    int k = 0;
                    Nourriture n;
                    while (k < ((Personnage) c).getBonusmalus().size()) {
                        n = ((Personnage) c).getBonusmalus().get(k);
                        if (n.getEtat() == 1) {
                            n.setDuree(n.getDuree() - 1);
                            if (n.getDuree() < 1) {
                                ((Personnage) c).effetnourriture(n, -1);
                                System.out.print("L'aliment suivant ne fait plus effet: ");
                                n.affiche();
                                ((Personnage) c).getBonusmalus().remove(n);
                              
                            }
                        }
                        k=k+1;

                    }

                }
                if(c.getPtVie()<1){
                    this.getMatMonde()[c.getPos().getX()][c.getPos().getY()].setCreature(null);
                    this.getlCrea().remove(c);
                    
                }
                i=i+1;
            }
            this.affichemat();
            System.out.println("Si vous voulez arrêter de jouer entrez quit si vous voulez sauvegarder taper save");
            choix = sc.next();
            switch(choix){
                case "save":
                    testi.sauvegarderPartie(this);
                    break;
            }
        }

    }

    /**
     * Méthoe qui rajoute un joueur à la liste de joueur du monde et qui ajoute
     * le personnage que précise le joueur dans la liste des créatures
     */
    public void creationJoueur() {
        System.out.println("Quel est votre nom?");
        Scanner saisie = new Scanner(System.in);
        String nmj = saisie.next();
        Joueur j = new Joueur();
        j.choisirperso();
        j.getPerso().setNom(nmj);
        this.lJoueur.add(j);
        this.ajouterCrea(j.getPerso());

    }
    public int nombrePerso(){
        int compt=0;
        for(Creature c: this.getlCrea()){
            if(c instanceof Personnage){
                compt+=1;
            }
            
        }
        return compt;
    }
    
    public int nombreMonstre(){
        int compt=0;
        for(Creature c: this.getlCrea()){
            if(c instanceof Monstre){
                compt+=1;
            }
            
        }
        return compt;
    }

}
