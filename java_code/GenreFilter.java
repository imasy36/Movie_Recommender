
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
public class GenreFilter implements Filter{
    private String myGenre;
    
    public GenreFilter(String Genre) {
    	myGenre = Genre;
    }
    
    @Override
    public boolean satisfies(String id){
    	try{
    	    return MovieDatabase.getGenres(id).contains(myGenre);}
    	catch(IOException e)
    	{
    	    System.out.println("Problem in reading file ");
    	    return false;
    	}    
    }
    
}
