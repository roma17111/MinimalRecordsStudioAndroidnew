package com.example.minimalrecords;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

public class Portfolio extends AppCompatActivity {

    MediaPlayer mPlayer;
    MediaPlayer mPlayer1;
    Button playButton, pauseButton, stopButton,
            playButton1, pauseButton1, stopButton1;
    SeekBar volumeControl, volumeControl1;
    AudioManager audioManager;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_portfolio);
        mPlayer=MediaPlayer.create(this, R.raw.s);
        mPlayer1=MediaPlayer.create(this, R.raw.a);
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        mPlayer1.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay1();
            }
        });
        mPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlay();
            }
        });
        playButton = findViewById(R.id.play);
        pauseButton = findViewById(R.id.pause);
        stopButton = findViewById(R.id.stop);
        playButton1 = findViewById(R.id.play1);
        pauseButton1 = findViewById(R.id.pause1);
        stopButton1 = findViewById(R.id.stop1);

        audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        int maxVolume = audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        int curValue = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        volumeControl = findViewById(R.id.seekBar3);
        volumeControl.setMax(maxVolume);
        volumeControl.setProgress(curValue);
        volumeControl.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
            }
            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }
            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        pauseButton1.setEnabled(false);
        stopButton1.setEnabled(false);
    }
    private void stopPlay(){
        mPlayer.stop();
        pauseButton.setEnabled(false);
        stopButton.setEnabled(false);
        try {
            mPlayer.prepare();
            mPlayer.seekTo(0);
            playButton.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play(View view){

        mPlayer.start();
        playButton.setEnabled(false);
        pauseButton.setEnabled(true);
        stopButton.setEnabled(true);

    }
    public void pause(View view){

        mPlayer.pause();
        playButton.setEnabled(true);
        pauseButton.setEnabled(false);
        stopButton.setEnabled(true);

    }
    public void stop(View view){
        stopPlay();
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPlayer.isPlaying()){
            stopPlay();
        }
        if (mPlayer1.isPlaying()) {
            stopPlay1();
        }
    }

    private void stopPlay1(){
        mPlayer1.stop();
        pauseButton1.setEnabled(false);
        stopButton1.setEnabled(false);
        try {
            mPlayer1.prepare();
            mPlayer1.seekTo(0);
            playButton1.setEnabled(true);
        }
        catch (Throwable t) {
            Toast.makeText(this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public void play1(View view){
        mPlayer1.start();
        playButton1.setEnabled(false);
        pauseButton1.setEnabled(true);
        stopButton1.setEnabled(true);
    }
    public void pause1(View view){

        mPlayer1.pause();
        playButton1.setEnabled(true);
        pauseButton1.setEnabled(false);
        stopButton1.setEnabled(true);
    }
    public void stop1(View view){
        stopPlay1();
    }

}