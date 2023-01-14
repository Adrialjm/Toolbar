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

public class Rickroll extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rickroll);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        VideoView Rickroll = findViewById(R.id.Rickroll);
        String path = "android.resource://" + getPackageName() + "/" + R.raw.rickroll;
        Uri uri = Uri.parse(path);
        Rickroll.setVideoURI(uri);
        Rickroll.start();
        //Con esto se buclea el video https://stackoverflow.com/questions/4746075/seamless-video-loop-with-videoview
        Rickroll.setOnCompletionListener ( new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion (MediaPlayer mediaPlayer){
                Rickroll.start();
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
                Intent intent = new Intent(Rickroll.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_buscar:
                intent = new Intent(Rickroll.this, Busqueda.class);
                startActivity(intent);
                return true;
            case R.id.action_perfil:
                intent = new Intent(Rickroll.this, Usuario.class);
                startActivity(intent);
                return true;
            case R.id.action_derecha:
                intent = new Intent(Rickroll.this, Busqueda.class);
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
}

