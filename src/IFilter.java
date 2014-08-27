package src;

public interface IFilter {
	public void sendData(Object payload);	// pipe.push(data)
	public void receiveData(Object payload);
}
