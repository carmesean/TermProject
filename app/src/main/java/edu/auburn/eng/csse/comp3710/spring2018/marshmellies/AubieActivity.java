package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import java.util.ArrayList;

public class AubieActivity extends AppCompatActivity {
    ArrayList<SimonButton> mSequenceList;   // Sequence list in the order the buttons sound
    SimonButton mNextButton;                // Most recently sounded button

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aubie);


    }

    public void ammendSequence() {
        //this is a test
    }

}


