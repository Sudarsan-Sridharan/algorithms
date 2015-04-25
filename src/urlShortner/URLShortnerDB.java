package urlShortner;

/* 
 * Bijective Mapping - Use Auto-Increment value of "id" from DB table for generating Hash value
 * 
 * Database table with three columns:
    -- id, integer, auto-increment
    -- longUrl, string, the long URL the user entered
    -- shortUrl, string, the shortened URL (or just the six characters)
 */
public class URLShortnerDB {
	
	public static final String SALT= "23456789bcdfghjkmnpqrstvwxyzBCDFGHJKLMNPQRSTVWXYZ-_";
	public static final int BASE = SALT.length();
	
	/*
	 * URLShortnerDB.encode() takes an ID and turns it into a short string
	 */
	public static String encode(int id){
		StringBuffer shortURL = new StringBuffer();
		
		while(id > 0){
			shortURL.insert(0, SALT.charAt(id%BASE));
			id/=BASE;
		}
		return shortURL.toString();
	}

	/*
	 *   URLShortnerDB.decode() takes a short string and turns it into an ID
	 */
	public static int decode(String shortUrl){
		int id=0;
		for(int i=0;i<shortUrl.length();i++)
			id = id*BASE+SALT.indexOf(shortUrl.charAt(i));
		return id;
	}
	
	public static void main(String[] args) {
		int id = 35236463;
		System.out.println(encode(id));
		
		String shortUrl = "7dGj4";
		System.out.println(decode(shortUrl));

	}

}
