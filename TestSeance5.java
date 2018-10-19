package org.centrale.projet.objet;

import java.util.ArrayList;
import java.util.Scanner;

/**
 *Test de la séance 5
 * @author Thibault
 */
public class TestSeance5 {
    
    public static void main(String[] args){

        World w5= new World(5, 10, 10);       
        
        

        w5.creationJoueur();
        w5.creeMondeAlea();
        w5.getlJoueur().get(0).affiche();
        w5.affichemat();
        w5.tourDeJeu();
        w5.getlJoueur().get(0).getPerso().afficheSac();
        
        /**To
        
        w5.getlJoueur().get(0).affiche();
        
        
        w5.creationJoueur();
        w5.creationJoueur();
        
        w5.creeMondeAlea();
        
        w5.getlJoueur().get(0).getPerso().affiche();
        w5.getlJoueur().get(1).getPerso().affiche();
        w5.getlJoueur().get(2).getPerso().affiche();
        w5.affichemat();
        w5.getlJoueur().get(0).deplaceperso(w5);
        w5.getlJoueur().get(1).deplaceperso(w5);
        w5.affichemat();
        
        w5.getlJoueur().get(0).getPerso().affiche();
        w5.tourDeJeu();
        w5.getlJoueur().get(0).getPerso().affiche();
   
       
        ArrayList<Creature> cAtt = new ArrayList<>();
        Personnage p = new Archer();
        cAtt.add(p);
        if (cAtt.get(0) instanceof Monstre){
            System.out.println("Vrai");
        }
        else {

            System.out.println("Faux");}
           */
        /**Scanner scan = new Scanner(System.in);
        System.out.println("Entrez une position X :");
        String x=scan.next();
        int X=1;
        try { 
            X=Integer.parseInt(x);
        }
        catch (NumberFormatException e){
            System.out.println("il faut entrer un entier !");
        }
        finally {
            System.out.println(X);
        }**/

    }
}   