public class Array1D {
	protected int AS;
	protected Object[] elements;
	
	public Array1D( int initialCapacity) 
	{ 
		if(initialCapacity < 1)  
			throw new IllegalArgumentException ("initialCapacity must be >= 1");
		
		elements = new Object[initialCapacity];
		AS = initialCapacity;
	}
	
	public Array1D()
	{
		elements = null;
		AS = 0;
	}
		
	public void create(int initialCapacity) 
	{ 
		if(initialCapacity < 1)  
			throw new IllegalArgumentException ("initialCapacity must be >= 1");
		
		elements = new Object[initialCapacity];
		AS = initialCapacity;
	}
	
	void checkIndex(int index)
	{
		if(index < 0 || index>=AS )
			throw new IndexOutOfBoundsException	("index = "+ index + "Size = "+ AS);
	}

	public void put( int index, Object theElement )
	{
		checkIndex(index);
		elements[index] = theElement;
	}
	
	public Object get( int index )
	{
		checkIndex(index);
		return elements[index];
	}

	
	public Object remove( int index )
	{
		checkIndex(index);
		
		Object ob = elements[index];
		elements[index] = null;
		
		return ob;
	}

	public int indexOf(Object theElement)
	{
		for(int i=0;i<AS; i++)
			if(elements[i]==theElement)
				return i;
		
		return -1;
	}

	
	public int length()
	{
		return AS;
	}
	
	public Object[] changeLength(int newLength)
	{
		Object newArray[]= new Object[newLength];
		System.arraycopy(elements, 0, newArray, 0, AS); 

		elements=newArray;
		AS=newLength;
		
		return newArray;
	}

	public String toString()
	{ 
		StringBuffer s = new StringBuffer("[");
		 
		for (int i = 0; i < AS; i++) 
			if(elements[i] ==null) 
				s.append("__, "); 
			else s.append(elements[i].toString() + ", "); 
		
		if(AS > 0) 
			s.delete(s.length() -2, s.length()); 
		
		s.append("]");
		return new String(s); 
	}
}