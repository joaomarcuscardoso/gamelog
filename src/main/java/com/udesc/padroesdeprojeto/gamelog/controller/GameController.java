package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.dto.GameRequestDTO;
import com.udesc.padroesdeprojeto.gamelog.factory.GameFactory;
import com.udesc.padroesdeprojeto.gamelog.model.Game;
import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    private final GameRepository gameRepository;
    @RequestMapping
    public ModelAndView index(Authentication authentication, Model model) {
        model.addAttribute("message", "This message is from the controller.");
        return new ModelAndView("home");

    }

    @PostMapping
    public ResponseEntity<Object> addGame(@RequestBody @Valid GameRequestDTO gameDto){
        GameFactory gameFactory = GameFactory.getInstance();

        Game game = gameFactory.createGames();

        game.setName(gameDto.getName());
        game.setReleased(gameDto.getReleased());
        game.setDeveloper(gameDto.getDeveloper());
        game.setDescription(gameDto.getDescription());
        game.setCoverImage(gameDto.getCoverImage());
        gameRepository.save(game);

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
}
