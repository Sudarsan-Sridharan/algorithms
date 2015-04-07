package skiingRedmart;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;


public class LongestSkiRundown {
	
	static SkiingElement[][] arr;
	static int maxLen=0;
	static SkiingElement initNode;
	
	private static SkiingElement[][] readElevationValues(File dataFile) {
		// TODO Auto-generated method stub	
		try {
			SkiingElement[][] elevationPoints= null;
			Scanner sc = new Scanner(dataFile);

			String[] fields = null;
			int[] size = new int[2];
			if (sc.hasNextLine()) {
				fields = sc.nextLine().split(" ");
				if (fields.length != 2)	throw new IllegalArgumentException("Invalid Size");
				else {
					for (int i = 0; i < fields.length; i++) size[i] = Integer.parseInt(fields[i]);
					elevationPoints = new SkiingElement[size[0]][size[1]];
				}
			}
			int rowIndex = 0;
			while (sc.hasNextLine()) {
				String line = sc.nextLine();
				if (line.isEmpty())	break;
				fields = line.split(" ");
				if (fields.length != size[1])
					throw new IllegalArgumentException("No of columns doesn't match the given size");

				for (int colIndex = 0; colIndex < fields.length; colIndex++)
					elevationPoints[rowIndex][colIndex] = new SkiingElement(
						Integer.parseInt(fields[colIndex]));
				rowIndex++;
			}
			if (rowIndex != size[0]) throw new IllegalArgumentException("No of rows doesn't match the given size");
			return elevationPoints;
		}
		catch(Exception e){
		   	System.out.println("Error occurred in while reading values from file:"+e.getMessage());
		   	return null;
		   }
		}

	private static SkiingElement[][] initializeArray(SkiingElement[][] arr){
		// Getting all Neighbors of less height
		for (int row=0; row<arr.length;row++){
			for (int col=0;col<arr.length;col++){
				fillNeighbours(arr[row][col], row, col);
			}
		}
		// Calculating MaxLen for all nodes
		for (int row=0; row<arr.length;row++){
			for (int col=0;col<arr.length;col++){
				calculateDepths(arr[row][col]);
				if (maxLen < arr[row][col].maxLen) 			initNode = arr[row][col];
				else if (maxLen == arr[row][col].maxLen)	initNode = getMaxDrop(initNode,arr[row][col]);
				maxLen = initNode.maxLen;
			}
		}
		return arr;
	}
	
	/*
	 * Initialize each element and its neighbors
	 */
	private static void fillNeighbours(SkiingElement curr, int row, int col){
			List<SkiingElement> adjList = new ArrayList<>();
			// Checking List of adjacent element
			if (col>0 			  && curr.value > arr[row][col-1].value)  	adjList.add(arr[row][col-1]); // Left Element
			if (col< arr.length-1 && curr.value > arr[row][col+1].value)  	adjList.add(arr[row][col+1]); // Right Element
			if (row>0 			  && curr.value > arr[row-1][col].value)  	adjList.add(arr[row-1][col]); // Top Element
			if (row<arr.length-1  && curr.value > arr[row+1][col].value) 	adjList.add(arr[row+1][col]); // Bottom Element
			curr.adjElements = adjList;
		}
	
	/*
	 * Calculate Max Path length for each element along with the last Element
	 */
	private static SkiingElement calculateDepths(SkiingElement curr) {
		if (!curr.isVisited){
			curr.isVisited = true;
			
			if (curr.adjElements.size()!=0) {
				SkiingElement maxNode = calculateDepths(curr.adjElements.get(0));
				for (int i=1;i<curr.adjElements.size();i++){
					SkiingElement othrNode = calculateDepths(curr.adjElements.get(i));
					if (othrNode.maxLen > maxNode.maxLen)
						maxNode=othrNode;
					else if (othrNode.maxLen == maxNode.maxLen)
						maxNode = getMaxDrop(maxNode, othrNode);
				}
				curr.nextElement = maxNode;
				curr.lastElementElevation = maxNode.lastElementElevation;
				curr.maxLen = maxNode.maxLen;
			} else {
				curr.maxLen = 0;
				curr.nextElement =null;
			}
			curr.maxLen++;
		}
		return curr;
	}
	
	/*
	 * Determine the Max drop of any give Node
	 */
	private static SkiingElement getMaxDrop(SkiingElement node1, SkiingElement node2){
		int depth1 = node1.value - node1.lastElementElevation;
		int depth2 = node2.value - node2.lastElementElevation;
		return (depth1>=depth2 ? node1:node2) ;
	}
	
	public static void main(String[] args) throws FileNotFoundException {
		// TODO Auto-generated method stub
		
		final long start = System.currentTimeMillis();			// Program Start Time
		
		File file = new File("src/skiingRedmart/input.txt");
		arr = readElevationValues(file);
		
		// Initialize
		arr = initializeArray(arr);
		
		// Printing values
		System.out.println("Start Node = "+ initNode.value + " , End Node = "+ initNode.lastElementElevation);
		System.out.println("Length = "+ maxLen + "  , Drop = "+ (initNode.value - initNode.lastElementElevation));
		SkiingElement temp = initNode;
		System.out.print(temp.value);
		while (temp.value != temp.lastElementElevation) {
			System.out.print("-"+temp.nextElement.value);
			temp= temp.nextElement;
		}
		System.out.println("");
		System.out.println("Elapsed Time: "+(System.currentTimeMillis() - start)/ 1000.0+ " sec");	
//		for (SkiingElement e : arr[3][3].maxLen)
//				System.out.println(e.value);
	}

}
