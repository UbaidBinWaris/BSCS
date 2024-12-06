/*
	Write a recursive program for an FA corresponding 
	to the language defined over {a,b} beginning with 
	and ending in different Letters. 
*/

import java.util.Scanner;

public class Runner {
	static char ch;
	static int ctr;
	static String str;

	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		System.out.print("String: ");

		str=new String(input.next());
		input.close();
		
		ctr=0;
		state1();
	}

	static void state1() {
		if( ctr==str.length()) { 
			System.out.println("String Rejected");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state2();
		else if( ch=='b' )	state3();
		else {
			System.out.println("Invalid Alphabets in String");
			System.exit(0);
		}
	}

	static void state2() {
		if( ctr==str.length()) { 
			System.out.println("String Rejected");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state2();
		else if( ch=='b' )	state4();
		else {
			System.out.println("Invalid Alphabets in String");
			System.exit(0);
		}
	}

	static void state3() {
		if( ctr==str.length()) { 
			System.out.println("String Rejected");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state5();
		else if( ch=='b' )	state3();
		else {
			System.out.println("Invalid Alphabets in String");
			System.exit(0);
		}
	}

	static void state4() {
		if( ctr==str.length()) { 
			System.out.println("String accepted");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state2();
		else if( ch=='b' )	state4();
		else {
			System.out.println("Invalid Alphabets in String");
			System.exit(0);
		}
	}

	static void state5() {
		if( ctr==str.length()) { 
			System.out.println("String accepted");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if( ch=='a' )		state5();
		else if( ch=='b' )	state3();
		else {
			System.out.println("Invalid Alphabets in String");
			System.exit(0);
		}
	}
}