package com.example.tooleverywhere;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;

import androidx.annotation.Nullable;

public class MusicService extends Service {
    private MediaPlayer player;

    //Esto lo hice con la ayuda del chatgpt porque no me empanaba de como hacerlo

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        player = MediaPlayer.create(this, R.raw.app1);
        player.setLooping(false); // Con esto solo se me reproduce una vez
        player.start();
        return START_STICKY;
    }



    @Override
    public void onDestroy() {
        super.onDestroy();
        player.stop();
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
