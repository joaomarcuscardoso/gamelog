package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.dto.LoginRequest;
import com.udesc.padroesdeprojeto.gamelog.dto.SignupRequest;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("/auth")
public class UserController {

    @Autowired
    private UserRepository repository;

    @GetMapping("/signin")
    public ModelAndView signin() {

        return new ModelAndView("signin");
    }
    @GetMapping("/signup")
    public ModelAndView signup() {

        return new ModelAndView("signup");
    }

    @PostMapping("/signin")
    public String authenticateUser(@Valid @ModelAttribute("loginRequest") LoginRequest loginRequest,
                                   RedirectAttributes redirectAttributes, HttpSession session) {
        User user = repository.findByUsername(loginRequest.getUsername()).orElse(null);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Login Failed!");
            return "redirect:/auth/signin";
        }

        PasswordEncoder encode = new BCryptPasswordEncoder();
        if (encode.matches(loginRequest.getPassword(), user.getPassword())) {
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            repository.save(user);
            session.setAttribute("token", token);
            session.setAttribute("email", user.getEmail());
            redirectAttributes.addFlashAttribute("message", "Login successful!");

            return "redirect:/games";
        } else {
            redirectAttributes.addFlashAttribute("error", "Login Failed!");
            return "redirect:/auth/signin";
        }
    }

    @PostMapping("/signup")
    public String processSignupForm(@ModelAttribute("signupRequest") SignupRequest signupRequest, RedirectAttributes redirectAttributes) {
        Boolean existUser = repository.existsByUsernameAndEmail(signupRequest.getUsername(), signupRequest.getEmail());
        if (existUser) {
            redirectAttributes.addFlashAttribute("error", "Error Ao Criar usuário!");
            return "redirect:/auth/signup";
        }

        PasswordEncoder encode = new BCryptPasswordEncoder();
        String passwordEncode = encode.encode(signupRequest.getPassword());

        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncode);
        repository.save(user);
        // Redirect to a success page or login page
        return "redirect:/auth/signin";
    }
}
