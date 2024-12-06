
public class addingMachine {
	static char carry;
	static String bNumber1;
	static String bNumber2;
	
	static String add( String s1, String s2 ) {
		int ctr;
		
		int size = s1.length()>s2.length() ? s1.length() : s2.length();  
		String s="";
		
		carry = '0';
		bNumber1 = binaryNumber.setLength( s1, size );
		bNumber2 = binaryNumber.setLength( s2, size );
		
		ctr = size-1;

		for( ; ctr>=0 ; ctr-- ) {
			switch (carry) {
				case '0': s = state0( bNumber1.charAt(ctr), bNumber2.charAt(ctr) ) + s; break;
				case '1': s = state1( bNumber1.charAt(ctr), bNumber2.charAt(ctr) ) + s; break;
			}			
		}
		
		return s;
	}
	
	static char state0( char digit1, char digit2 ) {
		if(digit1=='0' && digit2=='0') return '0';
		if((digit1=='0' && digit2=='1') || (digit1=='1' && digit2=='0')) return '1';

		carry = '1';
		return '0';
	}
	
	static char state1(char digit1, char digit2 ) {
		if(digit1=='1' && digit2=='1') return '1';
		if((digit1=='0' && digit2=='1') || (digit1=='1' && digit2=='0')) return '0';
		
		carry = '0';
		return '1';
	}
}
