package src;

import java.util.Iterator;
import java.util.TreeSet;

public class KwicStore<E> {
	private TreeSet<E> store;
	
	public KwicStore() {
		store = new TreeSet<E>();
	}
	
	public void add(E str) {
		store.add(str);
	}
	
	public Iterator<E> descendingIterator() {
		return store.descendingIterator();
	}
	
	public Iterator<E> iterator() {
		return store.iterator();
	}
}
