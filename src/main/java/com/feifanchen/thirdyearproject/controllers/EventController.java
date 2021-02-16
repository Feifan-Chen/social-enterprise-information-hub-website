package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.awt.*;

@Controller
@RequestMapping(value = "/events", produces = {MediaType.TEXT_HTML_VALUE})
public class EventController {
    private final EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("events", eventService.findAll());
        return "events/index";
    }
}
