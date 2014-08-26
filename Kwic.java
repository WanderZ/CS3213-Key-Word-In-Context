import java.util.*;
import java.io.*;

class Kwic
{
	private static ArrayList<String> ignoreList;
	private static KwicDS kwicDS;

	private static final String IGNORE_LIST = "ignore.txt";
	private static final String INPUT_LIST = "input.txt";

	public static void main(String[] args)
	{
		if (args.length == 2)
			Kwic.kwic();
		else
			System.out.println("Arguments: ignore-list input-list");
	}

	public static void kwic()
	{
		kwicDS = new KwicDS();
		readInput();
	}

	public static void readInput()
	{
		try
		{
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(IGNORE_LIST)));
			String str = "";
			while ((str = br.readLine()) != null)
			{
				ignoreList.add(str);
			}
			br.close();

			br = new BufferedReader(new InputStreamReader(new FileInputStream(INPUT_LIST)));
			str = "";
			while ((str = br.readLine()) != null)
			{
				kwicDS.add(str);
			}
			br.close();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}
}

class KwicDS
{
	private List<String> data;
	private List<List<String>> circularShifts;

	public KwicDS()
	{
		data = new ArrayList<String>();
		circularShifts = new ArrayList<List<String>>();
	}

	public int add(String str) 
	{ 
		data.add(str); 
		circularShifts.add(new ArrayList<String>());
		return data.size() - 1;
	}

	public void addCircularShifts(int i, List<String> csList)
	{
		circularShifts.set(i, csList);
	}

	public List<String> getCircularShifts(int i)
	{
		return circularShifts.get(i);
	}
}