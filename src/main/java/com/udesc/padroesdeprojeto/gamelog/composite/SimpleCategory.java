package com.udesc.padroesdeprojeto.gamelog.composite;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;

@Getter
public class SimpleCategory implements Category{

    private String name;

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
}
