package com.example.endevinaelnumero;

import android.graphics.drawable.Drawable;

public class Record {

    // Atributs de la classe Record
    private String nomGuanyador;
    private int intentos;
    private Drawable fotoPerfil;

    /*
    Constructor buit
     */
    public Record(){

    }

    /*
    Constructor on se li passa un nom i els intents
     */
    public Record(String nomGuanyador, int intentos){
        this.nomGuanyador = nomGuanyador;
        this.intentos = intentos;
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

    public Drawable getFotoPerfil(){
        return this.fotoPerfil;
    }

    public void setFotoPerfil(Drawable fotoPerfil){
        this.fotoPerfil = fotoPerfil;
    }
}
