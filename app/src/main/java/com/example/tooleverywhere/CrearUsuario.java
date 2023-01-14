package com.example.tooleverywhere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class CrearUsuario extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crearusuario);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaPlayer = MediaPlayer.create(this, R.raw.appeko);

        findViewById(R.id.button2).setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), Usuario.class);
            startActivity(intent);
        });

        final EditText editNombre = findViewById(R.id.Nombre);
        editNombre.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CrearUsuario.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("nombre", s.toString());
                editor.apply();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

        final EditText editMail = findViewById(R.id.Mail);
        editMail.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(CrearUsuario.this);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString("mail", s.toString());
                editor.apply();
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {
            }

        });

    }



    @Override
    public boolean onCreateOptionsMenu (Menu menu){
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected (MenuItem item){
        switch (item.getItemId()) {
            case R.id.action_izquierda:
                mediaPlayer.start();
                return true;
            case R.id.action_derecha:
                Intent intent = new Intent(CrearUsuario.this, Usuario.class);
                startActivity(intent);
                return true;
            case R.id.action_buscar:
                intent = new Intent(CrearUsuario.this, Busqueda.class);
                startActivity(intent);
                return true;
            case R.id.action_perfil:
                intent = new Intent(CrearUsuario.this, Usuario.class);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onPause () {
        super.onPause();
        stopService(new Intent(this, MusicService.class));
        mediaPlayer.pause();
    }

    @Override
    protected void onDestroy () {
        super.onDestroy();
        stopService(new Intent(this, MusicService.class));
        mediaPlayer.release();
    }



}

