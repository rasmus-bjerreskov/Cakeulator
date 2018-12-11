package com.example.werefrogs.cakeulator;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;


public class RecipeLibraryActivity extends AppCompatActivity {
    public static final String TAG = "Debug_key";
    public static final String EXTRA = "com.example.wereFrogs.Cakeulator";
    private EditText searchLibrary;
    private ArrayAdapter<Recipe> adapter;
    private SharedPreferences recipePref;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_library);

        searchLibrary = findViewById(R.id.et_search);
        ListView lv = findViewById(R.id.lv_Recipes);
        recipePref = getSharedPreferences(MainActivity.PREF, MODE_PRIVATE);
        adapter = new ArrayAdapter<Recipe>
                (this, android.R.layout.simple_list_item_1,
                        RecipeList.getInstance().getRecipeList());

        //Adapter for the Recipe Array list made
        lv.setAdapter(adapter);

        //Clicking recipe in list leads to its individual page
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick(" + i + ")");

                Recipe recipeClicked = (Recipe) adapterView.getItemAtPosition(i); //Creates an object that the adapter sees at position i
                //int msg = view.getId();
                Log.d(TAG, adapterView.getItemAtPosition(i).toString());
                Intent nextActivity = new Intent(RecipeLibraryActivity.this, RecipePageActivity.class);
                //Recipe recipeClicked = RecipeList.getInstance().getRecipe(i);
                int j = RecipeList.getInstance().getRecipeList().indexOf(recipeClicked);
                nextActivity.putExtra(EXTRA, j);
                startActivity(nextActivity);
            }
        });

        //long click deletes recipe, opens dialog box to confirm
        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            /**
             * @param parent
             * @param view
             * @param position
             * @param id
             * @return
             */

            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(RecipeLibraryActivity.this); //See MainActivity
                dialogBuilder.setMessage("Delete?");
                dialogBuilder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        RecipeList.getInstance().getRecipeList().remove(position);
                        adapter.notifyDataSetChanged();
                        saveRecipes();


                        Toast.makeText(RecipeLibraryActivity.this, "Item Deleted", Toast.LENGTH_SHORT).show();
                    }
                });
                dialogBuilder.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

                dialogBuilder.show();
                return true;

            }

        });

        searchLibrary.addTextChangedListener(new TextWatcher() {

            //The program listens and responds to changes in the EditText field
            @Override
            public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                RecipeLibraryActivity.this.adapter.getFilter().filter(cs);
            }

            @Override
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
            }

            @Override
            public void afterTextChanged(Editable arg0) {
            }
            /* makes sure that the sequence of characters in the search field is the same as in the
            recipe list view.
            Tutorial and source code from http://aboutyusata.blogspot.com/2015/07/android-adding-search-functionality-to.html
             */
        });
    }

    /**
     * Saves list of recipes by converting to Json string
     */
    public void saveRecipes() { //See MainActivity
        SharedPreferences.Editor prefsEditor = recipePref.edit();
        Gson gson = new Gson();
        String jsonString = gson.toJson(RecipeList.getInstance().getRecipeList());
        Log.d("saved", jsonString);
        prefsEditor.putString(MainActivity.SAVE_RECIPES, jsonString);
        prefsEditor.commit();
    }

    public void onStop() {
        super.onStop();
        saveRecipes();
    }

    public void onResume() {
        super.onResume();
        saveRecipes();
    }


}





