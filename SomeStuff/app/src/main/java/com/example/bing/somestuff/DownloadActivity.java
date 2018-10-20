package com.example.bing.somestuff;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutionException;


public class DownloadActivity extends AppCompatActivity {

    ImageView imageView;
    TextView textView;
    //pass in a string and pass out a string!
    public class DownloadWebText extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            String result = "";
            URL url;
            HttpURLConnection connection;
            try {
                url = new URL(strings[0]);
                connection = (HttpURLConnection) url.openConnection();
                InputStream in = connection.getInputStream();
                InputStreamReader reader = new InputStreamReader(in);
                int data = reader.read();

                while (data != -1){
                    result += (char)data;
                    data = reader.read();
                }

                return result;
            } catch (Exception e) {
                e.printStackTrace();
                return "Failed";
            }
        }
    }

    public class DownloadWebImage extends AsyncTask<String, Void, Bitmap> {

        @Override
        protected Bitmap doInBackground(String... strings) {
            URL url;
            try{
                url  = new URL(strings[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream in = connection.getInputStream();
                Bitmap mBitmap = BitmapFactory.decodeStream(in);
                return mBitmap;
            }catch (Exception e){
                e.printStackTrace();
                return  null;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        imageView = findViewById(R.id.imageView);
        textView = findViewById(R.id.textView);


        DownloadWebText task = new DownloadWebText();
        DownloadWebImage task2 = new DownloadWebImage();
        String result = "";
        Bitmap bitmap = null;
        try {
            result =  task.execute("https://www.netdom.me").get();
            bitmap = task2.execute("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo_top_86d58ae1.png").get();
            imageView.setImageBitmap(bitmap);
            textView.setText(result);
            Log.i("info",result);
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }
}
