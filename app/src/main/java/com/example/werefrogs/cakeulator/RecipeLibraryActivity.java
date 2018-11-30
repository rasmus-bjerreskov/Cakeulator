package com.example.werefrogs.cakeulator;

import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.ToggleButton;

import java.util.ArrayList;

public class RecipeLibraryActivity extends AppCompatActivity {
    public static final String TAG = "Debug_key";
    public static final String EXTRA = "com.example.wereFrogs.Cakeulator";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_library);



        ListView lv = findViewById(R.id.lv_Recipes);
        lv.setAdapter(new ArrayAdapter<Recipe>
                (this, android.R.layout.simple_list_item_1,
                        RecipeList.getInstance().getRecipeList()));

        Recipe r1 = new Recipe("Smørrebrød");
        r1.addIngredient(new Ingredient(1, "slice", "rye bread"));

        RecipeList.getInstance().addRecipe(r1);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Clicking recipe in list leads to it's individual page
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(RecipeLibraryActivity.this, RecipePage.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }

        });
    }
}
