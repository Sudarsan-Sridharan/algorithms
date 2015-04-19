package palantir;

/*
 * Desired Complexity = O(n)
 */
public class SumThreeSmallestNum {

	public static void main(String[] args){
		
        int [] input = new int[] {-1,-2,-3,-4,-5,-6};
        int res = sumThreeSmallest(input);
        System.out.println(res);
	}
	
	static int sumThreeSmallest(int [] data) {
		int a=0,b=0,c=0;
		int sum = 0;
		if(data.length<=3) {
			for(int i=0;i<data.length;i++) {
				sum+=data[i];
			}
		} else {
			a=data[0];
			b = data[1];
			c = data[2];
			
			for(int i=3;i<data.length;i++) {
				if(data[i]<=a && data[i]<b && data[i]<c){
				   c = b;
				   b = a;
				   a = data[i];
			   } else if(data[i]>a && data[i]<=b && data[i]<c) {
				   c = b;
				   b=data[i];
			   } else if(data[i]>a && data[i]>b && data[i]<=c)
				   c= data[i];
			}
			sum = a+b+c;
		}
		return sum;
    }

}
