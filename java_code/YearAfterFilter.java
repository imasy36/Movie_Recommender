import java.io.*;
public class YearAfterFilter implements Filter {
	private int myYear;
	
	public YearAfterFilter(int year) {
		myYear = year;
	}
	
	@Override
	public boolean satisfies(String id){
		try{
		    return MovieDatabase.getYear(id) >= myYear;}
		catch(IOException e)
		{
		    System.out.println("Problem in reading file ");
		    return false;
		}    
	}

}
