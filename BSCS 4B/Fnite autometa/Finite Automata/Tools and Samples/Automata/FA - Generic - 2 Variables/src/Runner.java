import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		int state = 0;
		boolean acceptStates[] = new boolean[] { false, false, true };
		
		int[][] transitionTable = new int[][] {
				{  1, -1 },
				{  2,  1 },
				{ -1, -1 }
		};
		
		Scanner input =new Scanner(System.in);
		System.out.print("String: ");

		String str=new String(input.next());
		input.close();
		
		int col;
		int ctr=0;
		char ch='\0';

		while( state!=-1 ) {
			if( ctr==str.length())
				break;
			
			ch = str.charAt(ctr++);
			col = (ch=='a' ? 0: (ch=='b' ? 1: -1));
			
			if( col==-1 )
				state = -1;
			else
				state = transitionTable[state][col];
		}
		
		if( state==-1 )
			System.out.println( "Invalid alphabets: " + ch );
		else
			System.out.println( acceptStates[state] ? "Accepted" : "Rejected" );
	}
}
