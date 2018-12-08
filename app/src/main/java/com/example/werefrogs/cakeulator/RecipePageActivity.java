package com.example.werefrogs.cakeulator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.werefrogs.cakeulator.Ingredient.formatter;
import java.util.ArrayList;

import static com.example.werefrogs.cakeulator.RecipeLibraryActivity.EXTRA;

public class RecipePageActivity extends AppCompatActivity {
    private TextView recipeName, recipeView;
    private EditText servings;
    private Recipe recipeToPrint;
    private Recipe recipeToFavourite;
    CheckBox checkBox;
    public String FavouriteName;
    public int FavouriteServings;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);

        recipeName = findViewById(R.id.tv_recipeName);
        recipeView = findViewById(R.id.tv_recipe);
        servings = findViewById(R.id.et_amount);
        checkBox = (CheckBox) findViewById(R.id.checkBox);

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

    /**
     *
     * Method returns iCheckBox=false if the favourites button is unchecked
     * Method returns iCheckBox=true if the favourites button is checked
     * @return
     */
    public void CheckFavourites() {

        if (checkBox.isChecked()) {
             recipeToPrint.setFavourite(true);
        }
        else {
            recipeToPrint.setFavourite(false);
        }
    }

    /**
     * Adds the recipe to the Favourites ArrayList when the CheckBox is ticked
     * @param view
     */

    public void onClickFavourites(View view) {
        CheckFavourites();
        setFavouriteName();
        setFavouriteServings();
        if (recipeToPrint.getFavourite(true)) {
            Recipe recipeFavourite = new Recipe();

            recipeFavourite.setName(FavouriteName);
            recipeFavourite.setServings(FavouriteServings);
            recipeFavourite.addIngredient(new Ingredient(1, "Slice", "rye bread"));
            FavouriteList.getInstance().addFavouriteRecipe(recipeFavourite);
        }else{
            //FavouriteList.getInstance().removeFavouriteRecipe(); -- Remove function. Indexing of Favourite recipes needed.
        }
    }

    /*
    public ArrayList FavouriteIngredients;
    public void setFavouriteIngredients() {
        this.FavouriteIngredients = recipeToPrint.getIngredients();
    }
    */

    //favourite setters
    public void setFavouriteName() {
        this.FavouriteName = recipeToPrint.getName();
    }
    public void setFavouriteServings() {
        this.FavouriteServings = recipeToPrint.getServings();
    }

    //favourite setters end

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
