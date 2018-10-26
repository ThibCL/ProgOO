package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * Cette classe représente un joueur dans le monde
 *
 * @author Thibault
 */

public class Joueur {

    /**
     * Personnage associé au joueur
     */
    private Personnage perso;

    /**
     * numero du joueur
     */
    private int numero;

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public Personnage getPerso() {
        return perso;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }

    /**
     * Méthode qui permet au joueur de choisir son personnage et le nom de celui
     * ci
     */
    public void choisirPerso() {
        System.out.println("Veuillez entrer un type de personnage: "
                + "\n -Archer"
                + "\n -Guerrier"
                + "\n -Mage"
                + "\n -Paysan");
        Scanner saisieUtilisateur = new Scanner(System.in);
        boolean choisi = false;
        while (choisi == false) {
            String str = saisieUtilisateur.next();
            choisi = true;
            switch (str) {
                case "Archer":
                    this.perso = new Archer();
                    break;
                case "Guerrier":
                    this.perso = new Guerrier();
                    break;
                case "Mage":
                    this.perso = new Mage();
                    break;
                case "Paysan":
                    this.perso = new Paysan();
                    break;
                default:
                    System.out.println("Ce n'est pas un type de personnage valide!");
                    System.out.println("Veuillez entrer un type de personnage: "
                            + "\n -Archer"
                            + "\n -Guerrier"
                            + "\n -Mage"
                            + "\n -Paysan");
                    choisi = false;
                    break;
            }

        }
        this.perso.setControle(1);
    }

    /**
     * fonction renvoyant la liste des personnages pouvant être attaqués par le
     * joueur selon sa position et sa distance max d'attaque
     *
     */
    public ArrayList<Creature> creaAttaquables(World w) {
        ArrayList<Creature> cAtt = new ArrayList<>();
        for (Creature c : w.getlCrea()) {
            if (this.getPerso().getPos().distance(c.getPos()) != 0 && this.getPerso().getPos().distance(c.getPos()) <= this.getPerso().getDistAttMax()) {
                cAtt.add(c);
            }
        }
        return cAtt;
    }

    /**
     * méthode qui permet au joeur d'attaquer une autre creature
     *
     * @param w
     */
    public void combattrePerso(World w) {
        ArrayList<Creature> cAtt = creaAttaquables(w);
        if (cAtt.size() == 0) {
            System.out.println("Impossible de te battre, tu n'as pas d'ennemi à portée d'attaque");
        } else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez le personnage que vous voulez combattre parmis les suivants :");
            int k = 1;
            for (Creature c : cAtt) {

                System.out.print(k + ": ");
                c.affiche();
                System.out.println();
                k = k + 1;
            }
            boolean choisi = false;
            int rep = -1;
            while (choisi == false) {
                System.out.println("Donner le numéro de la créature que vous voulez attaquer :");
                try {
                    rep = scan.nextInt();
                    choisi = true;
                } catch (java.util.InputMismatchException e) {
                    System.out.println("Ce n'est pas un numéro valide !");
                    choisi = false;
                } finally {
                    if (choisi == true) {
                        if (rep < 0 || rep > cAtt.size()) {
                            System.out.println("Ce n'est pas un numéro valide !");
                            choisi = false;
                        }
                    }
                }
            }
            if (this.getPerso() instanceof Combattant) {
                System.out.println();
                if (cAtt.get(rep - 1).getControle() == 0) {
                    System.out.println("Tu engages le combat contre le " + cAtt.get(rep - 1).getClass().getSimpleName());
                } else if (cAtt.get(rep - 1).getControle() == 1) {
                    System.out.println("Tu engages le combat contre " + ((Personnage) cAtt.get(rep - 1)).getNom());
                }

                ((Combattant) this.perso).combattre(cAtt.get(rep - 1));
            }
        }
    }

    /**
     * Permet de demander quelle deplacement le joueur veut faire en utilisant
     * les touches du clavier
     */
    public Point2D demanderDepl() {
        System.out.println("Veuillez entrer deplacement: ");
        System.out.println(" [a] [z] [e]");
        System.out.println(" [q]" + " J" + this.getNumero() + "  [d]");
        System.out.println(" [w] [x] [c]");
        Point2D pos = new Point2D();
        Scanner saisieUtilisateur = new Scanner(System.in);
        boolean choisi = false;
        while (choisi == false) {
            String str = saisieUtilisateur.next();
            choisi = true;
            switch (str) {
                case "z":
                    pos.setPosition(-1, 0);
                    break;
                case "e":
                    pos.setPosition(-1, 1);
                    break;
                case "a":
                    pos.setPosition(-1, -1);
                    break;
                case "q":
                    pos.setPosition(0, -1);
                    break;
                case "d":
                    pos.setPosition(0, 1);
                    break;
                case "w":
                    pos.setPosition(1, -1);
                    break;
                case "x":
                    pos.setPosition(1, 0);
                    break;
                case "c":
                    pos.setPosition(1, 1);
                    break;
                default:
                    System.out.println("Ce n'est pas un deplacement possible!");
                    System.out.println("Veuillez entrer deplacement: ");
                    System.out.println(" [a] [z] [e]");
                    System.out.println(" [q]" + " J" + this.getNumero() + "  [d]");
                    System.out.println(" [w] [x] [c]");
                    choisi = false;
                    break;
            }
        }
        return pos;
    }

    /**
     * méthode qui permet au joueur de décider ou deplacer son perso, la méthode
     * vérifie si il y a une case autour de libre et si c'est le cas demande des
     * coordonnées au joueur jusqu'a ce que le deplacement soit possible. Une
     * fois que le deplacement est possible la méthode appelle la méthode
     * déplacer de creature
     *
     * @param w
     */
    public void deplacePerso(World w) {
        if (this.perso.deplPossible(w) == false) {
            System.out.println("Impossible de te déplacer, tu dois te battre");
            //this.combattreperso();
        } else {
            Point2D pos;
            pos = demanderDepl();
            int x = pos.getX();
            int y = pos.getY();
            boolean bonnePos = false;
            while (!bonnePos) {
                if (this.getPerso().getPos().getX() + x < 0 || this.getPerso().getPos().getX() + x > w.getHauteur() - 1 || this.getPerso().getPos().getY() + y < 0 || this.getPerso().getPos().getY() + y > w.getLargeur() - 1) {
                    System.out.println("Vous vous cognez à un mur! Entrez une autre position");
                    pos = demanderDepl();
                    x = pos.getX();
                    y = pos.getY();
                } else if (w.getMatMonde()[this.perso.getPos().getX() + x][this.perso.getPos().getY() + y].getCreature() != null) {
                    System.out.println("Position déjà occupée, entrez une autre position!");
                    pos = demanderDepl();
                    x = pos.getX();
                    y = pos.getY();
                } else {
                    bonnePos = true;
                }
            }
            this.perso.deplacer(w, x, y);

        }

    }

    /**
     * Méthode qui permet au joueur de faire manger un aliment à son personnage
     *
     * @param w
     */
    public void mangerPerso(World w) {
        Scanner scani = new Scanner(System.in);
        if (!this.getPerso().nourritureNonActivee()) {
            System.out.println("Pas d'aliment à manger !");

        } else {
            System.out.println("Voici les aliments que vous possédez: ");
            for (int k = 0; k < this.getPerso().getBonusMalus().size(); k++) {
                if (this.getPerso().getBonusMalus().get(k).getEtat() == 0) {
                    System.out.print(k + ": ");
                    this.getPerso().getBonusMalus().get(k).affiche();
                }  
            }
            System.out.println("Tapez le numéro de l'aliment que vous voulez manger :");
            boolean bonneRep = false;
            int rep=-1;
            while (bonneRep ==false){
                String r = scani.next();
                try {
                    rep=Integer.parseInt(r);
                    if (rep<0 ||  rep>this.getPerso().getBonusMalus().size()-1||this.getPerso().getBonusMalus().get(rep).getEtat()==1){
                        System.out.println("Ce n'est pas un numéro valide! Tapez le numéro de l'aliment que vous voulez manger :");
                    }
                    else {
                        bonneRep=true;
                    }
                }
                catch (NumberFormatException e){
                    System.out.println("Il faut entrer un entier! Tapez le numéro de l'aliment que vous voulez manger :");
                    
                }
            }
            System.out.print("Vous avez mangé: ");
            this.getPerso().getBonusMalus().get(rep).afficheEffet();
            this.getPerso().effetNourriture(this.getPerso().getBonusMalus().get(rep), 1);
            this.getPerso().getBonusMalus().get(rep).setEtat(1);

        }
    }

    /**
     * Mérhode qui permet au joueur de faire boire une potion à son personnage
     *
     * @param w monde dans lequel le joueur joue
     */
    public void boirePerso(World w) {
        Scanner scani = new Scanner(System.in);
        if (this.getPerso().getSac().size() == 0) {
            System.out.println("Pas de potion à boire.");

        } else {
            System.out.println("Voici les potions que vous possédez: ");
            for (int k = 0; k < this.getPerso().getSac().size(); k++) {

                System.out.print(k + ": ");
                this.getPerso().getSac().get(k).affiche();
            }
            System.out.println("Taper le numéro de l'aliment que vous voulez boire:");

            int rep = scani.nextInt();
            this.getPerso().effetPotion(this.getPerso().getSac().get(rep));

            System.out.print("Vous avez bu: ");
            this.getPerso().getSac().get(rep).affiche();
            this.getPerso().getSac().remove(rep);
        }
    }

    /**
     * Methode qui permet d'afficher le nom du joueur et son personnage
     */
    public void affiche() {
        System.out.println("Le nom du joueur est " + this.getPerso().getNom() + " il controle le personnage:");
        this.perso.affiche();
    }

    public void getTexteSauvegarde(BufferedWriter writer) throws IOException {

        writer.write(this.getPerso().getNom() + "/");
        this.getPerso().getTexteSauvegarde(writer);
        writer.newLine();

    }

}
