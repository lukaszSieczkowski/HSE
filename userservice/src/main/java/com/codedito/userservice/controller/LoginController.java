package com.codedito.userservice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class LoginController {

    @GetMapping("/")
    public String showIndexPage() {
        return "index";
    }


    @RequestMapping("/login")
    public String showLoginPage(final Model model) {
        return "login_page";
    }

    @RequestMapping("/main")
    public String showUserPage(final ModelMap model) {
        return "protected/main";
    }

    @RequestMapping("/forgot_password")
    public String showForgotPasswordForm(final ModelMap model) {
        return "forgot_password";
    }

}
