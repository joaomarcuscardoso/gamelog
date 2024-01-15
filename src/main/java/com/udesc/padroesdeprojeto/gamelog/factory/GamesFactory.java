package com.udesc.padroesdeprojeto.gamelog.factory;

import org.springframework.stereotype.Service;

@Service
public abstract class GamesFactory {

    protected abstract Games createGames();
}
