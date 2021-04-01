package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.EventService;
import com.feifanchen.thirdyearproject.dao.TopicService;
import com.feifanchen.thirdyearproject.entities.Event;
import com.feifanchen.thirdyearproject.entities.EventSearchCriteria;
import com.feifanchen.thirdyearproject.entities.Podcast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.thymeleaf.util.StringUtils;

import javax.validation.Valid;
import java.sql.Timestamp;

@Controller
@RequestMapping(value = "/events", produces = {MediaType.TEXT_HTML_VALUE})
public class EventController {
    private final EventService eventService;
    private final TopicService topicService;

    @Autowired
    public EventController(EventService eventService, TopicService topicService) {
        this.eventService = eventService;
        this.topicService = topicService;
    }

    @GetMapping
    public String findAll(Model model){
        model.addAttribute("events", eventService.findList());
        return "/events/index";
    }

    @GetMapping(value = "/eventSearch")
    public String youtubeDemo(Model model) {
        //instantiate an empty address object
        EventSearchCriteria search = new EventSearchCriteria();

        //put the object in the model
        model.addAttribute("search", search);

        return "/admin/generator";
    }

    @PostMapping(value = "/eventSearch")
    public String urlSubmit(@Valid EventSearchCriteria search,  Model model){
        String[] array1 = search.getUrl().split("\\?");
        System.out.println(array1[0]);
        String s = StringUtils.substring(array1[0], array1[0].length()-12,array1[0].length());

        System.out.println(s);
        Event event = new Event();
        model.addAttribute("url", s);
        model.addAttribute("event", event);
        model.addAttribute("topicModel", topicService.findAll());

        return "events/singlevent";
    }

    @RequestMapping(path = "/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deleteEvent(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {
        eventService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/showSingle", method = {RequestMethod.GET})
    public String showSingleEvent(@RequestParam("id") long id, Model model){
        Event event =  eventService.findById(id).orElse(null);
        model.addAttribute("event", event);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/events/showEvent";
    }

    @RequestMapping(value = "/new", method = {RequestMethod.GET})
    public String newEvent(Model model){
        Event event =  new Event();
        model.addAttribute("event", event);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/events/newEvent";
    }

    @PostMapping(value = "/save")
    public String saveEvent(Event event, @RequestParam("id") long id){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        System.out.println(id);
        if(id == (long)0) {
            event.setTime(d);
        }
        eventService.save(event);

        return "redirect:/user/admin";
    }


    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String yourUrl(@RequestParam("name") String name, @RequestParam("information") String information, Model model) {
        System.out.println("hello");
        System.out.println("name="+name+";information="+information);
        model.addAttribute("events", eventService.findList());
        return "/events/index";
    }

}
