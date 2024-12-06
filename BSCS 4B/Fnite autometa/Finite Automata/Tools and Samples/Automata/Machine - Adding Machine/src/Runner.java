import java.util.Scanner;

public class Runner {
	public static void main(String[] args) {
		String binaryNumber1, binaryNumber2, resString;
		
		Scanner input =new Scanner(System.in);

		System.out.print("Enter Binary No: ");
		binaryNumber1 = new String(input.next());
		
		System.out.print("Enter Binary No: ");
		binaryNumber2 = new String(input.next());
		
		input.close();
		System.out.println();
		
		resString = addingMachine.add(binaryNumber1, binaryNumber2);

		System.out.println();
		System.out.print( "Sum: " + resString );
	}
}
