package com.example.bing.somestuff;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.ExecutionException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class JsonParseActivity extends AppCompatActivity {

    TextView txtTitle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_parse);

        txtTitle = findViewById(R.id.txtTitle);


        DownloadJsonData jsonData = new DownloadJsonData();
        jsonData.execute("https://gank.io/api/today");
        try {
            String result = jsonData.get();
            JSONObject json = new JSONObject(result);
            JSONObject results = new JSONObject(json.getString("results"));
            JSONArray recommends = results.getJSONArray("福利");
            JSONObject recommend = new JSONObject(recommends.get(0).toString());
            txtTitle.setText(recommend.getString("desc"));
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public class DownloadJsonData extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url;
            String result = "";
            try {
                url = new URL(strings[0]);
                URLConnection connection = url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();
                StringBuilder stringBuilder = new StringBuilder();
                while(data!=-1){
                    data = reader.read();
                    stringBuilder.append((char)data);
                }
                result = stringBuilder.toString();
                result = unicodeToString(result) ;
                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    public static String unicodeToString(String str) {

        Pattern pattern = Pattern.compile("(\\\\u(\\p{XDigit}{4}))");

        Matcher matcher = pattern.matcher(str);

        char ch;

        while (matcher.find()) {

            ch = (char) Integer.parseInt(matcher.group(2), 16);

            str = str.replace(matcher.group(1), ch+"" );

        }

        return str;

    }
}
