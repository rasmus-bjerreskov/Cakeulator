package com.example.werefrogs.cakeulator;

import java.util.ArrayList;

class FavouriteList {
    private static final FavouriteList ourInstance = new FavouriteList();
    private ArrayList<FavouriteList> favouriteList;

    static FavouriteList getInstance() {
        return ourInstance;
    }

    private FavouriteList() {
        this.favouriteList = new ArrayList<FavouriteList>();
    }
    /**
     * returns array of favourites
     * @return
     */
    public ArrayList<FavouriteList> getFavouriteList() {
        return this.favouriteList;
    }

}
