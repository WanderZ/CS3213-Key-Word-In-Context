package src;

import util.Message;

public class FilterPipe extends AbstractPipe implements IPipe {
	private IFilter destinationFilter;

	public FilterPipe(IFilter filter) {
		super();
		destinationFilter = filter;
	}
	
	@Override
	public void push(Message payload) {
		buffer.add(payload);
		while (buffer.peek() != null) {
			destinationFilter.receiveData(buffer.poll());
		}
	}
}
