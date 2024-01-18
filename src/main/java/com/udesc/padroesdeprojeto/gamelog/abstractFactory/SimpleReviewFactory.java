package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

import com.udesc.padroesdeprojeto.gamelog.model.Review;
import com.udesc.padroesdeprojeto.gamelog.model.SimpleConfig;

public class SimpleReviewFactory implements ReviewConfigFactory{

    @Override
    public Reviews createReview(String title, float rating, String comment) {
        return new Review(title, rating, comment);
    }

    @Override
    public Configs createConfigs(String platform, String completion) {
        return new SimpleConfig(platform, completion);
    }


}
