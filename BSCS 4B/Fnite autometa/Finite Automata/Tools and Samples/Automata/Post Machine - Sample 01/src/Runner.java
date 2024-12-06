
public class Runner {
	static int ctr;
	static int state;
	static String store;
	
	public static void main(String[] args) {
		boolean exitFlag=false;
		
		ctr = 0;
		state = 1;
		store = "aaabbb";

		System.out.println("State: START, " + store.substring(ctr, store.length()) );
		
		store = store + '#';
		System.out.println("State: ADD #, " + store.substring(ctr, store.length()) );
		
		while( !exitFlag ) {
			switch (state) {
				case 1:	exitFlag = read1();	break;
				case 2:	exitFlag = read2();	break;
				case 3:	exitFlag = read3();	break;
			}
		}
	}

	static boolean read1() {
		char ch = store.charAt(ctr++);
		System.out.println("State: READ1, " + store.substring(ctr, store.length()) );
		
		if( ch=='a' )	
			state=2; 
		else if( ch=='#' )	{
			System.out.println( "ACCEPTED!!!" );
			return true;
		}
		else {
			System.out.println("Machine CRASH!!!");
			return true;
		}
		
		return false;
	}
	
	static boolean read2() {
		char ch = store.charAt(ctr++);
		System.out.println("State: READ2, " + store.substring(ctr, store.length()) );
		
		
		if( ch=='a' )		{
			store = store + 'a';
			System.out.println("State: ADD a, " + store.substring(ctr, store.length()) );
		}
		else if( ch=='b' )	state = 3;
		else {
			System.out.println("Machine CRASH!!!");
			return true;
		}
		
		return false;
	}
	
	static boolean read3() {
		char ch = store.charAt(ctr++);
		System.out.println("State: READ3, " + store.substring(ctr, store.length()) );		
		
		if( ch=='b' ) {
			store = store + 'b';
			System.out.println("State: ADD b, " + store.substring(ctr, store.length()) );
		} 
		else if( ch=='#' ) {
			store = store + '#';
			System.out.println("State: ADD #, " + store.substring(ctr, store.length()) );
			state = 1;
		}
		else {
			System.out.println("Machine CRASH!!!");
			return true;
		}
		
		return false;
	}
}
