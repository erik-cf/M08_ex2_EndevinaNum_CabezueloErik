package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    // Declarem els components que haurem de codificar.
    EditText eTNumero;
    Button b;
    // Declarem les variables que necessita la aplicació si o si
    int intentos;
    int numRandom;
    int numIntroduit;

    /*
    Metode principal onCreate
     */
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // "Trobem" l'EditText on s'introdueix el numero pel seu ID
        eTNumero = findViewById(R.id.eTNumero);
        // "Trobem" el botó Endevina pel seu ID
        b = findViewById(R.id.button);
        // Truquem al metode que els hi dona un listener als components
        creaListeners();
    }

    /*
    Metode onResume on li donem valor a les variables numRandom
    y intentos per a que cada cop que carregui l'Activity sigui
    un nou numero random i es tornin a 0 els intents
     */
    protected void onResume() {
        super.onResume();
        intentos = 0;
        numRandom = (int) ((Math.random() * 1) + 1);
    }

    /*
    Aquest metode crea els listeners per als components
     */
    public void creaListeners(){
        // Listener per al boto:
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Agafem el numero introduit per l'usuari a l'editText
                numIntroduit = Integer.parseInt(eTNumero.getText().toString());
                // Si el numero introduit es mes petit d'1 o mes gran de 100...
                if(numIntroduit < 1 || numIntroduit > 100){
                    // Mostrem un Toast informant del rang amb el que es juga
                    Toast.makeText(MainActivity.this, R.string.outOfRangeNum, Toast.LENGTH_LONG).show();
                }else {
                    // Si el numIntroduit és igual al numRandom
                    if (numIntroduit == numRandom) {
                        // Es suma 1 intent (al cap i a la fi no deixa de ser un intent encara que encerti)
                        intentos++;
                        // Informem a l'usuari que ha guanyat
                        Toast.makeText(MainActivity.this, R.string.winnerToast, Toast.LENGTH_LONG).show();
                        // Creem una instancia del dialeg que surt quan es guanya
                        DialogFragment dialeg = new RankingDialog();
                        // Creem un nou bundle per afegir totes les dades que cal passar al dialeg
                        Bundle args = new Bundle();
                        // Fiquem a dins els intentos
                        args.putInt("intentos", intentos);
                        // Li fiquem al dialeg el paquet amb les variables
                        dialeg.setArguments(args);
                        // Mostrem el dialeg
                        dialeg.show(getSupportFragmentManager(), "Has guanyat!");
                    // Si el numero introduit és més petit que el Random
                    } else if (numIntroduit < numRandom) {
                        // Informem que cal introduir un número més gran per a endevinar-ho
                        Toast.makeText(MainActivity.this, R.string.biggerNum, Toast.LENGTH_LONG).show();
                        // Sumem un intent
                        intentos++;
                    // Resta de casos:
                    // És a dir, el número introduit és més gran que el Random
                    } else {
                        // Informem que cal introduir un número més petit per a endevinar-ho
                        Toast.makeText(MainActivity.this, R.string.smallerNum, Toast.LENGTH_LONG).show();
                        // Sumem un intent
                        intentos++;
                    }

                }
                // En qualsevol cas, sempre deixem buit l'Edit Text perquè l'usuari no l'esborri cada cop
                eTNumero.setText("");
            }
        });

        // Aquest listener fa que funcioni el botó d'OK o l'Enter del teclat com el botó d'endevina
        eTNumero.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                // Si l'event no és null(es pica cap botó) y si el botó picat es l'enter o el botó es l'okay
                if((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)){
                    // Truquem al listener del boto
                    b.callOnClick();
                }
                return false;
            }
        });
    }

}
