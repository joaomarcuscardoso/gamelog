package com.udesc.padroesdeprojeto.gamelog.composite;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class AbstractCategory implements Category{

    private List<Category> categoryList = new ArrayList<>();
    private String name;

    public AbstractCategory(String name){
        this.name = name;
    }
    @Override
    public void addCategorys(Category category) {
        categoryList.add(category);
    }

    @Override
    public void removeCategorys(Category category) {
        categoryList.remove(category);
    }


    public List<Category> getCategories() {
        return categoryList;
    }

    @Override
    @JsonIgnore
    public String getNames() {
        StringBuilder names = new StringBuilder(name + " [");

        for(Category category : categoryList){
            names.append(category.getNames()).append(", ");
        }
        if(!categoryList.isEmpty()){
            names.setLength(names.length() - 2);
        }

        names.append("]");
        return names.toString();
    }
}
