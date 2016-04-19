package com.example.brusk.hw3lastoflasts;

import android.content.Intent;
import android.database.SQLException;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by brusk on 15.04.2016.
 */
public class ListWords extends AppCompatActivity {
    EditText tr, en;
    TextView txtList;
    Db_Controller controller;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_words);

        tr = (EditText) findViewById(R.id.editTextTr);
        en = (EditText) findViewById(R.id.editTextEn);
        txtList = (TextView) findViewById(R.id.textView);

        controller = new Db_Controller(this, "", null, 1);
    }

    public void btnClick(View view) {

        switch (view.getId()) {
            case R.id.btnAdd:

                if (tr.getText().toString().isEmpty() && en.getText().toString().isEmpty()) {

                    Toast.makeText(ListWords.this, "Please write something to translate!", Toast.LENGTH_SHORT).show();
                    controller.listAllWords(txtList);

                } else {

                    try {
                        controller.insert_words(tr.getText().toString(), en.getText().toString());
                        controller.listAllWords(txtList);

                    } catch (SQLException e) {
                        Toast.makeText(ListWords.this, "Already exist ", Toast.LENGTH_SHORT).show();
                        controller.listAllWords(txtList);

                    }
                }

                break;
            case R.id.btnDelete:
                controller.delete_words(tr.getText().toString());
                Toast.makeText(ListWords.this, "Selected word has been deleted!", Toast.LENGTH_SHORT).show();
                controller.listAllWords(txtList);
                break;
            case R.id.showwords:
                controller.listAllWords(txtList);
        }

    }
}
