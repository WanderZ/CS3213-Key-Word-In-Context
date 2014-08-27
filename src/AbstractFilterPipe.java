package src;

public abstract class AbstractFilterPipe {
	protected IFilter destinationFilter;
	
	public AbstractFilterPipe(IFilter filter) {
		destinationFilter = filter;
	}
}
