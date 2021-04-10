package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.*;
import com.feifanchen.thirdyearproject.entities.ForumComment;
import com.feifanchen.thirdyearproject.entities.ForumPost;
import com.feifanchen.thirdyearproject.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.sql.Timestamp;
import java.util.Set;

@Controller
@RequestMapping(value = "/forum",  produces = {MediaType.TEXT_HTML_VALUE})
public class ForumController {
    public final ForumPostService forumPostService;
    public final ForumCommentService forumCommentService;
    public final TopicService topicService;
    public final UserRepository userRepository;
    @Autowired
    public ForumController(ForumPostService forumPostService, ForumCommentService forumCommentService, TopicService topicService, UserService userService, UserRepository userRepository) {
        this.forumPostService = forumPostService;
        this.forumCommentService = forumCommentService;
        this.topicService = topicService;
        this.userRepository = userRepository;
    }

    @GetMapping
    public String findAll (Model model){
        model.addAttribute("forumposts", forumPostService.findAll());
        //model.addAttribute("forumcomments", forumPostService.findAll());
        return "/forum/index";
    }

    @GetMapping(value = "/{id}")
    public String showSinglePost(@PathVariable("id") long id, Model model){
        ForumPost post =  forumPostService.findOne(id);
        post.addWatch();
        forumPostService.save(post);
        ForumComment comment = new ForumComment();
        comment.setPost(forumPostService.findOne(id));
        System.out.println(comment.getPost().getId());
        model.addAttribute("comment", comment);
        model.addAttribute("post", post);
        model.addAttribute("comments", post.getComments());
        return"/forum/singlepost";
    }

    @GetMapping(value = "/addComment")
    public String addComments(@RequestParam("id") long id,  Model model){
        ForumComment comment = new ForumComment();
        comment.setPost(forumPostService.findOne(id));
        System.out.println(comment.getPost().getId());
        model.addAttribute("comment", comment);
        return"/forum/addComment";
    }

    @GetMapping(value = "/addPost")
    public String addPost(Model model){
        ForumPost forumPost = new ForumPost();
        model.addAttribute("post", forumPost);
        return"/forum/addPost";
    }

    @RequestMapping(value = "/post/report/{id}", method = RequestMethod.GET)
    public String reportPost(@PathVariable("id") long id, Model model, RedirectAttributes redirectAttributes){
        ForumPost post = forumPostService.reportPost(id);
        if(post == null){
            return "redirect:/forum";
        }
        forumPostService.save(post);
        return "redirect:/forum";
    }

    @RequestMapping(value = "/comment/report/{id}", method = RequestMethod.GET)
    public String reportComment(@PathVariable("id") long id, @RequestParam("postid") long postid, Model model, RedirectAttributes redirectAttributes) {
        ForumComment comment = forumCommentService.reportComment(id);
        comment.setReport(1);
        if(comment == null){
            redirectAttributes.addFlashAttribute("wrong_message", "Please choose right comment to report");
            return "redirect:/forum/{id}";
        }
        forumCommentService.save(comment);
        redirectAttributes.addFlashAttribute("ok_message", "Report successfully, we will check it soon!");
        redirectAttributes.addAttribute("id", postid);
        return "redirect:/forum/{id}";
    }

    @RequestMapping(path = "post/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deletePost(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {

        ForumPost u = forumPostService.findOne(id);
        Set<ForumComment> c = u.getComments();
        System.out.println(c.size());
        for(ForumComment f : c){
            forumCommentService.deleteById(f.getId());
        }
        forumPostService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

    @RequestMapping(path = "/comment/delete/{id}", method = { RequestMethod.DELETE, RequestMethod.POST })
    public String deleteComment(@PathVariable("id") long id, RedirectAttributes redirectAttrs, Model model) {
            forumCommentService.deleteById(id);
        redirectAttrs.addFlashAttribute("ok_message", "Events deleted.");
        return "redirect:/user/admin";
    }

    @RequestMapping(value = "/showSingle", method = {RequestMethod.GET})
    public String showSingleForum(@RequestParam("id") long id, Model model){
        ForumPost post =  forumPostService.findOne(id);
        model.addAttribute("post", post);
        model.addAttribute("topicModel", topicService.findAll());
        //System.out.println(topicList.toString());
        return "/forum/showForum";
    }

    @RequestMapping(value = "/showReportSingle", method = {RequestMethod.GET})
    public String showReportedComment(@RequestParam("id") long id, Model model){
        ForumComment forumComment = forumCommentService.findOne(id);
        ForumPost post =  forumPostService.findOne(forumComment.getPost().getId());
        model.addAttribute("post", post);
        model.addAttribute("comment", forumComment);
        //System.out.println(topicList.toString());
        return "/forum/showReported";
    }

    @PostMapping(value = "/save")
    public String save(ForumPost post, @RequestParam("add_by") String add_by, @RequestParam("id") long id){
        forumPostService.save(post);

        return "redirect:/user/admin";
    }

    @PostMapping(value = "/saveComment")
    public String saveComment(ForumComment comment, @RequestParam("id") long id, @RequestParam("add_by") String add_by, RedirectAttributes redirectAttrs){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        User user = userRepository.findbyName(add_by);
        comment.setUsr_id(user);
        comment.setCreate_time(d);
        comment.setReport(0);
        forumCommentService.save(comment);
        System.out.println(comment.getPost().getId());
        redirectAttrs.addAttribute("id", id);
        return "redirect:/forum/{id}";
    }

    @PostMapping(value = "/savePost")
    public String savePost(ForumPost post, @RequestParam("add_by") String add_by){
        Timestamp d = new Timestamp(System.currentTimeMillis());
        User user = userRepository.findbyName(add_by);
        post.setP_usr(user);
        post.setWatch(0);
        post.setReport(0);
        post.setCreate_time(d);
        forumPostService.save(post);

        return "redirect:/forum";
    }

    @RequestMapping(value = "/pass")
    public String passForum(@RequestParam("id") long id, @RequestParam("type") int type){
        if(type == 1){
            ForumPost forumPost = forumPostService.findOne(id);
            forumPost.setReport(0);
            forumPostService.save(forumPost);
        }
        else if(type == 2){
            ForumComment forumComment = forumCommentService.findOne(id);
            forumComment.setReport(0);
            forumCommentService.save(forumComment);
        }
        return "redirect:/user/admin";
    }
}
