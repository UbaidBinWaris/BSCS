
public class binaryNumber {
	static String setLength( String s, int size ) {
		int ctr = size-s.length();
		for( ; ctr>0 ; ctr-- )
			s = '0' + s;
		
		return s;
	}
}
