package opdr1b;

import java.util.Vector;

public class MijnStack<T> implements Stack<T> {

	private Vector<T> myVector;
	int top;

	public MijnStack() {
		myVector = new Vector<T>();
	}

	@Override
	public void push(T element) {
		myVector.add(element);
	}

	@Override
	public T pop() throws PostfixException {
		T pop;
		if (myVector.size() > 0) {
			pop = myVector.elementAt(myVector.size() - 1);
			myVector.removeElementAt(myVector.size() - 1);

		} else {
			throw new PostfixException("stack is leeg");
		}
		return pop;

	}

	@Override
	public boolean isEmpty() {
		return myVector.isEmpty();
	}

	@Override
	public T top() throws PostfixException {
		if (myVector.isEmpty()) {
			throw new PostfixException("Stack is leeg");

		} else {
			return myVector.elementAt(myVector.size() - 1);
		}
	}

	@Override
	public int size() {
		return myVector.size();

	}

	public String toString() {
		return myVector.toString();
	}

}
