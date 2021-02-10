package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.YouTubeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/learningresources", produces = {MediaType.TEXT_HTML_VALUE})
public class YouTubeController {
//    private final YouTubeService youTubeService;
//    @Autowired
//    public YouTubeController(YouTubeService youTubeService) {
//        this.youTubeService = youTubeService;
//    }
//
//    @GetMapping
//    public String findAll(Model model){
//        model.addAttribute("youTubes", youTubeService.findAll());
//        return "learningresources/youtubeVideo";
//    }
}
