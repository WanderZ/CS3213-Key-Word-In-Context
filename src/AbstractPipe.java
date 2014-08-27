package src;

import java.util.LinkedList;
import java.util.Queue;

public abstract class AbstractPipe {
	protected Queue<Object> buffer;
	
	public AbstractPipe() {
		buffer = new LinkedList<Object>();
	}
	
	public void push(Object payload) {
		buffer.add(payload);
	}
}
