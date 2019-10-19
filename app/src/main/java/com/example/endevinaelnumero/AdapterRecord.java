package com.example.endevinaelnumero;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

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
        Record r = getItem(position);
        // Si la view no es nula, inflem el layout
        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.listview, parent, false);
        }
        // Trobem els diferents components del layout del listView
        TextView nomGuanyador = view.findViewById(R.id.nomRanking);
        TextView intentos = view.findViewById(R.id.intentsRanking);
        ImageView imatge = view.findViewById(R.id.fotoPerfil);

        // Li fiquem text a cada posicio del listView
        nomGuanyador.setText("NOM D'USUARI: " + r.getNomGuanyador());
        intentos.setText("NOMBRE D'INTENTS: " + String.valueOf(r.getIntentos()));
        imatge.setImageDrawable(r.getFotoPerfil());

        return view;

    }
}
