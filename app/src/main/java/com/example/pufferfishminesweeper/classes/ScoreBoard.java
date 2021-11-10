package com.example.pufferfishminesweeper.classes;

import java.io.Serializable;

public class ScoreBoard implements Serializable {

    private String nombre;
    private Integer puntuacion;

    public ScoreBoard(String nombre, Integer puntuacion) {
        this.nombre = nombre;
        this.puntuacion = puntuacion;
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
    
}
