package com.udesc.padroesdeprojeto.gamelog.decorator;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class FavoriteStatusDecorator implements  GameDecorator{
    private Game decoratedGame;
    @Override
    public String getName() {
        return decoratedGame.getName() + " - (FAVORITO)";
    }
}