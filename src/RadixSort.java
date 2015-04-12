
/*
 * LSD = Least-significant-digit-first string sort, e.g 1, 2, 3, 4, 5, 6, 7, 8, 9, 10
 * MSD = Most-significant-digit-first string sort,  e.g 1, 10, 2, 3, 4, 5, 6, 7, 8, 9
 */
public class RadixSort {
	
	/*
	 * Least-significant-digit-first string sort (LSD Sort)
	 * LSD string (radix) sort.
	 * Consider characters from right to left.
	 * Stably sort using dth character as the key (using key-indexed counting).
	 * Equal String Length
	 */
	public static void LSDStringSort(String[] a, int W)
	   {
	      int R = 256;
	      int N = a.length;
	      String[] aux = new String[N];
	      for (int d = W-1; d >= 0; d--)
	      {
	         int[] count = new int[R+1];
	         for (int i = 0; i < N; i++)
	            count[a[i].charAt(d) + 1]++;

	         for (int r = 0; r < R; r++)
	            count[r+1] += count[r];

	         for (int i = 0; i < N; i++)
	        	 aux[count[a[i].charAt(d)]++] = a[i];
	            
	         for (int i = 0; i < N; i++)
	            a[i] = aux[i];
	      }
	      
	   }
	
	public static void MSDStringSort(String[] a, int W){
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
		String [] a = {"Rear","Rest","Pact","Pest","Tact","Ramp","Tale","Rest"};
		int minLength =0;
		
		LSDStringSort(a,4);
		for (int i=0; i<a.length;i++)
			System.out.println(a[i]);
	}

}
