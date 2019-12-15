package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

/*
Aquesta classe mostra la foto del Ranking en gran.
 */
public class VeureFotoActivity extends AppCompatActivity {

    // Conte un ImageView i un botó per tornar enrere
    ImageView image;
    Button tornarRanking;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_veure_foto);
        // Ocultem la barra de dalt perquè es vegi millor la foto
        this.getSupportActionBar().hide();

        // Trobem els elements
        image = findViewById(R.id.bigfoto);
        tornarRanking = findViewById(R.id.backToRanking);

        // Li donem l'acció de back al botó amb el mètode finish()
        tornarRanking.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                VeureFotoActivity.this.finish();
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        // Agafem la Uri de la imatge dels intents
        Uri photoUri = Uri.parse(getIntent().getStringExtra("photoUri"));
        // I se la afegim
        image.setImageURI(photoUri);
    }
}
