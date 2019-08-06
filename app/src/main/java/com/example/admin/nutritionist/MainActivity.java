package com.example.admin.nutritionist;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.PhoneAccount;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private TextView info;
    private Button Login;
    private int counter = 5;
    Cursor cursor;
    SQLiteDatabase db;
    SQLiteOpenHelper openHelper;
    Button Signup;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Signup = (Button) findViewById(R.id.btn_signup);
        Name = (EditText)findViewById(R.id.nametxt);
        Password = (EditText)findViewById(R.id.passtxt);
        info = (TextView) findViewById(R.id.attemptview);
        Login = (Button)findViewById(R.id.loginbtn);
        openHelper = new DatabaseHelper(this);
        db = openHelper.getReadableDatabase();
        info.setText("No of attempts remaining: 5 ");
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               validate(Name.getText().toString(), Password.getText().toString());
              //  Intent intent = new Intent(MainActivity.this, home.class);
                //startActivity(intent);
            }
        });
        Signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Registration.class);
                startActivity(intent);
            }
        });
    }
    private void validate(String username, String userpassword){
       /* if((username.equals("Admin")) && (userpassword.equals("1234"))){
            Intent intent = new Intent(MainActivity.this, home.class);
            startActivity(intent);*/
        String email = Name.getText().toString();
        String pass = Password.getText().toString();
        //cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USER + "WHERE " + DatabaseHelper.COLUMN_USER_EMAIL + "=? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + "=?", new String[]{email, pass});
        cursor = db.rawQuery("Select * from login where email='"+email+"' and password = '"+pass+"'", null);
        if(cursor != null){
            if (cursor.getCount()>0){
                Intent intent = new Intent(MainActivity.this, home.class);
                startActivity(intent);
            }
            else {
                Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
                counter--;
                info.setText("No of attempts remaining: " + String.valueOf(counter));
                if (counter == 0) {
                    Login.setEnabled(false);
                }
            }
        }/*
       String email = Name.getText().toString();
       String pass = Password.getText().toString();
       //cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_USER + "WHERE " + DatabaseHelper.COLUMN_USER_EMAIL + "=? AND " + DatabaseHelper.COLUMN_USER_PASSWORD + "=?", new String[]{email, pass});
        cursor = db.rawQuery("Select * from login where email='"+email+"' and password = '"+pass+"'", null);
       if(cursor != null){
           if (cursor.getCount()>0){
               Intent intent = new Intent(MainActivity.this, home.class);
               startActivity(intent);
           }
           else
           {
               Toast.makeText(getApplicationContext(), "Login Failed!", Toast.LENGTH_SHORT).show();
           }
       }*/
    }
}
