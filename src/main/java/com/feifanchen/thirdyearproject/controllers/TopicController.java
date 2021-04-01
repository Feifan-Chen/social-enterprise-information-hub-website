package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.entities.ForumPost;
import com.feifanchen.thirdyearproject.entities.Topic;
import org.hibernate.validator.constraints.pl.REGON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.feifanchen.thirdyearproject.dao.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/topic",  produces = {MediaType.TEXT_HTML_VALUE})
public class TopicController {
    private final static Logger log = LoggerFactory.getLogger(TopicController.class);

    private final TopicService topicService;
    @Autowired
    public TopicController(TopicService topicService) {
        this.topicService = topicService;
    }

    @GetMapping
    public String findAll (Model model){
        model.addAttribute("topics", topicService.findAll());
        return "topic/index";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showTopic(@PathVariable("id") long id, Model model){
        Topic topic = topicService.findById(id);
        model.addAttribute("topic", topic);
        model.addAttribute("events", topic.getEvent());
        model.addAttribute("podcasts", topic.getPodcasts());
        model.addAttribute("youtubes", topic.getYoutube());
        model.addAttribute("posts", topic.getPosts());
        return "topic/showSingle";
    }

    @RequestMapping(value = "/showSingle", method = {RequestMethod.GET})
    public String showSingleForum(@RequestParam("id") long id, Model model){
        Topic topic =  topicService.findById(id);
        model.addAttribute("topic", topic);
        //System.out.println(topicList.toString());
        return "/topic/showTopic";
    }

    @RequestMapping(value = "/new")
    public String newTopic(Model model){
        Topic topic = new Topic();
        model.addAttribute("topic", topic);
        return "/topic/showTopic";
    }

    @PostMapping(value = "/save")
    public String save(Topic topic, @RequestParam("add_by") String add_by, @RequestParam("id") long id){
        topicService.save(topic);

        return "redirect:/user/admin";
    }

    @RequestMapping(path = "/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deleteComment(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {
        topicService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

}
