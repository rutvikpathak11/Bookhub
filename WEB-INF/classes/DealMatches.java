import java.util.ArrayList;
import java.util.Random;
import java.io.*;

public class DealMatches {

    private static boolean gotDeals = false;
    private static String tweetOne = null;
    private static String idOne = null;
    private static String tweetTwo = null;
    private static String idTwo = null;

    public static void getDeals(String fileName) {
        if (gotDeals)
            return;
        gotDeals = true;

        try {
            File file = new File(fileName);
            if(!file.exists())
                return;

            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            String tweetLine = "";

            ArrayList<String> tweets = new ArrayList<String>();
            ArrayList<String> ids = new ArrayList<String>();

            while ((line = bufferedReader.readLine()) != null) {
                if (!line.startsWith("prod name")) {
                    tweetLine += line+"<br>";
                    continue;
                }

                tweets.add(tweetLine);
                ids.add(line.replace("prod name: ", ""));
                tweetLine = "";
            }
            fileReader.close();

            if(tweets.size() == 0)
                return;

            Random randomizer = new Random();
            int index = randomizer.nextInt(tweets.size());
            tweetOne = tweets.remove(index);
            idOne = ids.remove(index);

            if(tweets.size() == 0)
                return;

            index = randomizer.nextInt(tweets.size());
            tweetTwo = tweets.remove(index);
            idTwo = ids.remove(index);
        } catch (IOException e) {
        }
    }

    public static String getTweetOne() {
        return tweetOne;
    }

    public static String getIdOne() {
        return idOne;
    }

    public static String getTweetTwo() {
        return tweetTwo;
    }

    public static String getIdTwo() {
        return idTwo;
    }
}

