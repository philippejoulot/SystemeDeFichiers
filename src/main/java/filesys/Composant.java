/**
 * Declaration du package
 */
package filesys;

/**
 * La classe Composant decrit un element du Systeme de Fichiers qui sera soit un
 * fichier, soit un repertoire.
 * @author Pierre Lizet et Philippe Joulot
 * @version 1.0
 */
public abstract class Composant {

    /**
     * . Le nom du composant
     */
    protected String nom;

    /**
     * . Constructeur de la classe Composant
     * @param parnom
     *            Le nom du composant
     * @throws ExceptionSystemeFichier
     *             Exception levee si le nom est vide.
     */
    public Composant(String parnom) throws ExceptionSystemeFichier {
        // initialise instance variables
        if (parnom.equals("")) {
            throw new ExceptionSystemeFichier(
                    "On ne peut pas ajouter un repertoire avec un nom vide.");
        } else {
            nom = parnom;
        }
    }

    /**
     * . La taille du composant
     * @return la taille du composant
     */
    public abstract int getTaille();

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result;
        if (nom != null) {
            result = prime * result + nom.hashCode();
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        Composant other = (Composant) obj;
        if (nom == null) {
            if (other.nom != null) {
                return false;
            }
        } else if (!nom.equals(other.nom)) {
            return false;
        }
        return true;
    }
    /**
     * . Getter Nom
     * @return nom
     */
    public String getNom() {
        return nom;
    }
}
