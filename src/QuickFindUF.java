import java.io.*;

public class QuickFindUF {

	private int[] id;
	
	public QuickFindUF(int N){
		id = new int[N];
		for (int i=0; i<N; i++)	id[i] = i;		// N-array access		
	}

	public boolean connected(int p, int q){
		return (id[p] == id[q]);					// 2- array access , one for each element
	}
	
	public void union(int p, int q){
		int pid = id[p];
		int qid = id[q];
		for (int i=0; i< id.length; i++) if (id[i] == pid) id[i] = qid;	
	// Max (2 + 2N) array access, one each for getting pid,qid and max 'N' for value change if all elements other than qid are in same group
	}
	/*  No of array access (for read or for write) : Order of growth of No of array access
	 Initialize = N
	 Union = N
	 Connected = 1
	 
	 DEFECT : Union too expensive
	 
	 For array of length 'N', Takes 'N^2' array access  to produce sequence of 'N' Union commands on 'N' objects
	 */
	
	public static void main(String[] args) throws IOException{
		System.out.println("**** WELCOME TO QUICK MERGE & FIND ***** ");
		System.out.print("Please Enter the length of array : ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int arraySize =  Integer.parseInt(in.readLine());
		QuickFindUF qf = new QuickFindUF(arraySize);
		System.out.print("Please operation to Perform : \nQuick Find : 1\nQuick Merge : 2");
		int op = Integer.parseInt(in.readLine());
		if (op==1){
			Stopwatch stopwatch = new Stopwatch();
			System.out.print("Starting Operation at :" + stopwatch.elapsedTime());
			System.out.print("\nEnter Value 1 ");
			int val1 = Integer.parseInt(in.readLine());
			System.out.print("\nEnter Value 2 ");
			int val2 = Integer.parseInt(in.readLine());
			System.out.print("\nResult :" + qf.connected(val1,val2) +
					" in "+stopwatch.elapsedTime());
		}
		else if(op==2){
				int val1 = 0;
				Stopwatch stopwatch = new Stopwatch();
			do {
				System.out.print("Starting Operation at :" + stopwatch.elapsedTime());
				System.out.print("\nEnter Value 1 ");
				val1 = Integer.parseInt(in.readLine());
				System.out.print("\nEnter Value 2 ");
				int val2 = Integer.parseInt(in.readLine());
				if (val1 != -1) qf.union(val1,val2);
			} while(val1 != -1);
			for (int a : qf.id){
				System.out.print("\t"+a);
			}
			System.out.println("\nElapsed Time : "+stopwatch.elapsedTime());			
		}  

	}
}
