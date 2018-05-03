package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.preference.PreferenceActivity;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;
import android.widget.Toast;

import static edu.auburn.eng.csse.comp3710.spring2018.marshmellies.StartGameActivity.SETTINGS_NAME;


public class OptionsActivity extends AppCompatActivity {
    Switch mSoundSwitch;
    RadioButton mEasyButton;
    RadioButton mMediumButton;
    RadioButton mHardButton;
    Button mSaveOptionsBtn;
    public static final String  SOUND_SETTINGS_NAME = "SoundSettingsFile";
    public static final String  DIFFICULTY_SETTINGS_NAME = "DifficultySettingsFile";
    boolean isSoundEnabled;
    int difficulty = 2;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_options);


        mSoundSwitch = (Switch) findViewById(R.id.sound_switch);
        mEasyButton = findViewById(R.id.easy_radio_btn);
        mMediumButton = findViewById(R.id.medium_radio_btn);
        mHardButton = findViewById(R.id.hard_radio_btn);

        mSaveOptionsBtn = findViewById(R.id.save_options_btn);
        mSaveOptionsBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                if (mEasyButton.isChecked()) {
                    SharedPreferences difficultySettings = getSharedPreferences(DIFFICULTY_SETTINGS_NAME, 0);
                    SharedPreferences.Editor editor = difficultySettings.edit();
                    editor.putInt("difficultyKey", 1);
                    editor.commit();

                }

                if (mMediumButton.isChecked()) {
                    SharedPreferences difficultySettings  = getSharedPreferences(DIFFICULTY_SETTINGS_NAME, 0);
                    SharedPreferences.Editor editor = difficultySettings.edit();
                    editor.putInt("difficultyKey", 2);
                    editor.commit();
                }

                if (mHardButton.isChecked()) {
                    SharedPreferences difficultySettings  = getSharedPreferences(DIFFICULTY_SETTINGS_NAME, 0);
                    SharedPreferences.Editor editor = difficultySettings.edit();
                    editor.putInt("difficultyKey", 3);
                    editor.commit();
                }

                Toast.makeText(OptionsActivity.this, "Options Saved!", Toast.LENGTH_SHORT).show();
            }
        });

        SharedPreferences switchSettings = getSharedPreferences(SOUND_SETTINGS_NAME, 0);
        isSoundEnabled = switchSettings.getBoolean("key", true);
        mSoundSwitch.setChecked(isSoundEnabled);

        SharedPreferences difficultySettings = getSharedPreferences(DIFFICULTY_SETTINGS_NAME, 0);
        difficulty = difficultySettings.getInt("difficultyKey", 0);
        if (difficulty == 1){
            mEasyButton.setChecked(true);
        }
        else if (difficulty == 2){
            mMediumButton.setChecked(true);
        }
        else{
            mHardButton.setChecked(true);
        }

        mSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked) {
                    Toast.makeText(OptionsActivity.this, "Sound enabled.", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(OptionsActivity.this, "Sound disabled.", Toast.LENGTH_SHORT).show();

                }

                SharedPreferences switchSettings = getSharedPreferences(SETTINGS_NAME, 0);
                SharedPreferences.Editor editor = switchSettings.edit();
                editor.putBoolean("key", isChecked);
                editor.commit();
            }
        });
    }




    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, OptionsActivity.class);
        return intent;
    }


}
