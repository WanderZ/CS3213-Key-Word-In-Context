package util;

import java.util.Comparator;

public class IgnoreWordComparator implements Comparator<String> {

	@Override
	public int compare(String arg0, String arg1) {
		return arg0.compareToIgnoreCase(arg1);
	}
	
}
