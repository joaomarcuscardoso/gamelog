package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
@RequestMapping("/games")
public class GameController {
    @Autowired
    private UserRepository userRepository;

    private User user;
    @GetMapping
    public ModelAndView index(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        User user = this.validate(model, session, redirectAttributes);
        if (user == null) {
            redirectAttributes.addFlashAttribute("error", "Authenticação falhou!");
            return new ModelAndView("redirect:/auth/signin");
        }

        return new ModelAndView("home");
    }

    private User validate(Model model, HttpSession session, RedirectAttributes redirectAttributes) {
        String token = (String) session.getAttribute("token");
        String email = (String) session.getAttribute("email");
//        System.out.println("TOKEN: " + token);
//        System.out.println("EMAIL: " + email);
        if (session.getAttribute("token") == null || session.getAttribute("email") == null)
            return null;

        User user = userRepository.findByToken(token).orElse(null);
        User userEmail = userRepository.findByEmail(email).orElse(null);
        if (user == null || userEmail == null)
            return null;

        if (!user.getEmail().equals(userEmail.getEmail()))
            return null;

        return user;
    }
}
