package com.example.tooleverywhere;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Gato2 extends AppCompatActivity {

    private MediaPlayer gatovideo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gato2);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Intent musicService = new Intent(this, MusicService.class);
        stopService(musicService);

        gatovideo = MediaPlayer.create(this, R.raw.app3);
        gatovideo.start();

        VideoView video = findViewById(R.id.inicio5);

        String path = "android.resource://" + getPackageName() + "/" + R.raw.video3;
        Uri uri = Uri.parse(path);
        video.setVideoURI(uri);
        video.start();
        video.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mediaPlayer) {
                video.start();
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_izquierda:
                Intent intent = new Intent(Gato2.this, Gato3.class);
                startActivity(intent);
                return true;
            case R.id.action_derecha:
                intent = new Intent(Gato2.this, Gato1.class);
                startActivity(intent);
                return true;
            case R.id.action_buscar:
                intent = new Intent(Gato2.this, Busqueda.class);
                startActivity(intent);
                return true;
            case R.id.action_perfil:
                intent = new Intent(Gato2.this, Usuario.class);
                startActivity(intent);
                return true;
            case R.id.action_silenciar:
                Intent musicService = new Intent(this, MusicService.class);
                stopService(musicService);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected  void onPause(){
        super.onPause();
        stopService(new Intent(this, MusicService.class));
        gatovideo.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
        gatovideo.release();
    }

}