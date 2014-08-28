package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import util.Message;

public class KwicMain {
	private static final String IGNORE_LIST = "ignore.txt";
	private static final String INPUT_LIST = "input.txt";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<String> ignoreList = readFile(IGNORE_LIST);
		List<String> inputList = readFile(INPUT_LIST);
		KwicStore<String> kwicStore = new KwicStore<String>();
		StorePipe storePipe = new StorePipe(kwicStore);
		
		for (int i = 0; i < inputList.size(); i++) {
			IFilter csFilter = new CircularShiftFilter(storePipe);
			IPipe fPipe = new FilterPipe(csFilter);
			fPipe.push(new Message(inputList.get(i)));
		}		
		
		 Iterator<String> itr = kwicStore.iterator();
		 while (itr.hasNext()) {
			 System.out.println("Output: " + itr.next());
		 }
	}
	
	private static List<String> readFile(String fileName) {
		ArrayList<String> outputList = new ArrayList<String>();
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
			String str = "";
			while ((str = br.readLine()) != null) {
				outputList.add(str);
			}
			br.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return outputList;
	}
}
