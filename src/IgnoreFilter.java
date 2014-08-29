package src;

import java.util.List;

import util.Message;

public class IgnoreFilter implements IFilter {

	/**
	 * List of keywords to ignore and filter out
	 */
	private List<String> keywords;
	
	/**
	 * Payload to be moving around
	 */
	private Message msg;
	
	/**
	 * The outward going pipe to transfer out the filtered data.
	 */
	private IPipe outputPipeEnd;
	
	public IgnoreFilter() {}
	
	public IgnoreFilter(IPipe pipe, List<String> keywords) {
		outputPipeEnd = pipe;
		this.keywords = keywords;
	}	
	
	@Override
	public void sendData(Message payload) {
		outputPipeEnd.push(msg);
	}

	@Override
	public void receiveData(Message payload) {
		msg = payload;
		removeIgnoreEntry();
	}
	
	public void removeIgnoreEntry() {
		List<String> msgGenerated = msg.getGenerated();
		Message finalMsg = new Message(msg.getMessage());
		
		for(String genStr : msgGenerated) {
			if(!keywords.contains(genStr.split(" ")[0].toLowerCase())) {
				finalMsg.addGeneratedText(genStr);
			}
		}
		sendData(finalMsg);
	}

}
