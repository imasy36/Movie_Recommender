
/**
 * Write a description of GenreFilter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.*;
public class DirectorsFilter implements Filter{
    private String[] directors;
    
    public DirectorsFilter(String dir) {
        directors = dir.split(",");
    }
    
    @Override
    public boolean satisfies(String id){
        try
        {
            for(String dir : directors)
            {
                if(MovieDatabase.getDirector(id).contains(dir))
                    return true;
            }
            return false;
        }
        catch(IOException e)
        {
            System.out.println("Problem in reading file ");
            return false;
        }    
    }
    
}
