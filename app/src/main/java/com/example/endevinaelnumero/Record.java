package com.example.endevinaelnumero;

import android.net.Uri;

public class Record {

    // Atributs de la classe Record
    private String nomGuanyador;
    private int intentos;
    private Uri fotoPerfil;

    /*
    Constructor on s'omplen els atributs del Record
     */
    public Record(String nomGuanyador, int intentos, Uri fotoPerfil){
        this.nomGuanyador = nomGuanyador;
        this.intentos = intentos;
        this.fotoPerfil = fotoPerfil;
    }

    /**
     * Metodes getters and setters
     */
    public  String getNomGuanyador(){
        return this.nomGuanyador;
    }

    public int getIntentos() {
        return this.intentos;
    }

    public Uri getFotoPerfil(){
        return this.fotoPerfil;
    }

}
