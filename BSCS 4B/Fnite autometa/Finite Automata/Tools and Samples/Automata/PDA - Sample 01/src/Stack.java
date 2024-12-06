public class Stack {
	protected Object[] stk;	// Stack
	protected int SS; 		// Stack Size
	protected int SC; 		// Stack Counter
		
	public Stack(int initialCapacity) 
	{ 
		if(initialCapacity < 1)  
			throw new IllegalArgumentException ("initialCapacity must be >= 1");
		
		stk = new Object[initialCapacity];
		SS = initialCapacity;
		SC = 0;
	}
		
	public Stack()
	{
		stk = null;
		SS = SC = 0;
	}
	
	public void create(int initialCapacity) 
	{ 
		if(initialCapacity < 1)  
			throw new IllegalArgumentException ("initialCapacity must be >= 1");
		
		stk = new Object[initialCapacity];
		SS = initialCapacity;
		SC = 0;
	}
	
	public boolean isfull()
	{
		return( SC==SS );
	}
	
	public boolean isempty()
	{
		return( SC==0 );
	}
	
	public boolean push(Object element)
	{
		if( SC==SS ) 
			return false;
			
		stk[SC] = element;
		SC++;
		return true;
	}
	
	public Object pop()
	{
		if( SC==0 ) 
			return false;
			
		SC--;
		return stk[SC];
	}

	public String toString()
	{ 
		int i;
		StringBuffer s = new StringBuffer("[");
	 
		for( i = 0; i < SC; i++) 
			s.append(stk[i].toString() + ", "); 

		for( ; i < SS; i++) 
			s.append("_, "); 

		if(SS > 0) 
			s.delete(s.length() -2, s.length()); 
		
		s.append("]");
		return new String(s); 
	}

	public String getStatus()
	{
		StringBuffer s = new StringBuffer("");
		s.append("SC: "+SC);
		return new String(s); 
	}
}