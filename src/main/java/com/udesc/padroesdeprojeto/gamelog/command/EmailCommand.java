package com.udesc.padroesdeprojeto.gamelog.command;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;
import org.springframework.beans.factory.annotation.Autowired;

public class EmailCommand implements Command{


    private final JavaMailSenderService mailSenderService;
    private final User user;
    private final Game game;
    public EmailCommand(JavaMailSenderService mailSenderService, User user, Game game){
        this.mailSenderService = mailSenderService;
        this.user = user;
        this.game = game;
    }
    @Override
    public void execute() {
        String body = user.getUsername() + "O jogo: " + game.getName() + "Foi criado e adicionado na sua biblioteca, não se esqueça de avalialo.";
        mailSenderService.sendEmail(user.getEmail(), "Novo jogo criado", body);
    }
}
