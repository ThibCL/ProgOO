package org.centrale.projet.objet;

import java.io.BufferedWriter;
import java.io.IOException;
import java.util.Random;
import java.util.StringTokenizer;

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
    
    public Potion(String element){
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);
        tokenizer.nextToken(); //on passe le nom
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        setPos(new Point2D(x,y));
        ptRecup = Integer.parseInt(tokenizer.nextToken());
    }
    
    public int getPtRecup() {
        return ptRecup;
    }

    public void setPtRecup(int ptRecup) {
        this.ptRecup = ptRecup;
    }
    
    @Override
    public abstract void affiche();

    @Override
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write(Integer.toString(this.getPtRecup())+ "");

    }
}
