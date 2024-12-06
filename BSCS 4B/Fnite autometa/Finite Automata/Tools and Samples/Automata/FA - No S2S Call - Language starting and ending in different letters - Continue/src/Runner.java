/*
 * Language L of strings beginning with and ending in different 
 * letters. Implement in such a way that the program keeps on 
 * prompting for the string until QUIT is entered and validates 
 * or invalidates the given strings. Run the string abbba, 
 * baaba, bab and ababb on it.
*/

import java.util.Scanner;

public class Runner {
	static char ch;
	static int ctr, state;
	static String str;
	static boolean exitFlag;

	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);

		while ( true ) {
			System.out.print("String (type quit to exit): ");
			str=new String(input.next());
		
			if( str.equalsIgnoreCase("quit") ) {
				break;
			}
			
			ctr=0;
			state = 1;
			exitFlag = false;
		
			while( !exitFlag ) {
				switch (state) {
					case 1:	exitFlag = state1();	break;
					case 2:	exitFlag = state2();	break;
					case 3:	exitFlag = state3();	break;
					case 4:	exitFlag = state4();	break;
					case 5:	exitFlag = state5();	break;
				}
			}
		}
		
		input.close();
		System.out.println( "Normal Termination" );
	}

	static boolean state1() {
		if( ctr==str.length()) { 
			System.out.println("String Rejected");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state=2;
		else if( ch=='b' )	state=3;
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}

	static boolean state2() {
		if( ctr==str.length()) { 
			System.out.println("String Rejected");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state=2;
		else if( ch=='b' )	state=4;
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}

	static boolean state3() {
		if( ctr==str.length()) { 
			System.out.println("String Rejected");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state=5;
		else if( ch=='b' )	state=3;
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}

	static boolean state4() {
		if( ctr==str.length()) { 
			System.out.println("String accepted");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state=2;
		else if( ch=='b' )	state=4;
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}

	static boolean state5() {
		if( ctr==str.length()) { 
			System.out.println("String accepted");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state=5;
		else if( ch=='b' )	state=3;
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}
}
