package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    ArrayList<Record> aLP;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        aLP = new ArrayList<Record>();
        Bundle args = getIntent().getExtras();
        boolean nomEscrit = args.getBoolean("nomEscrit");
        if(nomEscrit){
            String nom = args.getString("nomGuanyador");
            int intentos = args.getInt("intentos");
            try {
                escriuFitxer(nom, intentos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void escriuFitxer(String nom, int intentos) throws IOException {
        if(existeixFitxer("rankingPuntuacions.txt")) {
            OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("rankingPuntuacions.txt", Context.MODE_APPEND));
            osw.write(nom + "\r\n");
            osw.write(intentos + "\r\n");
            osw.close();
        }
    }

    private void llegeixFitxer() throws IOException {
        if(existeixFitxer("rankingPuntuacions.txt")) {
            BufferedReader br = new BufferedReader(new InputStreamReader(openFileInput("rankingPuntuacions.txt")));
            String nom;
            int intents;
            Record r;
            while (br.ready()) {
                nom = br.readLine();
                intents = Integer.parseInt(br.readLine());
                r = new Record(nom, intents);
                aLP.add(r);
            }
        }
    }

    private boolean existeixFitxer(String nomFitxer) throws IOException {
        File f = new File("rankingPuntuacions.txt");
        if(f.exists()) {
            return true;
        }else{
            f.createNewFile();
            return true;
        }
    }

    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
