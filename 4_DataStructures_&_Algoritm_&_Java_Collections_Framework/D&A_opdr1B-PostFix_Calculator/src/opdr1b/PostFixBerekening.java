package opdr1b;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;

/**
 * Klasse die zorgt voor de berekening van de postfix invoer
 * 
 * @author jwiltjer
 *
 */
public class PostFixBerekening {

	/**
	 * private constructor
	 *
	 */
	private PostFixBerekening() {
		//klasse kan niet geinstantieerd worden, heeft enkel 1 static methode. 
	}

	/**
	 * leest de karakters 1 voor 1 uit en berekent de uitkomst volgens de postfix
	 * methode
	 * 
	 * @return uitkomst in String formaat van de postfix berekening
	 * @throws PostfixException wanneer er letters of ongeldige operatoren ingevuld
	 *                          worden
	 */
	public static String berekenPostFix(String expressie) throws PostfixException {
		StreamTokenizer tkn = new StreamTokenizer(new StringReader(expressie));
		MijnStack<String> stack = new MijnStack<String>();
		tkn.ordinaryChar('/');
		tkn.ordinaryChar('-');

		int uitkomst = 0;
		boolean eind = false;

		while (!eind) {
			try {
				switch (tkn.nextToken()) {
				case StreamTokenizer.TT_NUMBER:
					int getal = (int) Math.round(tkn.nval);
					stack.push("" + getal);
					break;

				case StreamTokenizer.TT_WORD:
					throw new PostfixException("Letters zijn niet toegestaan");

				case StreamTokenizer.TT_EOF:
					eind = true;
					break;

				default:
					Operation o = Operation.fromString(Character.toString((char) tkn.ttype));

					if (o != null) { // o == null bij een ongeldige operator.
						int y = Integer.parseInt(stack.pop());
						int x = Integer.parseInt(stack.pop());
						uitkomst = o.apply(x, y);
						stack.push("" + uitkomst);
					} else {
						throw new PostfixException("vul een geldige operator in");
					}
				}
			} catch (IOException e) {
				throw new PostfixException("foute invoer, probeer opnieuw");
			}
		}
		return "" + uitkomst;
	}
}
