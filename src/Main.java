
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, ParseException {
         System.out.println("Hello world!");
        System.out.println("This is an example!");

        // Skapa ett JSON Object
        JSONObject jsonOb = new JSONObject();

        // Spara värden i JSON Object
        jsonOb.put("namn", "Marcus");
        jsonOb.put("age", 34);

        // Skriva ut värden
        System.out.println("Mitt namn är: " + jsonOb.get("namn"));
        System.out.println("Jag är " + jsonOb.get("age") + " år gammal.");

        // Övning
        Object o = new JSONParser().parse(new FileReader("data.json"));
        JSONObject jsonData = (JSONObject) o;

        JSONObject person = (JSONObject) jsonData.get("p1");
        String name= (String) person.get("name");
        System.out.println("P1 Name: " + name);

        String favoriteColor= (String) person.get("favoriteColor");
        System.out.println("P1 Favorite color: " + favoriteColor);
        fetchJsonFromAPI();
    }


    // Övning API
    static void fetchJsonFromAPI() throws IOException, ParseException {
        URL url = new URL ("https://api.wheretheiss.at/v1/satellites/25544");
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.connect();
        if (conn.getResponseCode() == 200) System.out.println("Koppling lyckades");
        else System.out.println("Koppling misslyckades");

        // Skapa StrBuilder och Scan object
        StringBuilder strData = new StringBuilder();
        Scanner scanner = new Scanner(url.openStream());

        // Bygger upp str med ISS data
        while (scanner.hasNext()) {
            strData.append(scanner.nextLine());
        }
        scanner.close();

        // Skapar JSONObject av fetched data
        JSONObject dataObject = (JSONObject) new
                JSONParser().parse(String.valueOf(strData));

        System.out.println(dataObject.get("velocity"));

        String name= (String) dataObject.get("name");
        String visibility= (String) dataObject.get("visibility");

        System.out.println("Station Name: " + name);
        System.out.println("Visibility is: " + visibility);
    }
}