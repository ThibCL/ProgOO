package org.centrale.projet.objet;
import java.io.*;
import java.util.StringTokenizer;

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
    public ChargementPartie(String nomFichier){
        nomSauvegarde = nomFichier;
        try {
            lecteur = new BufferedReader(new FileReader(nomFichier));
        }
        catch (FileNotFoundException e){
            System.out.println("Sauvegarde introuvable !");
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
    
    
    
    public World chargerPartie(){
        String ligne;
        try {
            BufferedReader fichier =getLecteur();
            ligne = fichier.readLine();
            String delimiteurs = " ";
            StringTokenizer tokenizer = new StringTokenizer(ligne, delimiteurs);
            if(tokenizer.hasMoreTokens()) {
                String mot = tokenizer.nextToken();
                int largeur =  Integer.parseInt(tokenizer.nextToken());
                mot = tokenizer.nextToken();
                int hauteur =  Integer.parseInt(tokenizer.nextToken());
                mot = tokenizer.nextToken();
                int nbrePerso = Integer.parseInt(tokenizer.nextToken());
                mot = tokenizer.nextToken();
                int nbreObj = Integer.parseInt(tokenizer.nextToken());
                World w = new World(5, largeur, hauteur);
                for (int i=1; i<nbrePerso+1; i++){
                       
                }
                ligne = fichier.readLine();
                return w;
            }
        }
        catch (Exception e) {
            System.out.println("Le fichier ne peut pas être lu.");
        }
    }
    
}
