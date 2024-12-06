import java.util.Scanner;
public class Runner {
	static char ch;
	static int ctr;
	static StringBuffer str;
	
	public static void main(String[] args) {
		Scanner input =new Scanner(System.in);
		System.out.print("Variablename: ");

		str=new StringBuffer(input.next());
		input.close();

		ctr=0;
		state1();
	}
	
	static void state1() {
		ch = str.charAt(ctr++);
		
		if( (ch>='A' && ch<='Z')||(ch>='a' && ch<='z')||(ch=='_') )
			state2();
		else
			System.out.println( "Invalid Variablename" );
	}

	static void state2() {
		if( ctr<str.length() ) {
			ch = str.charAt(ctr++);
		
			if( (ch>='A' && ch<='Z')||(ch>='a' && ch<='z')||(ch>='0' && ch<='9')||(ch=='_') )
				state2();
			else
				System.out.println( "Invalid Variablename" );
		}
		else
			System.out.println( "Valid Variablename" );
	}
}
