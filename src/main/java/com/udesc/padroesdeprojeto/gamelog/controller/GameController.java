package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.factory.GameFactory;
import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/game")
@RequiredArgsConstructor
public class GameController {

    private final GameRepository gameRepository;

    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody String name){
        GameFactory  gameFactory = new GameFactory();
        return null;
    }
}
