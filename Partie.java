
package org.centrale.projet.objet;

import java.io.FileNotFoundException;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Cette classe permet le lancement d'une partie par un ou plusieurs joueurs
 * @author Mathilde
 */
public class Partie {
    /**
    * Représente le monde dans lequel va être joué la partie
    **/
    private World w ; 

    public World getW() {
        return w;
    }

    public void setW(World w) {
        this.w = w;
    }
    
    public SauvegardePartie lancerPartie(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Bienvenue dans World of ECN !");
        System.out.println("Voulez vous :" +
                "\n - Commencer une nouvelle partie : tapez 'n' ;" +
                "\n - Charger une partie existante : tapez 'c' ;");
        boolean choisi = false;
        SauvegardePartie testi=new SauvegardePartie("SauvegardeNulle");
        while (choisi == false) {
            String choix = sc.next();
            switch (choix) {
                case "n":
                    choisi=true;
                    System.out.println("Nouvelle partie ! Entrez un nom de sauvegarde :");
                    
                    //On teste si la sauvegarde n'existe pas déjà afin de s'assurer que la nouvelle partie n'écrase pas une autre !
                    boolean fichierExistant=true;
                    while (fichierExistant == true){
                        choix=sc.next();
                        try{
                            ChargementPartie partieExistante = new ChargementPartie(choix);
                            System.out.println("Ce nom de sauvegarde existe déjà, choisissez en un autre.");
                            fichierExistant=true;
                        }
                        catch (FileNotFoundException e){
                            fichierExistant=false;
                        }
                    }
                    testi=new SauvegardePartie(choix);
                    w= new World(5,10,10);
                    System.out.println("Combien de joueurs êtes vous ?");
                    boolean entier = false; 
                    int rep=0;
                    while (entier==false){
                        String reponse=sc.next();
                        try{
                             rep=Integer.parseInt(reponse);
                             entier=true;
                        }
                        catch(NumberFormatException e){
                            System.out.println("Il faut entrer un entier ! Combien de joueurs êtes vous ?");
                            entier=false;
                        }
                    }
                    for (int i=1; i<rep+1;i++){
                        System.out.println("\nJoueur "+ i +":");
                        w.creationJoueur();
                        w.getlJoueur().get(i-1).setNumero(i);
                        w.getlJoueur().get(i-1).getPerso().setNomjControle("J"+i+" ");
                    }
                    w.creeMondeAlea();
                    break;
                case "c":
                    choisi=true;
                    System.out.println("Chargement d'une partie : Entrez le nom de la sauvegarde :");
                    fichierExistant=false;
                    while (fichierExistant == false){
                        choix=sc.next();
                        try{
                            ChargementPartie partie = new ChargementPartie(choix);
                             w=partie.chargerPartie();
                            fichierExistant=true;
                        }
                        catch (FileNotFoundException e){
                            System.out.println("Sauvegarde introuvable !");
                            System.out.println("Entrez le nom de la sauvegarde :");
                            fichierExistant=false;
                            
                        }
                    }
                    testi=new SauvegardePartie(choix);
                break;
                default :
                    System.out.println("Ce n'est pas une action possible!");
                    System.out.println("Voulez vous :" +
                        "\n - Commencer une nouvelle partie : tapez 'n' ;" +
                        "\n - Charger une partie existante : tapez 'c' ;");
            }
        }
    return testi;
    }
}
    
