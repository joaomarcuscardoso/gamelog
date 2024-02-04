package com.udesc.padroesdeprojeto.gamelog.factory;

import com.udesc.padroesdeprojeto.gamelog.model.Game;
import com.udesc.padroesdeprojeto.gamelog.model.User;

public class GameFactory extends GamesFactory{

    private static GameFactory instance;

    private GameFactory(){}

    public static GameFactory getInstance(){
        if(instance == null){
            instance = new GameFactory();
        }
        return instance;
    }
    @Override
    protected Game createGames() {
        return new Game();
    }

    @Override
    public Game setGames(String name, String released, String developer, String description, String coverImage) {
        Game game = this.createGames();

        game.setName(name);
        game.setReleased(released);
        game.setDeveloper(developer);
        game.setDescription(description);
        game.setCoverImage(coverImage);

        return game;
    }
}
