package filesys;

import java.util.ArrayList;
import java.util.Hashtable;

import org.junit.Test;

import junit.framework.Assert;

/**
 * @author Pierre Lizet et Philippe Joulot
 */
public class TestSys {
    /**.
     * Variable de taille pour les tests
     */
    public static final int TAILLEPOSITIVE1 = 10;
    /**.
     * Variable de taille pour les tests
     */
    public static final int TAILLEPOSITIVE2 = 20;
    /**.
     * Variable de taille pour les tests
     */
    public static final int TAILLENEGATIVE1 = -10;
    /**
     * @throws ExceptionSystemeFichier
     *             Test susceptible de lancer une exception de type
     *             ExceptionSystemeFichier
     */
    @Test
    public void calculTailleVide() throws ExceptionSystemeFichier {
        File fich1;
        fich1 = new File("fich1");
        int taille = fich1.getTaille();
        Assert.assertEquals(0, taille);
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test susceptible de lancer une exception de type
     *             ExceptionSystemeFichier
     */
    @Test
    public void calculTailleNonVide() throws ExceptionSystemeFichier {
        File fich1;
        fich1 = new File("fich1", TAILLEPOSITIVE1);
        int taille = fich1.getTaille();
        Assert.assertTrue(taille >= 0);
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test attendant une exception de type
     *             ExceptionSystemeFichier car la taille est negative
     */
    @Test(expected = ExceptionSystemeFichier.class)
    public void calculTailleNegative() throws ExceptionSystemeFichier {
        File fich1 = new File("fich1", TAILLENEGATIVE1);
        int taille = fich1.getTaille();
        Assert.assertTrue(taille >= 0);
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test susceptible de lancer une exception de type
     *             ExceptionSystemeFichier
     */
    @Test
    public void calculTailleRepertoire() throws ExceptionSystemeFichier {
        Repertoire dir1;
        dir1 = new Repertoire("Dir1");
        File fich1 = new File("fich1", TAILLEPOSITIVE1);
        File fich2 = new File("fich2", TAILLEPOSITIVE2);
        dir1.ajoutComposant(fich1);
        dir1.ajoutComposant(fich2);
        int taille = dir1.getTaille();
        Assert.assertTrue(taille == (fich1.getTaille() + fich2.getTaille()));
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test attendant une exception de type
     *             ExceptionSystemeFichier car on ajoute une reference nulle
     */
    @Test(expected = ExceptionSystemeFichier.class)
    public void referenceNulle() throws ExceptionSystemeFichier {
        Repertoire dir1 = new Repertoire("Dir1");
        dir1.ajoutComposant(null);
        Assert.assertTrue(!dir1.getComposants().contains(null));
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test attendant une exception de type
     *             ExceptionSystemeFichier car on ajoute
     *             un composant de meme nom
     */
    @Test(expected = ExceptionSystemeFichier.class)
    public void ajoutMemeNom() throws ExceptionSystemeFichier {
        Repertoire dir1;
        dir1 = new Repertoire("Dir1");
        File fich1 = new File("fich1", TAILLEPOSITIVE1);
        dir1.ajoutComposant(fich1);
        dir1.ajoutComposant(fich1);
        ArrayList<Composant> contenu = dir1.getComposants();
        Hashtable<Composant, Integer> h = new Hashtable<Composant, Integer>();
        for (Composant item : contenu) {
            if (!h.containsKey(item)) {
                h.put(item, Integer.valueOf(1));
            } else {
                Integer occurrence = h.get(item) + 1;
                h.remove(item);
                h.put(item, occurrence);
            }
        }
        Assert.assertTrue(h.size() == contenu.size());
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test attendant une exception de type ExceptionSystemeFichier
     *             car on ajoute un repertoire a lui-meme
     */
    @Test(expected = ExceptionSystemeFichier.class)
    public void ajoutSoitMeme() throws ExceptionSystemeFichier {
        Repertoire dir1 = new Repertoire("Dir1");
        dir1.ajoutComposant(dir1);
        Assert.assertTrue(!dir1.getComposants().contains(dir1));
    }

    /**
     * @throws ExceptionSystemeFichier
     *             Test attendant une exception de type ExceptionSystemeFichier
     *             car on ajoute un repertoire qui est un
     *             sous-repertoire de lui-meme
     */
    @Test(expected = ExceptionSystemeFichier.class)
    public void sousRepertoireLuiMeme() throws ExceptionSystemeFichier {
        Repertoire dir1;
        dir1 = new Repertoire("Dir1");
        Repertoire dir2 = new Repertoire("Dir2");
        Repertoire dir3 = new Repertoire("Dir3");
        File fich1 = new File("Fich1", TAILLEPOSITIVE1);
        File fich2 = new File("Fich2", TAILLEPOSITIVE1);
        dir1.ajoutComposant(dir2);
        dir1.ajoutComposant(fich1);
        dir2.ajoutComposant(dir3);
        dir3.ajoutComposant(dir1);
        dir3.ajoutComposant(fich2);
        Assert.assertTrue(!dir1.getComposants().contains(dir1));
    }
}
