package com.example.bing.somestuff;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SqliteActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);

        SQLiteDatabase myDataabse = this.openOrCreateDatabase("User", MODE_PRIVATE, null);
        myDataabse.execSQL("CREATE TABLE IF NOT EXISTS user (name VARCHAR, age INT(3))");

        myDataabse.execSQL("INSERT INTO User(name, age) values('nike', 20)");
        myDataabse.execSQL("INSERT INTO User(name, age) values('ada', 22)");

        Cursor c = myDataabse.rawQuery("SELECT * FROM User ", null);
        int nameIndex = c.getColumnIndex("name");
        int ageIndex = c.getColumnIndex("age");

        c.moveToFirst();

        while(c!=null){
            System.out.println(c.getString(nameIndex));
            System.out.println(c.getInt(ageIndex));

            c.moveToNext();
        }
    }

}
