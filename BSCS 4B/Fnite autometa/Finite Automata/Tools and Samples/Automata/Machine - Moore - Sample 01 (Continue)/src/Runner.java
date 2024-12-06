// Sample Moore Machine
// Developed by MN Khokhar
// For "Finite Automata Theory, Spring 2017, SZABIST (Islamabad Campus)"

/*
 * The machine should prompt the user for a string in an infinite loop. 
 * If the user enters the letter q, the machine should quit, otherwise it
 * should run the string on the machine, print the output and then 
 * prompt for the next string. Note that for each new input, it will 
 * be started from the state where it was left off by the previous
 * string. Run the string with the strings abbabbba, abbaba, baabbba, 
 * and abbabaaaab.
 */

import java.util.Scanner;

public class Runner {
	static char ch;
	static int ctr, state=0;
	static String str="";
	static boolean exitFlag;

	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		
		while ( true ) {
			System.out.print("String (q to quit): ");
			str=new String(input.next());
		
			if( str.equalsIgnoreCase("q") ) {
				break;
			}
			
			ctr = 0;
			exitFlag = false;
		
			System.out.println("Starting at state "+state);
				
			while( !exitFlag ) {
				switch (state) {
					case 0:	exitFlag = stateq0();	break;
					case 1:	exitFlag = stateq1();	break;
					case 2:	exitFlag = stateq2();	break;
					case 3:	exitFlag = stateq3();	break;
				}
			}
		}
		
		System.out.println();
		System.out.println("Exiting...");
		input.close();
	}

	static boolean stateq0() {
		System.out.print("1");
		
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' )		state=1;
		else if (ch == 'b')	state=3;
		else {
			System.out.println("Invalid String");
			return true;
		}
		
		return false;
	}

	static boolean stateq1() {	
		System.out.print("0");
		
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' ) 	state=3;
		else if (ch == 'b')	state=1;
		else {
			System.out.println("Invalid String");
			return true;
		}
		
		return false;
	}

	static boolean stateq2() {		
		System.out.print("0");
		
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' ) 	state=0;
		else if (ch == 'b')	state=3;
		else {
			System.out.println("Invalid String");
			return true;
		}
		
		return false;
	}

	static boolean stateq3() {
		System.out.print("1");
		
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			return true;
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' )		state=3;
		else if (ch == 'b')	state=2;
		else {
			System.out.println("Invalid String");
			return true;
		}
		
		return false;
	}
}

