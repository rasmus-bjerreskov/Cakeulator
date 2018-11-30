package com.example.werefrogs.cakeulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
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
        recipeToPrint = RecipeList.getInstance().getRecipe(i);

        recipeName = findViewById(R.id.tv_recipeName);
        recipeView = findViewById(R.id.tv_recipe);
        servings = (EditText) findViewById(R.id.et_amount);



        recipeName.setText(RecipeList.getInstance().getRecipe(i).getName());

        servings.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    updateUI();
                }
            }
        });
        updateUI();



    }

    public void updateUI() {
        int multiplier = Integer.parseInt(servings.getText().toString()) / recipeToPrint.getServings(); // given/default servings

        String recipePrint = "";

        for (Ingredient j : recipeToPrint.getIngredients()) {
            int newServings = j.getAmount() * multiplier;
            recipePrint += newServings + j.getStrings() + "\n";
        }
        recipeView.setText(recipePrint);

    }
}
