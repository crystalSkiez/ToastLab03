package com.evaniskosophia.lab03;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView upLeft, upRight, downLeft, downRight;
    SharedPreferences preference;
    SharedPreferences.Editor editor;
    SeekBar sp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp=(SeekBar)findViewById(R.id.seekbar);
        System.out.println(sp);

        preference = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        editor=preference.edit();
        editor.apply();

        upLeft = findViewById(R.id.topleftview);
        upRight = findViewById(R.id.toprightview);
        downLeft = findViewById(R.id.bottomleftview);
        downRight = findViewById(R.id.bottomrightview);

        sp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChange = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChange = progress/10;
                System.out.println("progress changed " + progressChange);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                upLeft.setTextSize(progressChange);
                upRight.setTextSize(progressChange);
                downRight.setTextSize(progressChange);
                downLeft.setTextSize(progressChange);
            }
        });

    }

    public void sharedListener(View v){
        Context context = getApplicationContext();
        int duration = Toast.LENGTH_SHORT;
        CharSequence text = "something went wrong";
        int clicks = 0;
        switch(v.getId()){
            case R.id.topleftview:
                clicks = preference.getInt("upLeft", 0);
                text="upper left: "+ ("" +clicks);
                editor.putInt("upLeft", clicks+1);
                editor.apply();
                break;
            case R.id.toprightview:
                clicks = preference.getInt("upRight", 0);
                text="upper right: "+ ("" +clicks);
                editor.putInt("upRight", clicks+1);
                editor.apply();
                break;
            case R.id.bottomleftview:
                clicks = preference.getInt("downLeft", 0);
                text="bottom left: "+ ("" +clicks);
                editor.putInt("downLeft", clicks+1);
                editor.apply();
                break;
            case R.id.bottomrightview:
                clicks = preference.getInt("downRight", 0);
                text="bottom right: "+ ("" +clicks);
                editor.putInt("downRight", clicks+1);
                editor.apply();

                break;
            default:
                break;

        }
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
    }
}
