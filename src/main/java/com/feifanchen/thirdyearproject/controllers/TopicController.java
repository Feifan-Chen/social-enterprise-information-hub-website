package com.feifanchen.thirdyearproject.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.feifanchen.thirdyearproject.dao.TopicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

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

}
