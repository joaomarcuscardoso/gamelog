package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.command.EmailCommand;
import com.udesc.padroesdeprojeto.gamelog.command.Invoker;
import com.udesc.padroesdeprojeto.gamelog.decorator.FavoriteStatusDecorator;
import com.udesc.padroesdeprojeto.gamelog.decorator.GameDecorator;
import com.udesc.padroesdeprojeto.gamelog.decorator.PlatinumStatusDecorator;
import com.udesc.padroesdeprojeto.gamelog.dto.GameRequestDTO;
import com.udesc.padroesdeprojeto.gamelog.facade.FileGenerator;
import com.udesc.padroesdeprojeto.gamelog.facade.FileGeneratorFacade;
import com.udesc.padroesdeprojeto.gamelog.factory.GameFactory;
import com.udesc.padroesdeprojeto.gamelog.command.TotalGamesEmailCommand;
import com.udesc.padroesdeprojeto.gamelog.dto.DlcRequestDTO;
import com.udesc.padroesdeprojeto.gamelog.dto.GameRequestDTO;
import com.udesc.padroesdeprojeto.gamelog.facade.FileGenerator;
import com.udesc.padroesdeprojeto.gamelog.facade.FileGeneratorFacade;
import com.udesc.padroesdeprojeto.gamelog.factory.DlcFactory;
import com.udesc.padroesdeprojeto.gamelog.factory.GameFactory;
import com.udesc.padroesdeprojeto.gamelog.factory.Games;
import com.udesc.padroesdeprojeto.gamelog.model.Dlc;
import com.udesc.padroesdeprojeto.gamelog.model.Game;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.repository.DlcRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import com.udesc.padroesdeprojeto.gamelog.repository.UserRepository;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;
import com.udesc.padroesdeprojeto.gamelog.state.ArchivedState;
import com.udesc.padroesdeprojeto.gamelog.state.IGameState;
import com.udesc.padroesdeprojeto.gamelog.state.PublishedState;
import com.udesc.padroesdeprojeto.gamelog.state.UnpublishedState;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;


import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;

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
    @Autowired
    private final DlcRepository dlcRepository;

    private Game game;

    private List<Game> games = new ArrayList<>();


    @GetMapping("/all")
    public ResponseEntity<List<Game>> listAll() {
        games.forEach(game -> {
            if (game.isFavorite()) {
                GameDecorator favorite = new FavoriteStatusDecorator(game);
                game.setName(favorite.getName());
            } else if (game.isPlatinum()) {
                GameDecorator favorite = new PlatinumStatusDecorator(game);
                game.setName(favorite.getName());
            }
        });
        return ResponseEntity.status(HttpStatus.OK).body(games);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id){

        Game optionalGame = gameRepository.findById(id).orElseThrow();

        return ResponseEntity.status(HttpStatus.OK).body(optionalGame);
    }

    @PostMapping
    public ResponseEntity<Object> addGame(@RequestBody @Valid GameRequestDTO gameDto){
        GameFactory gameFactory = GameFactory.getInstance();

        User user = userRepository.findById(gameDto.getIdUser()).orElseThrow(() -> new EntityNotFoundException(("Usuario não encontrado")));

        Game game = gameFactory.setGames(gameDto.getName(),gameDto.getReleased(),gameDto.getDeveloper(),
                gameDto.getDescription(),gameDto.getCoverImage());

        game.setUser(user);
        game.setFavorite(gameDto.isFavorite());
        game.setPlatinum(gameDto.isPlatinum());

        // State
        game.setIstate(new UnpublishedState(game));
        game.transitionToUnpublished();
        this.game = game;
        games.add(game);

        gameRepository.save(game);

        // Command
        long gameCount = gameRepository.countByUser(user);
        Invoker invoker = Invoker.getInstance();
        EmailCommand emailCommand = new EmailCommand(mailSenderService, user, game);
        TotalGamesEmailCommand totalGamesEmailCommand= new TotalGamesEmailCommand(mailSenderService, gameCount, user);
        invoker.addCommandEmail(emailCommand);
        invoker.addCommandEmail(totalGamesEmailCommand);
        invoker.executeCommandsEmail();

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PostMapping("/dlc/{gameId}")
    public ResponseEntity<Object> addDlc(@PathVariable Integer gameId,
                                         @RequestBody @Valid DlcRequestDTO dlcRequestDTO){
        DlcFactory dlcFactory = DlcFactory.getInstance();

        Game game = gameRepository.findById(gameId).orElseThrow(() -> new EntityNotFoundException(("Game não encontrado")));

        Dlc dlc = dlcFactory.setGames(dlcRequestDTO.getName(),dlcRequestDTO.getReleased(),dlcRequestDTO.getDeveloper(),
                dlcRequestDTO.getDescription(),dlcRequestDTO.getCoverImage());

        dlc.setGame(game);
        dlc.setExtraContentAdded(dlcRequestDTO.getExtraContentAdded());

        dlcRepository.save(dlc);

        return ResponseEntity.status(HttpStatus.OK).body(dlc);
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

    @PutMapping("/published/{gameId}")
    public ResponseEntity<Object> published(@PathVariable Integer gameId) {
        game.transitionToPublished();
        gameRepository.save(game);

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }

    @PutMapping("/archive/{gameId}")
    public ResponseEntity<Object> archiveGame(@PathVariable Integer gameId) {
        game.transitionToArchived();
        gameRepository.save(game);

        return ResponseEntity.status(HttpStatus.OK).body(game);
    }
}