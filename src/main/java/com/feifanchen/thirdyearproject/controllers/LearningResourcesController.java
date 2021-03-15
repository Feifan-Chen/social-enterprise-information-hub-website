package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.PodcastService;
import com.feifanchen.thirdyearproject.dao.TedtalkService;
import com.feifanchen.thirdyearproject.dao.YouTubeService;
import com.feifanchen.thirdyearproject.entities.Podcast;
import com.feifanchen.thirdyearproject.entities.YouTube;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;

@Controller
@RequestMapping(value = "/learningresources",  produces = {MediaType.TEXT_HTML_VALUE})
public class LearningResourcesController {
    private  final PodcastService podcastService;
    private final YouTubeService youTubeService;
    private  final TedtalkService tedtalkService;
    @Autowired
    public LearningResourcesController(PodcastService podcastService, YouTubeService youTubeService, TedtalkService tedtalkService) {
        this.podcastService = podcastService;
        this.youTubeService = youTubeService;
        this.tedtalkService = tedtalkService;
    }

    @GetMapping
    public String mainpage(Model model){
        return "/learningresources/index";
    }

    @GetMapping(value = "/youtube/{type}")
    public String findAllYoutube (@PathVariable int type, Model model){
        Iterable<YouTube> res = new ArrayList<YouTube>();
        if(type == 0)
            res = youTubeService.findAll();
        else if(type == 1)//lastest
            res = youTubeService.findAllLatest();
        else if(type == 2)//topics
            res = youTubeService.findAllByTopics();

        model.addAttribute("youTubes", res);
        return "/learningresources/youtube-list";
    }

    @GetMapping(value = "/podcast/{type}")
    public String findAllPodcast (@PathVariable int type, Model model){
        Iterable<Podcast> res = new ArrayList<Podcast>();
        if(type == 0)
            res = podcastService.findAll();
        else if(type == 1)//lastest
            res = podcastService.findAllLatest();
        else if(type == 2)//topics
            res = podcastService.findAllByTopics();

        model.addAttribute("podcasts", res);
        return "/learningresources/podcast-list";
    }
}
