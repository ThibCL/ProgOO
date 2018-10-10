/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.centrale.projet.objet;

/**
 *
 * @author Mathilde
 */
public class TestPoint2D {
    public static void main(String[] args){
        Point2D p1 = new Point2D();
        Point2D p2 = new Point2D(p1);
        Point2D p3 = new Point2D(4,5);
        p1.affiche();
        p2.affiche();
        p3.affiche();  
        p1.getX();
        p1.setPosition(10,3);
        p2.translate(1,1);
        p1.affiche();
        p2.affiche();
        double d= p1.distance(p2);
        System.out.println(d);
    }
}
