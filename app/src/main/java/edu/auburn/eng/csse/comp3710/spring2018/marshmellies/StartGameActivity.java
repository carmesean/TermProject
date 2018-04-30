package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
    Random rn = new Random();
    int sequenceIndex = 0; //index for CountDownTimer
    int currentSequenceIndex = 0; //index for Button onClickListener
    int countFromBeginning = 0;
    int currentLevel = 0;

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

        //When begin button is clicked by user, start the game
        beginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    public void startGame(){
        //reset all global variables
        initializeNewGame();

        incrementLevel();

        mBlueButton = findViewById(R.id.top_left_button);
        mBlueButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isSequenceMatch = isMatch(currentSequenceIndex, 0);
                if (isSequenceMatch){
                    mSequence.addToUserSequence(0);
                    countFromBeginning++;
                    currentSequenceIndex++;

                    if(countFromBeginning == currentLevel){
                        incrementLevel();
                    }
                }
                else{
                    endGame();
                }
            }
        });

        mRedButton = findViewById(R.id.top_right_button);
        mRedButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isSequenceMatch = isMatch(currentSequenceIndex, 1);
                if(isSequenceMatch){
                    mSequence.addToUserSequence(1);
                    countFromBeginning++;
                    currentSequenceIndex++;

                    if (countFromBeginning == currentLevel){
                        incrementLevel();
                    }
                }
                else{
                    endGame();
                }
            }
        });

        mYellowButton = findViewById(R.id.bottom_left_button);
        mYellowButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isSequenceMatch = isMatch(currentSequenceIndex, 2);
                if (isSequenceMatch){
                    mSequence.addToUserSequence(2);
                    countFromBeginning++;
                    currentSequenceIndex++;

                    if (countFromBeginning == currentLevel){
                        incrementLevel();
                    }
                }
                else{
                    endGame();
                }
            }
        });

        mGreenButton = findViewById(R.id.bottom_right_button);
        mGreenButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                isSequenceMatch = isMatch(currentSequenceIndex, 3);
                if (isSequenceMatch){
                    mSequence.addToUserSequence(3);
                    countFromBeginning++;
                    currentSequenceIndex++;

                    if (countFromBeginning == currentLevel){
                        incrementLevel();
                    }
                }
                else{
                    endGame();
                }
            }
        });



        //Begin Game
        //do {
/*        for (int i = 0; i < 5; i++){
            addNextSequence();
        }*/


/*            if (sequenceCount == 2) {
                isSequenceMatch = false;
            }*/

            //isSequenceMatch = false;

        //}while(isSequenceMatch);

        //if (!isSequenceMatch){
        //}
    }

/*    public void ammendSequence(int identifier) {
        //whenever the user presses a button, add button identifier to sequence list
        mSequence.getUserSequence().add(identifier);
    }*/

    //reset all global variables to start a new game
    public void initializeNewGame(){
        findViewById(R.id.begin_btn).setVisibility(View.INVISIBLE);
        findViewById(R.id.new_game_btn).setVisibility(View.INVISIBLE);
        mSequence = new Sequence();
        userScore = 0;
        sequenceCount = 0;
        sequenceIndex = 0;
        isSequenceMatch = true;
        currentLevel = 0;
        currentSequenceIndex = 0;
        countFromBeginning = 0;
    }

    public void incrementLevel(){
        currentLevel++;
        addNextNumberToSequence();
        playSystemSequence(mSequence.getSysSequence());

    }

    //choose a random number and add it the system Sequence
    public void addNextNumberToSequence(){
        int num = rn.nextInt(4);
        mSequence.addToSystemSequence(num);
        sequenceCount++;
    }

    //iterate through the system Sequence and play the selected button's sound and "press the button"
    public void playSystemSequence(final ArrayList<Integer> sysSequence){
        mBlueButton = findViewById(R.id.top_left_button);
        mRedButton = findViewById(R.id.top_right_button);
        mYellowButton = findViewById(R.id.bottom_left_button);
        mGreenButton = findViewById(R.id.bottom_right_button);

        new CountDownTimer(((sequenceCount + 1) * 1000), 1000){
            public void onTick(long sequenceTime){
                int buttonSelected = sysSequence.get(sequenceIndex);
                Handler h = new Handler();

                switch(buttonSelected){
                    case 0: //blue selected
                        mBlueButton.setPressed(true);

                        //this will trigger .5 seconds after button has been pressed
                        h.postDelayed(new Runnable(){
                            public void run() {
                                mBlueButton.setPressed(false);

                                if (blueMedia == null) {
                                    //create a blue button media player
                                    blueMedia = MediaPlayer.create(StartGameActivity.this,
                                            R.raw.blue_sound);
                                }
                                //sound the blue button
                                blueMedia.start();
                            }
                        }, 500);
                        sequenceIndex++;
                        break;
                    case 1: //red selected
                        mRedButton.setPressed(true);

                        //this will trigger .5 seconds after button has been pressed
                        h.postDelayed(new Runnable(){
                            public void run() {
                                mRedButton.setPressed(false);

                               if (redMedia == null) {
                                    //create a red button media player
                                    redMedia = MediaPlayer.create(StartGameActivity.this,
                                            R.raw.red_sound);
                                }
                                //sound the red button
                                redMedia.start();
                            }
                        }, 500);
                        sequenceIndex++;
                        break;
                    case 2: //yellow selected
                        mYellowButton.setPressed(true);

                        //this will trigger .5 seconds after button has been pressed
                        h.postDelayed(new Runnable(){
                            public void run() {
                                mYellowButton.setPressed(false);

                               if (yellowMedia == null) {
                                    //create a yellow button media player
                                    yellowMedia = MediaPlayer.create(StartGameActivity.this,
                                            R.raw.yellow_sound);
                                }
                                //sound the yellow button
                                yellowMedia.start();
                            }
                        }, 500);
                        sequenceIndex++;
                        break;
                    case 3: //green selected
                        mGreenButton.setPressed(true);

                        //this will trigger .5 seconds after button has been pressed
                        h.postDelayed(new Runnable(){
                            public void run() {
                                mGreenButton.setPressed(false);

                                if (greenMedia == null) {
                                    //create a green button media player
                                    greenMedia = MediaPlayer.create(StartGameActivity.this,
                                            R.raw.green_sound);
                                }
                                //sound the green button
                                greenMedia.start();
                            }
                        }, 500);
                        sequenceIndex++;
                        break;
                    default:
                        Toast.makeText(getApplicationContext(),
                                R.string.unknown_button_error_toast, Toast.LENGTH_SHORT).show();
                }
            }

            //After the count down timer is finished, do something
            public void onFinish(){
                sequenceIndex = 0;
                countFromBeginning = 0;
                currentSequenceIndex = 0;
            }
        }.start();
    }

    public void endGame(){
        Button mNewGame = findViewById(R.id.new_game_btn);
        mNewGame.setVisibility(View.VISIBLE);
        mNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startGame();
            }
        });
        Toast.makeText(getApplicationContext(), R.string.game_over,
                Toast.LENGTH_SHORT).show();
    }

    public boolean isMatch(int index, int selectedButtonNumber){
        return (mSequence.getSysSequence().get(index) == selectedButtonNumber);
    }
    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, StartGameActivity.class);
        return intent;
    }

}