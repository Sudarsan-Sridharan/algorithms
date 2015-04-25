import java.util.*;

/*
 * GENERATING ANAGRAMS
 */

public class Anagram {
    int count =0;
    TreeSet<String> myset = new TreeSet<>();
//    HashSet<String> myset = new HashSet<String>();
    
    public void anag(String s1, String s2)	{
    	if(s1.length() == 0) {
    		myset.add(s2);
    	}
//    	System.out.println("Inside Call - s1= "+s1+" ,s2= "+s2 );
    	for(int i = 0; i < s1.length(); i++)	{
    		anag(s1.substring(0, i) + s1.substring(i+1), s2+s1.charAt(i));
    		}
    	}
    
    public static void main(String[] args)	{
    	long timeNow = System.currentTimeMillis();
    	Anagram ana = new Anagram();
    	ana.anag("abba", "");
        for(String str : ana.myset){
            System.out.println(str);
        }
        System.out.println(ana.myset.size());
        System.out.println("Running Time :"+ (System.currentTimeMillis() - timeNow) + " ms");
    	System.out.println("Size = "+ ana.myset.size());
}
}