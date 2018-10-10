package org.centrale.projet.objet;

/**
 *Classe qui est la super classe de tous les montres qui vont être créés tels que les lapins ou les loups
 *(sous-classe de Creature)
 * @author Mathilde
 */
public abstract class Monstre extends Creature {

/**
 * Constructeur de Monstre qui prend en parametre  toutes les valeurs des attributs a initialiser
 * @param pV
 * @param pA
 * @param pP
 * @param dA
 * @param ptPar
 * @param pos 
 */
    public Monstre(int pV, int pA, int pP, int dA, int ptPar, Point2D pos){
        super(pV, pA, pP, dA, ptPar, pos);
    }
    
    
/**
 * Constructeur de Monstre qui initialise les attributs a partir des attributs du Monstre mis en parametre
 * @param m 
 */
    public Monstre(Monstre m){
        super(m);
    }
    
    //A modifier pour ajouter le coté aléatoire !!
/**
 * Constructeur de Monstre par default qui initialise les attributs a des valeurs choisies par default
 */
    public Monstre(){
        super();
    }
    
/**
 * Méthode qui permet d'afficher tous les atttributs du monstre
 */
    public abstract void affiche();/*{
        super.affiche();
    }*/
}
