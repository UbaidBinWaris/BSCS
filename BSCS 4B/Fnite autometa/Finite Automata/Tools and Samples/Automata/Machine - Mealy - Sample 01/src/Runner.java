// Sample Mealy Machine
// Developed by MN Khokhar
// For "Finite Automata Theory, Spring 2016, SZABIST (Islamabad Campus)"

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
		stateq0();
	}

	static void stateq0() {
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' ) {
			System.out.println("0");
			stateq1();
		}
		else if (ch == 'b') {
			System.out.println("0");
			stateq3();
		} else {
			System.out.println("Invalid String");
			System.exit(0);
		}
	}

	static void stateq1() {	
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' ) {
			System.out.println("1");
			stateq3();
		}
		else if (ch == 'b') {
			System.out.println("1");
			stateq2();
		} else {
			System.out.println("Invalid String");
			System.exit(0);
		}
	}

	static void stateq2() {		
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' ) {
			System.out.println("0");
			stateq3();
		}
		else if (ch == 'b') {
			System.out.println("1");
			stateq3();
		} else {
			System.out.println("Invalid String");
			System.exit(0);
		}
	}

	static void stateq3() {
		if (ctr == str.length()) {
			System.out.println();
			System.out.println("String completed ...");
			System.exit(0);
		}
		
		ch = str.charAt(ctr++);
		if (ch == 'a' ) {
			System.out.println("1");
			stateq3();
		}
		else if (ch == 'b') {
			System.out.println("1");
			stateq0();
		} else {
			System.out.println("Invalid String");
			System.exit(0);
		}
	}
}

