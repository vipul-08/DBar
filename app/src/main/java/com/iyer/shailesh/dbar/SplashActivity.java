package com.iyer.shailesh.dbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {
    private static int timer = 3000;
    private GifImageView gifImageView;
    private ProgressBar progressBar;

    sqlitehelper sqliteh=new sqlitehelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqliteh.insertquestions(1);
        setContentView(R.layout.activity_splash);
        gifImageView = (GifImageView) findViewById(R.id.gifView);
        progressBar = (ProgressBar) findViewById(R.id.pBar);
        progressBar.setVisibility(progressBar.VISIBLE);
        try {
            InputStream inputStream = getAssets().open("hunt.gif");
            byte [] bytes = IOUtils.toByteArray(inputStream);
            gifImageView.setBytes(bytes);
            gifImageView.startAnimation();
        } catch (Exception e) {

        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        }, timer);

    }

}
