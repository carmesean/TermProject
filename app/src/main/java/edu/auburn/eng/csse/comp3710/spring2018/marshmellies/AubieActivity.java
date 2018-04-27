package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class AubieActivity extends AppCompatActivity {
    Button mStartGameButton;
    Button mHowToPlayButton;
    Button mOptionsButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aubie);

        mStartGameButton = findViewById(R.id.start_game_button);
        mStartGameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = StartGameActivity.newIntent(AubieActivity.this);
                startActivity(intent);
            }
        });

        mHowToPlayButton = findViewById(R.id.how_to_play_button);
        mHowToPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = HowToPlayActivity.newIntent(AubieActivity.this);
                startActivity(intent);
            }
        });

        mOptionsButton = findViewById(R.id.options_button);
        mOptionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = OptionsActivity.newIntent(AubieActivity.this);
                startActivity(intent);
            }
        });
    }


}


