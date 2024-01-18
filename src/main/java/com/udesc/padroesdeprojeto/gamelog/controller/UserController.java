package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.dto.LoginRequest;
import com.udesc.padroesdeprojeto.gamelog.dto.SignupRequest;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.observer.ServerEvent;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.UUID;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserRepository repository;

    @PostMapping("/signin")
    public ResponseEntity<Object> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        User user = repository.findByUsername(loginRequest.getUsername()).orElse(null);
        if (user == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no Login.");

        PasswordEncoder encode = new BCryptPasswordEncoder();
        if (!encode.matches(loginRequest.getPassword(), user.getPassword()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error no Login");


        String token = UUID.randomUUID().toString();
        user.setToken(token);
        repository.save(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }

    @GetMapping("/signin")
    public ModelAndView signin() {

        return new ModelAndView("signin");
    }
    @GetMapping("/signup")
    public ModelAndView signup() {

        return new ModelAndView("signup");
    }

    @PostMapping("/signup")
    public ResponseEntity<Object> processSignupForm(@RequestBody @Valid SignupRequest signupRequest) {
        Boolean existUser = repository.existsByUsernameOrEmail(signupRequest.getUsername(), signupRequest.getEmail());
        if (existUser)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username ou email já cadastrado");

        PasswordEncoder encode = new BCryptPasswordEncoder();
        String passwordEncode = encode.encode(signupRequest.getPassword());
        User user = new User(signupRequest.getUsername(), signupRequest.getEmail(), passwordEncode);
        repository.save(user);

        ServerEvent gameEvent = ServerEvent.getInstance();
        gameEvent.addObserver(user);

        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}
