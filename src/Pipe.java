package src;

public class Pipe extends AbstractPipe implements IPipe {
	public Pipe(IFilter destinationFilter) {
		super(destinationFilter);
	}
	
	@Override
	public void push(Object payload) {
		destinationFilter.receiveData(payload);
	}
}
