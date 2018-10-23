
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
    
    /**
     * cet attribut contient l'objet sur lequel est placé le nuage si il y en a un 
     * cela permet à un nuage et un objet d'être au même endroit sur la map
     */
    private Objet objet;
 
    
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
        //On récupère l'objet si le nuage était sur un objet lors de la sauvegarde
        int aObjet=Integer.parseInt(tokenizer.nextToken());
        System.out.println(aObjet);
        if (aObjet==1){
            String delimiteurs2 = "[;]";
            StringTokenizer tokenizer2 = new StringTokenizer(element, delimiteurs2);
            tokenizer2.nextToken();
            String obj = tokenizer2.nextToken();
            //on créé un troisième curseur pour récupérer le type de l'objet
            StringTokenizer tokenizer3 = new StringTokenizer(obj, delimiteurs);
            String typeObj = tokenizer3.nextToken();
            Objet o ;
            switch(typeObj){
                case "Nourriture":
                    o = new Nourriture(obj);
                    break;
                case "Mana":
                    o = new Mana(obj);
                    break;
                default:
                    o = new Soin(obj);
                    break; //si la sauvegarde est bien faite l'objet ne peut qu'être un soin si ce n'est pas les autres cas
            }
            objet=o;
        }
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

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public Objet getObjet() {
        return objet;
    }
    
    /**
     * Méthode permettant d'afficher l'objet 
     */
    public void affiche(){
        System.out.println("Nuage Toxique ! Il a :"
                + "\n - "  + degAtt + " points de dégats d'attaque ;"
                + "\n - " + pourcentageAtt + "% de poucentage d'attaque ;");
        if (getObjet()!=null){
            System.out.println("Il est placé sur cet objet : "
                    + "\n - "  + getObjet() );
        }
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
        i=posAlea.nextInt(3)-1;
        j=posAlea.nextInt(3)-1;
        while (this.getPos().getX()+i<0 || this.getPos().getX()+i>w.getHauteur()-1 || 
                this.getPos().getY()+j<0 || this.getPos().getY()+j>w.getLargeur()-1){
            i=posAlea.nextInt(3)-1;
            j=posAlea.nextInt(3)-1;
        }
        Objet o1 = getObjet();
        if (o1!= null){
            w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].setObjet(o1);
            setObjet(null);
        }
        else {
            w.getMatMonde()[this.getPos().getX()][this.getPos().getY()].setObjet(null);
        }
        Objet o2 = w.getMatMonde()[this.getPos().getX()+i][this.getPos().getY()+j].getObjet();
        this.getPos().translate(i, j);
        if (o2!=null){
            setObjet(o2);
        }
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
        if (getObjet()==null){
            writer.write(" 0");
        }
        else {
            writer.write(" 1");
            writer.write(" [");
            this.getObjet().getTexteSauvegarde(writer);
            writer.write("] ");
        }
    }
    
    
    public String getAffichage(){        
        return "Tox";               
    }
}
