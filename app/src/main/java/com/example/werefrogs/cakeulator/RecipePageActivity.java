package com.example.werefrogs.cakeulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.werefrogs.cakeulator.RecipeLibraryActivity.EXTRA;

public class RecipePageActivity extends AppCompatActivity {
    private TextView recipeName, recipeView;
    private EditText servings;
    private Recipe recipeToPrint;
    private CheckBox checkBox_Favourites;
    private boolean checkBoxOnClick;


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

    public void addListenerOnButton() {
    checkBox_Favourites = (CheckBox) findViewById(R.id.checkBox);
    checkBoxOnClick= ((CheckBox) findViewById(R.id.checkBox)).isChecked();
    }


    public void updateUI() {
        String recipePrint = "";
        int multiplier;

        if (servings.getText().toString().equals(null) || servings.getText().toString().equals("")) {
            multiplier = 1; //overrides empty inputs, activity crashes without this
        } else {
            multiplier = Integer.parseInt(servings.getText().toString()) / recipeToPrint.getServings(); // given/default servings
        }

        for (Ingredient j : recipeToPrint.getIngredients()) {
            int newServings = j.getAmount() * multiplier;
            recipePrint += newServings + j.getStrings() + "\n";
        }

        recipeView.setText(recipePrint);
    }
}
