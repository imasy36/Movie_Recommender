
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
public class MinutesFilter implements Filter{
    private int min,max;
    
    public MinutesFilter(int mn,int mx) {
    	min = mn;
    	max = mx ;
    }
    
    @Override
    public boolean satisfies(String id){
    	try{
    	    return (min<=MovieDatabase.getMinutes(id) && MovieDatabase.getMinutes(id)<=max);}
    	catch(IOException e)
    	{
    	    System.out.println("Problem in reading file ");
    	    return false;
    	}    
    }
    
}
