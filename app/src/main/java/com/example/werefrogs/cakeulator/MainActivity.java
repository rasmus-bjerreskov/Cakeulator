package com.example.werefrogs.cakeulator;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Recipe r1 = new Recipe("Smørrebrød");
        r1.addIngredient(new Ingredient(1, "slice", "rye bread"));
        r1.addIngredient(new Ingredient(2, "slice", "roast beef"));
        r1.addIngredient(new Ingredient(4, "slice", "cucumber"));
        RecipeList.getInstance().addRecipe(r1);

        Intent intent = new Intent(this, RecipeLibraryActivity.class);
        startActivity(intent);


    }
}
