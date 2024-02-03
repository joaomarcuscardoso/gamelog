package com.udesc.padroesdeprojeto.gamelog.composite;

import java.util.List;

public interface Category {
    void addCategorys(Category category);
    void removeCategorys(Category category);
    String getNames();
    List<String> getGames();
}
