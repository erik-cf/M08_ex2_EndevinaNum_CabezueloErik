package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.widget.ListView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;

import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;


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
    File rutaImatgePerfil;
    File rutaImatges;
    String rutaImatgePerfilString;
    Uri photoURI;
    static final int REQUEST_IMAGE_CAPTURE = 1;


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
        // Truquem al métode que rep els arguments
        repArgs();
        rutaImatges = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        // Si s'ha escrit un nom de guanyador, passem a fer-li la foto:
        if(nomEscrit)
            takePhoto();
    }

    /*
    Aquest metode rep els arguments que es passen des de la anterior Activity a aquesta Activity
     */
    private void repArgs(){
        // Rebem els Extras
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
    Aquest metode escriu les dades al fitxer per després poder-les recuperar i que el Ranking sigui persistent
     */
    private void escriuFitxer() throws IOException {
        // Comprovem si existeix el fitxer abans d'intentar escriure en ell
        if(existeixFitxer()) {
            // Necessitarem un FileWriter per a escriure al fitxer,
            // li diem que no ha de sobreescriure les dades que ja conte,
            // sino que les ha de ficar al final del fitxer
            FileWriter fw = new FileWriter(f, true);
            // Escrivim les dades
            fw.write(nom + "\r\n");
            fw.write(intentos + "\r\n");
            fw.write(photoURI.toString() + "\r\n");
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
            Uri fitxerUri;
            while (br.ready()) {
                // Guardem la primera linea que es el nom d'usuari a la variable nom
                nom = br.readLine();
                // Llegim la següent linia que son els intents
                intents = Integer.parseInt(br.readLine());
                //Llegim la uri de la foto:
                fitxerUri = Uri.parse(br.readLine());
                // Inicialitzem l'objecte de Record amb les dades obtenides
                r = new Record(nom, intents, fitxerUri);
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

    /*
    Aquest mètode crida a l'Intent de la càmera, però previament fa unes accions
    com trucar al mètode que crea l'arxiu d'imatge.
     */
    private void takePhoto() {
        // Creem l'Intent de la càmera:
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        // Si hi ha una aplicació per fer de càmera, seguim, si no, mostrem un Toast:
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            try {
                // Creem la imatge de 0:
                rutaImatgePerfil = creaImatge();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

            // Si s'ha pogut crear la imatge:
            if (rutaImatgePerfil != null) {
                // Agafem la Uri de la imatge per passar-li a l'Intent de la camera
                // on ha de guardar la imatge:
                photoURI = FileProvider.getUriForFile(this,
                        this.getApplicationContext().getPackageName() + ".fileprovider",
                        rutaImatgePerfil);
                // Li passem a la càmera on ha de guardar la imatge:
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);

                // Comencem l'Activity de càmera:
                startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
            }
        }else{
            Toast.makeText(this, "Necessites càmera o aplicació de càmera perquè funcioni!", Toast.LENGTH_SHORT).show();
        }
    }

    /*
    Aquest es el mètode onActivityResult, que s'executa un cop la càmera ha tancat.
    Es crida automàticament, no s'ha de cridar manualment.
     */
    protected void onActivityResult(int requestCode, int resultCode, Intent i) {
        // Si tot va bé al intent de càmera:
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            try {
                // Escribim al fitxer les dades del usuari
                escriuFitxer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
    Aquest mètode ens crea la imatge de la foto.
     */
    private File creaImatge() throws IOException {
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        // Creem un arxiu on guardarem la imatge:
        File image = File.createTempFile(nom + intentos + timeStamp, ".jpg", rutaImatges);
        // Assignem el path de la foto (ruta)
        rutaImatgePerfilString = image.getAbsolutePath();
        // Retornem la imatge creada:
        return image;
    }

    /*
    El mètode onResume, on volem que es carregui l'adapter perquè s'actualitzi cada cop que
    es carregui aquesta Activity
     */
    public void onResume(){
        super.onResume();
        // Inicialitzem l'array de Records
        aLP = new ArrayList<Record>();

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



}
