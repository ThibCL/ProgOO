package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.IOException;

/**
 * Cette classe repérsente une Potion de type soin, c'est un objet permettant de soigner les créatures du monde l'utilisant 
 * (redonne des points de vie)
 * @author Thibault
 */
public class Soin extends Potion{
    
    public Soin(Point2D pos, int ptRecup){
        super(pos,ptRecup);
    }
    
    public Soin(){
        super();
    }
    
    /**
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){
        System.out.println("Potion de Soin ("+getPtRecup()+" pts de Vie)");
    }
    
    /**
    * Méthode permettant d'afficher les caractéristqiues de l'objet : effet, quantité,...
    */
    public void caracteristiques(){
        System.out.println("Cette potion permet de récupérer "+getPtRecup()+" points de Vie");
    }
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);

    }
}
