package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
    @Autowired
   private UserService userService;

    @RequestMapping(value = "/user/register", method = RequestMethod.POST)
    public String addUser(@ModelAttribute User user){
        userService.addUser(user);
        return "redirect:/";
    }
}
