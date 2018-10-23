
package org.centrale.projet.objet;
import java.io.*;
import java.util.Random;
import java.util.StringTokenizer;
/**
 *
 * @author Thibault
 */
public class NuageToxique extends Objet implements Deplacable,Combattant {
    
    /**
     * Dégats que fait le nuage à la créature rentre dedans
     */
    private int degAtt;
    
    /**
     * Pourcentage de chance que le nuage blesse un joueur
     */
    private int pourcentageAtt;
 
    public NuageToxique(){
        super();
        Random rand=new Random();
        this.degAtt=rand.nextInt(11)+5;
        this.pourcentageAtt=50+rand.nextInt(26);
        
    }
    
    /**
     * Constructeur de Element à partir d'une ligne de la sauvegarde
     * @param element ligne de la sauvegarde comportant les caractéristiques du nuage à créer
     */
    public NuageToxique(String element){
        String delimiteurs = " ";
        StringTokenizer tokenizer;
        tokenizer = new StringTokenizer(element, delimiteurs);
        tokenizer.nextToken(); //on passe le nom
        int x = Integer.parseInt(tokenizer.nextToken());
        int y = Integer.parseInt(tokenizer.nextToken());
        setPos(new Point2D(x,y));
        degAtt = Integer.parseInt(tokenizer.nextToken());
        pourcentageAtt = Integer.parseInt(tokenizer.nextToken());
    }

    public int getDegAtt() {
        return degAtt;
    }

    public void setDegAtt(int degAtt) {
        this.degAtt = degAtt;
    }

    public int getPourcentageAtt() {
        return pourcentageAtt;
    }

    public void setPourcentageAtt(int pourcentageAtt) {
        this.pourcentageAtt = pourcentageAtt;
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
    

 /**
  * Méthode pour le déplacement du nuage toxique 
  * @param w monde dans lequel le nuage se déplace
  * @param i 
  * @param j 
  */   
    public void deplacer(World w, int i, int j){
        Random posAlea= new Random();
        while (this.getPos().getX()+i<0 || this.getPos().getX()+i>w.getHauteur()-1 || 
                this.getPos().getY()+j<0 || this.getPos().getY()+j>w.getLargeur()-1){
            i=posAlea.nextInt(3)-1;
            j=posAlea.nextInt(3)-1;
        }
        Objet o =w.getMatMonde()[this.getPos().getX()+i][this.getPos().getY()+j].getObjet();
        if (o!=null){
            w.getlObjet().remove(o);
        }
        w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].setObjet(null);
        this.getPos().translate(i, j);
        w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].setObjet(this);
        if(w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].getCreature()!=null){
            this.combattre(w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].getCreature());
        }
    }
    
    /**
     * Méthode permettant au nuage d'attaquer une créature lorsqu'il la rencontre en se déplaçant
     * @param c créature à attaquer
     */
    public void combattre(Creature c){
        Random r= new Random();
        if(r.nextInt(101)<=this.pourcentageAtt){
            c.setPtVie(c.getPtVie()-degAtt);
            System.out.println("Le "+c.getClass().getSimpleName()+" a perdu "+ this.getDegAtt()+ " points de vie");
        }
        else{
            System.out.println("Le nuage ne fait aucun dégats au "+c.getClass().getSimpleName());
        }
        
    }
    
    /**
     * 
     * @param writer
     * @throws IOException 
     */
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write(Integer.toString(degAtt)+" ");
        writer.write(Integer.toString(this.pourcentageAtt));
        
        
    }
    
    
    public String getAffichage(){        
        return "Tox";               
    }
}
