package com.example.endevinaelnumero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.PopupMenu;
import android.widget.Toast;

public class RankingDialog extends DialogFragment {

    public EditText eT1;

    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final AlertDialog.Builder dialeg = new AlertDialog.Builder(getActivity(), R.style.AlertDialogTheme);
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.activity_ranking_dialog, null);
        eT1 = view.findViewById(R.id.nomGuanyador);
        dialeg.setView(view);
        dialeg.setCancelable(false);
        dialeg.setPositiveButton(R.string.accept, new DialogInterface.OnClickListener() {

            public void onClick(DialogInterface dialog, int which) {
                Intent i = new Intent(getContext(), RankingActivity.class);
                if(eT1.getText().equals("") ||eT1.getText() == null){
                    i.putExtra("nomEscrit", false);
                }else{
                    i.putExtra("nomEscrit", true);
                    i.putExtra("nomGuanyador", eT1.getText());
                }
                startActivity(i);
            }
        }).setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        return dialeg.create();
    }
}
