package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class SplashActivity extends AppCompatActivity {
    private final int DISPLAY_TIME = 1000;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, StartGameActivity.class);
        startActivity(intent);
        finish();
//        setContentView(R.layout.activity_splash);

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent = new Intent(SplashActivity.this, AubieActivity.class);
//                SplashActivity.this.startActivity(intent);
//                SplashActivity.this.finish();
//
//            }
//        }, DISPLAY_TIME);
    }
}
