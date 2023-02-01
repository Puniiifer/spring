package org.zer0ne.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.zer0ne.test.DAO.UserDAO;
import org.zer0ne.test.model.User;

@Controller
@RequestMapping("/")
public class AuthController {
    @Autowired
    private UserDAO userDAO;
    @GetMapping("/")
    public String hello(){
        return "hello";
    }
    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }
    @PostMapping("/login")
    public String getByLogin(@ModelAttribute("user") User user){
        return "redirect:/cabinet/{user.getIdByLogin()}";
    }
    @GetMapping("/registration")
    public String reg(Model model){
        model.addAttribute("user", new User());
        return "registration";
    }
    @PostMapping("/registration")
    public String add(@ModelAttribute("user") User user){
        userDAO.createUser(user);
        return "redirect:/login";
    }
}
