
public class CheckRuntime {
	static Stopwatch stopwatch = new Stopwatch();
	public int cnt;
	public double size = 999999999;
	
	public void displayRepeatedChar(){
		int ascii[] = new int[255];
		System.out.println("\n"+ascii[3]);
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CheckRuntime R = new CheckRuntime();
		int ascii[] = new int[255];
		for (int i = 0; i < R.size; i++){
			i += 1;
			R.cnt += 1;
		}
		double time = stopwatch.elapsedTime();
		System.out.print("Time Elapsed = " + time + " value of i : "+ R.cnt);
		R.displayRepeatedChar();
	}

}
