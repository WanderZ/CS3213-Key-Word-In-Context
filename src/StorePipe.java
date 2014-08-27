package src;

public class StorePipe implements IPipe {
	private KwicStore<String> store;
	
	public StorePipe(KwicStore<String> ks) {
		store = ks;
	}
	
	@Override
	public void push(Object payload) {
		store.add((String)payload);
	}

}
