package com.example.admin.nutritionist;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URI;

public class Recipe extends AppCompatActivity {
    private RecipeGetImp mFatSecretGet=new RecipeGetImp();
    private TextView fid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);
              String id  = getIntent().getExtras().getString("food_id");
        fid = findViewById(R.id.textView2);
        // fid.setText(id);
        getRecipe(Long.valueOf(id));
    }
    private URI imageUri;
    private ImageView imageView;
    private int preferredWidth = 80;
    private int preferredHeight = 80;
    private void getRecipe(final long id) {
        new AsyncTask<String, String, String>() {
            String data;
            String imageurl;
            @Override
            protected String doInBackground(String... arg0) {
                JSONObject rcGet = mFatSecretGet.getRecipe(id);
                try {
                    if (rcGet != null) {
                      //  JSONArray serving = servings.getJSONArray("serving");
                      //  JSONObject s = serving.optJSONObject(0);
                        String recipe_name = rcGet.getString("recipe_name");
                    String url = rcGet.getString("recipe_url");

                    //    String carbohydrate = s.getString("carbohydrate");
                      //  String protein = s.getString("protein");
                      //  String fat = s.getString("fat");
                      String recipe_description = rcGet.getString("recipe_description");
                       Log.e("recipe_description", recipe_description);
                        /**
                         * Displays results in the LogCat
                         */
                        Log.e("recipe_name", recipe_name);
                      Log.e("url", url);
                     //   Log.e("carbohydrate", carbohydrate);
                      //  Log.e("protein", protein);
                       // Log.e("fat", fat);
                        /*data="FoodName:"+food_name+"\n Calories:"+calories+
                                "\n Carbbohydrates:"+carbohydrate+
                                "\n Protein:"+protein+
                                "\n Fat:"+fat+
                                "\n Serving:"+serving_description+"\n";*/
                        data = recipe_name+"\n"+recipe_description+"\n"+url;
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
                fid.setMovementMethod(LinkMovementMethod.getInstance());
                fid.setText(data);
               // ImageLoader.getInstance().displayImage(imageurl, imageView);
              // imageView.setImageURI(Uri.parse(imageurl));
            }
        }.execute();
    }
}
