
package org.centrale.projet.objet;

import java.util.ArrayList;

/**
 *Test de la séance 5
 * @author Thibault
 */
public class TestSeance5 {
    public static void main(String[] args){
        /**World w5= new World();
        w5.creationJoueur();
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
        */
       Archer p = new Archer();
       Mana ob = new Mana();
       p.getSac().add(ob);
       p.afficheSac();
}
}
