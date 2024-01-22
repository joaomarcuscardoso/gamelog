package com.udesc.padroesdeprojeto.gamelog.abstractFactory;

import com.udesc.padroesdeprojeto.gamelog.model.User;

public interface ReviewConfigFactory {

    Reviews createReview(String title, float rating, String comment, User user);

    Configs createConfigs(String platform, String completion);
}
