package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;
import java.util.Random;

/**
 * Classe qui est une super classe de tous les personnages possibles tels que
 * les archers, les paysans... (sous-classe de Creature)
 *
 * @author Mathilde
 */
public abstract class Personnage extends Creature {

    /**
     * Represente le nom du personnage
     */
    private String nom;

    /**
     * Represente le nombre de points de mana du personnage pour les personnages
     * Magiques
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
     * Nombre de degats infligés par une attaque magique pour les personnages
     * Magiques
     */
    private int degMag;
    /**
     * Portée maximale de l'attaque à distance
     */
    private double distAttMax;

    /**
     * Tableau contenant les objets détenus par le personnage
     */
    private ArrayList<Objet> sac;
    /**
     * Liste de la nourriture que possède le personnagz
     */
    private ArrayList<Nourriture> bonusMalus;

    /**
     * Constructeur de Personnage qui prend en parametre toutes les valeurs des
     * attributs a initialiser
     *
     * @param nom Nom du personnage créé
     * @param ptV Points de vie du personnage créé
     * @param ptM Points de mana du personnage créé
     * @param pA Pourcentage d'attaque du personnage créé
     * @param pP Pourcentage de parade du personnage créé
     * @param pM Points de magie du personnage créé
     * @param rM Resistance à la magie du personnage créé
     * @param dA Dégats d'attaque du personnage créé
     * @param dM Dégats de magie du personnage créé
     * @param distM Distance maximale d'attaque du personnage créé
     * @param ptP Points de parade du personnage créé
     * @param s Sac du personnage contenant les objets détenus par le personnage
     * créé
     * @param p Poisition 2D du personnage créé
     */
    public Personnage(String nom, int ptMana, int pourcentageMag, int pourcentageResisMag, int degMag, double distAttMax, ArrayList<Objet> sac, ArrayList<Nourriture> bonusmalus, int pV, int pA, int pP, int dA, int ptP, Point2D pos) {
        super(pV, pA, pP, dA, ptP, pos);
        this.nom = nom;
        this.ptMana = ptMana;
        this.pourcentageMag = pourcentageMag;
        this.pourcentageResisMag = pourcentageResisMag;
        this.degMag = degMag;
        this.distAttMax = distAttMax;
        this.sac = new ArrayList<Objet>(sac);
        this.bonusMalus = new ArrayList<Nourriture>(bonusmalus);
    }

    /**
     * Constructeur de personnage qui initialise les attributs a partir des
     * attributs du personnage mis en parametre
     *
     * @param perso
     */
    public Personnage(Personnage perso) {
        super(perso);
        nom = perso.nom;
        ptMana = perso.ptMana;
        pourcentageMag = perso.pourcentageMag;
        pourcentageResisMag = perso.pourcentageResisMag;
        degMag = perso.degMag;
        distAttMax = perso.distAttMax;
        sac = new ArrayList(perso.getSac());
        bonusMalus = new ArrayList(perso.getBonusMalus());
    }

    //A améliorer avec Random
    /**
     * Constructeur de personnage par default qui initialise les attributs a des
     * valeurs choisies par default
     */
    public Personnage() {
        super();
        nom="Bob";
        ptMana = 0;
        pourcentageMag = 0;
        pourcentageResisMag = 40;
        degMag = 0;
        distAttMax = 1.42;
        sac = new ArrayList<>();
        bonusMalus = new ArrayList<>();
    }

    /**
     * Constructeur prenant en argument la ligne correspondant à la sauvegarde
     * du personnage dans un fichier et recréant le personnage correspondant
     *
     * @param element ligne correspondant à la sauvegarde du personnage dans un
     * fichier
     */
    public Personnage(String element) {
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);

        String typePerso = tokenizer.nextToken();
        nom = tokenizer.nextToken();
        ptMana = Integer.parseInt(tokenizer.nextToken());
        pourcentageMag = Integer.parseInt(tokenizer.nextToken());
        pourcentageResisMag = Integer.parseInt(tokenizer.nextToken());
        degMag = Integer.parseInt(tokenizer.nextToken());
        distAttMax = Float.parseFloat(tokenizer.nextToken());

        //on récupère le nombre d'objets dans le sac
        int objSac = Integer.parseInt(tokenizer.nextToken());

        //on récupère chaque objet en créant un nouveau curseur avec un délimiteur différent puisque les objets sont délimités par des crochets "[" et "]" et séparés par un ";" dans le fichier
        String delimiteurs2 = "[;]";
        StringTokenizer tokenizer2 = new StringTokenizer(element, delimiteurs2);

        if (objSac==0){
            sac = new ArrayList<>();
            //si le sac est vide on passe juste le [;] avec le premier cusreur et le deuxième pour pouvoir l'utiliser pour la nourriture portée par le personnage
            tokenizer.nextToken();
            tokenizer2.nextToken();
            tokenizer2.nextToken();
        }
        else { //sinon on construit chaque objet
            
            tokenizer2.nextToken();//on ne récupère pas ce qu'il y a avant la liste des objets
            //puis on construit chaque objet
            sac = new ArrayList<>();
            for (int i=0; i<objSac;i++){
                String objet = tokenizer2.nextToken();
                //on créé un troisième curseur pour récupérer le type de l'objet
                StringTokenizer tokenizer3 = new StringTokenizer(objet, delimiteurs);
                String typeObj = tokenizer3.nextToken();
                Objet o ;
                switch(typeObj){
                    case "Nourriture":
                        o = new Nourriture(objet);
                        break;
                    case "Mana":
                        o = new Mana(objet);
                        break;
                    case "Soin":
                        o = new Soin(objet);
                        break;
                    default:
                        o=new NuageToxique(objet); //si la sauvegarde est bien faite l'objet ne peut qu'être un Nuage Toxique si ce n'est pas les autres cas
                        break;

                }
                sac.add(o);

                //on fait avancer le premier curseur de 4 pour qu'il passe les objets 
                for (int k=0;k<4;k++){
                    tokenizer.nextToken();
                }
            }
            tokenizer.nextToken(); //on passe le dernier ;] avec le premier curseur
        }
        
        //on récupère le nombre d'objets de type Nourriture 
        int nbreNourriture = Integer.parseInt(tokenizer.nextToken());
        bonusMalus = new ArrayList<>();
        if (nbreNourriture==0){
            //si lpersonnage n'a pas de nourriture on passe juste le [;] avec les premier curseur
            tokenizer.nextToken();
        }
        else { //sinon on construit chaque nourriture
            /*on récupère chaque nourriture en utilisant le même délimiteur que pour les objets puisque les nourritures du 
            personnage sont délimités par des crochets "[" et "]" et séparés par un ";" dans le fichier*/
            tokenizer2.nextToken(); //on décale le curseur pour sauter le numéro correspondant au nombre de nourriture situé entre les objets du sac et les objets nourriture
            for (int i=0; i<nbreNourriture;i++){
                String nourriture = tokenizer2.nextToken();
                Nourriture n = new Nourriture(nourriture);
                bonusMalus.add(n);
                //on fait avancer l'autre curseur de 7 pour qu'il passe les nourritures
                for (int k=1;k<8;k++){
                    tokenizer.nextToken();
                }
            }
            tokenizer.nextToken(); //on passe le dernier ;] avec le premier curseur
        }
        setPtVie(Integer.parseInt(tokenizer.nextToken()));
        setPourcentageAtt(Integer.parseInt(tokenizer.nextToken()));
        setPourcentagePar(Integer.parseInt(tokenizer.nextToken()));
        setDegAtt(Integer.parseInt(tokenizer.nextToken()));
        setPtPar(Integer.parseInt(tokenizer.nextToken()));
        int x=Integer.parseInt(tokenizer.nextToken());
        int y= Integer.parseInt(tokenizer.nextToken());
        setPos(new Point2D(x,y));  
        setControle(0); //ces personnages ne sont pas contrôlés     */ 
}


    public String getNom() {
        return nom;
    }

    public void setNom(String value) {
        nom = value;
    }

    public int getPtMana() {
        return ptMana;
    }

    public void setPtMana(int value) {
        ptMana = value;
    }

    public int getPourcentageMag() {
        return pourcentageMag;
    }

    public void setPourcentageMag(int value) {
        pourcentageMag = value;
    }

    public int getPourcentageResisMag() {
        return pourcentageResisMag;
    }

    public void setPourcentageResisMag(int value) {
        pourcentageResisMag = value;
    }

    public int getDegMag() {
        return degMag;
    }

    public void setDegMag(int value) {
        degMag = value;
    }

    public double getDistAttMax() {
        return distAttMax;
    }

    public void setDistAttMax(double distAttMax) {
        this.distAttMax = distAttMax;
    }

    public ArrayList<Objet> getSac() {
        return sac;
    }

    public void setSac(ArrayList<Objet> sac) {
        this.sac = sac;
    }

    public ArrayList<Nourriture> getBonusMalus() {
        return bonusMalus;
    }

    public void setBonusMalus(ArrayList<Nourriture> bonusmalus) {
        this.bonusMalus = bonusmalus;
    }

    public abstract void affiche();

    /**
     * Méthode permettant d'afficher le contenu du sac du personnage
     */
    public void afficheSac() {
        ArrayList<Objet> sac = getSac();
        if (sac == null) {
            System.out.println("Ce personnage n'a rien dans son sac.");
        } else {
            System.out.println("Ce personnage a dans son sac : ");
            for (Objet o : sac) {
                o.affiche();
            }
        }
    }

    /**
     * Méthode permettant d'afficher la nourriture portée par le personnage
     */
    public void afficheNourriture() {
        ArrayList<Nourriture> nourriture = getBonusMalus();
        if (nourriture == null) {
            System.out.println("Ce personnage n'a pas de nourriture sur lui");
        } else {
            System.out.println("Ce personnage a sur lui: ");
            for (Nourriture n : nourriture) {
                n.affiche();
            }
        }

    }

    public abstract void ramasser(Objet o, World w);

    /**
     * méthode qui permet de modifier les caractéristiques du personnage en
     * fonction de la nourriture qu'il ramasse
     * @param n nourriture mangée
     * @param i entier permettant d'activer l'effet de la nourriture (quand il vaut 1) ou de la désactiver (quand il vaut -1) 
     * pour retrouver les caractéristiques initiales
     */
    public void effetNourriture(Nourriture n, int i) {
        
        //Si i vaut 1 on active l'effet de la nourriture
        if (i==1){
            
            //Pour chacune des caractéristiques, il faut vérifier si la caractéristique n'est pas <0 quand on applique l'effet malus
            switch (n.getCaracteristique()) {
                case 1:
                    
                    int diff = this.getPourcentageAtt() + n.getPtEffet();
                    if (diff<0){                        
                        n.setPtEffet(this.getPourcentageAtt());
                        this.setPourcentageAtt(0);
                    }
                    else {
                        this.setPourcentageAtt(this.getPourcentageAtt() + n.getPtEffet());
                    }
                    break;
                case 2:
                    
                    diff = this.getPourcentagePar() + n.getPtEffet();
                    if (diff<0){
                        n.setPtEffet(this.getPourcentagePar());
                        
                        this.setPourcentagePar(0);
                    }
                    else {
                        this.setPourcentagePar(this.getPourcentagePar() + n.getPtEffet());
                    }
                    break;
                case 3:
                    diff = this.getDegAtt() + n.getPtEffet();
                    if (diff<0){
                        n.setPtEffet(this.getDegAtt());
                        this.setDegAtt(0);
                    }
                    else {
                        this.setDegAtt(this.getDegAtt() + n.getPtEffet());
                    }
                    break;
                case 4:
                    diff = this.getPtPar() + n.getPtEffet();
                    if (diff<0){
                        n.setPtEffet(this.getPtPar());
                        this.setPtPar(0);
                    }
                    else {
                        this.setPtPar(this.getPtPar() + n.getPtEffet());
                    }
                    break;
                case 5:
                    diff = this.getPourcentageResisMag() + n.getPtEffet();
                    if (diff<0){
                        n.setPtEffet(this.getPourcentageResisMag());
                        this.setPourcentageResisMag(0);
                    }
                    else {
                        this.setPourcentageResisMag(this.getPourcentageResisMag() + n.getPtEffet());
                    }
                    break;
                case 6:
                    /*¨Pour la distance d'attaque maximale, il sera possible qu'elle soit négative si la nourrtiture est un malus, mais cela ne pose pas de problème
                    car dans tous les cas il ne pourra pas attaquer quelqu'un car distMax<=0*/
                    this.setDistAttMax(this.getDistAttMax() + n.getPtEffet());
                    break;
            }
        }
        
        //Sinon on le désactive
        else {
            switch (n.getCaracteristique()) {
                case 1:
                    this.setPourcentageAtt(this.getPourcentageAtt() - n.getPtEffet());
                    break;
                case 2:
                    this.setPourcentagePar(this.getPourcentagePar() - n.getPtEffet());
                    break;
                case 3:
                    this.setDegAtt(this.getDegAtt() - n.getPtEffet());
                    break;
                case 4:
                    this.setPtPar(this.getPtPar() - n.getPtEffet());
                    break;
                case 5:
                    this.setPourcentageResisMag(this.getPourcentageResisMag() - n.getPtEffet());
                    break;
                case 6:
                    this.setDistAttMax(this.getDistAttMax() - n.getPtEffet());
                    break;
            }
        }
    }

    /**
     * méthode qui applique les effets des potions aux personnages
     *
     * @param o
     */
    public void effetPotion(Objet o) {
        if (o instanceof Soin) {
            this.setPtVie(this.getPtVie() + ((Soin) o).getPtRecup());
        }
    }

    /**
     * Verifie si il y a un aliment non active dans le sac
     *
     * @return true d'il y en a un, false s'ils sont tous actives
     */
    public boolean nourritureNonActivee() {
        boolean verif = false;
        for (Nourriture n : this.getBonusMalus()) {
            if (n.getEtat() == 0) {
                verif = true;
            }
        }
        return verif;
    }


    public void getTexteSauvegarde(BufferedWriter writer) throws IOException {
        writer.write(this.getClass().getSimpleName() + " ");
        if (this.getNom() != null) {
            writer.write(this.getNom() + " ");
        } else {
            writer.write("bob ");
        }
        writer.write(Integer.toString(this.getPtMana()) + " ");
        writer.write(Integer.toString(this.getPourcentageMag()) + " ");
        writer.write(Integer.toString(this.getPourcentageResisMag()) + " ");
        writer.write(Integer.toString(this.getDegMag()) + " ");
        writer.write(Double.toString(this.getDistAttMax()) + " ");
        writer.write(Integer.toString(this.getSac().size())+" ");
        writer.write("[");
        for(int i=0;i<this.getSac().size();i++){
            this.getSac().get(i).getTexteSauvegarde(writer);
            writer.write(" ;");
        }
        writer.write("] ");
        writer.write(Integer.toString(this.getBonusMalus().size())+" ");
        writer.write("[");
        for(int i=0;i<this.getBonusMalus().size();i++){
            this.getBonusMalus().get(i).getTexteSauvegarde(writer);
            writer.write(" ;");
        }
        writer.write("] ");
        writer.write(Integer.toString(this.getPtVie()) + " ");
        writer.write(Integer.toString(this.getPourcentageAtt()) + " ");
        writer.write(Integer.toString(this.getPourcentagePar()) + " ");
        writer.write(Integer.toString(this.getDegAtt()) + " ");
        writer.write(Integer.toString(this.getPtPar()) + " ");
        writer.write(Integer.toString(this.getPos().getX()) + " ");
        writer.write(Integer.toString(this.getPos().getY()) + " ");
        if (this.getControle()==1){
            writer.write(this.getNomjControle());
        }

    }
    
    /**
     * Retourne une créature dans la liste des créatures 
     * @param w
     * @return 
     */
    public Creature creaAttaquables (World w){
        ArrayList <Creature> cAtt=new ArrayList<>();
        Creature ci;
        Random rf=new Random();
        for (Creature c : w.getlCrea()){
            if (this.getPos().distance(c.getPos())!=0 && this.getPos().distance(c.getPos())<=this.distAttMax && c.getPtVie()>0){
                cAtt.add(c);
            }
        }
        if(cAtt.size()==0){
            ci=null;
        }
        else{
            ci=cAtt.get(rf.nextInt(cAtt.size()));
        }
        
        return ci;
    }
}
