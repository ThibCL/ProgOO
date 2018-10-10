package org.centrale.projet.objet;

/**
 * Cette classe permet d'effectuer les tests des différents attributs et méthodes programmées à la séance 4
 * @author Mathilde
 */
public class TestSeance4 {
     public static void main(String[] args){
         
        //Parcours de nos conteneurs de Personnages puis de monstres 
        //et affichage de chaque élément
        World w = new World();
        w.creeMondeAlea();
        for (Personnage p : w.getlPerso()){
            p.getPos().affiche();
            System.out.println();
        }
        for (Monstre m : w.getlMonstre()){
            m.getPos().affiche();
            System.out.println();
        }
        for (Objet o : w.getlObjet()){
            o.getPos().affiche();
            System.out.println();
        }
     }
 
}
