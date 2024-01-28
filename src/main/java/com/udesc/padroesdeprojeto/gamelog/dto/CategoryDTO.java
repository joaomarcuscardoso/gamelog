package com.udesc.padroesdeprojeto.gamelog.dto;

import lombok.Data;

import java.util.List;

@Data
public class CategoryDTO {

    private String name;
    private List<CategoryDTO> categories;

    public CategoryDTO(String name, List<CategoryDTO> categories) {
        this.name = name;
        this.categories = categories;
    }

}
