package com.udesc.padroesdeprojeto.gamelog.controller;

import com.udesc.padroesdeprojeto.gamelog.composite.AbstractCategory;
import com.udesc.padroesdeprojeto.gamelog.composite.CategoryInitializer;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    @Autowired
    private final CategoryInitializer categoryInitializer;

    @GetMapping()
    @ResponseBody
    public AbstractCategory getCategories() {
        return categoryInitializer.getRootCategory();
    }

    @GetMapping("/allNames")
    @ResponseBody
    public String getAllNamesCategories(){
        return categoryInitializer.getRootCategory().getNames();
    }

    @GetMapping("/allGames")
    @ResponseBody
    public List<String> getAllgamesCategories(){
        return categoryInitializer.getRootCategory().getGames();
    }
}
