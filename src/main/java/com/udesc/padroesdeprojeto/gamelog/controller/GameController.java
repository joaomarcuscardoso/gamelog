package com.udesc.padroesdeprojeto.gamelog.controller;

import jakarta.servlet.http.Cookie;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class GameController {
    @RequestMapping("/home")
    public ModelAndView index(Authentication authentication, Model model, Cookie cookie) {
        System.out.println("token: " + cookie.getValue());
        model.addAttribute("message", "This message is from the controller.");
        System.out.println("Authenticated user: " + authentication.getName());
        return new ModelAndView("home");
    }
}
