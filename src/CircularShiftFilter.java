package src;

import util.Message;

public class CircularShiftFilter implements IFilter {

	/**
	 * The default delimiter for this keyword in context problem.
	 */
	private static final String DELIMITER = " ";

	/**
	 * The default delimiter regular expression used for this keyword in context problem.
	 * "\\s+" is any amount of white space will be treated as one delimiter to split the words up.
	 */
	private static final String DELIMITER_REGEX = "\\s+"; 

	/**
	 * Message in the system storing the original string and the generated strings
	 */
	private Message msg;

	/**
	 * The outward going pipe to transfer out the filtered data.
	 */
	private IPipe outputPipeEnd;

	// Empty constructor For testing purposes
	public CircularShiftFilter() {
//		keywords = new ArrayList<String>();
//		keywords.add("The".toLowerCase());
	}

	public CircularShiftFilter(IPipe pipe) {
		outputPipeEnd = pipe;
//		keywords = list;
	}

	public void runCircularShift() {
		// do shifting
		// for every valid circular shift
		//			String circularShifted = inputString;
		String[] stringInParts = msg.getMessage().split(DELIMITER_REGEX);
		StringBuilder output;
		for(int i=0; i<stringInParts.length; i++) {
//			if(!keywords.contains(stringInParts[i])) {
				output = new StringBuilder();
				int j = i;
				do {
					output.append(stringInParts[j]);
					output.append(DELIMITER);
					//					System.out.print(stringInParts[j]);
					j++;
					if(j == stringInParts.length) j = 0; // To circulate around the string of words.
				} while(j != i);
				//				System.out.println();
				//				System.out.println(output.toString());
				msg.addGeneratedText(output.toString());
//			}
		}
		sendData(msg);
	}

	@Override
	public void sendData(Message payload) {
		outputPipeEnd.push(payload);
	}

	@Override
	public void receiveData(Message payload) {
		msg = payload;
		runCircularShift();
	}

	// For Testing purposes.
	public static void main(String[] args) {
		CircularShiftFilter test = new CircularShiftFilter();
		test.receiveData(new Message("The    Day After Tomorrow".toLowerCase()));
		//		test.runCircularShift();

		test.receiveData(new Message("the happy    go lucky".toLowerCase()));
		test.receiveData(new Message("OnE of The jokers".toLowerCase()));
	}

}
