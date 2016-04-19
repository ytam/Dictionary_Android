package com.example.brusk.hw3lastoflasts;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.view.View;
import android.widget.TextView;

/**
 * Created by brusk on 15.04.2016.
 */
public class Db_Controller extends SQLiteOpenHelper {
    public Db_Controller(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, "TEST.db", factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL("CREATE TABLE WORDS(ID INTEGER PRIMARY KEY AUTOINCREMENT,TURKISH TEXT NOT NULL,ENGLISH TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int İ, int İ1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXIST WORDS;");
        onCreate(sqLiteDatabase);

    }

    public void insert_words(String turkish, String english) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("TURKISH", turkish);
        contentValues.put("ENGLISH", english);
        this.getWritableDatabase().insertOrThrow("WORDS", "", contentValues);

    }

    public void delete_words(String turkish) {
        this.getWritableDatabase().delete("WORDS", "TURKISH='" + turkish + "'", null);

    }

    public void listAllWords(TextView textView) {
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT * FROM WORDS", null);
        textView.setText("");

        while (cursor.moveToNext()) {
            textView.append(cursor.getString(1) + " = " + cursor.getString(2) + "\n");
        }

    }

    public String searchWord(String turkish){
        Cursor cursor = this.getReadableDatabase().rawQuery("SELECT ENGLISH FROM WORDS WHERE TURKISH='"+turkish+"'", null);

        String a, b;
        b = "not found";
        if(cursor.moveToFirst())
        {
            do{
                a = cursor.getString(0);

                if(a.equals(turkish))
                {
                    b = cursor.getString(1);

                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }


}
