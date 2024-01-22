package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

import com.udesc.padroesdeprojeto.gamelog.model.Review;
import com.udesc.padroesdeprojeto.gamelog.model.SimpleConfig;
import com.udesc.padroesdeprojeto.gamelog.model.User;

public class SimpleReviewFactory implements ReviewConfigFactory{

    @Override
    public Review createReview(String title, float rating, String comment, User user) {
        return new Review(title, rating, comment, user);
    }

    @Override
    public SimpleConfig createConfigs(String platform, String completion) {
        return new SimpleConfig(platform, completion);
    }


}
