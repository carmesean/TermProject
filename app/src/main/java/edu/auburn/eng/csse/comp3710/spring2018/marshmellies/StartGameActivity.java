package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.CountDownTimer;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import java.util.ArrayList;
import java.util.Random;

import android.view.animation.Animation;
import android.widget.TextView;
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
    boolean delayAfterSystemSequence = true;
    int difficultySelected = 2; //by default, set difficulty to Easy (change when PREFERENCES works)
    long difficultyDuration = 0;
    int maximumLevel = 5;
    boolean userHasReachedMaxLevel = false;
    CountDownTimer cdt;

    Button mBlueButton;
    Button mRedButton;
    Button mYellowButton;
    Button mGreenButton;
    MediaPlayer blueMedia;
    MediaPlayer redMedia;
    MediaPlayer yellowMedia;
    MediaPlayer greenMedia;
    TextView scoreTV;
    TextView turnTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game);

        findViewById(R.id.new_game_btn).setVisibility(View.INVISIBLE);
        findViewById(R.id.score_text_view).setVisibility(View.INVISIBLE);
        findViewById(R.id.turn_alternate_view).setVisibility(View.INVISIBLE);

        Button beginBtn = findViewById(R.id.begin_btn);

        //When begin button is clicked by user, start the game
        beginBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startGame();
            }
        });
    }

    @Override
    protected void onPause(){
        super.onPause();

        if (cdt != null){
            cdt.cancel();
        }
    }

    @Override
    protected void onStop(){
        super.onStop();

        if (cdt != null){
            cdt.cancel();
        }
    }

/*    @Override
    protected void onResume(){
        super.onResume();

        //create new CDT with remaining sequence...maybe
    }*/

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
                    showYouLoseAlert();
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
                    showYouLoseAlert();
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
                    showYouLoseAlert();
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
                    showYouLoseAlert();
                    endGame();
                }
            }
        });
    }

/*    public void ammendSequence(int identifier) {
        //whenever the user presses a button, add button identifier to sequence list
        mSequence.getUserSequence().add(identifier);
    }*/

    //reset all global variables to start a new game
    public void initializeNewGame(){
        findViewById(R.id.begin_btn).setVisibility(View.INVISIBLE);
        findViewById(R.id.new_game_btn).setVisibility(View.INVISIBLE);
        scoreTV = findViewById(R.id.score_text_view);
        scoreTV.setVisibility(View.VISIBLE);
        scoreTV.setText("Score: 0");
        turnTV = findViewById(R.id.turn_alternate_view);
        turnTV.setVisibility(View.VISIBLE);
        turnTV.setText(R.string.aubie_turn);
        mSequence = new Sequence();
        userScore = 0;
        sequenceCount = 0;
        sequenceIndex = 0;
        isSequenceMatch = true;
        currentLevel = 0;
        currentSequenceIndex = 0;
        countFromBeginning = 0;

        if (difficultySelected == 1){ //Easy Difficulty
            difficultyDuration = 1500;
        }
        else if (difficultySelected == 2){ //Medium Difficulty
            difficultyDuration = 1000;
        }
        else{
            difficultyDuration = 500; // Hard Difficulty
        }
    }

    public void incrementLevel(){
        if (currentLevel != 0){
            userScore++;
        }
        scoreTV.setText("Score: " + userScore);
        disableAllButtons();

        if (currentLevel == maximumLevel){
            userHasReachedMaxLevel = true;
            showYouWinAlert();
            endGame();
        }
        else{
            currentLevel++;
            addNextNumberToSequence();
            turnTV.setText(R.string.aubie_turn);
            playSystemSequence(mSequence.getSysSequence());
        }

    }

    //choose a random number and add it the system Sequence
    public void addNextNumberToSequence(){
        int num = rn.nextInt(4);
        mSequence.addToSystemSequence(num);
        sequenceCount++;
    }

    public void showYouWinAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StartGameActivity.this);
        builder.setTitle(R.string.win_alert_title);
        builder.setMessage(R.string.win_alert_message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );

        AlertDialog youWinAlert = builder.create();
        youWinAlert.show();
    }

    public void showYouLoseAlert(){
        AlertDialog.Builder builder = new AlertDialog.Builder(StartGameActivity.this);
        builder.setTitle(R.string.lose_alert_title);
        builder.setMessage(R.string.lose_alert_message);
        builder.setCancelable(true);

        builder.setPositiveButton(
                "Ok",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.cancel();
                    }
                }
        );

        AlertDialog youLoseAlert = builder.create();
        youLoseAlert.show();
    }

    //iterate through the system Sequence and play the selected button's sound and "press the button"
    public void playSystemSequence(final ArrayList<Integer> sysSequence){
        mBlueButton = findViewById(R.id.top_left_button);
        mRedButton = findViewById(R.id.top_right_button);
        mYellowButton = findViewById(R.id.bottom_left_button);
        mGreenButton = findViewById(R.id.bottom_right_button);

        cdt = new CountDownTimer(((sequenceCount + 2) * difficultyDuration), difficultyDuration){
            public void onTick(long sequenceTime){
                if(delayAfterSystemSequence){
                    delayAfterSystemSequence = false;
                }
                else{
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
                                                R.raw.blue_400_m);
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
                                                R.raw.red_420_m);
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
                                                R.raw.yellow_440_m);
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
                                                R.raw.green_460_m);
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
            }

            //After the count down timer is finished, do something
            public void onFinish(){
                sequenceIndex = 0;
                countFromBeginning = 0;
                currentSequenceIndex = 0;
                delayAfterSystemSequence = true;
                turnTV.setText(R.string.player_turn);
                enableAllButtons();
            }
        }.start();
    }

    public void enableAllButtons(){
        (findViewById(R.id.top_left_button)).setEnabled(true);
        (findViewById(R.id.top_right_button)).setEnabled(true);
        (findViewById(R.id.bottom_left_button)).setEnabled(true);
        (findViewById(R.id.bottom_right_button)).setEnabled(true);
    }

    public void disableAllButtons(){
        (findViewById(R.id.top_left_button)).setEnabled(false);
        (findViewById(R.id.top_right_button)).setEnabled(false);
        (findViewById(R.id.bottom_left_button)).setEnabled(false);
        (findViewById(R.id.bottom_right_button)).setEnabled(false);
    }

    public void endGame(){
        Button mNewGame = findViewById(R.id.new_game_btn);
        mNewGame.setVisibility(View.VISIBLE);
        turnTV.setVisibility(View.INVISIBLE);
        disableAllButtons();
        mNewGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                startGame();
            }
        });
    }

    public boolean isMatch(int index, int selectedButtonNumber){
        return (mSequence.getSysSequence().get(index) == selectedButtonNumber);
    }
    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, StartGameActivity.class);
        return intent;
    }

}