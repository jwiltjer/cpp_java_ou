package opdr1b;

public interface Stack<T> {
	
	public T pop() throws PostfixException;

	public boolean isEmpty();
	
	public T top() throws PostfixException;
	
	public int size();

	void push(T element);


}
