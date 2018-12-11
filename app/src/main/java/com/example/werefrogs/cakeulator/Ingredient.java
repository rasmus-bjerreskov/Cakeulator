package com.example.werefrogs.cakeulator;

import java.text.DecimalFormat;

public class Ingredient {
    private double amount;
    private String unit;
    private String item;

    //from https://stackoverflow.com/a/7678994
    public static DecimalFormat formatter = new DecimalFormat(); //public static because we only need one instance for all classes

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

    /**
     * returns the amount for the ingredient
     * @return
     */
    public double getAmount() {
        return amount;
    }

    /**
     * returns the the unit of the ingredient
     * @return
     */
    public String getUnit() {
        return unit;
    }

    /**
     * sets the unit used
     * @param unit
     */
    public void setUnit(String unit) {
        this.unit = unit;
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
        return formatter.format(this.amount) + getStrings();
    }
}
