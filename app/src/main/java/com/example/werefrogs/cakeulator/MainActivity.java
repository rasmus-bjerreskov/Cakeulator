package com.example.werefrogs.cakeulator;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText newName, newAmount, newUnit, newItem, newServing;
    private Recipe newRecipe;
    private Ingredient newIngredient;
    private ListView lvIngredients;
    private ArrayAdapter<Ingredient> adapterIngredient;
    private ArrayList<Ingredient> arrayIngredient = new ArrayList<Ingredient>();

    private SharedPreferences recipePref;
    public static final String PREF = "recipePref";
    public static final String SAVE_RECIPES = "saveRecipe_key";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        newName = findViewById(R.id.et_addName);
        newAmount = findViewById(R.id.et_addAmount);
        newUnit = findViewById(R.id.et_addUnit);
        newItem = findViewById(R.id.et_addItem);
        lvIngredients = findViewById(R.id.lv_ingredients);
        newServing = findViewById(R.id.et_addServings);

        newRecipe = new Recipe();
        adapterIngredient = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, arrayIngredient);
        lvIngredients.setAdapter(adapterIngredient);
        lvIngredients.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            /**
             *
             * @param parent
             * @param view
             * @param position
             * @param id
             * @return
             */

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(MainActivity.this);
                dialogBuilder.setMessage("Delete?"); //Builds the pop up dialog with the message "Delete?"
                dialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() { //What the "YES" button does
                    public void onClick(DialogInterface dialog, int which) {
                        arrayIngredient.remove(position); //removes the selected item from the list
                        adapterIngredient.notifyDataSetChanged();
                        Toast.makeText(MainActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() { //What the "NO" button does
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss(); //dismisses the dialog box and returns to the previous activity
                    }
                });
                dialogBuilder.show(); //https://stackoverflow.com/questions/38808006/delete-item-from-listview-with-dialog-android, user israelbenh
                return true;
            }

        });
        recipePref = getSharedPreferences(PREF, MODE_PRIVATE);
        loadRecipes();

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

    //Adds given recipe to the library
    public void buttonPressed_addToLibrary(View v) {
        toastSaved();

        //Sets servings
        int servingToAdd = 1;
        if (!newServing.getText().toString().isEmpty()) {
            //servings defaults to 1
            servingToAdd = Integer.parseInt(newServing.getText().toString());
        }
        newRecipe.setServings(servingToAdd);

        //Sets name and adds recipe to master (singleton) list
        if (!newName.getText().toString().isEmpty()) { //does not set name to ""
            String nameToAdd = newName.getText().toString();
            Log.d("setName", nameToAdd);
            newRecipe.setName(nameToAdd);
        }

        Log.d("getName", newRecipe.getName());

        //checks if ingredients have been added, then saves and resets the template
        if (newRecipe.getIngredients().isEmpty()) {
            toastNoIngredients();
        } else {
            RecipeList.getInstance().addRecipe(newRecipe);
            recipeReset();
        }
    }

    public void buttonPressed_toLibrary(View v) { //Switches activities (Main to Recipe Library)
        Intent intent = new Intent(this, RecipeLibraryActivity.class);
        startActivity(intent);
    }

    public void ingredientReset() { //Resets the ingredient input fields to blank
        newAmount.setText(null);
        newUnit.setText(null);
        newItem.setText(null);
        newIngredient = null;
    }

    public void recipeReset() { //Resets the recipe name input field and the list to blank
        newName.setText(null);
        newServing.setText(null);
        newRecipe = null;

        newRecipe = new Recipe(); //recycles newRecipe instance

        //clears the ingredients array and creates a new adapter
        arrayIngredient.clear();
        adapterIngredient = new ArrayAdapter<Ingredient>(this, android.R.layout.simple_list_item_1, arrayIngredient);
        lvIngredients.setAdapter(adapterIngredient);
    }

    public void saveRecipes() { //Saves the created recipes
        SharedPreferences.Editor prefsEditor = recipePref.edit(); //creates a new SharedPreferences
        Gson gson = new Gson();
        String jsonString = gson.toJson(RecipeList.getInstance().getRecipeList()); //this will be the String that SharedPreferences can save
        Log.d("saved", jsonString);
        prefsEditor.putString(SAVE_RECIPES, jsonString);
        prefsEditor.commit();
    }

    public void loadRecipes() { //loads recipes from SharedPreferences
        //https://medium.com/@evancheese1/shared-preferences-saving-arraylists-and-more-with-json-and-gson-java-5d899c8b0235
        Type listType = new TypeToken<ArrayList<Recipe>>() { //Creates a token for Json to interpret
        }.getType();

        /*
        retrieves saved data
        https://www.tutorialspoint.com/gson/gson_serialization_examples.htm
         */
        Gson gson = new Gson();
        String json = recipePref.getString(SAVE_RECIPES, "[]");
        RecipeList.getInstance().setRecipes((ArrayList<Recipe>) gson.fromJson(json, listType));
    }

    //toasts
    public void toastSaved() {
        //Makes a toast (short popup text) whenever the "Add to Library" button is pressed
        Context context = getApplicationContext();
        CharSequence text = "Recipe added!";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void toastNoIngredients() {
        Context context = getApplicationContext();
        CharSequence text = "Add some ingredients first";
        int duration = Toast.LENGTH_LONG;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }

    public void onStop() {
        super.onStop();
        saveRecipes();
    }

    public void onPause() {
        super.onPause();
        saveRecipes();
    }

    public void onRestart() {
        super.onRestart();
        saveRecipes();
    }
    public void onResume() {
        super.onResume();
        saveRecipes();
    }
}


