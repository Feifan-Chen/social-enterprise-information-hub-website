package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.*;
import com.feifanchen.thirdyearproject.entities.Roles;
import com.feifanchen.thirdyearproject.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Set;

@Controller
@RequestMapping(value = "/user", produces = { MediaType.TEXT_HTML_VALUE})
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final RolesRepository rolesRepository;
    private final EventService eventService;
    private final YouTubeService youTubeService;
    private final PodcastService podcastService;
    private final TedtalkService tedtalkService;
    private final ForumPostService forumPostService;
    private final ForumCommentService forumCommentService;
    private final UserService userService;
    private final TopicService topicService;
    @Autowired
    public UserController(RolesRepository rolesRepository, EventService eventService, YouTubeService youTubeService, PodcastService podcastService, TedtalkService tedtalkService, ForumPostService forumPostService, ForumCommentService forumCommentService, UserService userService, TopicService topicService) {
        this.rolesRepository = rolesRepository;
        this.eventService = eventService;
        this.youTubeService = youTubeService;
        this.podcastService = podcastService;
        this.tedtalkService = tedtalkService;
        this.forumPostService = forumPostService;
        this.forumCommentService = forumCommentService;
        this.userService = userService;
        this.topicService = topicService;
    }

    @RequestMapping(value = "/admin", method = RequestMethod.GET)
    public String showAdmin(Model model){
        model.addAttribute("events", eventService.findAll());
        model.addAttribute("podcast", podcastService.findAll());
        model.addAttribute("youtube", youTubeService.findAll());
        model.addAttribute("teds", tedtalkService.findAll());
        model.addAttribute("forum", forumPostService.findAll());
        model.addAttribute("reportpost", forumPostService.findAllReported());
        model.addAttribute("reportcomment", forumCommentService.findAllReported());
        model.addAttribute("topics", topicService.findAll());


        return "/admin/index";
    }




    @GetMapping
    public String findAll (Model model){
        model.addAttribute("users", userService.findAll());
        return "/user/index";
    }

    @GetMapping("/login")
    public String showLoginForm(Model model){
        model.addAttribute("user", new User());

        return "/user/login";
    }
    @GetMapping("/register")
    public String showSignForm(Model model){
        model.addAttribute("user", new User());

        return "/user/sign";
    }

    @PostMapping("/process_reges")
    public String saveNewUser(User user,  RedirectAttributes redirectAttributes){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encodedpw = encoder.encode(user.getPassword());
        user.setPassword(encodedpw);

        Roles changemaker = rolesRepository.findNormal();
        Set<Roles> roles = new HashSet<Roles>(1);
        roles.add(changemaker);
        user.setRoles(roles);
        userService.save(user);
        changemaker.getUsers().add(user);

        redirectAttributes.addFlashAttribute("ok_message", "Successful!");
        return "redirect:/user/login";
    }
}
