/**
 * Declaration du package
 */
package filesys;

/**
 * @author Pierre Lizet et Philippe Joulot
 *
 */
public class ExceptionSystemeFichier extends Exception {

    /**.
     * Identificateur
     */
    private static final long serialVersionUID = 7643154131469600578L;
    /**.
     * Exception par defaut
	*/
	public ExceptionSystemeFichier() {
		super("Erreur systeme");
	}
	/**
	 * @param message
	 *     Message lie a l'exception
	 * @param cause
	 *     Cause liee a l'exception
	 */
	public ExceptionSystemeFichier(String message, Throwable cause) {
		super(message, cause);
	}
	/**
	 * @param message
	 *     Message lie a l'exception
	 */
	public ExceptionSystemeFichier(String message) {
		super(message);
	}
	/**
	 * @param cause
	 *     Cause liee a l'exception
	 */
	public ExceptionSystemeFichier(Throwable cause) {
		super("Erreur systeme", cause);
	}
}
