package com.udesc.padroesdeprojeto.gamelog.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    @GetMapping
    public ResponseEntity<Object> index() {
        String content = "ok";
        return ResponseEntity.status(HttpStatus.OK).body(content);
    }
}
