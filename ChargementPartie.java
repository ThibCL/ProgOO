package org.centrale.projet.objet;
import java.io.*;
import java.util.StringTokenizer;
import java.util.ArrayList;

/**
 * Classe permettant de charger une partie ayant été précedemment sauvegardée
 * @author Mathilde
 */
public class ChargementPartie {
    /**
     * nom du fichier de sauvegarde à charger 
     */
    private String nomSauvegarde;
    /**
     * attribut permettant la lecture du fichier 
     */
    private BufferedReader lecteur;
    
    /**
     * construit le chargement à partir de
     * @param nomFichier nom du fichier à charger 
     */
    public ChargementPartie(String nomFichier) throws FileNotFoundException{
        nomSauvegarde = nomFichier;
        try {
            lecteur = new BufferedReader(new FileReader(nomSauvegarde + ".txt"));
        }
        catch (FileNotFoundException e){
            System.out.println("Sauvegarde introuvable !");
            throw new FileNotFoundException();
        }
    }

    public String getNomSauvegarde() {
        return nomSauvegarde;
    }

    public BufferedReader getLecteur() {
        return lecteur;
    }

    public void setNomSauvegarde(String nomSauvegarde) {
        this.nomSauvegarde = nomSauvegarde;
    }

    public void setLecteur(BufferedReader lecteur) {
        this.lecteur = lecteur;
    }
    
    public ElementDeJeu creerElementJeu(String element, String type){
        ElementDeJeu e;
        
        switch (type){
            case "Archer":
                e = new Archer(element);
                break;
            case "Guerrier":
                e = new Guerrier(element);
                break;
            case "Paysan":
                e = new Paysan(element);
                break;
            case "Mage":
                e = new Mage(element);
                break;
            case "Lapin":
                e = new Lapin(element);
                break;
            case "Loup":
                e = new Loup(element);
                break;
            case "Mana":
                e= new Mana(element);
                break;
            case "Soin":
                e= new Soin(element);
                break;
            case "Nourriture":
                e= new Nourriture(element);
                break;
            default : //si la sauvegarde a été bien faite, le seul type restant possible est nuage toxique
                e=new NuageToxique(element);
                break;
            
        }
        return(e);
    }
    
    public World chargerPartie(){
        String ligne;
        try {
            BufferedReader fichier =getLecteur();
            String delimiteurs = " ";
            StringTokenizer tokenizer;
            String mot;
            int[] parametres = new int[5];
            
            //On récupère les parametres du jeu que l'on place dans le tableau parametre
            //d'abord la largeur du monde, puis la hauteur, puis le nombre de créatures, puis le nombre d'objet et pour finir le nombre de joueurs
            for (int i=0; i<5; i++){                
                ligne = fichier.readLine();
                tokenizer = new StringTokenizer(ligne, delimiteurs);
                mot = tokenizer.nextToken();
                int k =  Integer.parseInt(tokenizer.nextToken());
                parametres[i]=k;                
            }

            //on créé le monde vide 
            World w = new World(parametres[1], parametres[0]);
            
            //on créé les personnages et les monstres
            for (int i=0; i<parametres[2]-parametres[4]; i++){
                ligne = fichier.readLine();
                tokenizer = new StringTokenizer(ligne, delimiteurs);
                String type = tokenizer.nextToken();
                ElementDeJeu e =creerElementJeu(ligne, type);
                Creature c =(Creature)(e);
                w.ajouterCrea(c);
            }
            
            //On créé les objets du monde 
            for (int i=0; i<parametres[3];i++){
                ligne = fichier.readLine();
                tokenizer = new StringTokenizer(ligne, delimiteurs);
                String type = tokenizer.nextToken();
                Objet o = (Objet)(creerElementJeu(ligne, type));
                w.ajouterObjet(o);
            }
            
            //On créé les joueurs 
            for (int i=0;i<parametres[4];i++){
                ligne = fichier.readLine();
                Joueur j = new Joueur();
                String delimiteur2="/";
                StringTokenizer tokenizer2 = new StringTokenizer(ligne, delimiteur2);
                tokenizer2.nextToken(); //on passe le mot "Joueur"
                String persoJoueur = tokenizer2.nextToken();
                //on récupère le type du personnage du joueur 
                tokenizer = new StringTokenizer(persoJoueur, delimiteurs);
                String typePerso = tokenizer.nextToken();
                j.setPerso((Personnage)(creerElementJeu(ligne, typePerso)));
                j.getPerso().setControle(1);
                int h=i+1;
                j.setNumero(h);
                j.getPerso().setNomjControle("J"+h+" ");
                w.ajouterCrea(j.getPerso());
                w.ajouterJoueur(j);
            }
            
            return w;
            }
        catch (IOException e) {
            System.out.println("Le fichier ne peut pas être lu.");
            World w = new World(50,50);//on créé un monde par default si il n'y a pas de partie chargée
            return w;
        }
    }
    
}
