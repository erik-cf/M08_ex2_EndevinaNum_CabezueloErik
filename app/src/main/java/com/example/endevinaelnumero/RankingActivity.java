package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {

    ArrayList<Record> aLP;
    ListView lv;
    File f;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        f = new File(getFilesDir(), "rankingPuntuacions.txt");
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

        try {
            llegeixFitxer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        lv = findViewById(R.id.listView);
        AdapterRecord adapterRecords = new AdapterRecord(this, aLP);
        lv.setAdapter(adapterRecords);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void escriuFitxer(String nom, int intentos) throws IOException {
        if(existeixFitxer()) {
            FileWriter fw = new FileWriter(f, true);
            fw.write(nom + "\r\n");
            fw.write(intentos + "\r\n");
            fw.close();
        }
    }

    private void llegeixFitxer() throws IOException {
        if(existeixFitxer()) {
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
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

    private boolean existeixFitxer() throws IOException {

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
