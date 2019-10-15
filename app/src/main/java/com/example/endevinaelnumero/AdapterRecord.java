package com.example.endevinaelnumero;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterRecord extends BaseAdapter {
    protected Activity activity;
    protected ArrayList<Record> records;

    public AdapterRecord(Activity activity, ArrayList<Record> records){
        this.activity = activity;
        this.records = records;
    }

    @Override
    public int getCount() {
        return this.records.size();
    }

    @Override
    public Object getItem(int position) {
        return records.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.listview, null);
        }
            Record r = new Record();

            TextView nomGuanyador = view.findViewById(R.id.nomGuanyador);
            nomGuanyador.setText(nomGuanyador.getText() + r.getNomGuanyador());

            TextView intentos = view.findViewById(R.id.intentsRanking);
            intentos.setText(intentos.getText() + String.valueOf(r.getIntentos()));

            ImageView imatge = view.findViewById(R.id.fotoPerfil);
            imatge.setImageDrawable(r.getFotoPerfil());

            return view;

        }
}
