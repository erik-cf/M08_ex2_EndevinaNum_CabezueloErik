package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;

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
    int intentos = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final int numRandom = (int) ((Math.random() * 101) + 1);
        eTNumero = findViewById(R.id.eTNumero);
        b = findViewById(R.id.button);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                int numIntroduit = Integer.parseInt(eTNumero.getText().toString());
                if(numIntroduit < 1 || numIntroduit > 100){
                    Toast.makeText(MainActivity.this, "Has de ficar un número entre 1 i 100!", Toast.LENGTH_LONG).show();
                    eTNumero.setText("");
                }else {
                    if (numIntroduit == numRandom) {
                        intentos++;
                        Toast.makeText(MainActivity.this, "Has endevinat el número!", Toast.LENGTH_LONG).show();
                    } else if (numIntroduit < numRandom) {
                        Toast.makeText(MainActivity.this, "El número que busquem es més gran que el que has introduit.", Toast.LENGTH_LONG).show();
                        intentos++;
                        eTNumero.setText("");
                    } else {
                        Toast.makeText(MainActivity.this, "El número que busquem es més petit que el que has introduit.", Toast.LENGTH_LONG).show();
                        intentos++;
                        eTNumero.setText("");
                    }
                }
            }
        });

        eTNumero.setOnEditorActionListener(new TextView.OnEditorActionListener() {

            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if((event != null && (event.getKeyCode() == KeyEvent.KEYCODE_ENTER)) || (actionId == EditorInfo.IME_ACTION_DONE)){
                    b.callOnClick();
                }
                return false;
            }
        });

    }
}
