package com.example.brusk.hw3lastoflasts;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by brusk on 15.04.2016.
 */
public class Translate extends Activity {

    Button btnTR, btnConfig, btnSearch;
    TextView textView;
    Db_Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.translate);

        btnTR= (Button) findViewById(R.id.btnTR_EN);
        btnSearch= (Button) findViewById(R.id.btnSrch);
        btnConfig= (Button) findViewById(R.id.btnConfig);
        textView= (TextView) findViewById(R.id.txtTransEN);

        btnConfig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Translate.this, ListWords.class);
                startActivity(i);

            }
        });

        btnSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText a = (EditText)findViewById(R.id.editText);
                String str = a.getText().toString();
                textView= (TextView) findViewById(R.id.txtTransEN);


                String Tr_En= controller.searchWord(str);

                textView.setText(Tr_En);


            }
        });

    }
}
