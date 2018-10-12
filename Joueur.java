
package org.centrale.projet.objet;
import java.util.ArrayList;
import java.util.Scanner; 
/**
 *
 * @author Thibault
 */
public class Joueur {
    /**
     * Nom du joueur 
     */
    private String nomJoueur;
    /**
     * Personnage associé au joueur
     */
    private Personnage perso;


    public String getNomJoueur() {
        return nomJoueur;
    }
    
   
    public Personnage getPerso() {
        return perso;
    }

    public void setNomJoueur(String nomJoueur) {
        this.nomJoueur = nomJoueur;
    }

    public void setPerso(Personnage perso) {
        this.perso = perso;
    }
    /**
     * Méthode qui permet au joueur de choisir son personnage et le nom de celui ci
     */
    public void choisirperso(){
        System.out.println("Veuillez entrer un type de personnage: "
                + "\n -Archer"
                + "\n -Guerrier"
                + "\n -Mage"
                + "\n -Paysan");
        Scanner saisieUtilisateur = new Scanner(System.in); 
        String str = saisieUtilisateur.next(); 
        switch(str){
            case "Archer": this.perso= new Archer();
            case "Guerrier": this.perso=new Guerrier();
            case "Mage": this.perso=new Mage();
            case "Paysan": this.perso=new Paysan();
        }
        System.out.println("Entrez le nom de votre personnage :");
        String nm = saisieUtilisateur.next();
        this.perso.setNom(nm);
        
    }
    
    /** fonction renvoyant la liste des personnages pouvant être attaqués par le joueur selon sa position et sa distance max d'attaque 
     * 
     */
    public ArrayList<Creature> creaAttaquables (World w){
        ArrayList<Creature> cAtt = new ArrayList<>();
        for (Creature c : w.getlCrea()){
            if (this.getPerso().getPos().distance(c.getPos())<=this.getPerso().getDistAttMax()){
                cAtt.add(c);
            }
        }
        return cAtt;
    }
    /**
     * méthode qui permet au joeur d'attaquer une autre creature
     * @param w 
     */
    public void combattreperso(World w){
        ArrayList<Creature> cAtt = creaAttaquables(w);
        if (cAtt.size()==0){
            System.out.println("Impossible de te battre, tu n'as pas d'ennemi à portée d'attaque");
        }
        else {
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez le personnage que vous voulez combattre parmis les suivants :");
            for (Creature c : w.getlCrea()){
                c.affiche();
                }
            }
    }
       
            
    /**
     * méthode qui permet au joueur de décider ou deplacer son perso, la méthode vérifie si il y a une case autour de libre
     * et si c'est le cas demande des coordonnées au joueur jusqu'a ce que le deplacement soit possible. Une fois que le 
     * deplacement est possible la méthode appelle la méthode déplacer de creature
     * @param w 
     */
    public void deplaceperso(World w){
        if(this.perso.deplpossible(w)==false){
            System.out.println("Impossible de te déplacer, tu dois te battre");
            //this.combattreperso();
        }
        
        else{
            Scanner scan = new Scanner(System.in);
            System.out.println("Entrez une position X :");
            int x=scan.nextInt();
            System.out.println("Entrez une position Y :");
            int y=scan.nextInt();
            while (w.getMatMonde()[this.perso.getPos().getX()+x][this.perso.getPos().getY()+y]==1){
                System.out.println("Position déjà occupée, entrez une autre position!");
                System.out.println("Entrez une position X :");
                x=scan.nextInt();
                System.out.println("Entrez une position Y :");
                y=scan.nextInt();

            }
            this.perso.deplacer(w, x, y);
            
        }
        
    }
    
    public void affiche(){
        System.out.println("Le nom du joueur est "+ this.getNomJoueur()+ " il controle le personnage:");
        this.perso.affiche();
    }
    
    
    
    
}
