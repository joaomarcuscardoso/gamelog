package com.udesc.padroesdeprojeto.gamelog.observer;

import com.udesc.padroesdeprojeto.gamelog.model.Game;

public interface IGame {
    void addObserver(IObserver obs);
    void removeObserver(IObserver obs);
    void notifyObservers(Game game);
}
