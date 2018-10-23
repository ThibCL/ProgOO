
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
                    choix=sc.next();
                    testi=new SauvegardePartie(choix);
                    w= new World(5,10,10);
                    System.out.println("Combien de joueurs êtes vous ?");
                    boolean entier = false; 
                    int rep=0;
                    while (entier==false){
                        try{
                             rep=sc.nextInt();
                             entier=true;
                        }
                        catch(InputMismatchException e){
                            System.out.println("Il faut entrer un entier ! Combien de joueurs êtes vous ?");
                            entier=false;
                        }
                    }
                    for (int i=0; i<rep;i++){
                        w.creationJoueur();
                        w.getlJoueur().get(i).setNumero(i);
                    }
                    w.creeMondeAlea();
                    break;
                case "c":
                    choisi=true;
                    System.out.println("Chargement d'une partie : Entrez le nom de la sauvegarde :");
                    boolean fichierExistant=false;
                    while (fichierExistant == false){
                        choix=sc.next();
                        try{
                            ChargementPartie partie = new ChargementPartie(choix);
                             w=partie.chargerPartie();
                            fichierExistant=true;
                        }
                        catch (FileNotFoundException e){
                            System.out.println("Entrez le nom de la sauvegarde :");
                            fichierExistant=false;
                            
                        }
                    }
                    testi=new SauvegardePartie(choix);
                    break;
                default :
                    System.out.println("Ce n'est pas une action possible!");
                    System.out.println("Voulez vous :" +
                        "\n - Commencer une nouvelle partie : tapez 'new' ;" +
                        "\n - Charger une partie existante : tapez 'charger' ;");
            }
        }
    return testi;
    }
}
    
