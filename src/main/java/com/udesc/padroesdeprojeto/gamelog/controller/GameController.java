package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.dto.GameRequestDTO;
import com.udesc.padroesdeprojeto.gamelog.factory.GameFactory;
import com.udesc.padroesdeprojeto.gamelog.model.Game;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameRepository gameRepository;
    private final UserRepository userRepository;
    @RequestMapping
    public ModelAndView index(Authentication authentication, Model model) {
        model.addAttribute("message", "This message is from the controller.");
        return new ModelAndView("home");

    }

    @GetMapping
    public ResponseEntity<List<Game>> listAll(){
        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findAll());
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){

        Game optionalGame = gameRepository.findById(id).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(optionalGame);
    }

    @PostMapping
    public ResponseEntity<Object> addGame(@RequestBody @Valid GameRequestDTO gameDto){
        GameFactory gameFactory = GameFactory.getInstance();

        User user = userRepository.findById(gameDto.getIduUser()).orElseThrow();

        Game game = gameFactory.createGames();

        game.setName(gameDto.getName());
        game.setReleased(gameDto.getReleased());
        game.setDeveloper(gameDto.getDeveloper());
        game.setDescription(gameDto.getDescription());
        game.setCoverImage(gameDto.getCoverImage());
        game.setUser(user);
        gameRepository.save(game);

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }


}
