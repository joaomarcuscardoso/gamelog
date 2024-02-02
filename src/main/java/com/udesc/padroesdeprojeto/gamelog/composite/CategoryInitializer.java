package com.udesc.padroesdeprojeto.gamelog.composite;



import com.udesc.padroesdeprojeto.gamelog.repository.GameRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public class CategoryInitializer {

    private  AbstractCategory rootCategory = new AbstractCategory("Categories");


    @PostConstruct
    public void initializeCategories(){

        SimpleCategory indieCategory = new SimpleCategory("Indie");
        AbstractCategory adventureCategory = new AbstractCategory("Adventure");
        SimpleCategory survivalCategory = new SimpleCategory("Survival");
        adventureCategory.addCategorys(survivalCategory);

        AbstractCategory actionCategory = new AbstractCategory("Action");
        actionCategory.addCategorys(new SimpleCategory("Hack n slash"));
        actionCategory.addCategorys(new SimpleCategory("Fps"));
        actionCategory.addCategorys(new SimpleCategory("Horror"));



        indieCategory.setGames(Arrays.asList("Game1", "Game2"));
        survivalCategory.setGames(Arrays.asList("Game3", "Game4"));


        rootCategory.addCategorys(indieCategory);
        rootCategory.addCategorys(adventureCategory);
        rootCategory.addCategorys(actionCategory);
    }

    public AbstractCategory getRootCategory(){
        return rootCategory;
    }
}
