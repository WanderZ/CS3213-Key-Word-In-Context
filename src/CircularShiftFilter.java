package src;

import util.Message;
import java.util.ArrayList;
import java.util.List;

public class CircularShiftFilter implements IFilter {
	private String inputString;
	private IPipe outputPipeEnd;
	private List<String> keywords;
	
	public CircularShiftFilter() {
		keywords = new ArrayList<String>();
		keywords.add("The".toLowerCase());
	}
	
	public CircularShiftFilter(IPipe pipe) {
		this();
		outputPipeEnd = pipe;
	}
	
	public void runCircularShift() {
		// do shifting
		// for every valid circular shift {
//			String circularShifted = inputString;
		String[] stringInParts = inputString.split(" ");
		for(int i=0; i<stringInParts.length; i++) {
			if(!keywords.contains(stringInParts[i])) {
				int j = i;
				do {
					System.out.print(stringInParts[j]);
					j++;
					if(j == stringInParts.length) j = 0;
				} while(j!=i);
				System.out.println();
			}
			
		}
//			sendData(new Message(circularShifted));
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
	
	// For Testing purposes.
	public static void main(String[] args) {
		CircularShiftFilter test = new CircularShiftFilter();
		test.receiveData(new Message("The Day After Tomorrow".toLowerCase()));
//		test.runCircularShift();
		
		test.receiveData(new Message("the happy go lucky".toLowerCase()));
		test.receiveData(new Message("OnE of The jokers".toLowerCase()));
	}
	
}
