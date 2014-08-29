package util;

public class Message {
	
	private String data;
	
	public Message(String str) { data = str; }
	
	public String getMessage() { return data; }
	
	public String toString() {
		return this.data;
	}
}