package src;

import util.Message;

public class CircularShiftFilter implements IFilter {
	private String inputString;
	private IPipe outputPipeEnd;
	
	public CircularShiftFilter() {}
	
	public CircularShiftFilter(IPipe pipe) {
		outputPipeEnd = pipe;
	}
	
	public void runCircularShift() {
		// do shifting
		// for every valid circular shift {
			String circularShifted = inputString;
			sendData(new Message(circularShifted));
		// }
	}

	@Override
	public void sendData(Message payload) {
		outputPipeEnd.push(payload);
	}

	@Override
	public void receiveData(Message payload) {
		inputString = payload.getMessage();
		runCircularShift();
	}
	
}
