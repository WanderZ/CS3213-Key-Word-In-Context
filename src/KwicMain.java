package src;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import util.IgnoreWordComparator;
import util.Message;

public class KwicMain {
	
	/**
	 * Default file location for the ignore keyword list
	 */
	private static String IGNORE_LIST;
	
	/**
	 * Default file location for the input string text
	 */
	private static String INPUT_LIST;

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
	 * Positions of tokens to ignore in circular permutations.
	 */
	private static final int IGNORE_POS = 0; 
	
	public static void main(String[] args) {
		if (args.length == 2) {
			INPUT_LIST = args[0];
			IGNORE_LIST = args[1];
			
			List<String> inputList = readFile(INPUT_LIST);
			Set<String> ignoreSet = new TreeSet<String>(new IgnoreWordComparator());
			ignoreSet.addAll(readFile(IGNORE_LIST));
			
			// Create modules and connect them
			KwicStore<String> 	kwicStore 		= new KwicStore<String>();
			StorePipe 			storePipe 		= new StorePipe(kwicStore);

			IFilter 			formatFilter 	= new FormatFilter(storePipe);
			IPipe 				formatPipe 		= new FilterPipe(formatFilter);
			
			IFilter 			ignoreFilter 	= new IgnoreFilter(formatPipe, ignoreSet, IGNORE_POS);
			IPipe 				ignorePipe 		= new FilterPipe(ignoreFilter);
			
			IFilter 			csFilter 		= new CircularShiftFilter(ignorePipe, DELIMITER, DELIMITER_REGEX);
			IPipe 				csPipe 			= new FilterPipe(csFilter);
			
			for (int i = 0; i < inputList.size(); i++) {			
				// Begin data flow
				csPipe.push(new Message(inputList.get(i)));
			}		
			
			// Output results
			Iterator<String> itr = kwicStore.iterator();
			StringBuilder sb = new StringBuilder();
			while (itr.hasNext()) {
				sb.append(itr.next() + "\n");
			}
			System.out.print(sb.toString());
		}
		else System.out.println("Usage: input-file ignore-words-file");
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
