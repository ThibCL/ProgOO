package org.centrale.projet.objet;

import java.io.*;

/**
 *
 * @author Thibault
 */
public class SauvegardePartie {

    private BufferedWriter writer;
    private String filename;

    public SauvegardePartie(String filename) {
        try {

            this.writer = new BufferedWriter(new FileWriter(filename + ".txt"));

        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } // on attrape l'exception si il y a un probleme lors de l'ecriture dans le fichier
        catch (IOException ex) {
            ex.printStackTrace();
        }

        this.filename = filename;
    }

    public BufferedWriter getWriter() {
        return writer;
    }

    public String getFilename() {
        return filename;
    }

    public void setWriter(BufferedWriter writer) {
        this.writer = writer;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public void sauvegarderPartie(World w) throws IOException {
        writer.write("Largeur " + Integer.toString(w.getLargeur()));
        writer.newLine();
        writer.write("Hauteur " + Integer.toString(w.getHauteur()));

        writer.newLine();
        writer.write("NombrePerso " + Integer.toString(w.nombrePerso()));

        writer.newLine();
        writer.write("NombreMonstre " + Integer.toString(w.nombreMonstre()));
        writer.newLine();
        writer.write("NombreObjet " + Integer.toString(w.getlObjet().size()));
        writer.newLine();
        writer.write("NombreJoueur " + Integer.toString(w.getlJoueur().size()));
        writer.newLine();
        for (Creature c : w.getlCrea()) {
            if (c instanceof Personnage && c.getControle() == 0) {
                ((Personnage) c).getTexteSauvegarde(writer);
                writer.newLine();
           
                
            } else if (c instanceof Monstre) {
                ((Monstre) c).getTexteSauvegarde(writer);
                writer.newLine();
            }

        }
        for (Objet o : w.getlObjet()) {
            o.getTexteSauvegarde(writer);
            writer.newLine();
        }
        for (Joueur j : w.getlJoueur()) {
            j.getTexteSauvegarde(writer);
        }
        try {

            writer.close();

        } // on attrape l'exception potentielle 
        catch (IOException ex) {
            ex.printStackTrace();
        }
    }

}
