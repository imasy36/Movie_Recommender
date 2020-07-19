import java.io.*;
/**
 * Write a description of inputRatings here.
 * 
 * @author (imasy36) 
 * @version (a version number or a date)
 */
public class inputRatings {
    public static void main(String[] args)
    {
        try
        {
            RecommendationRunner rr = new RecommendationRunner();
            System.out.println("<tr><th>  </th><th> Movie </th><th> Rating </th></tr>");
            for(String movie : rr.getItemsToRate())
            {
                String poster = "<td>"+getPosterLink(movie)+"</td>" + "\n";
                String Movie = "<td>"+Integer.toString(MovieDatabase.getYear(movie)) + "  "+ "<a href=\"" + getImdbLink(movie) + "\">";
                Movie+=MovieDatabase.getTitle(movie)+"</a>"+"</td>"+"\n";
                String ratings = "<td>"+"\n";
                for(int i=0;i<=10;i++)
                {
                    ratings+=ratingHelp(movie,i)+"\n";
                }
                ratings+="</td>"+"\n";
                String row = "<tr>"+"\n"+poster+Movie+ratings+"</tr>"+"\n";
                System.out.println(row);
            }
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    private static String ratingHelp(String id,int count)
    {
        if (count>0)
            return "<input type=\"radio\" name=\""+id+"\" value=\""+count+"\">"+count+"</input>";
        else
            return "<input type=\"radio\" name=\""+id+"\" value=\"0\" checked>"+"No rating"+"</input>";
    }
    private static String getImdbLink(String id)
    {
        while(id.length()!=7)
        {
            id = "0" + id;
        }    
        return "https://www.imdb.com/title/tt"+id+ "/";
    }
    private static String getPosterLink(String id)throws IOException
    {
        if(MovieDatabase.getPoster(id).equals("N/A"))
            return "N/A";
        String poster = "<img src=\"https://www.dukelearntoprogram.com//capstone/data/";
        poster+=MovieDatabase.getPoster(id).substring(7)+"\" height=\"50\">";
        return poster;
    }
}