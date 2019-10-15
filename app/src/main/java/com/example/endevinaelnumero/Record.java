package com.example.endevinaelnumero;

import android.graphics.drawable.Drawable;

public class Record {
    String nomGuanyador;
    int intentos;
    Drawable fotoPerfil;

    public Record(){

    }

    public Record(String nomGuanyador, int intentos){
        this.nomGuanyador = nomGuanyador;
        this.intentos = intentos;
    }

    public  String getNomGuanyador(){
        return this.nomGuanyador;
    }

    public int getIntentos() {
        return intentos;
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
