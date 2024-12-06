
public class complementingMachine {
	static String complement( String str ) {
		int ctr = str.length() - 1;
		String s = "";
		
		for( ; ctr>=0 ; ctr-- ) 
			s = (str.charAt(ctr)=='1' ? '0' : '1') + s;

		return s;
	}
}
