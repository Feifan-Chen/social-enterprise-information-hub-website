package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.ForumCommentService;
import com.feifanchen.thirdyearproject.dao.ForumPostService;
import com.feifanchen.thirdyearproject.entities.ForumComment;
import com.feifanchen.thirdyearproject.entities.ForumPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/forum",  produces = {MediaType.TEXT_HTML_VALUE})
public class ForumController {
    public final ForumPostService forumPostService;
    public final ForumCommentService forumCommentService;
    @Autowired
    public ForumController(ForumPostService forumPostService, ForumCommentService forumCommentService) {
        this.forumPostService = forumPostService;
        this.forumCommentService = forumCommentService;
    }

    @GetMapping
    public String findAll (Model model){
        model.addAttribute("forumposts", forumPostService.findAll());
        //model.addAttribute("forumcomments", forumPostService.findAll());
        return "/forum/index";
    }

    @GetMapping(value = "/{id}")
    public String showSinglePost(@PathVariable("id") long id, Model model){
        model.addAttribute("post", forumPostService.findOne(id));
        model.addAttribute("comments", forumPostService.findOne(id).getComments());
        return"/forum/singlepost";
    }

    @RequestMapping(value = "/{id}/post", method = RequestMethod.POST)
    public String reportPost(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes){
        ForumPost post = forumPostService.reportPost(id);
        if(post == null){
            return "redirect:/forum";
        }
        forumPostService.save(post);
        return "redirect:/forum";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String reportComment(@PathVariable ("id") long id , @RequestParam("commentid") long commentid, Model model, RedirectAttributes redirectAttributes) {
        ForumComment comment = forumCommentService.reportComment(commentid);
        if(comment == null){
            redirectAttributes.addFlashAttribute("wrong_message", "Please choose right comment to report");
            return "redirect:/forum/{id}";
        }
        forumCommentService.save(comment);
        redirectAttributes.addFlashAttribute("ok_message", "Report successfully, we will check it soon!");
        return "redirect:/forum/{id}";
    }

    }
