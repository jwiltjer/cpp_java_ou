package opdr1b;

import java.util.Vector;

/**
 * Klasse implementeerd een custom stack.
 * 
 * @author jwiltjer
 *
 * @param <T> generieke T
 */
public class MijnStack<T> {

	private Vector<T> myVector;

	/**
	 * default constructor, instantieerd een nieuwe stack
	 */
	public MijnStack() {
		myVector = new Vector<T>();
	}

	/**
	 * voegt het element toe aan de stack(bovenop)
	 * 
	 * @param element: toe te voegen element
	 */
	public void push(T element) {
		myVector.add(element);
	}

	/**
	 * methode die het laatste element van de stack verwijderd en dit element terug
	 * geeft voorwaarde:stack mag niet leeg zijn.
	 * 
	 * @throws PostfixExceptie wanneer de stack leeg is
	 * @return verwijderde element
	 */
	public T pop() throws PostfixException {
		if (!myVector.isEmpty()) {
			return myVector.remove(myVector.size() - 1);
		} else {
			throw new PostfixException("stack is leeg");
		}

	}

	/**
	 * controleert of de stack leeg is
	 * 
	 * @return stack is leeg(true) of niet (false)
	 */
	public boolean isEmpty() {
		return myVector.isEmpty();
	}

	/**
	 * geeft het element terug welke bovenaan op de stack staat. voorwaarde: stack
	 * is niet leeg
	 * 
	 * @throws PostfixExceptie wanneer de stack leeg is.
	 * @return element bovenaan de stack
	 */
	public T top() throws PostfixException {
		if (myVector.isEmpty()) {
			throw new PostfixException("Stack is leeg");

		} else {
			return myVector.elementAt(myVector.size() - 1);
		}
	}

	/**
	 * haalt de grootte van de stack op.
	 * 
	 * @return aantal elementen op de stack
	 */
	public int size() {
		return myVector.size();

	}

	/**
	 * @return string representatie van de stack
	 */
	public String toString() {
		return myVector.toString();
	}

}
