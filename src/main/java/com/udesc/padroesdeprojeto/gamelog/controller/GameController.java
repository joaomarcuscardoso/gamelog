package com.udesc.padroesdeprojeto.gamelog.controller;

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
@RequestMapping("/games")
public class GameController {
    @RequestMapping
    public ModelAndView index(Authentication authentication, Model model) {
        model.addAttribute("message", "This message is from the controller.");
        return new ModelAndView("home");
    }
}