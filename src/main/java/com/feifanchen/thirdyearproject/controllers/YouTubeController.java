package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.YouTubeService;
import com.feifanchen.thirdyearproject.entities.YouTubeVideo;
import com.feifanchen.thirdyearproject.entities.YoutubeSearchCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.util.List;

@Controller
@RequestMapping(value = "/test", produces = {MediaType.TEXT_HTML_VALUE})
public class YouTubeController {

    private final YouTubeService youTubeService;
    @Autowired
    public YouTubeController(YouTubeService youTubeService) {
        this.youTubeService = youTubeService;
    }


    @GetMapping(value = "/youtubeDemo")
    public String youtubeDemo(Model model) {
        //instantiate an empty address object
        YoutubeSearchCriteria youtubeSearchCriteria = new YoutubeSearchCriteria();

        //put the object in the model
        model.addAttribute("youtubeSearchCriteria", youtubeSearchCriteria);

        //get out
        System.out.println("hereh");
        return "youtubeDemo";
    }

    @PostMapping(value = "/youtubeDemo")
    public String formSubmit(@Valid YoutubeSearchCriteria youtubeSearchCriteria, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            System.out.println("pass here");
            return "youtubeDemo";
        }

        //get the list of YouTube videos that match the search term
        List<YouTubeVideo> videos = youTubeService.fetchVideosByQuery(youtubeSearchCriteria.getQueryTerm());

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
        return "showYoutubeResults";
    }

    @PostMapping(value = "/showSingle")
    public String showSingle(@RequestParam("title") String title, @RequestParam("thumbnail") String thumbnail,
                             @RequestParam("description") String description, @RequestParam("url") String url, @RequestParam("time") Date time, Model model){
        if(title  == null){
            return "youtubeDemo";
        }

        YouTubeVideo video = new YouTubeVideo();
        video.setTitle(title);
        video.setThumbnail(thumbnail);
        video.setDescription(description);
        video.setUrl(url);
        video.setTime(time);
        model.addAttribute("video", video);
        return "/learningresources/youtubevideo";
    }
}
