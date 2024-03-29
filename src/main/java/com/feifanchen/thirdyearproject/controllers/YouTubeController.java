package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.TopicService;
import com.feifanchen.thirdyearproject.dao.YouTubeService;
import com.feifanchen.thirdyearproject.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
@RequestMapping(value = "/youtubeadmin", produces = {MediaType.TEXT_HTML_VALUE})
public class YouTubeController {

    private final YouTubeService youTubeService;
    private final TopicService topicService;
    @Autowired
    public YouTubeController(YouTubeService youTubeService, TopicService topicService) {
        this.youTubeService = youTubeService;
        this.topicService = topicService;
    }


    @GetMapping(value = "/youtubeDemo")
    public String youtubeDemo(Model model) {
        //instantiate an empty address object
        YoutubeSearchCriteria youtubeSearchCriteria = new YoutubeSearchCriteria();
        YoutubeSearchCriteria search = new YoutubeSearchCriteria();

        //put the object in the model
        model.addAttribute("search", search);

        //put the object in the model
        model.addAttribute("youtubeSearchCriteria", youtubeSearchCriteria);

        //get out
        System.out.println("hereh");
        return "/admin/generator";
    }

    @PostMapping(value = "/youtubeDemo")
    public String formSubmit(@Valid YoutubeSearchCriteria youtubeSearchCriteria, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            System.out.println("pass here");
            return "/admin/youtubeDemo";
        }

        //get the list of YouTube videos that match the search term
        List<YouTubeVideo> videos = youTubeService.fetchVideosByQuery(youtubeSearchCriteria.getQueryTerm(), 0);

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
        return "/admin/showYoutubeResults";
    }


    @RequestMapping(value = "/youtubeDemoByURL")
    public String formSubmitbyURL(@Valid YoutubeSearchCriteria youtubeSearchCriteria, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            return "/admin/generator";
        }

        String[] strarray=youtubeSearchCriteria.getQueryTerm().split("=");
        //get the list of YouTube videos that match the id
        List<YouTubeVideo> videos = youTubeService.fetchVideosByQuery(strarray[1], 0);

        if (videos != null && videos.size() > 0) {
            YouTubeVideo video = videos.get(0);
            model.addAttribute("video", video);
        } else {
            return "redirect:/user/admin";
        }

        model.addAttribute("topicModel", topicService.findAll());
        return "/learningresources/youtubevideo";
    }

    @RequestMapping(value = "/youtubeDemoByChannel")
    public String formSubmitbyChannel(Model model) {
        //get the list of YouTube videos that match the id
        List<YouTubeVideo> videos = youTubeService.fetchVideosByQuery("null", 1);
        YoutubeSearchCriteria youtubeSearchCriteria = new YoutubeSearchCriteria();
        youtubeSearchCriteria.setQueryTerm("Flourish Together");

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
        return "/admin/showYoutubeResults";
    }

    @PostMapping(value = "/showSingle")
    public String showSingle(@RequestParam("title") String title, @RequestParam("thumbnail") String thumbnail,
                             @RequestParam("description") String description, @RequestParam("url") String url, @RequestParam("time") Date time, Model model){
        if(title  == null){
            return "/admin/youtubeDemo";
        }

        YouTubeVideo video = new YouTubeVideo();
        video.setTitle(title);
        video.setThumbnail(thumbnail);
        video.setDescription(description);
        String[] strarray=url.split("=");
        video.setUrl(strarray[1]);
        video.setTime(time);

        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());
        return "/learningresources/youtubevideo";
    }

    @RequestMapping(value = "/showSingleVideo")
    public String showSingleVideo(@RequestParam("id") long id, Model model){
        YouTubeVideo video =  youTubeService.findOne(id);
//        Topic test = topicService.findById(2);
//        System.out.println(topicService.findAll().);
        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/learningresources/youtubevideo";
    }

    @RequestMapping(value = "/save", method={RequestMethod.POST})
    public String saveVideo(YouTubeVideo video, @RequestParam("add_by") String add_by, @RequestParam("id") long id){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(id);
        if(id == (long)0) {
            video.setAdd_at(d);
            video.setAdd_by(add_by);
        }
        youTubeService.save(video);

        return "redirect:/user/admin";
    }
}
