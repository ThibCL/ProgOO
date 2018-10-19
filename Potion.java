package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Représente un objet de type Potion (sous-classe de potion)
 * @author Thibault
 */
public abstract class Potion extends Objet {
    
    /**
     * nombre de point de vie ou de mana recupéré grace a la potion
     */
    private int ptRecup;
    
    public Potion(Point2D pos, int ptR){
        super(pos);
        ptRecup = ptR;
    }
    
    public Potion(){
        super();
        Random intAlea= new Random();
        ptRecup = 5+intAlea.nextInt(15);
    }

    public int getPtRecup() {
        return ptRecup;
    }

    public void setPtRecup(int ptRecup) {
        this.ptRecup = ptRecup;
    }
    
    public abstract void affiche();
    public abstract void caracteristiques();
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write(Integer.toString(this.getPtRecup())+ " ");
        writer.newLine();
    }
}
