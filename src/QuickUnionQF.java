import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class QuickUnionQF {

	private int[] id;
	
	public QuickUnionQF(int N){
		id = new int[N];
		for (int i=0; i<N; i++)	id[i] = i;		// N-array access		
	}

	private int root(int i){
		while (i != id[i]) i= id[i];
		return i;					// Max N- array access , one for each element
	}

	public boolean connected(int p, int q){
		return (root(p) == root(q));					// Max N- array access , one for each element
	}
	
	public void union(int p, int q){
		int pid = root(p);
		int qid = root(q);
		id[pid] = qid;									// Max N array access
	}
	/*  No of array access (for read or for write) : Order of growth of No of array access
	 Initialize = N
	 Union = N
	 Connected = N
	 
	 DEFECT : Trees can get big, "Find" is too expensive
	 
	 For array of length 'N', Takes 'N^2' array access  to produce sequence of 'N' Union commands on 'N' objects
	 */
	
	public static void main(String[] args) throws IOException{
		System.out.println("**** WELCOME TO QUICK UNION & FIND ***** ");
		System.out.println("Please Enter the length of array : ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int arraySize =  Integer.parseInt(in.readLine());
		QuickUnionQF qf = new QuickUnionQF(arraySize);
		System.out.println("Please operation to Perform : \nQuick Find : 1\nQuick Merge : 2");
		int op = Integer.parseInt(in.readLine());
		if (op==1){
			Stopwatch stopwatch = new Stopwatch();
			System.out.println("Starting Operation at :" + stopwatch.elapsedTime());
			System.out.println("Enter Value 1 ");
			int val1 = Integer.parseInt(in.readLine());
			System.out.println("Enter Value 2 ");
			int val2 = Integer.parseInt(in.readLine());
			System.out.println("Result :" + qf.connected(val1,val2) +
					" in "+stopwatch.elapsedTime());
		}
		else if(op==2){
			Stopwatch stopwatch = new Stopwatch();
			System.out.println("Starting Operation at :" + stopwatch.elapsedTime());
			System.out.println("Enter Value 1 ");
			int val1 = Integer.parseInt(in.readLine());
			System.out.println("Enter Value 2 ");
			int val2 = Integer.parseInt(in.readLine());
			qf.union(val1,val2);
			System.out.println("Number "+val1+" and Number "+val2+
					" have been connected in "+stopwatch.elapsedTime());			
		}  

	}
}
