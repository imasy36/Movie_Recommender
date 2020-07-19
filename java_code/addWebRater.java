import java.io.*;
import org.apache.commons.csv.*;
/**
 * Write a description of addWebRater here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class addWebRater {
    public static void main(String args[])
    {
        try
        {
            BufferedWriter bw = new BufferedWriter(new FileWriter("data/ratings.csv",true));
            FileReader fr = new FileReader("data/webRater.csv");
            int no = Integer.parseInt(getNewId("ratings.csv"))+1;
            String id = Integer.toString(no);
            CSVParser par = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(fr);
            for (CSVRecord rec : par)
            {
                String data = id+","+rec.get(0)+","+rec.get(1)+",1.23E+04\n";
                bw.write(data);
            }
            bw.close();
            fr.close();        
            System.out.println(id);
        }
        catch(IOException e)
        {
            System.out.println(e);
        }
    }
    private static String getNewId(String fname)throws IOException
        {   
            String res="";
            FileReader f = new FileReader("data/"+fname);
            CSVParser par = CSVFormat.EXCEL.withFirstRecordAsHeader().parse(f);
            for (CSVRecord rec : par)
            {
                res=rec.get(0);
            }
            return res;
        }
}
