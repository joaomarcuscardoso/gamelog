package com.udesc.padroesdeprojeto.gamelog.factory;

import com.udesc.padroesdeprojeto.gamelog.model.User;
import org.springframework.stereotype.Service;

@Service
public abstract class GamesFactory {

    protected abstract Games createGames();

    protected abstract Games setGames(String name, String released, String developer, String description, String CoverImage);
}
