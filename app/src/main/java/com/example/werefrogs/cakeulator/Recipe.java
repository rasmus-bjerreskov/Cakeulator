package com.example.werefrogs.cakeulator;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private ArrayList<Ingredient> ingredients;

    /**
     * creates empty recipe with only a name
     *
     * @param name
     */
    public Recipe(String name) {
        this.name = name;
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
     * returns the arrayList object part of the recipe
     *
     * @return
     */
    public ArrayList<Ingredient> getIngredients() {
        return this.ingredients;
    }

    @Override
    public String toString() {
        return this.name;
    }
}