import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class RestClient {
    public static void main(String[] args) {
        try {
            String apiUrl = "https://api.agify.io/?name=pranav";

            URL url = new URL(apiUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            conn.setRequestMethod("GET");

            BufferedReader br = new BufferedReader(
                    new InputStreamReader(conn.getInputStream()));

            String inputLine;
            StringBuilder response = new StringBuilder();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();

            String json = response.toString();

            System.out.println("Raw JSON Response:");
            System.out.println(json);

            String name = json.split("\"name\":\"")[1].split("\"")[0];
            String age = json.split("\"age\":")[1].split("[,}]")[0];
            String count = json.split("\"count\":")[1].split("[,}]")[0];

            System.out.println("\nParsed Data:");
            System.out.println("Name: " + name);
            System.out.println("Predicted Age: " + age);
            System.out.println("Count: " + count);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}