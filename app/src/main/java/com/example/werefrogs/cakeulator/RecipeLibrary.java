package com.example.werefrogs.cakeulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class RecipeLibrary extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_library);

        Recipe r1 = new Recipe("Smørrebrød");
        r1.addIngredient(new Ingredient(1, "slice", "rye bread"));

        RecipeList.getInstance().addRecipe(r1);

        ListView lv = findViewById(R.id.lv_Recipes);
        lv.setAdapter(new ArrayAdapter<Recipe>
                (this, android.R.layout.simple_list_item_1, RecipeList.getInstance().getRecipeList()));
    }
}
