package src;

import java.util.LinkedList;
import java.util.Queue;

public class StorePipe extends AbstractPipe implements IPipe {
	private KwicStore<String> store;
	
	public StorePipe(KwicStore<String> ks) {
		super();
		store = ks;
	}
	
	@Override
	public void push(Object payload) {
		buffer.add(payload);
		while (buffer.peek() != null) {
			store.add((String)buffer.poll());
		}
	}

}
