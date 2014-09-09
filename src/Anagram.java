import java.util.*;

/*
 * GENERATING ANAGRAMS
 */

public class Anagram {
    int count =0;
    HashSet<String> myset = new HashSet<String>();
    
    public void anag(String s1, String s2)	{
    	if(s1.length() == 0) {
    		myset.add(s2);
    	}
    	
    	for(int i = 0; i < s1.length(); i++)	{
    		anag(s1.substring(0, i) + s1.substring(i+1, s1.length()), s1.charAt(i) + s2);
    		}
    	}
    
    public static void main(String[] args)	{
    	long timeNow = System.currentTimeMillis();
    	Anagram ana = new Anagram();
    	ana.anag("president", "");
    	System.out.println("Size = "+ ana.myset.size());
        for(String str : ana.myset){
            System.out.println(str);
        }
        System.out.println("Running Time :"+ (System.currentTimeMillis() - timeNow) + " ms");
}
}