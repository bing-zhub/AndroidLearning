package com.example.bing.somestuff;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

public class SqliteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        SQLiteDatabase myDatabase = this.openOrCreateDatabase("newUser", MODE_PRIVATE, null);
        myDatabase.execSQL("CREATE TABLE IF NOT EXISTS newUser (name VARCHAR, age INT(3), id INTEGER PRIMARY KEY)");
        // 与SQL完全一样
        myDatabase.execSQL("INSERT INTO newUser(name, age) values('nike', 20)");
        myDatabase.execSQL("INSERT INTO newUser(name, age) values('ada', 22)");

        Cursor c = myDatabase.rawQuery("SELECT * FROM newUser ", null);
        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");

        c.moveToFirst();

        while(!c.isAfterLast()){
            Log.i("result",c.getString(nameIndex)+" "+c.getString(ageIndex));
            Log.i("result", " --------------");
            c.moveToNext();
        }
    }

}
