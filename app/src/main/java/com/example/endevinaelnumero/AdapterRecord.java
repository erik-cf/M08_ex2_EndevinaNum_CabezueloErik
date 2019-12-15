package com.example.endevinaelnumero;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
/*
Aquesta classe és la classe del Adapter de la ListView que hi ha al Ranking.
 */
public class AdapterRecord extends ArrayAdapter<Record> {

    /*
    Constructor de l'adapter per al ranking de puntuacions
     */
    public AdapterRecord(Context c, ArrayList<Record> records){
        super(c, 0, records);
    }

    /*
    Métode que li dona format al listview
     */
    public View getView(int position, View view, ViewGroup parent) {
        // Creem un nou objecte Record on guardarem cada item del listview
       final  Record record = getItem(position);
        // Si la view no es nula, inflem el layout
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        }
        // Trobem els diferents components del layout del listView
        TextView nomGuanyador = view.findViewById(R.id.nomRanking);
        TextView intentos = view.findViewById(R.id.intentsRanking);
        ImageView fotoPerfil = view.findViewById(R.id.fotoPerfil);

        // Li fiquem text a cada posicio del listView
        fotoPerfil.setImageURI(record.getFotoPerfil());
        nomGuanyador.setText("NOM D'USUARI: " + record.getNomGuanyador());
        intentos.setText("NOMBRE D'INTENTS: " + String.valueOf(record.getIntentos()));

        // Afegim un ActionListener a la foto que ens la mostra més gran si hi cliquem:
        fotoPerfil.setOnClickListener(new View.OnClickListener(){

            public void onClick(View view) {
                Intent i =  new Intent(getContext(), VeureFotoActivity.class);
                i.putExtra("photoUri", record.getFotoPerfil().toString());
                view.getContext().startActivity(i);
            }
        });


        return view;

    }
}
