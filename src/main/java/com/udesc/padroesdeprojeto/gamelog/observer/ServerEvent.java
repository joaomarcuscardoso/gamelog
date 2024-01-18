package com.udesc.padroesdeprojeto.gamelog.observer;

import lombok.NoArgsConstructor;
import org.springframework.mail.MailSender;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class ServerEvent implements IEventServer {
    private static ServerEvent instance = null;
    
    public static ServerEvent getInstance() {
        if (instance == null) {
            instance = new ServerEvent();
        }
        return instance;
    }
    private final List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers(MailSender mailSender, String subject, String message) {
        for (IObserver obs : observers) {
            obs.notifyUser(mailSender, subject, message);
        }
    }
}
