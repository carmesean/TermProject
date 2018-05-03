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


public class OptionsActivity extends AppCompatActivity {
    Switch mSoundSwitch;
    RadioButton mEasyButton;
    RadioButton mMediumButton;
    RadioButton mHardButton;
    public static final String  SETTINGS_NAME = "SettingsFile";
    boolean isSoundEnabled;

    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_options);


        mSoundSwitch = (Switch) findViewById(R.id.sound_switch);
        mEasyButton = findViewById(R.id.easy_radio_btn);
        mMediumButton = findViewById(R.id.medium_radio_btn);
        mHardButton = findViewById(R.id.hard_radio_btn);

        SharedPreferences switchSettings = getSharedPreferences(SETTINGS_NAME, 0);
        isSoundEnabled = switchSettings.getBoolean("key", true);
        mSoundSwitch.setChecked(isSoundEnabled);


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

        if (mEasyButton.isChecked()) {
            switchSettings = getSharedPreferences(SETTINGS_NAME, 0);
            SharedPreferences.Editor editor = switchSettings.edit();
            editor.putInt("radioKey", 1);

        }

        if (mMediumButton.isChecked()) {
            switchSettings = getSharedPreferences(SETTINGS_NAME, 0);
            SharedPreferences.Editor editor = switchSettings.edit();
            editor.putInt("radioKey", 2);
        }

        if (mHardButton.isChecked()) {
            switchSettings = getSharedPreferences(SETTINGS_NAME, 0);
            SharedPreferences.Editor editor = switchSettings.edit();
            editor.putInt("radioKey", 3);
        }
    }


    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, OptionsActivity.class);
        return intent;
    }


}
