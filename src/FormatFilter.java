package src;

import java.util.LinkedList;
import java.util.List;

import util.Message;

public class FormatFilter implements IFilter {

	private IPipe outputPipeEnd;
	
	public FormatFilter(IPipe pipe) {
		outputPipeEnd = pipe;
	}
	
	@Override
	public void sendData(Message payload) {
		outputPipeEnd.push(payload);
	}

	@Override
	public void receiveData(Message payload) {
		format(payload);
	}
	
	private void format(Message msg) {
		@SuppressWarnings("unchecked")
		List<String[]> circularShifts = (List<String[]>)msg.getData();
		List<String> result = new LinkedList<String>();
		
		for (String[] particularCircularShift : circularShifts) {
			char[] token = particularCircularShift[0].toLowerCase().toCharArray();
			token[0] = (char) (token[0] - 32);
			particularCircularShift[0] = new String(token);
			
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < particularCircularShift.length; i++)
				sb.append(particularCircularShift[i]);
			
			result.add(sb.toString());
		}
		sendData(new Message(result));
	}

}
