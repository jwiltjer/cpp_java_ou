package opdr1b;

import java.io.IOException;
import java.io.StreamTokenizer;
import java.io.StringReader;
/**
 * Klasse die zorgt voor de berekening van de postfix invoer 
 * @author jwiltjer
 *
 */
public class PostFixBerekening {
	private StreamTokenizer tkn;
	private MijnStack<String> stack;

	/**
	 * instantieert een PostFixBerekening en zorgt ervoor dat '/' en '-' als
	 * karakters worden gelezen.
	 * 
	 * @param expressie die in de tokenlezer gaat
	 */
	public PostFixBerekening(String expressie) {
		tkn = new StreamTokenizer(new StringReader(expressie));
		stack = new MijnStack<String>();
		tkn.ordinaryChar('/');
		tkn.ordinaryChar('-');
	}

	/**
	 * leest de karakters 1 voor 1 uit en berekent de uitkomst volgens de postfix manier
	 * 
	 * @return uitkomst in String formaat van de postfix berekening
	 * @throws PostfixException wanneer er letters of ongeldige operatoren ingevuld worden
	 */
	public String berekenPostFix() throws PostfixException {
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
					String waardeTtype = Character.toString((char) tkn.ttype);
					Operation o = Operation.fromString(waardeTtype);
					
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
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return "" + uitkomst;
	}
}
