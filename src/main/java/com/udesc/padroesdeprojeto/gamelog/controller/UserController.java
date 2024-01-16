package com.udesc.padroesdeprojeto.gamelog.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/auth")
public class UserController {

    @GetMapping("/signin")
    public ModelAndView signin() {

        return new ModelAndView("signin");
    }
    @GetMapping("/signup")
    public ModelAndView signup() {

        return new ModelAndView("signup");
    }
}
