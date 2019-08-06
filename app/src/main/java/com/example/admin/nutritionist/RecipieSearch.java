package com.example.admin.nutritionist;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RecipieSearch extends AppCompatActivity {
    ListView listView;
    private RecipeAdapter recipeAdapter;
    private List<Recipie_row> mitem;
    Button click;
    private RecipieSaerchImp ri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipie_search);
        listView=findViewById(R.id.lv2);
        mitem=new ArrayList<>();
        click = findViewById(R.id.button6);
        recipeAdapter=new RecipeAdapter(this,mitem);
        listView.setAdapter(recipeAdapter);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                recipeAdapter.clear();
                ri = new RecipieSaerchImp();
                EditText editText = findViewById(R.id.editText2);

                String query = editText.getText().toString();
                searchRecipie(query);
                recipeAdapter.notifyDataSetChanged();
            }


        });

       getRecipe();
    }
    public void getRecipe(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Should to be refactored
                String fid=mitem.get(position).getRecipe_id();
                Log.e("lid",(mitem.get(position).getRecipe_id()));
                Intent intent=new Intent(getApplicationContext(),Recipe.class);
                intent.putExtra("food_id",fid);
                startActivity(intent);
            }
        });
    }

    private void searchRecipie(final String query) {

        new AsyncTask<String, String, String>() {
            String d;
            @Override
            protected String doInBackground(String... arg0) {
                JSONObject recipe = ri.searchRecipie(query);
                JSONArray RECIPE_ARRAY;
                try {

                    if (recipe != null) {
                        RECIPE_ARRAY = recipe.getJSONArray("recipe");
                        if (RECIPE_ARRAY != null) {
                            for (int i = 0; i < RECIPE_ARRAY.length(); i++) {
                                // Log.d("URL",FatSecretSearch.url2);
                                JSONObject recipe_items = RECIPE_ARRAY.optJSONObject(i);
                                String recipe_name = recipe_items.getString("recipe_name");
                                String recipe_id = recipe_items.getString("recipe_id");
                                Log.e("URL", recipe_name);
                                Recipie_row item=new Recipie_row();
                                item.setRecipe_id(recipe_id);
                                item.setRecipe_name(recipe_name);
                                mitem.add(item);
                            }

                        }
                    }
                } catch (JSONException exception) {
                    return "error";
                }
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result.equals("Error")) {
                    Toast.makeText(getApplicationContext(), "No Items Containing Your Search", Toast.LENGTH_SHORT).show();
                    //  Foodsearch.data.setText(this.d);
                }
                recipeAdapter.notifyDataSetChanged();
            }
        }.execute();

    }
}
