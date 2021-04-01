package com.feifanchen.thirdyearproject.controllers;

import com.fasterxml.jackson.databind.node.POJONode;
import com.feifanchen.thirdyearproject.dao.PodcastService;
import com.feifanchen.thirdyearproject.dao.TedtalkService;
import com.feifanchen.thirdyearproject.dao.TopicService;
import com.feifanchen.thirdyearproject.dao.YouTubeService;
import com.feifanchen.thirdyearproject.entities.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.ArrayList;

@Controller
@RequestMapping(value = "/learningresources",  produces = {MediaType.TEXT_HTML_VALUE})
public class LearningResourcesController {
    private  final PodcastService podcastService;
    private final YouTubeService youTubeService;
    private  final TedtalkService tedtalkService;
    private final TopicService topicService;
    @Autowired
    public LearningResourcesController(PodcastService podcastService, YouTubeService youTubeService, TedtalkService tedtalkService, TopicService topicService) {
        this.podcastService = podcastService;
        this.youTubeService = youTubeService;
        this.tedtalkService = tedtalkService;
        this.topicService = topicService;
    }

    @GetMapping
    public String mainpage(Model model){
        return "/learningresources/index";
    }

    @GetMapping(value = "/youtube/{type}")
    public String findAllYoutube (@PathVariable int type, Model model){
        Iterable<YouTubeVideo> res = new ArrayList<YouTubeVideo>();
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

    @GetMapping(value = "/ted/{type}")
    public String findAllTed (@PathVariable int type, Model model){
        Iterable<Tedtalk> res = new ArrayList<>();
        if(type == 0)
            res = tedtalkService.findAll();
        else if(type == 1)//lastest
            res = tedtalkService.findAllLatest();
//        else if(type == 2)//topics
//            res = tedtalkService.findAllByTopics();

        model.addAttribute("ted", res);
        return "/learningresources/ted-list";
    }

    @RequestMapping(path = "/podcast/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deletePodcast(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {

        podcastService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

    @RequestMapping(path = "/youtube/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deleteYoutube(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {

        youTubeService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

    @RequestMapping(path = "/ted/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deleteTED(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {

        tedtalkService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/showSinglePodcast")
    public String showSingleVideo(@RequestParam("id") long id, Model model){
        Podcast video =  podcastService.findOne(id);
        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/learningresources/PodcastVideo";
    }

    @RequestMapping(value = "/newPodcast")
    public String newPodcast( Model model){
        Podcast video =  new Podcast();
        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/learningresources/PodcastVideo";
    }

    @PostMapping(value = "/save")
    public String saveVideo(Podcast video, @RequestParam("add_by") String add_by, @RequestParam("id") long id){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(id);
        if(id == (long)0) {
            video.setAdd_at(d);
            video.setAdd_by(add_by);
        }
        podcastService.save(video);

        return "redirect:/user/admin";
    }

    @PostMapping(value = "/search")
    public String urlPodcastSubmit(@Valid YoutubeSearchCriteria search, Model model){
        String[] array1 = search.getQueryTerm().split("posts/");
        System.out.println(array1[1]);
        String s = StringUtils.substring(array1[1], 0,7);

        System.out.println(s);
        Podcast video = new Podcast();
        model.addAttribute("orign", search.getQueryTerm());
        model.addAttribute("url", s);
        model.addAttribute("video", video);
        model.addAttribute("topicModel", topicService.findAll());

        return "/learningresources/NewPodcast";
    }
}
