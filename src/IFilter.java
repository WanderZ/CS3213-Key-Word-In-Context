package src;

import util.Message;

public interface IFilter {
	public void sendData(Message payload);	// pipe.push(data)
	public void receiveData(Message payload);
}
