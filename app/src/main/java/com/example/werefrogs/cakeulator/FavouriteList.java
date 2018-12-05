package com.example.werefrogs.cakeulator;

import java.util.ArrayList;

class FavouriteList {
    private static final FavouriteList ourInstance = new FavouriteList();
    private ArrayList<Recipe> favouriteList;

    static FavouriteList getInstance() {
        return ourInstance;
    }

    private FavouriteList() {
        this.favouriteList = new ArrayList<Recipe>();
    }
    /**
     * returns array of favourites
     * @return
     */
    public ArrayList<Recipe> getFavouriteList() {
        return this.favouriteList;
    }
    public void addFavouriteRecipe(Recipe rfav) {
        this.favouriteList.add(rfav);
    }
    /**
     * returns array at index
     * @param i
     * @return
     */
    public Recipe getRecipe(int i) {
        return favouriteList.get(i);
    }
}
