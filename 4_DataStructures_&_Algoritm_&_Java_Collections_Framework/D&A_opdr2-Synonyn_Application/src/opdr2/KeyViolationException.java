package opdr2;

public class KeyViolationException extends Exception {

	/**
	 * Custom Exception klasse om ongeldige invoeren af te vangen
	 */
	private static final long serialVersionUID = 1L;
	
	public KeyViolationException() {
		super();
	}

	public KeyViolationException(String e) {
		super(e);
		}

}
