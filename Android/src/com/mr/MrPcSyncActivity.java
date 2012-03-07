package com.mr;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;

public class MrPcSyncActivity extends MMActivity{
    private static AudioManager mAudioManager; 
    private SoundPool soundPool;
    private int current_id;
    private int current_volume;
    private int max_volume;
    private int old_volume;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState, R.layout.main);
        findViewById(R.id.button1).setOnClickListener(this);
        findViewById(R.id.button2).setOnClickListener(this);
        findViewById(R.id.button3).setOnClickListener(this);
        findViewById(R.id.button4).setOnClickListener(this);
        
        mAudioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE); 
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        
        max_volume = mAudioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        old_volume = current_volume = mAudioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 1000);
        current_id = soundPool.load("system/media/audio/ui/camera_click.ogg", 1);
        
    }
    
    
    
    @Override
    public void onClick(View v) {
        super.onClick(v);
        
        if (v.getId()==R.id.button1){
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 0, 0);
        }else if(v.getId()==R.id.button2){
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, 5, 0);
        }
        else if(v.getId()==R.id.button3){
            mAudioManager.setStreamVolume(AudioManager.STREAM_MUSIC, max_volume, 0);
        }
        else if(v.getId()==R.id.button4){
            soundPool.play(current_id, 1, 1, 1, 0, 1);
        }
    }



    @Override
    protected void ok() {
    }
    @Override
    protected void cancel() {
        this.finish();
    }
}