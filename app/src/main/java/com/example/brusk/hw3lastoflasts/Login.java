package com.example.brusk.hw3lastoflasts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    Button btn;
    Database helper = new Database(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn_login);


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText a = (EditText) findViewById(R.id.input_email);
                String str = a.getText().toString();
                EditText b = (EditText) findViewById(R.id.input_password);
                String pass = b.getText().toString();

                String password = helper.searchPass(str);

                String EMAIL_REGEX = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

                if (pass.equals(password)) {

                    Toast temp = Toast.makeText(Login.this, "Welcome it is nice to see you again ", Toast.LENGTH_SHORT);
                    temp.show();

                    Intent i = new Intent(Login.this, Translate.class);
                    startActivity(i);


                } else {


                    String emailstr = a.getText().toString();
                    String pass1str = b.getText().toString();


                    if (!emailstr.matches(EMAIL_REGEX)) {

                        a.setError("This e-mail address is invalid!");


                    } else if (pass1str.length() < 4) {

                        b.setError("This password is too short!");


                    } else {
                        Members m = new Members();
                        m.setEmail(emailstr);
                        m.setPassword(pass1str);
                        helper.insertMembers(m);

                    }

                    Toast temp = Toast.makeText(Login.this, "This e-mail and pass combination is new! You have been registered. Enjoy! ", Toast.LENGTH_SHORT);
                    temp.show();

                    Intent intent = new Intent(Login.this, Translate.class);
                    startActivity(intent);



                }

            }
        });


    }


}
