package src;

public class FilterPipe extends AbstractFilterPipe implements IPipe {
	public FilterPipe(IFilter destinationFilter) {
		super(destinationFilter);
	}
	
	@Override
	public void push(Object payload) {
		destinationFilter.receiveData(payload);
	}
}
