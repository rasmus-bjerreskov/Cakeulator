package com.example.werefrogs.cakeulator;

import java.util.ArrayList;

class RecipeList {
    private static final RecipeList ourInstance = new RecipeList();
    private ArrayList<Recipe> recipes;

    public static RecipeList getInstance() {
        return ourInstance;
    }

    private RecipeList() {
        this.recipes = new ArrayList<Recipe>();

    }
    /**
     * returns array of recipes
     * @return
     */
    public ArrayList<Recipe> getRecipeList() {
        return this.recipes;
    }

    public void setRecipes(ArrayList<Recipe> recipes) {
        this.recipes = recipes;
    }

    /**
     * add recipe array
     * @param r
     */
    public void addRecipe(Recipe r) {
        this.recipes.add(r);
    }

    /**
     * returns array at index
     * @param i
     * @return
     */
    public Recipe getRecipe(int i) {
        return recipes.get(i);
    }
}
