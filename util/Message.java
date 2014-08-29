package util;

import java.util.List;
import java.util.ArrayList;

public class Message {
	
	private String data;
	private List<String> generated;
	
	public Message(String str) { 
		data = str; 
		generated = new ArrayList<String>();
	}
	
	public String getMessage() {
		return data; 
	}
	
	public void addGeneratedText(String generatedStr) {
		generated.add(generatedStr);
	}
	
	public List<String> getGenerated() {
		return this.generated;
	}
	
	public String toString() {
		return this.data;
	}
}