package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

public interface ReviewConfigFactory {

    Reviews createReview(String title, float rating, String comment);

    Configs createConfigs(String platform, String completion);
}
