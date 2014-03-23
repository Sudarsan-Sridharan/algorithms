
public class CheckRuntime {

	public int cnt;
	public double size = 1000000000;
	
	public double timeElapsed(){
		Stopwatch stopwatch = new Stopwatch();
		return (stopwatch.elapsedTime());
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckRuntime R = new CheckRuntime();
		Stopwatch stopwatch = new Stopwatch();
		for (int i = 0; i < R.size; i++){
			i += 1;
			R.cnt += 1;
		}
		double time = stopwatch.elapsedTime();
		System.out.print("Time Elapsed = " + time + " value of i : "+ R.cnt);
	}

}
