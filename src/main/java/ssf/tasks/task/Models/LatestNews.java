package ssf.tasks.task.Models;



import jakarta.json.Json;
import jakarta.json.JsonObject;

public class LatestNews {
    private String id; // using id as string because it has "" in the JSON result. Does not work if in int
    private int published_on;
    private String title;
    private String url;
    private String imageurl;
    private String body;
    private String tags;
    // private String categories;
    // need to split vertical bars!

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPublished_on() {
        return published_on;
    }

    public void setPublished_on(int published_on) {
        this.published_on = published_on;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    // public String getCategories() {
    // return categories;
    // }
    // public void setCategories(String categories) {
    // this.categories = categories;
    // }

    public static LatestNews create(JsonObject jo) {
        LatestNews la = new LatestNews();
        la.setBody(jo.getString("body"));
        // la.setCategories(jo.getString("categories"));
        la.setId(jo.getString("id"));
        la.setImageurl(jo.getString("imageurl"));
        la.setPublished_on(jo.getInt("published_on"));
        la.setTags(jo.getString("tags"));
        la.setTitle(jo.getString("title"));
        la.setUrl(jo.getString("url"));
        return la;
    }
    public JsonObject toJson(String s) {
        return Json.createObjectBuilder()
        .add("body", body)
        .add("id", id)
        .add("imageurl", imageurl)
        .add("published_on", published_on)
        .add("tags", tags)
        .add("title", title)
        .add("url", url)

        .build();
    }
    // converting to list to save in server
    // public List<LatestNews> formlist() {
    // List<LatestNews> list = new LinkedList<>();
    // return list;
    // }
}
