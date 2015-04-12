package redmart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;
import java.util.Scanner;

public class Spreadsheet {

	final static int ASCIIOFFSET = 65;
	final static int rowMax = 26;
	private HashMap<String, SpreadsheetCell> spreadSheet;
	private static int row, col;

	/*
	 * Spreadsheet Cell Class
	 */
	class SpreadsheetCell {

		private String cellId;
		private String value;
		private boolean isEvaluated;

		public SpreadsheetCell(String cellId, String exp) {
			this.cellId = cellId;
			this.value = exp;
			this.isEvaluated = false;
		}
	}
	
	/*
	 * Spreadsheet Constructor
	 */
	public Spreadsheet() {
		spreadSheet = new HashMap<>();
	}
	
	/*
	 * Main method
	 */
	public static void main(String[] args) throws FileNotFoundException {
		Spreadsheet obj = new Spreadsheet();
		obj.readSpreadSheetValues();	// Read and Initialize Spreadsheet Values
		obj.evaluateSpreadsheet();		// Evaluate Spreadsheet Cell Values
		obj.printSheet();				// Print formatted Spreadsheet Cell Values

	}
	/*
	 * Read and Initialize Spreadsheet Values
	 */
	private void readSpreadSheetValues() throws FileNotFoundException {
		File file = new File("src/redmart/spreadSheetInput");
		Scanner in = new Scanner(new FileInputStream(file));
		String[] s = in.nextLine().split(" ");
		row = Integer.parseInt(s[1]);
		col = Integer.parseInt(s[0]);
		if (row > rowMax || row < 1 || col < 1) {
			in.close();
			throw new IllegalArgumentException("Illegal Row / Column values.");
		}

		for (int i = ASCIIOFFSET; i < row + ASCIIOFFSET; i++)
			for (int j = 1; j <= col; j++) {
				String key = String.valueOf(Character.toChars(i)) + j;
				SpreadsheetCell value = new SpreadsheetCell(key,in.nextLine());
				spreadSheet.put(key, value);
			}
		in.close();
	}
	/*
	 * Evaluate Spreadsheet Cell Values
	 */
	private void evaluateSpreadsheet() {
		for (String s: spreadSheet.keySet()){
			SpreadsheetCell temp = spreadSheet.get(s);
			if (!temp.isEvaluated)
				evaluateCell(temp);
		}
	}
	
	// Evaluate each cell individually
	private String evaluateCell(SpreadsheetCell s) {
		if (isNumeric(s.value)) setSpreadsheetCell(s);
		else if (spreadSheet.containsKey(s.value) && spreadSheet.get(s.value).isEvaluated){
			s.value = spreadSheet.get(s.value).value;
			setSpreadsheetCell(s);
		} else {
			String [] str = s.value.split(" ");
			StringBuffer newStr = new StringBuffer();
			for (int i =0;i< str.length; i++){
				if (spreadSheet.containsKey(str[i])){
					str[i] = evaluateCell(spreadSheet.get(str[i]));
				}
				newStr.append(str[i].trim());
				if (i != str.length) newStr.append(" ");
			}
			s.value = String.valueOf(evalPostfix(newStr.toString()));
			setSpreadsheetCell(s);
		}
		return s.value;
	}
	
	// Update and set Spreadsheet Cell Values
	private void setSpreadsheetCell(SpreadsheetCell s){
		s.isEvaluated = true;
		spreadSheet.put(s.cellId, s);
	}
	
	/*
	 * Calculate and return Postfix calculated value
	 */
	private double evalPostfix(String str) {
		return PostfixEval.evalPostfix(str.toString());
	}

	// Checks if the string is numeric
	public static boolean isNumeric(String str) {
		try { double d = Double.parseDouble(str); } 
		catch (NumberFormatException nfe) {	return false;}
		return true;
	}
	
	/*
	 * Print formatted Spreadsheet Cell Values
	 */
	private void printSheet(){
		System.out.println(col + " " + row);
		for (int i = ASCIIOFFSET; i < row + ASCIIOFFSET; i++)
			for (int j = 1; j <= col; j++) {
				String key = String.valueOf(Character.toChars(i)) + j;
				SpreadsheetCell value = spreadSheet.get(key);
				System.out.printf("%.5f", Double.parseDouble(value.value));
				System.out.println("");
			}
	}

	/*
	 * Reverse Polish Notation(PostFix) Calculator
	 */
	private static class PostfixEval {

		public static final Set<String> operator = new HashSet<>(
				Arrays.asList(new String[] { "+", "-", "*", "/", "++", "--" }));

		static double evalPostfix(String postfix) {
			Stack<Double> tempStack = new Stack<>();
			double result = 0;
			if (Spreadsheet.isNumeric(postfix)) return Double.parseDouble(postfix);
			for (String s : postfix.split(" ")) {
				if (!operator.contains(s) && s!= " ")
					tempStack.push(Double.parseDouble(s));
				else if (operator.contains(s)) {
					if (!tempStack.isEmpty()) {
						switch (s) {
						case "+":
							result = tempStack.pop() + tempStack.pop();
							break;
						case "-":
							result = tempStack.pop() - tempStack.pop();
							break;
						case "*":
							result = tempStack.pop() * tempStack.pop();
							break;
						case "/":
							double op2 = tempStack.pop();
							double op1 = tempStack.pop();
							result = op1/op2;
							break;
						case "++":
							double incr = tempStack.pop();
							result = incr++;
							break;
						case "--":
							double decr = tempStack.pop();
							result = decr--;
							break;
						}
					}
					tempStack.push(result);
				}
			}
			return result;
		}
	}
}
