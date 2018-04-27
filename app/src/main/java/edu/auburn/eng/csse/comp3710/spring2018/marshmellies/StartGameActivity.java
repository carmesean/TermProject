package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;

import android.view.animation.Animation;
import android.widget.Toast;



public class StartGameActivity extends AppCompatActivity {
    Sequence mSequence;
    int[] buttonID = {1, 2, 3, 4}; //blue: 1, red:2, yellow: 3, green: 4
    int userScore = 0;
    boolean isSequenceMatch = true;
    int sequenceCount = 0;
    Random rngJesus = new Random();

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

        findViewById(R.id.new_game_btn).setVisibility(View.INVISIBLE);

        Button beginBtn = findViewById(R.id.begin_btn);
        beginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    public void startGame(){
        findViewById(R.id.begin_btn).setVisibility(View.INVISIBLE);
        findViewById(R.id.new_game_btn).setVisibility(View.INVISIBLE);
        mSequence = new Sequence();

        //Begin Game
        do {
            addNextSequence();
            playSystemSequence(mSequence.getSysSequence());

            isSequenceMatch = false;

        }while(isSequenceMatch);

        if (!isSequenceMatch){
            Button mNewGame = findViewById(R.id.new_game_btn);
            mNewGame.setVisibility(View.VISIBLE);
            mNewGame.setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    startGame();
                }
            });
        }
    }

/*    public void ammendSequence(int identifier) {
        //whenever the user presses a button, add button identifier to sequence list
        mSequence.getUserSequence().add(identifier);
    }*/

    public void addNextSequence(){
        int num = rngJesus.nextInt(4);
        mSequence.addToSystemSequence(num);
        sequenceCount++;
    }

    public void playSystemSequence(ArrayList<Integer> sysSequence){
        mBlueButton = findViewById(R.id.top_left_button);
        mRedButton = findViewById(R.id.top_right_button);
        mYellowButton = findViewById(R.id.bottom_left_button);
        mGreenButton = findViewById(R.id.bottom_right_button);

        for (int i = 0; i < sysSequence.size(); i++){
            int buttonSelected = sysSequence.get(i);
            switch(buttonSelected){
                case 0: //blue selected
                    mBlueButton.setPressed(true);
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

                    Handler h = new Handler();
                    h.postDelayed(new Runnable(){
                        public void run() {
                            mBlueButton.setPressed(false);
                        }
                    }, 500);
                    Toast.makeText(getApplicationContext(),
                            R.string.blue_toast, Toast.LENGTH_SHORT).show();
                    break;
                case 1: //red selected
                    mRedButton.setPressed(true);
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

                    Handler h1 = new Handler();
                    h1.postDelayed(new Runnable(){
                        public void run() {
                            mRedButton.setPressed(false);
                        }
                    }, 500);
                    Toast.makeText(getApplicationContext(),
                            R.string.red_toast, Toast.LENGTH_SHORT).show();
                    break;
                case 2: //yellow selected
                    mYellowButton.setPressed(true);
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

                    Handler h2 = new Handler();
                    h2.postDelayed(new Runnable(){
                        public void run() {
                            mYellowButton.setPressed(false);
                        }
                    }, 500);
                    Toast.makeText(getApplicationContext(),
                            R.string.yellow_toast, Toast.LENGTH_SHORT).show();
                    break;
                case 3: //green selected
                    mGreenButton.setPressed(true);
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

                    Handler h3 = new Handler();
                    h3.postDelayed(new Runnable(){
                        public void run() {
                            mGreenButton.setPressed(false);
                        }
                    }, 500);
                    Toast.makeText(getApplicationContext(),
                            R.string.green_toast, Toast.LENGTH_SHORT).show();
                    break;
                default:
                    Toast.makeText(getApplicationContext(),
                            R.string.unknown_button_error_toast, Toast.LENGTH_SHORT).show();
            }
        }
    };


    public boolean isMatch(ArrayList<Integer> sysSequence, ArrayList<Integer> userSequence){
        return false;
    }
    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, StartGameActivity.class);
        return intent;
    }

}