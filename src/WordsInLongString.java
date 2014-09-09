import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;


public class WordsInLongString {
	
	public static HashSet<String> myDict;
	public static PrintWriter out;
	
	public static boolean findWords(String input) {
		String searchString ="";
		String foundString = "";
		int len;
		for (int i = 0; i < input.length(); i++) {
			searchString += input.charAt(i);
			
			if(myDict.contains(searchString))
				foundString = searchString;
		}
		
		if ((len = foundString.length()) > 0) {
			out.print(foundString+" ");
			findWords(input.substring(len));
		}
		return true;
	}

	public static void main(String[] args) throws IOException {
		myDict = new HashSet<>();
		BufferedReader dict = new BufferedReader(new FileReader("dictionary.txt"));
		String str;
		while ((str = dict.readLine()) != null) {
			myDict.add(str);
		}
		System.out.println("Dictionary : " + myDict.size());
		dict.close();
		
		BufferedReader input = new BufferedReader(new FileReader("input.txt"));
		out = new PrintWriter("output.txt");
		String inputStr = input.readLine();
		if (findWords(inputStr))
			System.out.println("Done");
		input.close();
		out.close();
	}

}
