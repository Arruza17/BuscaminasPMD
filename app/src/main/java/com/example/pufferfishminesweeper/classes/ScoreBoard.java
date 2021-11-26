package com.example.pufferfishminesweeper.classes;

import java.io.Serializable;
import java.sql.Blob;

public class ScoreBoard implements Serializable {

    private String nombre;
    private Integer puntuacion;
    private byte[] image;

    public ScoreBoard(String nombre, Integer puntuacion, byte[] image) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
        this.image = image;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getPuntuacion() {
        return puntuacion;
    }

    public void setPuntuacion(Integer puntuacion) {
        this.puntuacion = puntuacion;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
