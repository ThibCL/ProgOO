
package org.centrale.projet.objet;

/**
 *
 * @author Thibault
 */
public class NuageToxique extends Objet implements Deplacable,Combattant {
 
    public NuageToxique(){
        super();
    }
    
    public NuageToxique(String element){
        super(element);
    }
    
/**
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){
        System.out.println("Nuage Toxique");
    }
    
    /**
    * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
    */
    public void caracteristiques(){
        System.out.println("Ce nuage est toxique");
    }
    
    public void deplacer(World w, int i, int j){
        
    }
    public void combattre(Creature c){
        
    }
}
