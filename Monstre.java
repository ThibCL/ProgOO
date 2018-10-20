package org.centrale.projet.objet;

import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;


/**
 * Classe qui est la super classe de tous les montres qui vont être créés tels
 * que les lapins ou les loups (sous-classe de Creature)
 *
 * @author Mathilde
 */
public abstract class Monstre extends Creature {

    /**
     * Constructeur de Monstre qui prend en parametre toutes les valeurs des
     * attributs a initialiser
     *
     * @param pV
     * @param pA
     * @param pP
     * @param dA
     * @param ptPar
     * @param pos
     */
    public Monstre(int pV, int pA, int pP, int dA, int ptPar, Point2D pos) {
        super(pV, pA, pP, dA, ptPar, pos);
    }

    /**
     * Constructeur de Monstre qui initialise les attributs a partir des
     * attributs du Monstre mis en parametre
     *
     * @param m
     */
    public Monstre(Monstre m) {
        super(m);
    }
    
/**
 * Constructeur de Monstre par default qui initialise les attributs a des valeurs choisies par default
 */
    public Monstre(){
        super();
    }
    
    /**
     * Constructeur prenant en argument la ligne correspondant à la sauvegarde du monstre dans un fichier et recréant le personnage correspondant
     * @param element ligne correspondant à la sauvegarde du monstre dans un fichier
     */
    public Monstre(String element){
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);
        tokenizer.nextToken();//on passe le type de monstre
        setPtVie(Integer.parseInt(tokenizer.nextToken()));
        setPourcentageAtt(Integer.parseInt(tokenizer.nextToken()));
        setPourcentagePar(Integer.parseInt(tokenizer.nextToken()));
        setDegAtt(Integer.parseInt(tokenizer.nextToken()));
        setPtPar(Integer.parseInt(tokenizer.nextToken()));
        int x=Integer.parseInt(tokenizer.nextToken());
        int y= Integer.parseInt(tokenizer.nextToken());
        setPos(new Point2D(x,y));   
        setControle(0);
    }


    /**
     * Méthode qui permet d'afficher tous les atttributs du monstre
     */
    public abstract void affiche();/*{
        super.affiche();
    }*/


    public void getTexteSauvegarde(BufferedWriter writer) throws IOException {
        writer.write(this.getClass().getSimpleName() + " ");
        writer.write(Integer.toString(this.getPtVie()) + " ");
        writer.write(Integer.toString(this.getPourcentageAtt()) + " ");
        writer.write(Integer.toString(this.getPourcentagePar()) + " ");
        writer.write(Integer.toString(this.getDegAtt()) + " ");
        writer.write(Integer.toString(this.getPtPar()) + " ");
        writer.write(Integer.toString(this.getPos().getX()) + " ");
        writer.write(Integer.toString(this.getPos().getY()) + " ");
        writer.newLine();
    }
}
