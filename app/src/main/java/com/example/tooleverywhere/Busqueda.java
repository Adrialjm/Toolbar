package com.example.tooleverywhere;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Busqueda extends AppCompatActivity {

    private MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_busqueda);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mediaPlayer = MediaPlayer.create(this, R.raw.appeko);

        Button buscar = findViewById(R.id.Busqueda);
        buscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText buscado = findViewById(R.id.buscado);
                String textobuscado = buscado.getText().toString();

                if (textobuscado.equalsIgnoreCase("Gato pelea")) {
                    Intent intent = new Intent(Busqueda.this, Gato1.class);
                    startActivity(intent);
                } else if (textobuscado.equalsIgnoreCase("Gato arabe")) {
                    Intent intent = new Intent(Busqueda.this, Gato2.class);
                    startActivity(intent);
                }
                else if (textobuscado.equalsIgnoreCase("Gato saludo")) {
                    Intent intent = new Intent(Busqueda.this, Gato3.class);
                    startActivity(intent);
                }
                else if (textobuscado.equalsIgnoreCase("Gato bluetooth")) {
                    Intent intent = new Intent(Busqueda.this, Gato4.class);
                    startActivity(intent);
                }
                else if (textobuscado.equalsIgnoreCase("Gato granjero")) {
                    Intent intent = new Intent(Busqueda.this, Gato5.class);
                    startActivity(intent);
                }
                else if (textobuscado.equalsIgnoreCase("Loli neko")) {
                    Intent intent = new Intent(Busqueda.this, Rickroll.class);
                    startActivity(intent);
                }
                else if (textobuscado.equalsIgnoreCase("nigga")) {
                    mediaPlayer = MediaPlayer.create(Busqueda.this, R.raw.mikonword);
                    mediaPlayer.start();
                }
                else if (textobuscado.equalsIgnoreCase("Amogus")) {
                    Intent intent = new Intent (Busqueda.this, Amogus.class);
                    startActivity(intent);
                }
                else {
                    mediaPlayer = MediaPlayer.create(Busqueda.this, R.raw.pekoractm);
                    mediaPlayer.start();}

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
                mediaPlayer.start();
                return true;
            case R.id.action_derecha:
                Intent intent = new Intent(Busqueda.this, MainActivity.class);
                startActivity(intent);
                return true;
            case R.id.action_buscar:
                intent = new Intent(Busqueda.this, Busqueda.class);
                startActivity(intent);
                mediaPlayer.start();
                return true;
            case R.id.action_perfil:
                intent = new Intent(Busqueda.this, Usuario.class);
                startActivity(intent);
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

}