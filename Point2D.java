package org.centrale.projet.objet;
import java.lang.Math;

/**
 * Cette classe représente un point à coordonnées entières dans un plan cartésien à deux dimensions
 * @author Mathilde
 */
public class Point2D {
    
    private int x;
    private int y;
  
/**
 * Constructeur de Point2D à partir de deux paramètres entiers
 * @param x
 * @param y 
 */
    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }
    
/**
 * Constructeur de Point2D à partir d'un autre Point2D (constructeur de recopie)
 * @param p 
 */
    public Point2D(Point2D p) {
        x = p.x;
        y = p.y;
    }
    
/**
 * Constructeur de Point2D sans paramètre initialisant les coordonnées à (0,0)
 */
     public Point2D() {
        x = 0;
        y = 0;
    }
    
    public void setX(int x) {
        this.x = x;
    }
    
    public int getX() {
        return x;
    }
    
    public void setY(int y) {
        this.y = y;
    }
    
    public int getY() {
        return y;
    }
    
    public void setPosition(int x, int y){
        this.x=x;
        this.y=y;
    }
    
/**
 * Méthode permettant d'ajouter un incrément (négatif ou positif) aux coordonnées
 * @param dx
 * @param dy 
 */
    public void translate(int dx, int dy){
        x+=dx;
        y+=dy;
    }
    
    /**
     * Méthode permettant d'afficher un Point2D 
     */
    public void affiche(){
        System.out.println("Position : ["+x+" ; "+y+"]");
    }
    
    /**
     * Méthode permettant de calculer la distance entre le Point2D et le Poin2D entré en paramètre 
     * @param p1
     * @return 
     */
    public double distance(Point2D p1){
        double dx=Math.pow(p1.getX()-x,2);
        double dy=Math.pow(p1.getY()-y,2);
        return Math.sqrt(dx+dy);
    }
}


