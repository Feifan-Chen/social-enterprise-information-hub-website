package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(value = "/user", produces = { MediaType.TEXT_HTML_VALUE})
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String findAll (Model model){
        model.addAttribute("users", userService.findAll());
        return "user/index";
    }

}
