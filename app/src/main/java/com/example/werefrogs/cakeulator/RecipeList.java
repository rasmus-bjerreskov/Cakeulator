package com.example.werefrogs.cakeulator;

import java.util.ArrayList;

class RecipeList {
    private static final RecipeList ourInstance = new RecipeList();
    private ArrayList<Recipe> recipes;

    static RecipeList getInstance() {
        return ourInstance;
    }

    private RecipeList() {
        this.recipes = new ArrayList<>();

    }
    public ArrayList<Recipe> getRecipeList() {
        return this.recipes;
    }

    public Recipe getRecipe(int i) {
        return recipes.get(i);
    }
}
