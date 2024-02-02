package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

import com.udesc.padroesdeprojeto.gamelog.factory.Games;
import com.udesc.padroesdeprojeto.gamelog.model.User;
import com.udesc.padroesdeprojeto.gamelog.visitor.ReviewVisitor;

public interface Reviews {

    Integer getGameId();
    Integer getUserId();
    Integer getDlcId();

    void accept(ReviewVisitor visitor);
}
