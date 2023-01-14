package com.example.tooleverywhere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;


public class MainActivity extends AppCompatActivity {

    private ImageView gif;
    private MediaPlayer sonidoFallo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Igual hay una manera más eficiente de hacer esto, pero ni idea
        Intent musicService = new Intent(this, MusicService.class);
        startService(musicService);
        VideoView videoinicio = findViewById(R.id.inicio1);

        //Gracias mi pana https://www.youtube.com/watch?v=zSsRXMlTA2A
        String path = "android.resource://" + getPackageName() + "/" + R.raw.videoinicio;
        Uri uri = Uri.parse(path);
        videoinicio.setVideoURI(uri);
        videoinicio.start();
        //Con esto se buclea el video https://stackoverflow.com/questions/4746075/seamless-video-loop-with-videoview
        videoinicio.setOnCompletionListener ( new MediaPlayer.OnCompletionListener(){
            @Override
            public void onCompletion (MediaPlayer mediaPlayer){
                videoinicio.start();
            }
        });

        //Yep, le he dado acceso a internet a la app solo para esta tonteria

        gif = findViewById(R.id.Gifgato);
        String url = "https://media.tenor.com/gXMtMCZ5WccAAAAd/cat-head-bump-cat-nodding.gif";
        Uri urlparse = Uri.parse (url);
        Glide.with(getApplicationContext()).load(urlparse).into(gif);

        sonidoFallo = MediaPlayer.create(this, R.raw.appeko);
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
                    Intent intent = new Intent(MainActivity.this, Gato1.class);
                    startActivity(intent);
                    return true;
                case R.id.action_derecha:
                    sonidoFallo.start();
                    return true;
                case R.id.action_buscar:
                    intent = new Intent(MainActivity.this, Busqueda.class);
                    startActivity(intent);
                    return true;
                case R.id.action_perfil:
                    intent = new Intent(MainActivity.this, Usuario.class);
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

        //Con esto cuando cierro/pauso la app deja de hacer ruido.
    @Override
    protected  void onPause(){
        super.onPause();
        stopService(new Intent(this, MusicService.class));
        sonidoFallo.pause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
        sonidoFallo.release();
    }

    }