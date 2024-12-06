
public class binaryConversions {
	static String toBinary( int no ) {
		String s="";
		
		while( no > 0 ) {
	    	s = no%2 + s;
	    	no = no/2;
		}
		
		return s;
	}
	
	static public int toDecimal(String s) {
	    int v = 0;
	    for( int ctr = 0; ctr < s.length(); ctr++) {
	        v *= 10;
	        v += s.charAt(ctr) - '0';
	    }
	    return v;
	}
	
	static String setLength( String s, int size ) {
		int ctr = size-s.length();
		for( ; ctr>0 ; ctr-- )
			s = '0' + s;
		
		return s;
	}
}