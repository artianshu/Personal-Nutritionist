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

public class Foodsearch extends AppCompatActivity {
    ListView listView;
    private ListAdapter listAdapter;
    private List<Listitem> mitem;
    Button click;
    private FoodSearchImp foodSearchImp;

    public static String urldata = FoodSearchImp.url2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foodsearch);
        listView=findViewById(R.id.lv);
        mitem=new ArrayList<>();
        click = findViewById(R.id.button5);
        listAdapter=new ListAdapter(this,mitem);
        listView.setAdapter(listAdapter);
        click.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listAdapter.clear();
                foodSearchImp = new FoodSearchImp();
                EditText editText = findViewById(R.id.editText);

                String query = editText.getText().toString();
                searchFood2(query);
                listAdapter.notifyDataSetChanged();
            }


        });

        getFood();

    }


    public void searchFood(View view) {

    }
    public void getFood(){
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 // Should to be refactored
                    String fid=mitem.get(position).getID();
                    Log.e("lid",(mitem.get(position).getID()));
                Intent intent=new Intent(getApplicationContext(),FoodActivity.class);
                intent.putExtra("food_id",fid);
                startActivity(intent);
            }
        });
    }

    private void searchFood2(final String query) {

        new AsyncTask<String, String, String>() {
            String d;
            @Override
            protected String doInBackground(String... arg0) {
                JSONObject food = foodSearchImp.searchFood(query);
                JSONArray FOODS_ARRAY;
                try {

                    if (food != null) {
                        FOODS_ARRAY = food.getJSONArray("food");
                        if (FOODS_ARRAY != null) {
                            for (int i = 0; i < FOODS_ARRAY.length(); i++) {
                                // Log.d("URL",FatSecretSearch.url2);
                                JSONObject food_items = FOODS_ARRAY.optJSONObject(i);
                                String food_name = food_items.getString("food_name");
                                String food_id = food_items.getString("food_id");
                                Log.e("URL", food_name);
                                Listitem item=new Listitem();
                                item.setFood_id(food_id);
                                item.setFood_name(food_name);
                                mitem.add(item);
                            }


                           // listAdapter=new ListAdapter(getApplicationContext());
                            //listView.setAdapter(listAdapter);

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
                    Toast.makeText(getApplicationContext(), "Result not defined", Toast.LENGTH_SHORT).show();
                    //  Foodsearch.data.setText(this.d);
                }
                listAdapter.notifyDataSetChanged();
            }
        }.execute();

    }
}



