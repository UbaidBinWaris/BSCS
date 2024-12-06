import java.util.Stack;

public class Runner {
	static Stack<Character> stk;
	
	static char ch;
	static int ctr;
	static String state;
	static String str;
	static boolean exitFlag;

	public static void main(String[] args) {
		str = "aaabbb#";
		stk = new Stack<>();
		
		stk.push((Character) '#');
		
		ctr=0;
		state = "R1";
		exitFlag = false;
		
		while( !exitFlag ) {
			switch (state) {
				case "R1":	exitFlag = read1();	break;
				case "R2":	exitFlag = read2();	break;
				case "P1":	exitFlag = pop1();	break;
				case "P2":	exitFlag = pop2();	break;
			}
		}
	}

	static boolean read1() {
		System.out.println("State: READ1, Stack: " + stk.toString() + ", Tape: " + str.substring(0, ctr));
		ch = str.charAt(ctr++);
		
		if( ch=='a' ) {
			stk.push((Character) 'a');
			System.out.println("State: PUSHa, Stack: " + stk.toString() + ", Tape: " + str.substring(0, ctr));
			state="R1";	
		}
		else if( ch=='b' )	state="P1";
		else if( ch=='#' )	state="P2";
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}
	
	static boolean read2() {
		System.out.println("State: READ2, Stack: " + stk.toString() + ", Tape: " + str.substring(0, ctr));
		ch = str.charAt(ctr++);
		
		if( ch=='a' ) {
			System.out.println("String Rejected");
			return true;
		}
		else if( ch=='b' )	state="P1";
		else if( ch=='#' )	state="P2";
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}
	
	static boolean pop1() {
		System.out.println("State: POP-1, Stack: " + stk.toString() + ", Tape: " + str.substring(0, ctr));
		ch = (char) stk.pop();
		
		if( ch=='a' ) state="R2";
		else if( (ch=='b') || (ch=='#') )	
		{
			System.out.println("String Rejected");
			return true;
		}
		else {
			System.out.println("Invalid Alphabets in String");
			return true;
		}

		return false;
	}
	
	static boolean pop2() {
		System.out.println("State: POP-2, Stack: " + stk.toString() + ", Tape: " + str.substring(0, ctr));
		ch = (char) stk.pop();
		
		if( (ch=='a') || (ch=='b') ) 
		{
			System.out.println("String Rejected");
			return true;
		}
		else if(  ch=='#' )	
		{
			System.out.println("String ACCEPTED !!!");
			return true;
		}

		System.out.println("Invalid Alphabets in String");
		return true;
	}	
}