package com.example.werefrogs.cakeulator;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RecipeListAdapter extends ArrayAdapter<Recipe> {

    private Context mContext;
    int mResource;

    public RecipeListAdapter(Context context, int resource, ArrayList<Recipe> objects) {
        super(context, resource, objects);
        mContext = context;
        mResource = resource;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);

        String recipeName = getItem(position).toString();

        TextView tvRecipe = convertView.findViewById(android.R.id.text1); //the TextView used in simple_list_item_1
        tvRecipe.setText(recipeName);

        return convertView;
    }
}
