package com.example.admin.nutritionist;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class feed extends AppCompatActivity {

    EditText  editTextmsg, editTextSub;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        editTextSub = (EditText) findViewById(R.id.editText_sub);
        editTextmsg = (EditText) findViewById(R.id.editText_msg);
        //info = (TextView) findViewById(R.id.attemptview);
        send = (Button) findViewById(R.id.btn_send);

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String to = "artikumari.mca17@rvce.edu.in";
                //String subject = editTextSub.getText().toString();
                String sub = editTextSub.getText().toString();

                String message = editTextmsg.getText().toString();
                Intent email = new Intent(Intent.ACTION_SEND);
                email.putExtra(Intent.EXTRA_EMAIL, new String[]{to});
                email.putExtra(Intent.EXTRA_SUBJECT, sub);
                email.putExtra(Intent.EXTRA_TEXT, message);
                email.setType("message/rfc822");
                startActivity(Intent.createChooser(email, "Choose an email Client:"));

            }
        });

    }

}
