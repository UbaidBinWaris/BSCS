import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		int no1, no2;
		String binaryNumber1, binaryNumber2, resString;
		
		Scanner input =new Scanner(System.in);

		System.out.print("Enter No1: ");
		no1 = Integer.parseInt( input.next() );

		System.out.print("Enter No2: ");
		no2 = Integer.parseInt( input.next() );
		
		input.close();
		System.out.println();
		
		binaryNumber1 = binaryConversions.toBinary( no1 );
		binaryNumber1 = binaryConversions.setLength( binaryNumber1, 8 );
		System.out.println( "No1: " + binaryNumber1 );
		
		binaryNumber2 = binaryConversions.toBinary( no2 );
		binaryNumber2 = binaryConversions.setLength( binaryNumber2, 8 );
		System.out.println( "No2: " + binaryNumber2 );
		
		binaryNumber2 = complementingMachine.complement(binaryNumber2);
		
		System.out.println();
		System.out.print( "Complemented No2: " + binaryNumber2 );

		resString = addingMachine.add(binaryNumber1, binaryNumber2);

		System.out.println();
		System.out.print( "Sum of No1 and Complemented No2: " + resString );
		
		resString = addingMachine.add(resString, "1");

		System.out.println();
		System.out.print( "Result incremented by 1: " + resString );
		
		System.out.println();
		System.out.print( "Final Result: " + binaryConversions.toDecimal(resString) );	
	}
}
