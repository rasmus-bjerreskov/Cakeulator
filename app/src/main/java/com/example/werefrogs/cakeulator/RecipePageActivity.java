package com.example.werefrogs.cakeulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.werefrogs.cakeulator.RecipeLibraryActivity.EXTRA;

public class RecipePageActivity extends AppCompatActivity {
    private TextView recipeName, recipeView;
    private EditText servings;
    private Recipe recipeToPrint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(EXTRA, 0);

        recipeName = findViewById(R.id.tv_recipeName);
        recipeView = findViewById(R.id.tv_recipe);
        servings = findViewById(R.id.et_amount);

        recipeToPrint = RecipeList.getInstance().getRecipe(i);
        String recipePrint = "";

        for (Ingredient j : recipeToPrint.getIngredients()) {
            recipePrint += j.getAmount() + j.getStrings() + "\n";
        }

        recipeName.setText(RecipeList.getInstance().getRecipe(i).getName());
        recipeView.setText(recipePrint);


    }
}
