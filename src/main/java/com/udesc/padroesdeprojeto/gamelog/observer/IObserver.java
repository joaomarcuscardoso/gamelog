package com.udesc.padroesdeprojeto.gamelog.observer;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import org.springframework.mail.MailSender;

public interface IObserver {
    void notifyUser(MailSender mailSender, String message, String subject);
}
