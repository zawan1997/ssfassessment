package ssf.tasks.task.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.json.Json;
import jakarta.json.JsonObject;

import ssf.tasks.task.Services.NewsService;

@RestController
@RequestMapping(path = "/news")
public class RestNewsController {

    @Autowired
    NewsService nSv;

    @GetMapping(path = "{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getArticle(
            @PathVariable("id") String id) {
        if (nSv.searchValidity(id) != true) {
            JsonObject errResp = Json.createObjectBuilder()
                    .add("error", "cannot find news article ${id}")
                    .build();
            String payload = errResp.toString();
            // return 400
            return ResponseEntity
                    // .status(HttpStatus.BAD_REQUEST)
                    .badRequest() // 400
                    .body(payload);

        }
        nSv.retrievebyID(id);
        // after retrieving data, creating Json object and flooding
        JsonObject obj = Json.createObjectBuilder()
                .add("id", id)
                .build();
        return ResponseEntity.ok(obj.toString());
    }

}
