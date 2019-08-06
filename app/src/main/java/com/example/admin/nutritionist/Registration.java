package com.example.admin.nutritionist;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.view.View.OnClickListener;
import android.widget.Toast;
import android.content.Intent;

public class Registration extends AppCompatActivity {

    SQLiteOpenHelper openHelper;
    SQLiteDatabase db;
    EditText name, height, age, email;
    EditText pass, weight;
    RadioGroup radioSexGroup, radioDiabeticGroup;
    RadioButton radioSexButton, radioDiabeticButton;
    Button register;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        openHelper = new DatabaseHelper(this);
        name = (EditText) findViewById(R.id.editText_name);
        height = (EditText) findViewById(R.id.editText_height);
        weight = (EditText) findViewById(R.id.editText_weight);
        age = (EditText) findViewById(R.id.editText_age);
        email = (EditText) findViewById(R.id.editText_email);
        pass = (EditText) findViewById(R.id.editText_pass);
        register = (Button) findViewById(R.id.btn_register);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioGroup_sex);
        radioDiabeticGroup = (RadioGroup) findViewById(R.id.radioGroup_diabetic);
        //addListenerOnButton();
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                int sexid = radioSexGroup.getCheckedRadioButtonId();
                int diabeticid = radioDiabeticGroup.getCheckedRadioButtonId();
                radioSexButton = (RadioButton) findViewById(sexid);
                radioDiabeticButton = (RadioButton) findViewById(diabeticid);
                String sex = radioSexButton.getText().toString();
                String diabetic = radioDiabeticButton.getText().toString();

                db = openHelper.getWritableDatabase();
                String uname = name.getText().toString();
                String uweight = weight.getText().toString();
                String uheight = height.getText().toString();
                String uage = age.getText().toString();
                String upass = pass.getText().toString();
                String uemail = email.getText().toString();
                //if (uname.isEmpty() || uheight.isEmpty() || uage.isEmpty() || uemail.isEmpty() || upass.isEmpty() || uweight.isEmpty() || sex.isEmpty() || diabetic.isEmpty()){
                if (uname.isEmpty() || uheight.isEmpty() || uage.isEmpty() || uemail.isEmpty() || upass.isEmpty() || uweight.isEmpty()){
                    Toast.makeText(Registration.this, "All fields are mandatory", Toast.LENGTH_LONG).show();
                }
               else {

                    insertdata(uname, uage, uemail, uheight, upass, uweight);
                    Toast.makeText(Registration.this, "Successfull", Toast.LENGTH_LONG).show();
                    //insertdata(uname, uage, uemail, uheight, upass, uweight, sex, diabetic);
                    Intent intent = new Intent(Registration.this, MainActivity.class);
                    startActivity(intent);
                }

            }

            private void insertdata(String uname, String uage, String uemail, String uheight, String upass, String uweight)
           //private void insertdata(String uname, String uage, String uemail, String uheight, String upass, String uweight, String sex, String diabetic)
            {
                ContentValues contentValues = new ContentValues();
                contentValues.put(DatabaseHelper.COLUMN_USER_NAME, uname);
                contentValues.put(DatabaseHelper.COLUMN_USER_HEIGHT, uheight);
                contentValues.put(DatabaseHelper.COLUMN_USER_WEIGHT, uweight);
                contentValues.put(DatabaseHelper.COLUMN_USER_AGE, uage);
                contentValues.put(DatabaseHelper.COLUMN_USER_EMAIL, uemail);
                contentValues.put(DatabaseHelper.COLUMN_USER_PASSWORD, upass);
               // contentValues.put(DatabaseHelper.COLUMN_USER_SEX, sex);
                //
                // 36contentValues.put(DatabaseHelper.COLUMN_USER_DIABETIC, diabetic);
                long id = db.insert(DatabaseHelper.TABLE_USER, null, contentValues);
            }
        });
       // public void insertdata(String )
   // public void addListenerOnButton(){

    }



    public void checkButton(View view) {
    }
}
