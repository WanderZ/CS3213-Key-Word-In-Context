package src;

import java.util.List;

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
			@SuppressWarnings("unchecked")
			List<String> data = (List<String>)buffer.poll().getData();
			for (String str : data) store.add(str);
		}
	}

}
