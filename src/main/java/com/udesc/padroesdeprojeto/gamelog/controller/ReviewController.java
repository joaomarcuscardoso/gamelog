package com.udesc.padroesdeprojeto.gamelog.controller;

import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/review")
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping("/review")
    public ResponseEntity<Object> addReview(){
        return null;
    }

    @PostMapping("/detailed")
    public ResponseEntity<Object> addDetailedReview(){
        return null;
    }
}
