package ssf.tasks.task.Controllers;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import ssf.tasks.task.Models.LatestNews;
import ssf.tasks.task.Services.NewsService;

@Controller
public class NewsController {

    @Autowired
    private NewsService newsServ;

    @GetMapping("/")
    public String showArticles(Model model) {
        List<LatestNews> ln = newsServ.getArticles();
        model.addAttribute("ln", ln);

        return "start";
    }

@PostMapping("/articles")
    public String save(Model model, @RequestBody MultiValueMap<String,String> form) {
         LatestNews latest = new LatestNews();
         //boolean checked = "c1".getState();
         //if( cb=true) {
         latest.setId((form.getFirst("id")));
        latest.setBody(form.getFirst("body"));
        latest.setImageurl(form.getFirst("imageurl"));
       latest.setPublished_on(Integer.parseInt(form.getFirst("published_on")));
        latest.setTags(form.getFirst("tags"));
         latest.setTitle(form.getFirst("titles"));
        latest.setUrl(form.getFirst("url"));
        model.addAttribute("saved", latest);
        List <LatestNews>list=new LinkedList<>();
        list.add(latest);
        newsServ.saveArticles(list);
        //}
       return "redirect:/";
     }


    
}
