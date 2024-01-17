package com.udesc.padroesdeprojeto.gamelog.observer;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
public class GameEvent implements  IGame {
    private static GameEvent instance = null;
    
    public static GameEvent getInstance() {
        if (instance == null) {
            instance = new GameEvent();
        }
        return instance;
    }
    private List<IObserver> observers = new ArrayList<>();

    @Override
    public void addObserver(IObserver obs) {
        observers.add(obs);
    }

    @Override
    public void removeObserver(IObserver obs) {
        observers.remove(obs);
    }

    @Override
    public void notifyObservers(Game game) {
        for (IObserver obs : observers) {
            obs.update(game);
        }
    }
}
