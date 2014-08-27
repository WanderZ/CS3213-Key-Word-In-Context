package src;

public abstract class AbstractPipe {
	protected IFilter destinationFilter;
	
	public AbstractPipe(IFilter filter) {
		destinationFilter = filter;
	}
}
