
/*
 * $ echo A B C D E F G H I | java Subset 3
 * $ echo AA BB BB BB BB BB CC CC | java Subset 8
 * 
 */
public class Subset {

	public static void main(String[] args) {
		int num = Integer.parseInt(args[0]);
		String [] allStrings = StdIn.readAllStrings();
		StdRandom.shuffle(allStrings);
		for (int i = 0; i < num; i++) {
			System.out.println(allStrings[i]);
		}
	}
}
