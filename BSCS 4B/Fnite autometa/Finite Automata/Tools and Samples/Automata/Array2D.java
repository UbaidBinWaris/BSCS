
public class Array2D {
	protected int trows;
	protected int tcols;
	protected Object[] elements;
	
	public Array2D( int rows, int cols ) 
	{ 
		if( rows<1 || cols<1 )  
			throw new IllegalArgumentException ("initialCapacity must be >= 1");
		
		elements = new Object[rows*cols];
		trows = rows;
		tcols = cols;	
	}
	
	public Array2D()
	{
		trows = tcols = 0;
	}
		
	public void create( int rows, int cols ) 
	{ 
		if( rows<1 || cols<1 )  
			throw new IllegalArgumentException ("initialCapacity must be >= 1");
		
		elements = new Object[rows*cols];
		trows = rows;
		tcols = cols;	
	}
	
	void checkIndex(int row, int col)
	{
		if( row < 0 || row>=trows || col < 0 || col>=tcols )
			throw new IndexOutOfBoundsException	("Row: "+ row + ", Range: 0 - "+trows+"Col: "+ col + ", Range: 0 - "+tcols);
	}

	public void put( int row, int col, Object theElement )
	{
		checkIndex(row, col);
	
		elements[row*tcols+col] = theElement;
		return;
	}
	
	public Object get( int row, int col )
	{
		checkIndex(row, col);
		return elements[row*tcols+col];
	}

	public Object remove( int row, int col )
	{
		checkIndex(row, col);
		
		Object ob = elements[row*tcols+col];
		elements[row*tcols+col] = null;
		
		return ob;
	}

	public int nrows()
	{
		return trows;
	}
	
	public int ncols()
	{
		return tcols;
	}
	
	public String toString()
	{ 
		StringBuffer s = new StringBuffer("[");
		 
		for (int r=0; r<trows; r++) {
			for (int c=0; c<tcols; c++) {
				if(elements[r*tcols+c] ==null) 
					s.append("_, "); 
				else s.append(elements[r*tcols+c].toString() + ", ");
			}
			
			s.delete(s.length() -2, s.length());
			s.append(" # ");
		}
		
		if(trows>0 && tcols>0) 
			s.delete(s.length() -2, s.length()); 
		
		s.append("]");
		return new String(s); 
	}
}
