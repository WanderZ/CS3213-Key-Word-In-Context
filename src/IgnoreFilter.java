package src;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import util.Message;

public class IgnoreFilter implements IFilter {

	/**
	 * List of keywords to ignore and filter out
	 */
	private Set<String> keywords;

	/**
	 * Positions of tokens to ignore in circular permutations.
	 */
	private int IGNORE_POS; 
	
	/**
	 * The outward going pipe to transfer out the filtered data.
	 */
	private IPipe outputPipeEnd;
	
	public IgnoreFilter(IPipe pipe, Set<String> keywords, int pos) {
		this.outputPipeEnd = pipe;
		this.keywords = keywords;
		this.IGNORE_POS = pos;
	}	
	
	@Override
	public void sendData(Message payload) {
		outputPipeEnd.push(payload);
	}

	@Override
	public void receiveData(Message payload) {
		removeIgnoreEntry(payload);
	}
	
	private void removeIgnoreEntry(Message msg) {
		@SuppressWarnings("unchecked")
		List<String[]> circularShifts = (List<String[]>)msg.getData();
		List<String[]> result = new LinkedList<String[]>();
		
		for(String[] particularCircularShift : circularShifts) {
			if(!keywords.contains(particularCircularShift[IGNORE_POS])) {
				result.add(particularCircularShift);
			}
		}
		sendData(new Message(result));
	}

}
