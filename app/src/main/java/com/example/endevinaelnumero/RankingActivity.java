package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;

public class RankingActivity extends AppCompatActivity {
    // Declarem els objectes que la classe necessitarà
    File f;
    ArrayList<Record> aLP;
    ListView lv;
    AdapterRecord adapterRecords;
    boolean nomEscrit;
    String nom;
    int intentos;
    Bundle args;


    /*
    Metode onCreate de l'activity
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        // Això fa que quan li donis al botó enrere de la barra superior, et torni a la MainActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // Inicialitzem el File
        f = new File(getFilesDir(), "rankingPuntuacions.txt");
        // Inicialitzem l'array de Records
        aLP = new ArrayList<Record>();
        // Truquem al métode que rep els arguments
        repArgs();
        // Si hi ha un nomEscrit al dialeg vol dir que l'usuari volia entrar al ranking
        if(nomEscrit) {
            try {
                // Truquem al metode escriuFitxer
                escriuFitxer(nom, intentos);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // "Trobem" la listView del layout
        lv = findViewById(R.id.listView);
        // Inicialitzem l'adapter donant-li l'arrayList aLP com a parametre
        adapterRecords = new AdapterRecord(this, aLP);

        try {
            // Truquem al metode llegeixFitxer per omplir l'adapter
            llegeixFitxer();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Truquem al metode que ordena l'array pel nombre d'intents
        ordenaArray();
        // Li fiquem l'adapter al listView un cop hem terminat de gestionar-ho
        lv.setAdapter(adapterRecords);
    }

    /*
    Aquest metode rep els arguments que es passen des del dialeg a aquesta Activity
     */
    private void repArgs(){
        // Rebem els Extras passats del dialeg al Bundle args
        args = getIntent().getExtras();
        // Agafem el boolea que serveix per saber si s'ha escrit un nom o no per a entrar al record
        nomEscrit = args.getBoolean("nomEscrit");
        // Si el nom esta escrit
        if(nomEscrit) {
            // Agafem el nom
            nom = args.getString("nomGuanyador");
            // Agafem els intents
            intentos = args.getInt("intentos");
        }
    }

    /*
    Aquest metode escriu les dades al fitxer
     */
    private void escriuFitxer(String nom, int intentos) throws IOException {
        // Comprovem si existeix el fitxer abans d'intentar escriure en ell
        if(existeixFitxer()) {
            // Necessitarem un FileWriter per a escriure al fitxer,
            // li diem que no ha de sobreescriure les dades que ja conte,
            // sino que les ha de ficar al final del fitxer
            FileWriter fw = new FileWriter(f, true);
            // Escrivim les dades
            fw.write(nom + "\r\n");
            fw.write(intentos + "\r\n");
            // Tanquem el FileWriter
            fw.close();
        }
    }

    /*
    Aquest metode llegeix les dades del fitxer i les fica
    dins de l'Adapter per a que es mostri al ListView
     */
    private void llegeixFitxer() throws IOException {
        // Comprovem si existeix cap fitxer per llegir
        if(existeixFitxer()) {
            // Necessitarem un BufferedReader per a llegir el fitxer
            BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));
            // Declarem les variables necessaries per a ficar les dades del fitxer
            String nom;
            int intents;
            // Declarem un objecte de Record per a ficar-ho dins l'adapter
            Record r;
            // Mentre que hi hagi cap cosa per llegir:
            while (br.ready()) {
                // Guardem la primera linea que es el nom d'usuari a la variable nom
                nom = br.readLine();
                // Llegim la següent linia que son els intents
                intents = Integer.parseInt(br.readLine());
                // Inicialitzem l'objecte de Record amb les dades obtenides
                r = new Record(nom, intents);
                // Fiquem l'objecte dins l'Adapter
                adapterRecords.add(r);
            }
        }
    }

    /*
    Aquest metode comprova si existeix el fitxer o no a la ruta d'aquest
     */
    private boolean existeixFitxer() throws IOException {
        // Si existeix
        if(f.exists()) {
            // Retornem true
            return true;
        // Si no...
        }else{
            // El creem buit
            f.createNewFile();
            // Si es crea retornem true perque ja existeix
            return true;
        }
    }

    /*
    Aquest metode ordena l'array per tal que mostri al que tingui menys intents el primer
     */
    private void ordenaArray(){
        // Utilitzem el metode sort
        adapterRecords.sort(new Comparator<Record>() {

            // Comparem els intents d'1 record amb el següent i aixi successivament
            public int compare(Record record, Record t1) {
                return record.getIntentos() - t1.getIntentos();
            }
        });
    }

    /*
    Aquest metode fa que quan li donis al botó enrere de la barra superior, et torni a la MainActivity
     */
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
}
