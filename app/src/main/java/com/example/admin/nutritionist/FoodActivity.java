package com.example.admin.nutritionist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class FoodActivity extends AppCompatActivity {
    private FoodGetImp mFatSecretGet=new FoodGetImp();
    private FoodSearchImp foodSearchImp=new FoodSearchImp();
    private TextView fid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food);

        String id  = getIntent().getExtras().getString("food_id");
        fid = findViewById(R.id.textView);
       // fid.setText(id);
        getFood(Long.valueOf(id));
    }


    private void getFood(final long id) {
        new AsyncTask<String, String, String>() {
            String data;
            @Override
            protected String doInBackground(String... arg0) {
                JSONObject foodGet = mFatSecretGet.getFood(id);
                try {
                    if (foodGet != null) {
                           JSONObject servings = foodGet.getJSONObject("servings");
                            JSONArray serving = servings.getJSONArray("serving");
                            JSONObject s = serving.optJSONObject(0);
                            String food_name = foodGet.getString("food_name");
                            String calories = s.getString("calories");

                            String carbohydrate = s.getString("carbohydrate");
                            String protein = s.getString("protein");
                             String fat = s.getString("fat");
                            String serving_description = s.getString("serving_description");
                            Log.e("serving_description", serving_description);
                            /**
                             * Displays results in the LogCat
                             */
                            Log.e("food_name", food_name);
                       Log.e("calories", calories);
                       Log.e("carbohydrate", carbohydrate);
                        Log.e("protein", protein);
                        Log.e("fat", fat);
                              data="FoodName:"+food_name+"\n\nCalories:"+calories+
                                      "\nCarbbohydrates:"+carbohydrate+
                                      "\nProtein:"+protein+
                                      "\nFat:"+fat+
                                      "\nServing:"+serving_description+"\n";
                            //data = food_name;
                        }

                } catch (JSONException exception) {
                    return "Error";
                }
                return "";
            }

            @Override
            protected void onPostExecute(String result) {
                super.onPostExecute(result);
                if (result.equals("Error"))
                    Toast.makeText(getApplicationContext(), "No Items Containing Your Search", Toast.LENGTH_SHORT).show();
                fid.setText(data);
            }
        }.execute();
    }
}
