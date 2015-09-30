package ohtu;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import java.io.IOException;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.util.EntityUtils;

public class Main {

    public static void main(String[] args) throws IOException {
        String studentNr = "012345678";
        if ( args.length>0) {
            studentNr = args[0];
        }

        String url = "http://ohtustats-s2015.herokuapp.com/students/"+studentNr+"/submissions";

        CloseableHttpClient client = HttpClients.createDefault();
        HttpGet get = new HttpGet(url);
        CloseableHttpResponse execute = client.execute(get);
        String bodyText = EntityUtils.toString(execute.getEntity());

        System.out.println("json-muotoinen data:");
        System.out.println( bodyText );

//        GsonBuilder builder = new GsonBuilder();
//        builder.registerTypeAdapter(Submission.class, Enosaa)
//        Gson mapper = builder.create();
        // En osaa
        Gson mapper = new Gson();
        Submission[] subs = mapper.fromJson(bodyText, Submission[].class);

        System.out.println("Oliot:");
        for (Submission submission : subs) {
            System.out.println(submission);
        }

    }
}