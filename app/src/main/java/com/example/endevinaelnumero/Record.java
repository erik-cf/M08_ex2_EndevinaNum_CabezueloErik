package com.example.endevinaelnumero;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class Record {

    // Atributs de la classe Record
    private String nomGuanyador;
    private int intentos;
    private Bitmap fotoPerfil;

    /*
    Constructor buit
     */
    public Record(){

    }

    public Record(String nomGuanyador, int intentos, Bitmap fotoPerfil){
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

    public void setNomGuanyador(String nomGuanyador) {
        this.nomGuanyador = nomGuanyador;
    }

    public void setIntentos(int intentos) {
        this.intentos = intentos;
    }

    public Bitmap getFotoPerfil(){
        return this.fotoPerfil;
    }

    public void setFotoPerfil(Bitmap fotoPerfil){
        this.fotoPerfil = fotoPerfil;
    }
}
