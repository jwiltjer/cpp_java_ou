package theater;

/**
 * Exeption handling klasse voor het Theater
 * 
 * @author jwiltjer
 *
 */
public class TheaterException extends Exception {

	private static final long serialVersionUID = 1L;

	public TheaterException() {
		super();
		
	}

	public TheaterException(String e) {
		super(e);
	}
	
}
