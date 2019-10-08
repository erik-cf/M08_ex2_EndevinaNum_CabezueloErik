package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStreamWriter;

public class RankingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Bundle args = getIntent().getExtras();
        boolean nomEscrit = args.getBoolean("nomEscrit");
        if(nomEscrit){
            String nom = args.getString("nomGuanyador");
            int intentos = args.getInt("intentos");
            try {
                escriuNomFitxer(nom, intentos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void escriuNomFitxer(String nom, int intentos) throws FileNotFoundException {
        OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("rankingPuntuacions", Context.MODE_APPEND));

    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
