package com.udesc.padroesdeprojeto.gamelog.command;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.service.JavaMailSenderService;

public class TotalGamesEmailCommand implements Command{

    private final JavaMailSenderService mailSenderService;

    private long gamesCount;

    private User user;

    public TotalGamesEmailCommand(JavaMailSenderService mailSenderService, long gamesCount, User user){
        this.mailSenderService = mailSenderService;
        this.gamesCount = gamesCount;
        this.user = user;
    }
    @Override
    public void execute() {
        String body = user.getUsername() + " O seu total de jogos cadastrado é" + gamesCount;
        mailSenderService.sendEmail(user.getEmail(), "Total de jogos cadastrados em sua biblioteca", body);
    }
}
