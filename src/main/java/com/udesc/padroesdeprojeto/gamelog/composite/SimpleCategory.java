package com.udesc.padroesdeprojeto.gamelog.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class SimpleCategory implements Category{

    private String name;
    private List<String> games = new ArrayList<>();

    public SimpleCategory(String name){
        this.name = name;
    }
    @Override
    public void addCategorys(Category category) {

    }

    @Override
    public void removeCategorys(Category category) {

    }

    @Override
    @JsonIgnore
    public String getNames() {
        return name;
    }

    @Override
    public List<String> getGames() {
        return games;
    }
}
