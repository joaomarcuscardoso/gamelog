package com.udesc.padroesdeprojeto.gamelog.composite;



import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Component;

@Component
public class CategoryInitializer {

    private  AbstractCategory rootCategory = new AbstractCategory("Categories");


    @PostConstruct
    public void initializeCategories(){

        SimpleCategory indieCategory = new SimpleCategory("Indie");
        AbstractCategory adventureCategory = new AbstractCategory("Adventure");
        adventureCategory.addCategorys(new SimpleCategory("Survival"));

        AbstractCategory actionCategory = new AbstractCategory("Action");
        actionCategory.addCategorys(new SimpleCategory("Hack n slash"));
        actionCategory.addCategorys(new SimpleCategory("Fps"));
        actionCategory.addCategorys(new SimpleCategory("Horror"));


        rootCategory.addCategorys(indieCategory);
        rootCategory.addCategorys(adventureCategory);
        rootCategory.addCategorys(actionCategory);
    }

    public AbstractCategory getRootCategory(){
        return rootCategory;
    }
}
