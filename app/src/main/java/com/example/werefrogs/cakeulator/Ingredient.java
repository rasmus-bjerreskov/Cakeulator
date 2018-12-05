package com.example.werefrogs.cakeulator;

public class Ingredient {
    private double amount;
    private String unit;
    private String item;

    /**
     * @param amount
     * @param unit
     * @param item
     */
    public Ingredient(double amount, String unit, String item) {
        this.amount = amount;
        this.unit = unit;
        this.item = item;
    }

    public Ingredient(double amount, String item) {
        this.amount = amount;
        this.unit = "";
        this.item = item;
    }

    /**
     * @return
     */
    public double getAmount() {
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
    public String getItem() {
        return item;
    }

    /**
     * @param amount
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }


    /**
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @param item
     */
    public void setItem(String item) {
        this.item = item;
    }

    /**
     * returns unit and item name
     *
     * @return
     */
    public String getStrings() {
        if (this.unit.isEmpty()) {
            return " " + this.item;
        } else {
            return " " + this.unit + " " + this.item;
        }
    }

    public String toString() {
        return this.amount + getStrings();
    }
}
