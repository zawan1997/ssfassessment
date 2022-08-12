package ssf.tasks.task.Services;

import java.io.Reader;
import java.io.StringReader;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import ssf.tasks.task.Models.LatestNews;
import ssf.tasks.task.Repositories.NewsRepo;

@Service
public class NewsService {

    private static final String URL = "https://min-api.cryptocompare.com/data/v2/news/?lang=EN";

    @Value("${API_KEY}")
    private String key;

    @Autowired
    private NewsRepo newsrepo;

    String payload;

    List<LatestNews> inputs = new LinkedList<>();

    public List<LatestNews> getArticles() {

        String url = UriComponentsBuilder.fromUriString(URL).toUriString();

        RequestEntity<Void> req = RequestEntity.get(url).build();
        RestTemplate template = new RestTemplate();
        ResponseEntity<String> resp = template.exchange(req, String.class);

        if (resp.getStatusCodeValue() != 200) {
            System.err.println("Error code not 200");
        }

        payload = resp.getBody();
        System.out.println("payload" + payload);

        Reader strReader = new StringReader(payload);
        JsonReader jsonReader = Json.createReader(strReader);
        JsonObject results = jsonReader.readObject();
        JsonArray end = results.getJsonArray("Data");
        //customising to JSON Object with contents we want
        List<LatestNews> list = new LinkedList<>();
        for (int i = 0; i < end.size(); i++) {
            JsonObject jo = end.getJsonObject(i);
            list.add(LatestNews.create(jo));
        }
        return list;
    }

    //Attempting to save key value pair as ID, ARTICLE contents
    //Pass in a list and the key gets selected from the list and then saved together
    public void saveArticles(List<LatestNews> list) {
        list.get(0);
        newsrepo.save(list.get(0).toString(),list.toString());
       
         System.out.printf("key in String",list.get(0));
    }

    //Comparing if the id passed in == the id thats 
    //in the key, retrieve it 
    public void retrievebyID(String id) {
        
        if (inputs.contains(id)) {
            while (id.equals(id)) {
                newsrepo.get(inputs.toString());
            }
        }
    }

    //to check if there is the id in redis 
    public boolean searchValidity(String id){
        boolean isvalid= newsrepo.isValidArticle(id, inputs.toString());
        System.out.println("Valid Article?" + isvalid);
        return isvalid;
    }


}
