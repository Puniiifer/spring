package org.zer0ne.test.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zer0ne.test.DAO.UserDAO;
import org.springframework.ui.Model;
import org.zer0ne.test.model.User;
import org.zer0ne.test.repository.UserRepository;

import java.security.Principal;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRepository userRepository;
    @GetMapping
    public String cabinet(Principal principal, Model model){
        model.addAttribute("user", userRepository.findByLogin(principal.getName()));
        return "user/cabinet";
    }
    @GetMapping("/{id}/edit")
    public String edit(@ModelAttribute User user, @PathVariable("id") int id, Model model){
        model.addAttribute("user", userDAO.getById(id));
        return "user/edit";
    }
    @PatchMapping("/{id}")
    public String update(@ModelAttribute User user){
        userDAO.updateUser(user);
        return "redirect:/user";
    }






}
