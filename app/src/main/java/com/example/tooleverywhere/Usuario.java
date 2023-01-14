package com.example.tooleverywhere;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Usuario extends AppCompatActivity {

    private MediaPlayer mediaPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_usuario);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaPlayer = MediaPlayer.create(this, R.raw.appeko);

        findViewById(R.id.button).setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), CrearUsuario.class);
            startActivity(intent);
        });

        SharedPreferences preferencesNombre = PreferenceManager.getDefaultSharedPreferences(Usuario.this);
        String texto1 = preferencesNombre.getString("nombre", "");
        EditText editNombre = findViewById(R.id.Nombre);
        editNombre.setText(texto1);

        SharedPreferences preferencesMail = PreferenceManager.getDefaultSharedPreferences(Usuario.this);
        String texto2 = preferencesMail.getString("mail", "");
        EditText editMail = findViewById(R.id.Mail);
        editMail.setText(texto2);

        EditText Nombre = findViewById(R.id.Nombre);
        editNombre.setFocusable(false);
        editNombre.setCursorVisible(false);

        EditText Mail = findViewById(R.id.Mail);
        editMail.setFocusable(false);
        editMail.setCursorVisible(false);
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
                    Intent intent = new Intent(Usuario.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                case R.id.action_buscar:
                    intent = new Intent(Usuario.this, Busqueda.class);
                    startActivity(intent);
                    return true;
                case R.id.action_perfil:
                    intent = new Intent(Usuario.this, Usuario.class);
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

