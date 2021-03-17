package com.feifanchen.thirdyearproject.controllers;

import com.feifanchen.thirdyearproject.dao.RolesRepository;
import com.feifanchen.thirdyearproject.dao.UserService;
import com.feifanchen.thirdyearproject.entities.Roles;
import com.feifanchen.thirdyearproject.entities.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Controller
@RequestMapping(value = "/user", produces = { MediaType.TEXT_HTML_VALUE})
public class UserController {
    private final static Logger log = LoggerFactory.getLogger(UserController.class);

    private final RolesRepository rolesRepository;

    private final UserService userService;
    @Autowired
    public UserController(RolesRepository rolesRepository, UserService userService) {
        this.rolesRepository = rolesRepository;
        this.userService = userService;
    }

    @GetMapping
    public String findAll (Model model){
        model.addAttribute("users", userService.findAll());
        return "user/index";
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
        return "redirect:/homepage";
    }
}
