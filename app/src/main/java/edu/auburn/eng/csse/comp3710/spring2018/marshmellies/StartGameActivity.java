package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import android.view.animation.Animation;



public class StartGameActivity extends AppCompatActivity {
    Sequence mSequence;
    int[] buttonID = {1, 2, 3, 4}; //blue: 1, red:2, yellow: 3, green: 4
    Button mBlueButton;
    Button mRedButton;
    Button mYellowButton;
    Button mGreenButton;
    MediaPlayer blueMedia;
    MediaPlayer redMedia;
    MediaPlayer yellowMedia;
    MediaPlayer greenMedia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        mBlueButton = findViewById(R.id.top_left_button);
        mRedButton = findViewById(R.id.top_right_button);
        mYellowButton = findViewById(R.id.bottom_right_button);
        mGreenButton = findViewById(R.id.bottom_left_button);

//
//        Animation anim = new AlphaAnimation(0.0f, 1.0f);
//        anim.setDuration(50);
//        anim.setStartOffset(20);
//        anim.setRepeatMode(Animation.REVERSE);
//        anim.setRepeatCount(Animation.INFINITE);
//        mBlueButton.startAnimation(anim);

        mBlueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (blueMedia == null) {
                    //create a blue button media player
                    blueMedia = MediaPlayer.create(StartGameActivity.this, R.raw.blue_sound);
                }
                //sound the blue button
                blueMedia.start();
            }
        });

        mRedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (redMedia == null) {
                    //create a red button media player
                    redMedia = MediaPlayer.create(StartGameActivity.this, R.raw.red_sound);
                }
                //sound the red button
                redMedia.start();

            }
        });

        mYellowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (yellowMedia == null) {
                    //create a yellow button media player
                    yellowMedia = MediaPlayer.create(StartGameActivity.this, R.raw.yellow_sound);
                }
                //sound the yellow button
                yellowMedia.start();
            }
        });

        mGreenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (greenMedia == null) {
                    //create a green button media player
                    greenMedia = MediaPlayer.create(StartGameActivity.this, R.raw.green_sound);
                }
                //sound the green button
                greenMedia.start();
            }
        });

    }

    public void ammendSequence(int identifier) {
        //whenever the user presses a button, add button identifier to sequence list
        mSequence.getUserSequence().add(identifier);
    }


}