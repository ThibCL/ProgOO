package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.StringTokenizer;
import java.io.*;

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
    private ArrayList<Nourriture> bonusmalus;

    /**
     * Constructeur de Personnage qui prend en parametre toutes les valeurs des
     * attributs a initialiser
     *
     * @param nom Nom du personnage créé
     * @param ptV Points de vie du personnage créé
     * @param ptM Points de mana du personnage créé
     * @param pA Points d'attaque du personnage créé
     * @param pP Points de parade du personnage créé
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
        this.sac = sac;
        this.bonusmalus = bonusmalus;
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
        bonusmalus = new ArrayList(perso.getBonusmalus());
    }

    //A améliorer avec Random
    /**
     * Constructeur de personnage par default qui initialise les attributs a des
     * valeurs choisies par default
     */
    public Personnage() {
        super();
        ptMana = 0;
        pourcentageMag = 0;
        pourcentageResisMag = 40;
        degMag = 0;
        distAttMax = 1.42;
        sac = new ArrayList<>();
        bonusmalus = new ArrayList<>();
    }
    
    /**
     * Constructeur prenant en argument la ligne correspondant à la sauvegarde du personnage dans un fichier et recréant le personnage correspondant
     * @param element ligne correspondant à la sauvegarde du personnage dans un fichier
     */
    public Personnage(String element){
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);

        String typePerso = tokenizer.nextToken();
        String nom = tokenizer.nextToken();
        ptMana = Integer.parseInt(tokenizer.nextToken());
        pourcentageMag= Integer.parseInt(tokenizer.nextToken());
        pourcentageResisMag= Integer.parseInt(tokenizer.nextToken());
        degMag = Integer.parseInt(tokenizer.nextToken());
        distAttMax = Float.parseFloat(tokenizer.nextToken());

        //on récupère le nombre d'objets dans le sac
        int objSac = Integer.parseInt(tokenizer.nextToken());
        ArrayList<Objet> sac = new ArrayList<>();
        //on récupère chaque objet en créant un nouveau curseur avec un délimiteur différent puisque les objets sont délimités par des crochets "[" et "]" et séparés par un ";" dans le fichier
        String delimiteurs2 = "[;]";
        StringTokenizer tokenizer2 = new StringTokenizer(element, delimiteurs2);
        tokenizer2.nextToken(); //on ne récupère pas ce qu'il y a avant la liste des objets
        //puis on construit chaque objet
        for (int i=0; i<objSac;i++){
            String objet = tokenizer2.nextToken();

            //on créé un troisième curseur pour récupérer le type de l'objet
            StringTokenizer tokenizer3 = new StringTokenizer(objet, delimiteurs);
            String typeObj = tokenizer3.nextToken();
            Objet o ;
            switch(typeObj){
                case "Nourriture":
                    o = new Nourriture(objet);
                case "Mana":
                    o = new Mana(objet);
                case "Soin":
                    o = new Soin(objet);
                default:
                    o=new NuageToxique(objet); //si la sauvegarde est bien faite l'objet ne peut qu'être un Nuage Toxique si ce n'est pas les autres cas
                            
            }
            sac.add(o);
            }

            //on fait avancer le premier curseur de 4 pour qu'il passe les objets 
            for (int k=0;k<4;k++){
                tokenizer.nextToken();
            }

        //on récupère le nombre d'objets de type Nourriture 
        int nbreNourriture = Integer.parseInt(tokenizer.nextToken());
        ArrayList<Nourriture> bonusmalus = new ArrayList<>();
        /*on récupère chaque nourriture en utilisant le même délimiteur que pour les objets puisque les nourritures du 
        personnage sont délimités par des crochets "[" et "]" et séparés par un ";" dans le fichier*/
        tokenizer2.nextToken(); //on décale le curseur pour sauter le numéro correspondant au nombre de nourriture situé entre les objets du sac et les objets nourriture
        for (int i=0; i<nbreNourriture;i++){
            String nourriture = tokenizer2.nextToken();
            Nourriture n = new Nourriture(nourriture);
            bonusmalus.add(n);
            //on fait avancer l'autre curseur de 7 pour qu'il passe les nourritures
            for (int k=1;k<8;k++){
                tokenizer.nextToken();
            }
        }
        setPtVie(Integer.parseInt(tokenizer.nextToken()));
        setPourcentageAtt(Integer.parseInt(tokenizer.nextToken()));
        setPourcentagePar(Integer.parseInt(tokenizer.nextToken()));
        setDegAtt(Integer.parseInt(tokenizer.nextToken()));
        setPtPar(Integer.parseInt(tokenizer.nextToken()));
        int x=Integer.parseInt(tokenizer.nextToken());
        int y= Integer.parseInt(tokenizer.nextToken());
        Point2D pos = new Point2D(x,y);     
        setControle(0); //ces personnages ne sont pas contrôlés        
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

    public ArrayList<Nourriture> getBonusmalus() {
        return bonusmalus;
    }

    public void setBonusmalus(ArrayList<Nourriture> bonusmalus) {
        this.bonusmalus = bonusmalus;
    }

    public abstract void affiche();

    public void afficheSac() {
        ArrayList<Objet> sac = getSac();
        if (sac == null) {
            System.out.println("Ce personnage n'a rien dans son sac.");
        } else {
            for (Objet o : sac) {
                System.out.print("Ce personnage a dans son sac un(e) ");
                o.affiche();
            }
        }
    }

    public abstract void ramasser(Objet o, World w);

    /**
     * méthode qui permet de modifier les caractéristique du personnage en
     * fonction de la nourriture qu'il ramasse
     *
     * @param i vaut 1 ou -1 si le personnage ramasse ou si la duree s'est
     * écoulé
     */
    public void effetnourriture(Nourriture n, int i) {
        

            switch (n.getCaracteristique()) {
                case 1:
                    this.setPourcentageAtt(this.getPourcentageAtt() + n.getPteffet() * i);
                    break;
                case 2:
                    this.setPourcentagePar(this.getPourcentagePar() + n.getPteffet() * i);
                    break;
                case 3:
                    this.setDegAtt(this.getDegAtt() + n.getPteffet() * i);
                    break;
                case 4:
                    this.setPtPar(this.getPtPar() + n.getPteffet() * i);
                    break;
                case 5:
                    this.setPourcentageAtt(this.getPourcentageAtt() + n.getPteffet() * i);
                    break;
                case 6:
                    this.setPourcentageResisMag(this.getPourcentageResisMag() + n.getPteffet() * i);
                    break;
                case 7:
                    this.setDistAttMax(this.getDistAttMax() + n.getPteffet() * i);
                    break;
            }
        
    }
    /**
     * méthode qui applique les effet des potions aux personnages
     * @param o 
     */
    public void effetPotion(Objet o){
        if(o instanceof Soin){
            this.setPtVie(this.getPtVie()+((Soin) o).getPtRecup());
        }
    }
    /**
     * Verifie si il y a un aliment non active dans le sac
     * @return true d'il y en a un, false s'ils sont tous actives
     */
    public boolean nourritureNonActivee(){
        boolean verif=false;
        for(Nourriture n: this.getBonusmalus()){
            if(n.getEtat()==0){
                verif=true;
            }
        }
        return verif;
    }
    
    
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        writer.write(this.getClass().getSimpleName()+" ");
                if(this.getNom()!=null){
                    writer.write(this.getNom()+" ");
                }
                else{
                    writer.write("bob ");
                }
                writer.write(Integer.toString(this.getPtMana())+" ");
                writer.write(Integer.toString(this.getPourcentageMag())+" ");
                writer.write(Integer.toString(this.getPourcentageResisMag())+" ");
                writer.write(Integer.toString(this.getDegMag())+" ");
                writer.write(Double.toString(this.getDistAttMax())+" ");
                //writer.write(Integer.toString(this)+" ");
                //writer.write(Integer.toString(this)+" ");
                writer.write(Integer.toString(this.getPtVie())+" ");
                writer.write(Integer.toString(this.getPourcentageAtt())+" ");
                writer.write(Integer.toString(this.getPourcentagePar())+" ");
                writer.write(Integer.toString(this.getDegAtt())+" ");
                writer.write(Integer.toString(this.getPtPar())+" ");
                writer.write(Integer.toString(this.getPos().getX())+" ");
                writer.write(Integer.toString(this.getPos().getY())+" ");
                writer.newLine();
    }
}

