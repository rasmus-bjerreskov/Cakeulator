package com.example.werefrogs.cakeulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.werefrogs.cakeulator.Ingredient.formatter;

import static com.example.werefrogs.cakeulator.RecipeLibraryActivity.EXTRA;

public class RecipePageActivity extends AppCompatActivity {
    private TextView recipeName, recipeView;
    private EditText servings;
    private Recipe recipeToPrint;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        recipeName = findViewById(R.id.tv_recipeName);
        recipeView = findViewById(R.id.tv_recipe);
        servings = findViewById(R.id.et_amount);

        Bundle b = getIntent().getExtras();
        int i = b.getInt(EXTRA, 0);


        servings.setHint(Integer.toString(RecipeList.getInstance().getRecipe(i).getServings()));
        recipeToPrint = RecipeList.getInstance().getRecipe(i);
        recipeName.setText(RecipeList.getInstance().getRecipe(i).getName());

        /*
        updates the UI when clicking outside of the EditText field
        https://stackoverflow.com/a/47729775
        see activity_recipe_page.xml
         */
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
        String recipePrint = "";
        double multiplier;

        if (servings.getText().toString().equals(null) || servings.getText().toString().equals("")) {
            multiplier = 1; //overrides empty inputs, activity crashes without this
        } else {
            multiplier = Double.parseDouble(servings.getText().toString())
                    / recipeToPrint.getServings(); // given/default servings
        }

        for (Ingredient j : recipeToPrint.getIngredients()) {
            double newServings = j.getAmount() * multiplier;
            recipePrint += formatter.format(newServings) + j.getStrings() + "\n";
        }
        recipeView.setText(recipePrint);
    }
}
