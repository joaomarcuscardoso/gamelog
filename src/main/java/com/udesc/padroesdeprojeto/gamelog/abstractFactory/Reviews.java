package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

import com.udesc.padroesdeprojeto.gamelog.factory.Games;
import com.udesc.padroesdeprojeto.gamelog.model.User;

public interface Reviews {

    Integer getGameId();
    Integer getUserId();
    Integer getDlcId();
}
