package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.TopicService;
import com.feifanchen.thirdyearproject.dao.YouTubeService;
import com.feifanchen.thirdyearproject.entities.Topic;
import com.feifanchen.thirdyearproject.entities.TopicModel;
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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

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

        //put the object in the model
        model.addAttribute("youtubeSearchCriteria", youtubeSearchCriteria);

        //get out
        System.out.println("hereh");
        return "/admin/youtubeDemo";
    }

    @PostMapping(value = "/youtubeDemo")
    public String formSubmit(@Valid YoutubeSearchCriteria youtubeSearchCriteria, BindingResult bindingResult, Model model) {
        //check for errors
        if (bindingResult.hasErrors()) {
            System.out.println("pass here");
            return "/admin/youtubeDemo";
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

        List<TopicModel> topicModel = new ArrayList<>();

        for(Topic t : topicService.findAll()){
            topicModel.add(new TopicModel(t, false));
        }
        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicModel);
        model.addAttribute("id", 0);
        return "/learningresources/youtubevideo";
    }

    @RequestMapping(value = "/showSingleVideo")
    public String showSingleVideo(@RequestParam("id") long id, Model model){
        YouTubeVideo video =  youTubeService.findOne(id);

        List<TopicModel> topicModel = new ArrayList<>();
        for(Topic t : topicService.findAll()){
            topicModel.add(new TopicModel(t, false));
        }

        for(TopicModel t : topicModel){
            for(Topic v: video.getTopic()){
                if(v.getId() == t.getTopic().getId())
                    t.setIschecked(true);
            }
        }

        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicModel);
        model.addAttribute("id", video.getId());
        //System.out.println(topicList.toString());
        return "/learningresources/youtubevideo";
    }

    @PostMapping(value = "/save")
    public String saveVideo(YouTubeVideo video, @RequestParam("add_by") String add_by, @RequestParam("id") long id){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        if(id != 0)
            video.setId(id);

        video.setAdd_at(d);
        video.setAdd_by(add_by);
        List<Topic> topics = new ArrayList<Topic>();
        video.setTopic(topics);
        youTubeService.save(video);

        System.out.println(add_by);

        return "/learningresources/index";
    }
}
