import java.util.*;
import java.io.*;
/**
 * Write a description of FourthRatings here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class FourthRatings {
    FourthRatings()
    {
        this("ratings.csv");
    }
    FourthRatings(String file)
    {
        RaterDatabase.initialize(file);
    }
    public double getAverageById(String id, int minRaters)
    {
        double avg=0.0;
        double sum=0.0;
        int total_raters=0;
        for(Rater r : RaterDatabase.getRaters())
        {
            if(r.getItemsRated().contains(id))
            {
                sum=sum+r.getRating(id);
                total_raters++;;
            }
        }
        if (minRaters <= total_raters)
            avg=sum/total_raters;    
        return avg;
    }
    public ArrayList<Rating> getAverage(int minRaters) throws IOException
    {
        ArrayList<Rating> ratings = new ArrayList<Rating>();
        ArrayList<String> myMovies = MovieDatabase.filterBy(new TrueFilter());
        for(String id : myMovies)
        {
            if(getAverageById(id,minRaters)>0)
            ratings.add(new Rating(id,getAverageById(id,minRaters)));
        }
        return ratings;
    }
    public ArrayList<Rating> getAverageByFilter(int minRaters,Filter filterCriteria) throws IOException
    {
        ArrayList<String> mov = MovieDatabase.filterBy(filterCriteria);
        ArrayList<Rating> ans = new ArrayList<Rating>();
        for(String id: mov)
        {
            if(getAverageById(id,minRaters)>0)
                ans.add(new Rating(id,getAverageById(id,minRaters)));
        }
        return ans;
    }
    private double dotProduct(Rater me, Rater r)
    {
        double dpro = 0 ;
        for(String movie : me.getItemsRated())
        {
            if(r.hasRating(movie))
            {
                dpro = dpro + (me.getRating(movie)-5) * (r.getRating(movie)-5) ;
            }
        }
        return dpro;
    }
    private ArrayList<Rating> getSimilarities(String id)
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        Rater me = RaterDatabase.getRater(id);
        for(Rater r : RaterDatabase.getRaters())
        {
            if(!r.getID().equals(id))
            {
               double product = dotProduct(me,r);
                list.add(new Rating(r.getID(),product)); 
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
    public ArrayList<Rating> getSimilarRatingsByFilter(String id,int numSimilarRaters, int minRaters,Filter f) throws IOException
    {
        ArrayList<Rating> list = new ArrayList<Rating>();
        ArrayList<Rating> ratersClose = new ArrayList<Rating>(getSimilarities(id).subList(0,numSimilarRaters));
        for(String movie : MovieDatabase.filterBy(f))
        {
            int totalRaters = 0;
            double wAvg=0;
            for(Rating rater : ratersClose )
            {
                if(RaterDatabase.getRater(rater.getItem()).hasRating(movie))
                {
                    totalRaters++ ;
                    wAvg = wAvg + rater.getValue()*RaterDatabase.getRater(rater.getItem()).getRating(movie);
                }
            }
            if(totalRaters >= minRaters)
            {
                wAvg = wAvg/totalRaters;
                list.add(new Rating(movie,wAvg));
            }
        }
        Collections.sort(list,Collections.reverseOrder());
        return list;
    }
}
