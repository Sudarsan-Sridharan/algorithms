import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class weightedQuickUnion {

	private int[] id;
	private int[] size;
	
	public weightedQuickUnion(int N){
		id = new int[N];
		size = new int[N];
		for (int i=0; i<N; i++)	{
			id[i] = i;		// N-array access
			size[i]= 1;
		}
	}

	private int root(int i){
		while (i != id[i]) {
			id[i] = id[id[i]];	// One pass Path compression
			i= id[i];
		}
		return i;			// Max N- array access , one for each element
	}

	public boolean connected(int p, int q){
		return (root(p) == root(q));		// Max N- array access , one for each element
	}
	
	public void union(int p, int q){
		int pid = root(p);
		int qid = root(q);
		if (pid == qid) return;				// Max N array access
		if(size[pid] < size[qid]){
			id[pid] = qid;
			size[qid] += size[pid];
		}
		else {
			id[qid] = pid;
			size[pid] += size[qid];
		}
			
	}
	public static void main(String[] args) throws IOException{
		System.out.println("**** WELCOME TO Weighted Queue Union ***** ");
		System.out.print("Please Enter the length of array : ");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		int arraySize =  Integer.parseInt(in.readLine());
		weightedQuickUnion wqu = new weightedQuickUnion(arraySize);
		int val1 = 0;
		do {
			System.out.print("\nEnter Value 1 :");
			val1 = Integer.parseInt(in.readLine());
			System.out.print("\nEnter Value 2 :");
			int val2 = Integer.parseInt(in.readLine());
			if (val1 != -1) wqu.union(val1, val2);
		}while (val1 != -1);
		System.out.println("\nThe id Array :");
		for (int a : wqu.id){
			System.out.print("\t"+a);
		}
		System.out.println("\nThe size Array :");
		for (int a : wqu.size){
			System.out.print("\t"+a);
		}

	}

}
