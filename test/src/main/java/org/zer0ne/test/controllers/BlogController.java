package org.zer0ne.test.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.zer0ne.test.DAO.BlogDAO;
import org.zer0ne.test.DAO.UserDAO;
import org.zer0ne.test.model.Blog;
import org.springframework.ui.Model;
import org.zer0ne.test.repository.BlogRepository;
import org.zer0ne.test.repository.UserRepository;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogDAO blogDAO;
    @Autowired
    private UserDAO userDAO;
    @Autowired
    private UserRepository userRepository;
    @GetMapping()
    public String all(Principal principal, Model model) {
        model.addAttribute("blogs", blogDAO.getBlogs());
        model.addAttribute("user", userRepository.findByLogin(principal.getName()));
        return "blog/main";
    }
    @GetMapping("/add")
    public String add(Model model){
        model.addAttribute("blog", new Blog());
        return "blog/add";
    }
    @PostMapping()
    public String create(@ModelAttribute("blog") Blog blog, Principal principal){
        blog.setAuthor(userDAO.getId(userRepository.findByLogin(principal.getName())));
        blogDAO.createBlog(blog);
        return "redirect:/blog";
    }
    @GetMapping("/{id}")
    public String getBlogById(@PathVariable("id") int id, Model model, Principal principal){
        model.addAttribute("user", userRepository.findByLogin(principal.getName()));
        model.addAttribute(blogDAO.getBlog(id));
        return "blog/show";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("blog", blogDAO.getBlog(id));
        return "blog/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("blog") Blog blog, @PathVariable("id") int id){
        blogDAO.updateBlog(blog);
        return"redirect:/blog";
    }
    @DeleteMapping("/{id}/delete")
    public String delete(@PathVariable("id") int id){
        blogDAO.deleteBlogById(id);
        return"redirect:/blog";
    }


}
