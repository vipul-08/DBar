package com.iyer.shailesh.dbar;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.felipecsl.gifimageview.library.GifImageView;

import org.apache.commons.io.IOUtils;

import java.io.InputStream;

public class SplashActivity extends AppCompatActivity {
    private GifImageView gifImageView;
    ImageView logoView;
    TextView captionView;
    sqlitehelper sqliteh=new sqlitehelper(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        sqliteh.insertquestions(1);
        setContentView(R.layout.activity_splash);
        gifImageView = (GifImageView) findViewById(R.id.gifView);
        logoView = (ImageView) findViewById(R.id.log_cog);
        captionView = (TextView) findViewById(R.id.captionView);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                captionView.setVisibility(View.GONE);
                logoView.setVisibility(View.GONE);
                gifImageView.setVisibility(View.VISIBLE);
                try {
                    InputStream inputStream = getAssets().open("compass.gif");
                    byte [] bytes = IOUtils.toByteArray(inputStream);
                    gifImageView.setBytes(bytes);
                    gifImageView.startAnimation();
                } catch (Exception e) {

                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        captionView.setVisibility(View.VISIBLE);
                        captionView.setText("#BackToTheFuture");
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {

                                Intent i = new Intent(SplashActivity.this, MainActivity.class);
                                startActivity(i);
                                finish();

                            }
                        },2000);
                    }
                }, 3000);
            }
        }, 3000);

    }

}
