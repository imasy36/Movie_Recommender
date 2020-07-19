import edu.duke.*;
import java.io.*;
import java.util.*;
import org.apache.commons.csv.*;
/**
 * Write a description of FirstRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FirstRatings {
    public ArrayList<Movie> loadMovies(String fname) throws IOException
    {
        ArrayList<Movie> movies = new ArrayList<Movie>();
        FileReader fr = new FileReader(fname);
        CSVParser par = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fr);
        for(CSVRecord rec : par)
        {
            Movie m = new Movie(rec.get(0), rec.get(1), rec.get(2), rec.get(4), rec.get(5), rec.get(3), rec.get(7), Integer.parseInt(rec.get(6)));    
            movies.add(m);
        }
        return movies;
    }
    public void testLoadMovies() throws IOException
    {
        ArrayList<Movie> movies = loadMovies("data/ratedmoviesfull.csv");
        HashMap<String,Integer> dir = new HashMap<String,Integer>();
        int comedy = 0;
        int lar150 = 0;
        for(Movie m : movies)
        {   
            if (m.getGenres().toLowerCase().contains("comedy"))
                comedy++;
            if (m.getMinutes()>150)
                lar150++;
            String[] directors = m.getDirector().split(", ");
            for(String d : directors)
            {
                if (dir.containsKey(d))
                {
                    dir.put(d,dir.get(d)+1);
                }
                else
                {
                    dir.put(d,1);
                }
            }
        }
        int max = 0 ;
        for(String key : dir.keySet())
        {
            if (dir.get(key) > max)
            {
                max = dir.get(key);
            }
        }
        for(String key : dir.keySet())
        {
            if (dir.get(key) == max)
            {
                System.out.println(key + " : " + max);
            }
        }
        System.out.println("Total no of movies : " + movies.size() );
        System.out.println("Total no of Comedy movies : " + comedy );
        System.out.println("Total no of movies > 150 min : " + lar150 );
    }
    public ArrayList<Rater> loadRaters(String fname) throws IOException
    {
        ArrayList<Rater> raters = new ArrayList<Rater>();
        FileReader fr = new FileReader(fname);
        CSVParser par = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fr);
        for(CSVRecord rec : par)
        {
            int flag=0;
            for(Rater r : raters)
            {
                if(rec.get(0).equals(r.getID()))
                {
                    flag=1;
                    r.addRating(rec.get(1),Double.parseDouble(rec.get(2))); 
                }
            }
            if(flag==0)
            {
                Rater m = new EfficientRater(rec.get(0));  
                m.addRating(rec.get(1),Double.parseDouble(rec.get(2)));
                raters.add(m);
            }
        }
        return raters;
    }
    public void testLoadRaters() throws IOException
    {
        ArrayList<Rater> raters = loadRaters("data/ratings.csv");
        System.out.println("Total Raters : " + raters.size());
        ArrayList<String> movies = new ArrayList<String>();
        String Id = "193" ;
        int mx=0;
        int m=0;
        String mov="1798709";
        for(Rater r : raters)
        {
            if(r.getID().equals(Id))
                System.out.println("Id : " + r.getID() + " || No of Ratings : " + r.numRatings());
            if (r.numRatings() > mx)
                mx=r.numRatings();
            if (r.getItemsRated().contains(mov))
                m++;
            for(String movie : r.getItemsRated() )
            {
                if (!movies.contains(movie))
                    movies.add(movie);
            }     
        }
        for(Rater r : raters)
        {
            if(r.numRatings() == mx)
            System.out.println("Id : " + r.getID() + " || No of Ratings : " + r.numRatings());
        }
        System.out.println("Movie : " + mov + " has " + m + " ratings " );
        System.out.println("There are " + movies.size() + " different rated movies");
    }
}
