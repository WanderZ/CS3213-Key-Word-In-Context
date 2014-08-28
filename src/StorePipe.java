package src;

import util.Message;

public class StorePipe extends AbstractPipe implements IPipe {
	private KwicStore<String> store;
	
	public StorePipe(KwicStore<String> ks) {
		super();
		store = ks;
	}
	
	@Override
	public void push(Message payload) {
		buffer.add(payload);
		while (buffer.peek() != null) {
			store.add(buffer.poll().getMessage());
		}
	}

}
