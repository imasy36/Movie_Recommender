import java.util.*;
import java.io.*;
/**
 * Write a description of RecommendationRunner here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RecommendationRunner implements Recommender {
    private Random myRandom;
    public RecommendationRunner()
    {
        myRandom=new Random();
    }
    @Override
    public ArrayList<String> getItemsToRate()
    {
        try
        {
            ArrayList<String> list = new ArrayList<String>();
            FourthRatings fr = new FourthRatings();
            String Genres = "Biography,Drama,Thriller,Animation,Adventure,Comedy,Horror,Mystery,Sci-Fi,History,Music,Sport";
            for(String genre: Genres.split(","))
            {
                ArrayList<String> movies = MovieDatabase.filterBy(new GenreFilter(genre));
                int temp;
                do
                {
                    temp = myRandom.nextInt(movies.size()-4);
                    
                }while(fr.getAverageById(movies.get(temp),20)>0.0);
                list.add(movies.get(temp));
            }
            return list;
        }
        catch(IOException e)
        {
            System.out.println(e);
            return null;
        }
    }
    @Override
    public void printRecommendationsFor (String webRaterID)
    {
        try
        {
            FourthRatings fr = new FourthRatings();
            ArrayList<Rating> recMovies = fr.getSimilarRatingsByFilter(webRaterID,30,3,new TrueFilter());
            int i =0 ;
            ArrayList<String> ans = new ArrayList<String>();
            Rater rater = RaterDatabase.getRater(webRaterID);
            if (recMovies.size()==0)
            {
                System.out.println("<p>Sorry we cannot find any recommendation for you</p>");
                System.exit(1);
            }
            for(Rating mov : recMovies)
            {
                if(!rater.hasRating(mov.getItem()))
                {
                    ans.add(mov.getItem());
                    i++;
                }
                if (i ==20 )
                    break;
            }
            System.out.println("<style>body{background-color: #dadce3;}table{background-color: #feffd4;}</style>");
            System.out.println("<center><h1> Following Movies are recommended for you </h1>");
            System.out.println("<table>");
            System.out.println("<tr><th>Rank</th><th>Movie Title</th><th>Poster</th></tr>");
            for(int j=0;j<ans.size();j++)
            {
                String poster = "N/A";
                if(!MovieDatabase.getPoster(ans.get(j)).equals("N/A"))
                    poster = "<img src=\"https://www.dukelearntoprogram.com//capstone/data/"+MovieDatabase.getPoster(ans.get(j)).substring(7)+"\" height=\"50\">";
                System.out.println("<tr><td>"+(j+1)+"</td><td>"+MovieDatabase.getTitle(ans.get(j))+"</td><td>"+poster+"</td></tr>");
            }
            System.out.println("</table></center>");
            System.out.println("<footer><p>By : imasy36  [ imasy36@gmail.com ] </p></footer> ");
        }
        catch(IOException e)
        {
            System.out.println(e);
            System.exit(1);
        }
    }
}
