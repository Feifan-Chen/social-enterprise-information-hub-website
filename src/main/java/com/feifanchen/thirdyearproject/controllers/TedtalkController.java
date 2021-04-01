package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.PodcastService;
import com.feifanchen.thirdyearproject.dao.TedtalkService;
import com.feifanchen.thirdyearproject.dao.TopicService;
import com.feifanchen.thirdyearproject.dao.YouTubeService;
import com.feifanchen.thirdyearproject.entities.Tedtalk;
import com.feifanchen.thirdyearproject.entities.YouTubeVideo;
import com.feifanchen.thirdyearproject.entities.YoutubeSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Controller
@RequestMapping(value = "/tedtalk",  produces = {MediaType.TEXT_HTML_VALUE})
public class TedtalkController {
    private  final TedtalkService tedtalkService;
    private final TopicService topicService;
    private final YouTubeService youTubeService;
    @Autowired
    public TedtalkController(PodcastService podcastService, YouTubeService youTubeService, TedtalkService tedtalkService, TopicService topicService, YouTubeService youTubeService1) {
        this.tedtalkService = tedtalkService;
        this.topicService = topicService;
        this.youTubeService = youTubeService1;
    }

    @RequestMapping(value = "/showSingleTed")
    public String showSingleVideo(@RequestParam("id") long id, Model model){
        Tedtalk video =  tedtalkService.findOne(id);
        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/learningresources/TedVideo";
    }

    @PostMapping(value = "/save")
    public String saveVideo(Tedtalk video, @RequestParam("add_by") String add_by, @RequestParam("id") long id){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(id);
        if(id == (long)0) {
            video.setAdd_at(d);
            video.setAdd_by(add_by);
        }
        tedtalkService.save(video);

        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/tedDemo")
    public String formSubmit(@Valid YoutubeSearchCriteria youtubeSearchCriteria, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "/admin/generator";
        }

        //get the list of YouTube videos that match the search term
        List<YouTubeVideo> videos = youTubeService.fetchVideosByQuery(youtubeSearchCriteria.getQueryTerm(), 2);

        if (videos != null && videos.size() > 0) {
            model.addAttribute("numberOfVideos", videos.size());
        } else {
            model.addAttribute("numberOfVideos", 0);
        }

        //put it in the model
        model.addAttribute("videos", videos);

        //add the criteria to the model as well
        model.addAttribute("youtubeSearchCriteria", youtubeSearchCriteria);

        //get out
        return "/admin/showTEDresults";
    }

    @PostMapping(value = "/showSingle")
    public String showSingle(@RequestParam("title") String title, @RequestParam("thumbnail") String thumbnail,
                             @RequestParam("description") String description, @RequestParam("url") String url, @RequestParam("time") Date time, Model model){
        if(title  == null){
            return "/admin/youtubeDemo";
        }

        Tedtalk video = new Tedtalk();
        video.setTitle(title);
        video.setThumbnail(thumbnail);
        video.setDescription(description);
        String[] strarray=url.split("=");
        video.setUrl(strarray[1]);
        video.setTime(time);

        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());
        return "/learningresources/TedVideo";
    }

}
