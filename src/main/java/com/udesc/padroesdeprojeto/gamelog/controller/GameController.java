package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.command.EmailCommand;
import com.udesc.padroesdeprojeto.gamelog.command.Invoker;
import com.udesc.padroesdeprojeto.gamelog.dto.GameRequestDTO;
import com.udesc.padroesdeprojeto.gamelog.facade.FileGenerator;
import com.udesc.padroesdeprojeto.gamelog.facade.FileGeneratorFacade;
import com.udesc.padroesdeprojeto.gamelog.factory.GameFactory;
import com.udesc.padroesdeprojeto.gamelog.factory.Games;
import com.udesc.padroesdeprojeto.gamelog.model.Game;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.observer.GameEvent;
import com.udesc.padroesdeprojeto.gamelog.observer.IObserver;
import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Controller
@RequestMapping("/games")
@RequiredArgsConstructor
public class GameController {
    @Autowired
    private final GameRepository gameRepository;
    @Autowired
    private final UserRepository userRepository;
    @Autowired
    private JavaMailSenderService mailSenderService;


//    @GetMapping
//    public ResponseEntity<List<Game>> listAll() {
//        return ResponseEntity.status(HttpStatus.OK).body(gameRepository.findAll());
//    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){

        Game optionalGame = gameRepository.findById(id).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(optionalGame);
    }

    @PostMapping
    public ResponseEntity<Object> addGame(@RequestBody @Valid GameRequestDTO gameDto){
        GameFactory gameFactory = GameFactory.getInstance();

        User user = userRepository.findById(gameDto.getIdUser()).orElseThrow();

        Game game = gameFactory.createGames();

        game.setName(gameDto.getName());
        game.setReleased(gameDto.getReleased());
        game.setDeveloper(gameDto.getDeveloper());
        game.setDescription(gameDto.getDescription());
        game.setCoverImage(gameDto.getCoverImage());
        game.setUser(user);
        gameRepository.save(game);

        // Observer
//        GameEvent gameEvent = GameEvent.getInstance();
//        gameEvent.notifyObservers(game);

        // Command
        Invoker invoker = Invoker.getInstance();
        EmailCommand emailCommand = new EmailCommand(mailSenderService, user, game);

        invoker.addCommandEmail(emailCommand);
        invoker.executeCommandsEmail();

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

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

    @GetMapping("/download/{type}")
    public ResponseEntity<Object> downloadFile(@PathVariable("type") String type) {
        FileGenerator fileGenerator = new FileGeneratorFacade();
        List<Game> games = gameRepository.findAll();
        byte[] fileContent = fileGenerator.saveFile(type, games);
        if (fileContent == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Digite o Tipo de Documento para executar download, valor permitido: pdf ou csv");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", "data." + type.toLowerCase());
        return ResponseEntity.ok()
                .headers(headers)
                .body(fileContent);
    }
}