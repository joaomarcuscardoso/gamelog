package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

import com.udesc.padroesdeprojeto.gamelog.model.DetailedConfig;
import com.udesc.padroesdeprojeto.gamelog.model.DetailedReview;
import com.udesc.padroesdeprojeto.gamelog.model.User;

public class DetailedFactory implements ReviewConfigFactory{
    @Override
    public Reviews createReview(String title, float rating, String comment, User user) {
        return new DetailedReview(title, rating, comment, user);
    }

    @Override
    public Configs createConfigs(String platform, String completion) {
        return new DetailedConfig(platform, completion);
    }
}
