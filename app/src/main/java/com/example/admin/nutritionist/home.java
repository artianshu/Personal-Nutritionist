package com.example.admin.nutritionist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class home extends AppCompatActivity {

    Button search, diet, recipe, feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        search = (Button) findViewById(R.id.btn_search);
        diet = (Button) findViewById(R.id.btn_diet);
        recipe = (Button) findViewById(R.id.btn_recipe);
        feedback = (Button) findViewById(R.id.btn_feedback);

      /*  feedback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(home.this, feed.class);
                startActivity(intent);
            }
        });*/
    }

    public void showFoods(View view){

        Intent intent=new Intent(this,Foodsearch.class);
        startActivity(intent);
    }
    public void showrecipie(View view){

        Intent intent=new Intent(this,RecipieSearch.class);
        startActivity(intent);
    }
    public void showdp(View view){

        Intent intent=new Intent(this,diet_plan.class);
        startActivity(intent);
    }
    public void showfeedback(View view){

        Intent intent=new Intent(this,feed.class);
        startActivity(intent);
    }
}
