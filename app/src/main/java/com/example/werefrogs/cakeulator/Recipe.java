package com.example.werefrogs.cakeulator;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private int servings;
    private ArrayList<Ingredient> ingredients;

    public Recipe() {
        this.name = "Unnamed recipe";
        this.servings = getServings();
        this.ingredients = new ArrayList<Ingredient>();
    }

    /**
     * adds an ingredient object to the array
     *
     * @param ingredient
     */
    public void addIngredient(Ingredient ingredient) {
        this.ingredients.add(ingredient);
    }

    /**
     * sets name to Unnamed recipe if no string given
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    public void setServings(int servings) {
        this.servings = servings;
    }

    /**
     * returns the arrayList object part of the recipe
     *
     * @return
     */
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    /**
     * @return
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return
     */
    public int getServings() {
        return this.servings;
    }

    @Override
    public String toString() {
        return this.name;
    }
}
