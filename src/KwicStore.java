package src;

import java.util.Iterator;
import java.util.TreeSet;

public class KwicStore<T> {
	private TreeSet<T> store;
	
	public KwicStore() {
		store = new TreeSet<T>();
	}
	
	public void add(T str) {
		store.add(str);
	}
	
	public Iterator<T> descendingIterator() {
		return store.descendingIterator();
	}
	
	public Iterator<T> iterator() {
		return store.iterator();
	}
}
