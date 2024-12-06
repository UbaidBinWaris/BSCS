public class Runner {
	public static void main(String[] args) {
		temp( 0, 0, 0 );
		temp( 0, 0, 1 );

		temp( 0, 1, 0 );
		temp( 0, 1, 1 );

		temp( 1, 0, 0 );
		temp( 1, 0, 1 );

		temp( 1, 1, 0 );
		temp( 1, 1, 1 );
}

	static void temp( int oldA, int oldB, int inBit ) {
		int O = inBit & oldB;
		int A = (inBit & (oldA & oldB));
		int B = oldA;
		uint(~A);
		System.out.println( "new A = NOT of " + A );
		System.out.println( "new B = " + B );
		System.out.println( "Output = " + O );
		System.out.println();
	}
}