package com.udesc.padroesdeprojeto.gamelog.observer;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import org.springframework.mail.MailSender;

public interface IEventServer {
    void addObserver(IObserver obs);
    void removeObserver(IObserver obs);
    void notifyObservers(MailSender mailSender, String subject, String message);
}
