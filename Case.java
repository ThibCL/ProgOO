
package org.centrale.projet.objet;

/**
 * Cette classe représente une case de la matrice du monde. 
 * Elle permet de définir deux attributs : creature pour savoir si une créature est sur la case 
 * et objet pour savoir si un objet est sur la case.
 * @author Mathilde
 */
public class Case {
    /**
     * position de la case
     */
    private Point2D pos;
    /**
     * contient la créature de la case, si elle en contient un
     */
    private Creature creature;
    /**
     * contient l'objet de la case, si elle en contient un
     */
    private Objet objet;

    public Case(Point2D pos){
        this.pos = new Point2D(pos);
    }
    
    public void setPos(Point2D pos) {
        this.pos = pos;
    }

    public void setCreature(Creature creature) {
        this.creature = creature;
    }

    public void setObjet(Objet objet) {
        this.objet = objet;
    }

    public Point2D getPos() {
        return pos;
    }

    public Creature getCreature() {
        return creature;
    }

    public Objet getObjet() {
        return objet;
    }
    
    
}
