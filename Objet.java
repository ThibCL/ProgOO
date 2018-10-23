package org.centrale.projet.objet;

import java.util.StringTokenizer;
import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Représente un objet dans le monde : c'est une super classe de tous les objets du monde
 * @author Thibault
 */
public abstract class Objet extends ElementDeJeu {
    
    /**
     * Represente la position d'un objet dans le monde
     */
    private Point2D pos;


    public Objet(Point2D pos) {
        this.pos = new Point2D(pos);
    }
    
    
    public Objet() {
        this.pos = new Point2D();
    }
    
    /**
     * Constructeur de Objet à partir d'une ligne de la sauvegarde
     * @param element ligne de la sauvegarde comportant les caractéristiques de l'objet à créer
     */
    public Objet(String element){
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);
        tokenizer.nextToken();//on passe le type de l'objet
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        pos = new Point2D(x,y);
    }

    public Point2D getPos() {
        return pos;
    }

    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    
    /**
     * Méthode permettant d'afficher l'objet 
     */
    public abstract void affiche();
    
    /**
     * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
     */
    public abstract void caracteristiques();
    
    
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        writer.write(this.getClass().getSimpleName()+" ");
        writer.write(Integer.toString(this.getPos().getX())+" ");
       
        writer.write(Integer.toString(this.getPos().getY())+" ");

    }
    
    public abstract String getAffichage();

}
    
    
    
    
 
