
/**
 * Write a description of printRecommendations here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class printRecommendations {
    public static void main(String args[])
    {
        RecommendationRunner rr = new RecommendationRunner();
        String id = args[0];
        rr.printRecommendationsFor(id);
    }
}
