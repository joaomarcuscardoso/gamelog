package com.udesc.padroesdeprojeto.gamelog.composite;

public interface Category {
    void addCategorys(Category category);
    void removeCategorys(Category category);
    String getNames();
}
