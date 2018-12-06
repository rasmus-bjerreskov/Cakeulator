package com.example.werefrogs.cakeulator;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    EditText newName, newAmount, newUnit, newItem, newServing;
    Recipe newRecipe;
    Ingredient newIngredient;
    ListView lvIngredients;
    ArrayAdapter<Ingredient> adapterIngredient;
    ArrayList<Ingredient> arrayIngredient = new ArrayList<Ingredient>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newName = findViewById(R.id.et_addName);
        newAmount = findViewById(R.id.et_addAmount);
        newUnit = findViewById(R.id.et_addUnit);
        newItem = findViewById(R.id.et_addItem);
        lvIngredients = findViewById(R.id.lv_ingredients);
        newServing = findViewById(R.id.et_amount);

        newRecipe = new Recipe();
        adapterIngredient = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, arrayIngredient);
    }

    public void buttonPressed_toLibrary(View v) { //Switches activities (Main to Recipe Library)
        Intent intent = new Intent(this, RecipeLibraryActivity.class);
        startActivity(intent);
    }

    public void buttonPressed_addToLibrary(View v) { //Adds given recipe to the library
        //Makes a toast (short popup text) whenever the "Add to Library" button is pressed
        Context context = getApplicationContext();
        CharSequence text = "Recipe added!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();

        //User inputted recipe name
        String nameToAdd = newName.getText().toString();
        Log.d("setName", nameToAdd);
        newRecipe.setName(nameToAdd);
        Log.d("getName", newRecipe.getName());
        RecipeList.getInstance().addRecipe(newRecipe);

        //User inputted servings
        int servingToAdd = 1;
        if (!newServing.getText().toString().isEmpty()) {
            //servings defaults to 1
            servingToAdd = Integer.parseInt(newServing.getText().toString());
        }
        newRecipe.setServings(servingToAdd);
        recipeReset();
    }

    public void buttonPressed_addIngredient(View v) { //Adds ingredient to the List View
        double amountToAdd = Double.parseDouble(newAmount.getText().toString());
        String unitToAdd = newUnit.getText().toString();
        String itemToAdd = newItem.getText().toString();

        newIngredient = new Ingredient(amountToAdd, unitToAdd, itemToAdd);
        newRecipe.addIngredient(newIngredient);

        arrayIngredient.add(newIngredient);
        lvIngredients.setAdapter(adapterIngredient);
        ingredientReset();
    }

    public void ingredientReset() { //Resets the ingredient input fields to blank
        newAmount.setText(null);
        newUnit.setText(null);
        newItem.setText(null);
        newIngredient = null;
    }

    public void recipeReset() { //Resets the recipe input fields and the list to blank
        newName.setText(null);
        newServing.setText(null);
        newRecipe = null;

        newRecipe = new Recipe(); //recycles newRecipe instance

        //clears the ingredients array and creates a new adapter
        arrayIngredient.clear();
        adapterIngredient = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, arrayIngredient);
        lvIngredients.setAdapter(adapterIngredient);
    }
}
