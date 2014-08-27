package src;

import java.util.LinkedList;
import java.util.Queue;

public class FilterPipe extends AbstractPipe implements IPipe {
	private IFilter destinationFilter;

	public FilterPipe(IFilter filter) {
		super();
		destinationFilter = filter;
	}
	
	@Override
	public void push(Object payload) {
		buffer.add(payload);
		while (buffer.peek() != null) {
			destinationFilter.receiveData(buffer.poll());
		}
	}
}
