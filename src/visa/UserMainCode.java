package visa;

public class UserMainCode {

    public static void GetJumpCount(int up,int down,int N,int[] height){
    	int jumpCount = 0;
    	
    	for (int i = 0; i < N; i++) {
			int h= height[i];
			int reached=0;
			while(reached<h){
				jumpCount++;
				reached+=up;
				if (reached>=h) break;
				reached-=down;
			}
		}
    	System.out.println(jumpCount);
    }
    
	public static void NumberingBottles(int N) {
		int sum = 0;
		int[][] arr = new int[N+1][N+1];

		for (int i = 0; i < N; i++)	arr[i][0]=1;
		
		//Initializing
		for (int i = 1; i < N; i++) {
			for (int j = 1; j <=i ; j++) {
				if (i==1) 	arr[i][j] = arr[i-1][j-1]+1;
				else 		arr[i][j] = arr[i-1][j-1]+arr[i-1][j];
			}
			arr[i][i+1] = 1;
		}
		
		// Calculating Sum
		for (int i = 0; i < N; i++) {
			sum+=arr[N-1][i];
		}
		System.out.println(sum);
    	
		// Printing
		System.out.println("Pascal's Triangle");
		for (int i = 0; i < N; i++) {
			for (int j = 0; j <=i+1; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println(" ");
		}
    }
    
    public static void main(String args[]){
    	int arr[] ={20,5,12,11,3};
    	GetJumpCount(3, 1, 5, arr);
    	NumberingBottles(5);
    }

}
