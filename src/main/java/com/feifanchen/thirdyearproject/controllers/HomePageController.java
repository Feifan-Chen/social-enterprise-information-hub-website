package com.feifanchen.thirdyearproject.controllers;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/homepage", produces = {MediaType.TEXT_HTML_VALUE})
public class HomePageController {

    @RequestMapping(method = RequestMethod.GET)
    public String welcome() {
        return "index";
    }

}
