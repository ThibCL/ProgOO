package org.centrale.projet.objet;

/**
 * Cette classe permet d'effectuer les tests des différents attributs et méthodes programmées à la séance 2
 * @author Mathilde
 */
public class TestSeance2 {
     public static void main(String[] args){
         World w = new World();
         w.getRobin().getPos().affiche();
         w.getBugs1().getPos().affiche();
         w.getPaon().getPos().affiche();
         w.getRobin().deplace();
         w.getPaon().deplace();
         w.getBugs1().deplace();
         w.getRobin().getPos().affiche();
         w.getBugs1().getPos().affiche();
         w.getPaon().getPos().affiche();
         
         World w2 = new World();
         w2.getRobin().getPos().affiche();
         w2.getGuillaumeT().getPos().affiche();
         w2.getRobin().deplace();
         w2.getRobin().getPos().affiche();
         w2.getGuillaumeT().getPos().affiche();
         
        double dist = w2.getRobin().getPos().distance(w2.getGuillaumeT().getPos());
        System.out.println("la distance entre robin et guillaumeT est de "+dist+" cases");
        
        w2.creeMondeAlea();
        w2.getRobin().getPos().affiche();
        w2.getBugs1().getPos().affiche();
        w2.getBugs2().getPos().affiche();
        w2.getWolfe().getPos().affiche();
        w2.getPaon().getPos().affiche();
        w2.getGuillaumeT().getPos().affiche();
        w2.getMerlin().getPos().affiche();
        w2.getGrosBill().getPos().affiche();
        
     }
}
