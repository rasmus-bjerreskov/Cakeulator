package com.example.werefrogs.cakeulator;

import android.content.Intent;
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

public class RecipeLibraryActivity extends AppCompatActivity {
    public static final String TAG = "Debug_key";
    public static final String EXTRA = "com.example.wereFrogs.Cakeulator";
    EditText searchLibrary;
    ArrayAdapter<Recipe> adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_library);

        searchLibrary = (EditText) findViewById(R.id.et_search);
        ListView lv = findViewById(R.id.lv_Recipes);
/**
 * creates an adapter for the Recipe array
 */
        adapter = new ArrayAdapter<Recipe>
                (this, android.R.layout.simple_list_item_1,
                        RecipeList.getInstance().getRecipeList());
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            /**
             * Clicking recipe in list leads to its individual page
             * @param adapterView
             * @param view
             * @param i
             * @param l
             */
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Log.d(TAG, "onItemClick(" + i + ")");
                Intent nextActivity = new Intent(RecipeLibraryActivity.this, RecipePageActivity.class);
                nextActivity.putExtra(EXTRA, i);
                startActivity(nextActivity);
            }
        });
        searchLibrary.addTextChangedListener(new TextWatcher() {
            /**
             * The program listens and responds to changes in the EditText field
             * @param cs
             * @param arg1
             * @param arg2
             * @param arg3
             */

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
            recipe list view
             */

        });
    }
}


