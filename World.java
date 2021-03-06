package org.centrale.projet.objet;

import java.io.FileNotFoundException;
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
     *
     * @param h hauteur du monde
     * @param l largeur du monde
     */
    public World(int h, int l) {
        hauteur = h;
        largeur = l;
        matMonde = new Case[hauteur][largeur];
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
        this.hauteur = h;
        this.largeur = l;
        this.matMonde = new Case[hauteur][largeur];
        //initialisation de la matrice par l'ajout de cases vides
        for (int li = 0; li < hauteur; li++) {
            for (int ti = 0; ti < largeur; ti++) {
                this.matMonde[li][ti] = new Case(new Point2D(li, ti));
            }
        }
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
        for (int k = 0; k < nbralea.nextInt(nbr*2); k++) {
            Soin vdk = new Soin();
            this.ajouterObjet(vdk);
        }
        for (int k = 0; k < nbralea.nextInt(nbr*2); k++) {
            Mana rhm = new Mana();
            this.ajouterObjet(rhm);
        }
        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            Nourriture burger = new Nourriture();
            this.ajouterObjet(burger);
        }

        for (int k = 0; k < nbralea.nextInt(nbr); k++) {
            NuageToxique cumulus = new NuageToxique();
            this.ajouterObjet(cumulus);
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
     * Méthode pour ajouter des personnages au tableau des personnages et
     * compléter la matrice du monde par leur ajout à leur position
     *
     * @param crea personnage à ajouter au monde et donc au tableau des
     * personnages
     */
    public void ajouterCrea(Creature crea) {
        this.lCrea.add(crea);
        Point2D pos = crea.getPos();
        int x = pos.getX();
        int y = pos.getY();
        matMonde[x][y].setCreature(crea);
    }

    /**
     * Méthode pour ajouter des objets au tableau des objets et compléter la
     * matrice du monde par leur ajout à leur position
     *
     * @param o objet à ajouter au tableau des objets
     */
    public void ajouterObjet(Objet o) {
        this.lObjet.add(o);
        Point2D pos = o.getPos();
        int x = pos.getX();
        int y = pos.getY();
        matMonde[x][y].setObjet(o);
    }

    /**
     * Méthode pour ajouter des joueurs au tableau des joueurs et compléter la
     * matrice du monde par l'ajout de son personnage à sa position
     *
     * @param j joueur à ajouter au tableau des joueurs
     */
    public void ajouterJoueur(Joueur j) {
        this.lJoueur.add(j);
        Personnage p = j.getPerso();
        Point2D pos = p.getPos();
        int x = pos.getX();
        int y = pos.getY();
        matMonde[x][y].setCreature(p);
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
            getMatMonde()[c.getPos().getX()][c.getPos().getY()].setCreature(null);
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
                getMatMonde()[o.getPos().getX()][o.getPos().getY()].setObjet(null);
                o.getPos().setPosition(x, y);
                getMatMonde()[x][y].setObjet(o);
            }
        }
    }

    /**
     * méthode qui affiche tous les creatures et objets qu'il y a dans un monde
     */
    public void affiche() {
        for (Creature c : this.getlCrea()) {
            c.affiche();
            System.out.println();
        }
        for (Objet o : this.getlObjet()) {
            o.affiche();
            System.out.println();
        }
        for (Joueur j : this.getlJoueur()) {
            j.affiche();
            System.out.println();
        }
    }

    public void afficheMat() {
        for (int i = 0; i < hauteur; i++) {
            for (int j = 0; j < largeur; j++) {
                Creature c = this.matMonde[i][j].getCreature();
                Objet o = this.matMonde[i][j].getObjet();
                if (this.matMonde[i][j].getCreature() == null && this.matMonde[i][j].getObjet() == null) {
                    System.out.print(" [" + "   " + "," + "   " + "]");
                } else if (this.matMonde[i][j].getCreature() == null && this.matMonde[i][j].getObjet() != null) {
                    System.out.print(" [" + "   " + "," + o.getAffichage() + "]");
                } else if (this.matMonde[i][j].getCreature() != null && this.matMonde[i][j].getObjet() == null) {
                    System.out.print(" [" + c.getAffichage() + "," + "   " + "]");
                } else if (this.matMonde[i][j].getCreature() != null && this.matMonde[i][j].getObjet() != null) {
                    System.out.print(" [" + c.getAffichage() + "," + o.getAffichage() + "]");
                }

            }
            System.out.println();
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Lancement : méthode qui définit le tour de jeu et qui fait jouer les
     * joueurs un par un avant de faire joueur les autres entitées Le joueur à
     * le choix de se déplacer ou de combattre Si on veut arrêter de jouer il
     * faut rentrer quit entre deux tour de jeu
     */
    public void tourDeJeu(SauvegardePartie testi) throws IOException {
        Scanner sc = new Scanner(System.in);
        System.out.println();
        System.out.println("Voici la carte du monde :");
        this.afficheMat();
        String choix = "rien";
        SauvegardePartie testo = new SauvegardePartie(testi.getFilename());
        testo.sauvegarderPartie(this);

        while (Objects.equals(choix, "n") == false) {
            System.out.println("Nouveau tour !");
            for (Joueur j : this.getlJoueur()) {
                if (j.getPerso().getPtVie() > 0) {
                    System.out.println("C'est au joueur " + j.getNumero() + " (" + j.getPerso().getNom() + ") de jouer. ");
                    j.getPerso().affiche();
                    System.out.println();
                    
                    boolean choisi = false;
                    while (choisi == false) {
                        System.out.println("Voulez vous  : "
                            + "\n - Combattre : tapez 'c' ;"
                            + "\n - vous Deplacer : tapez 'd' ;"
                            + "\n - Manger : tapez 'm' ;"
                            + "\n - Boire : tapez 'b' ;");
                        choix = sc.next();
                        switch (choix) {
                            case "Combattre":
                            case "c":
                                choisi=j.combattrePerso(this);
                                break;
                            case "Deplacer":
                            case "d":
                                choisi=j.deplacePerso(this);
                                break;
                            case "Manger":
                            case "m":
                                choisi=j.mangerPerso(this);
                                break;
                            case "Boire":
                            case "b":
                                choisi=j.boirePerso(this);
                                break;
                            default:
                                System.out.println("Ce n'est pas une action valide ! ");
                                break;
                        }
                    }

                }
            }
            //faire jouer les autres entitees sur le terrain
            System.out.println();
            System.out.println("C'est au tour du monde de jouer");
            int i = 0;
            Random rf = new Random();
            int ji;
            while (i < this.getlCrea().size()) {
                Creature c = this.getlCrea().get(i);
                //for (Creature c : this.getlCrea()) {
                ji = rf.nextInt(2);
                if (c.getControle() == 0 && c.getPtVie() > 0) {
                    if (ji == 0) {
                        c.deplacer(this, 0, 0);
                    } else if ((ji == 1) && (c.creaAttaquables(this) != null) && (c instanceof Combattant)) {
                        ((Combattant) c).combattre(c.creaAttaquables(this));

                    }
                    //Sinon la créature ne fait rien à ce tour
                }

                if (c instanceof Personnage) {
                    int k = 0;
                    Nourriture n;
                    while (k < ((Personnage) c).getBonusMalus().size()) {
                        n = ((Personnage) c).getBonusMalus().get(k);
                        if (n.getEtat() == 1) {
                            n.setDuree(n.getDuree() - 1);
                            if (n.getDuree() < 1) {
                                ((Personnage) c).effetNourriture(n, -1);
                                System.out.print("L'aliment suivant ne fait plus effet: ");
                                n.affiche();
                                ((Personnage) c).getBonusMalus().remove(n);
                                k = k - 1;

                            }
                        }
                        k = k + 1;

                    }

                }

                //On supprime les créatures mortes 
                if (c.getPtVie() < 1 && c.getControle() == 0) {
                    this.getMatMonde()[c.getPos().getX()][c.getPos().getY()].setCreature(null);
                    this.getlCrea().remove(c);
                    i = i - 1;
                }
                i = i + 1;
            }

            //on retire les joueurs morts
            for (int r = 0; r < this.getlJoueur().size(); r++) {
                if (this.getlJoueur().get(r).getPerso().getPtVie() < 1) {
                    this.getMatMonde()[this.getlJoueur().get(r).getPerso().getPos().getX()][this.getlJoueur().get(r).getPerso().getPos().getY()].setCreature(null);
                    this.getlCrea().remove(this.getlJoueur().get(r).getPerso());
                    System.out.println("Le joueur " + this.getlJoueur().get(r).getPerso().getNom() + " est mort ce soir");
                    this.getlJoueur().remove(this.getlJoueur().get(r));
                    r = r - 1;

                }
            }

            //on fait bouger les nuages
            for (int j = 0; j < this.getlObjet().size(); j++) {
                if (this.getlObjet().get(j) instanceof NuageToxique) {
                    ((NuageToxique) this.getlObjet().get(j)).deplacer(this, 0, 0);
                }
            }

            //on affiche la matrice à chaque tour de jeu
            this.afficheMat();

            System.out.println("Souhaitez-vous continuer à jouer ? o/n");
            choix = sc.next();
        }
        System.out.println("Voulez-vous sauvegarder avant de quitter? o/n");
        choix = sc.next();
        boolean choisi = false;
        while (choisi == false) {
            switch (choix) {
                case "o":
                    testo = new SauvegardePartie(testi.getFilename());
                    testo.sauvegarderPartie(this);
                    choisi = true;
                    break;
                case "n":
                    System.out.println("Partie non sauvegardée !");
                    choisi = true;
                    break;
                default:
                    System.out.println("Entrée non valide ! Voulez-vous sauvegarder avant de quitter? o/n");
            }
        }
    }

    /**
     * Méthode qui rajoute un joueur à la liste de joueur du monde et qui ajoute
     * le personnage que précise le joueur dans la liste des créatures
     */
    public void creationJoueur() {
        System.out.println("Quel est votre nom?");
        Scanner saisie = new Scanner(System.in);
        String nmj = saisie.next();
        Joueur j = new Joueur();
        j.choisirPerso();
        j.getPerso().setNom(nmj);
        this.lJoueur.add(j);
        this.ajouterCrea(j.getPerso());

    }

    public int nombrePerso() {
        int compt = 0;
        for (Creature c : this.getlCrea()) {
            if (c instanceof Personnage) {
                compt += 1;
            }

        }
        return compt;
    }

    public int nombreMonstre() {
        int compt = 0;
        for (Creature c : this.getlCrea()) {
            if (c instanceof Monstre) {
                compt += 1;
            }

        }
        return compt;
    }

}
