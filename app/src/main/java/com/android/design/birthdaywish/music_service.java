package com.android.design.birthdaywish;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class music_service  extends Service {

    private MediaPlayer player;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


         player = MediaPlayer.create(getApplicationContext(),R.raw.bdaytone);
         player.setLooping(true);
         player.start();

         // if we want to set the volume of the tone the we have to use AudioManager attribute

        AudioManager manager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);
        manager.setStreamVolume(AudioManager.STREAM_MUSIC, manager.getStreamMaxVolume(AudioManager.STREAM_MUSIC) / 2, 0);

        // this will play the song in full volume, if we want to decrease is programatically then do this

        // now it will play in 50% volume

        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        player.stop();
        player.release();
        super.onDestroy();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
