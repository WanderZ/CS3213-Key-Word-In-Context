package src;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import util.Message;

public class CircularShiftFilter implements IFilter {

	/**
	 * The outward going pipe to transfer out the filtered data.
	 */
	private IPipe outputPipeEnd;

	/**
	 * The default delimiter for this keyword in context problem.
	 */
	private static String DELIMITER;

	/**
	 * The default delimiter regular expression used for this keyword in context problem.
	 * "\\s+" is any amount of white space will be treated as one delimiter to split the words up.
	 */
	private static String DELIMITER_REGEX; 

	public CircularShiftFilter(IPipe pipe, String delim, String delimRegex) {
		outputPipeEnd = pipe;
		DELIMITER = delim;
		DELIMITER_REGEX = delimRegex;
	}

	@Override
	public void sendData(Message payload) {
		outputPipeEnd.push(payload);
	}

	@Override
	public void receiveData(Message payload) {
		runCircularShift(payload);
	}

	private void runCircularShift(Message msg) {
		String[] stringInParts = ((String)msg.getData()).split(DELIMITER_REGEX);
		List<String[]> circularShifts = new LinkedList<String[]>();
		
		for(int i = 0; i < stringInParts.length; i++) {
			List<String> particularCircularShift = new ArrayList<String>();
			int j = i;
			
			do {
				particularCircularShift.add(stringInParts[j]);
				particularCircularShift.add(DELIMITER);
				
				j = (j+1) % stringInParts.length;
			} 
			while(j != i);
			circularShifts.add(particularCircularShift.toArray(new String[particularCircularShift.size()]));
		}
		sendData(new Message(circularShifts));
	}

}
