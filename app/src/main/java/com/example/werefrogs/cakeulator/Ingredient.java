package com.example.werefrogs.cakeulator;

public class Ingredient {
    private int amount;
    private String unit;
    private String ingredient;

    /**
     *
     * @param amount
     * @param unit
     * @param ingredient
     */
    public Ingredient(int amount, String unit, String ingredient) {
        this.amount = amount;
        this.unit = unit;
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

    public String toString() {
        return "" + this.amount + " " + this.unit + " " + this.ingredient; //problem: two spaces if no unit ("2  eggs")
    }
}
