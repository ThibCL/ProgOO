
package org.centrale.projet.objet;
import java.io.*;
import java.util.Random;
/**
 *
 * @author Thibault
 */
public class NuageToxique extends Objet implements Deplacable,Combattant {
    
    /**
     * Dégats que fait le nuage quand un personnage rentre dedans
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
    
    public NuageToxique(String element){
        super(element);
        
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
    
    public boolean deplpossible(World w){
        boolean verif=false;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.getPos().getX()+i-1>-1 && this.getPos().getX()+i-1<w.getHauteur()-1 && this.getPos().getY()+j-1>-1 && this.getPos().getY()+j-1<w.getLargeur()-1){
                    if(w.getMatMonde()[this.getPos().getX()+i-1][this.getPos().getY()+j-1].getObjet()==null){
                        verif=true;
                    }
                }
            }
        }
        return verif;
        
    }
    
    public void deplacer(World w, int i, int j){
        if(deplpossible(w)==false){
            System.out.println("La creature ne peut pas se déplacer");
            
        }
        else {
            Random posAlea= new Random();
            while (this.getPos().getX()+i<0 || this.getPos().getX()+i>w.getHauteur()-1 || 
                    this.getPos().getY()+j<0 || this.getPos().getY()+j>w.getLargeur()-1 || 
                    w.getMatMonde()[this.getPos().getX()+i][this.getPos().getY()+j].getObjet()!=null){
                i=posAlea.nextInt(3)-1;
                j=posAlea.nextInt(3)-1;
            }
        }
        w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].setObjet(null);
        this.getPos().translate(i, j);
        w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].setObjet(this);
        if(w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].getCreature()!=null){
            this.combattre(w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].getCreature());
        }
    }
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
    
    public void getTexteSauvegarde(BufferedWriter writer) throws IOException{
        super.getTexteSauvegarde(writer);
        writer.write(Integer.toString(degAtt)+" ");
        writer.write(Integer.toString(this.pourcentageAtt));
        
        
    }
    
    
    public String getAffichage(){        
        return "N";               
    }
}
