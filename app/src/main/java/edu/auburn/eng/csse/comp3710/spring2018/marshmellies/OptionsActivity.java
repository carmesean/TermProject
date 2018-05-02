package edu.auburn.eng.csse.comp3710.spring2018.marshmellies;

import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import android.widget.Switch;



public class OptionsActivity extends AppCompatActivity {
    RadioButton mSoundOn;
    RadioButton mSoundOff;
    Switch mSoundSwitch;
    @Override
    protected void onCreate(Bundle savedInstanceBundle){
        super.onCreate(savedInstanceBundle);
        setContentView(R.layout.activity_options);


        mSoundSwitch = (Switch) findViewById(R.id.sound_switch);
        mSoundSwitch.setChecked(true);
        mSoundSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {
                if (isChecked == false) {
                    //change theme
                    setTheme(R.style.AppNoSoundTheme);
                    //add preferences
                }
                else {
                    //change to original theme
                    setTheme(R.style.AppTheme);
                }
            }
        });
    }

    public void optionPreferences(View v){
        //change difficult based on radio button selected
        //Toggle Sound on/off
        //Preferences
        //unnecesarry change
    }

    public static Intent newIntent(Context packageContext){
        Intent intent = new Intent(packageContext, OptionsActivity.class);
        return intent;
    }
}
