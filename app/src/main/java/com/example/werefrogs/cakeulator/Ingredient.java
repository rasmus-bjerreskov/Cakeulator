package com.example.werefrogs.cakeulator;

public class Ingredient {
    private int amount;
    private String unit;
    private String ingredient;

    /**
     * @param amount
     * @param unit
     * @param ingredient
     */
    public Ingredient(int amount, String unit, String ingredient) {
        this.amount = amount;
        this.unit = unit;
        this.ingredient = ingredient;
    }

    public Ingredient(int amount, String ingredient) {
        this.amount = amount;
        this.unit = "";
        this.ingredient = ingredient;
    }

    /**
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * @return
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @return
     */
    public String getIngredient() {
        return ingredient;
    }

    /**
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @param ingredient
     */
    public void setIngredient(String ingredient) {
        this.ingredient = ingredient;
    }

    /**
     * returns unit and ingredient name
     *
     * @return
     */
    public String getStrings() {
        if (this.unit.isEmpty()) {
            return " " + this.ingredient;
        } else {
            return " " + this.unit + " " + this.ingredient;
        }
    }

    public String toString() {
        if (this.unit.equals("")) {
            return Integer.toString(this.amount) + " " + this.ingredient;
        } else {
            return Integer.toString(this.amount) + " " + this.unit + " " + this.ingredient;
        }
    }
}
