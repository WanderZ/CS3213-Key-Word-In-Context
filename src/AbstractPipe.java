package src;

import java.util.LinkedList;
import java.util.Queue;

import util.Message;

public abstract class AbstractPipe implements IPipe {
	
	/**
	 * First in first out memory buffer
	 */
	protected Queue<Message> buffer;
	
	public AbstractPipe() {
		buffer = new LinkedList<Message>();
	}
	
	public void push(Message payload) {
		buffer.add(payload);
	}
}
