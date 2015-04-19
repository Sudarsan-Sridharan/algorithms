package palantir;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;

import javax.swing.plaf.basic.BasicScrollPaneUI.HSBChangeListener;

/*
 * Desired Complexity = O(nlogn)
 */
public class MostFrequentCharacters {

	public static void main(String[] args) throws FileNotFoundException {
		File file = new File("src/palantir/input");
		Scanner in = new Scanner(new FileInputStream(file));
//		Scanner in = new Scanner(System.in);

		 String res,res1;
	        String _input;
	        _input = in.nextLine();
	        
	        int _frequencyThreshold;
	        _frequencyThreshold = Integer.parseInt(in.nextLine());
	        
	        long timeNow = System.nanoTime();
	        res = frequentCharacters(_input, _frequencyThreshold);
	        long newTime = System.nanoTime();
	        res1 = frequentCharacters1(_input, _frequencyThreshold);
	        System.out.println("Hash  = "+res+" ,Running Time :"+ (newTime - timeNow) + " ms");
	        System.out.println("Array = "+res1+" ,Running Time :"+ (System.nanoTime() - newTime) + " ms");
	}
	
	/*
	 * Using HashMap and Arrays.sort() - O(nlogn) 
	 */
	static String frequentCharacters(String input, int frequencyThreshold) {
		HashMap<Character,Integer> myMap = new HashMap<>();
		for (char c: input.replaceAll(" ", "").toCharArray()){
			if (!myMap.containsKey(c)){
				myMap.put(c,1);
			} else {
				myMap.put(c,myMap.get(c)+1);
			}
		}
		StringBuffer output =  new StringBuffer();
		for (Map.Entry<Character, Integer> entry : myMap.entrySet()){
			if (entry.getValue() >= frequencyThreshold){
				output.append(entry.getKey());
			}
			
		}
		char[] my = output.toString().toCharArray();
		Arrays.sort(my);
		String string = new String(my);
		return string;


    }
	/*
	 * Using Arrays and counting characters - O(n)
	 */
	private static String frequentCharacters1(String input,
			int frequencyThreshold) {
		int[] myArray = new int[256];
		char[] inputArray = input.replaceAll(" ", "").toCharArray();
		for (char c: inputArray){
			myArray[c]++;
		}
		
		StringBuffer output =  new StringBuffer();
		for (int i='a';i<='z';i++){
			if (myArray[i]>=frequencyThreshold){
				output.append(String.valueOf((char)i));
			}
		}
		return output.toString();
	}

}
