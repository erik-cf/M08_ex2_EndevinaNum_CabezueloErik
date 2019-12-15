package com.example.endevinaelnumero;

import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

public class RankingDialog extends DialogFragment {

    // Declarem els objectes i variables necessaries
    public EditText eT1;
    Intent i;
    Bundle args;
    int intentos;
    LayoutInflater inflater;

    /*
    Metode onCreateDialog principal
     */
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Li donem l'estil d'AlertDialog al dialeg
        final AlertDialog.Builder dialeg = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        // Obtenim l'inflater
        inflater = getActivity().getLayoutInflater();
        // Inflem el layout
        View view = inflater.inflate(R.layout.activity_ranking_dialog, null);
        // Creem l'intent per a començar en algun lloc l'Activity del Ranking
        i = new Intent(getContext(), RankingActivity.class);
        // Rebem els arguments que es passen des de la MainActivity
        args = getArguments();
        intentos = args.getInt("intentos");
        // Utilitzem el mateix bundle per desar els arguments que li volguem passar al ranking
        args = new Bundle();
        args.putInt("intentos", intentos);

        // "Trobem" l'EditText del guanyador per a gestionar accions
        eT1 = view.findViewById(R.id.nomGuanyador);
        // Li donem una view al dialeg
        dialeg.setView(view);
        // Li diem que no sigui cancel·lable
        dialeg.setCancelable(false);
        // Li donem accions al botó positiu (Acceptar)
        dialeg.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Si no s'escriu nom pero li ha donat al boto acceptar
                // Sera com si l'usuari no volgues entrar al Ranking
                if(eT1.getText().equals("") || eT1.getText() == null){
                    args.putBoolean("nomEscrit", false);
                // Si ha ficat un nom, el pasarem al ranking per escriure-ho
                }else {
                    args.putBoolean("nomEscrit", true);
                    args.putString("nomGuanyador", eT1.getText().toString());
                    i.putExtras(args);
                }
                // Cridem a l'Activity del ranking
                startActivity(i);
            }
        // Ara li donem accions al boto negatiu
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                // Li passem al ranking que no vol sortir al ranking per a que no escrigui res
                i.putExtra("nomEscrit", false);
                // Comencem l'activitat del ranking
                startActivity(i);
            }
        });
        return dialeg.create();
    }
}
