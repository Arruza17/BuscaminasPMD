/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pufferfishminesweeper.game;

/**
 * @author YERAY
 */
public class Board {

    private int filas;
    private int columnas;
    private static String[][] bombas;

    public int getFilas() {
        return filas;
    }

    public void setFilas(int filas) {
        this.filas = filas;
    }

    public int getColumnas() {
        return columnas;
    }

    public void setColumnas(int columnas) {
        this.columnas = columnas;
    }

    public void setBombas(int cantBombas) {
        new BombGenerator(bombas, cantBombas);
    }

    public String[][] getBombas() {
        return bombas;
    }

    public Board(int difficulty) {
        int cantBombas = 0;
        switch (difficulty) {
            case 1:
                filas = 5;
                columnas = 5;
                cantBombas = 3;
                break;
            case 2:
                filas = 8;
                columnas = 8;
                cantBombas = 8;
                break;
            case 3:
                filas = 10;
                columnas = 10;
                cantBombas = 12;
                break;
            case 4:
                filas = 12;
                columnas = 12;
                cantBombas = 30;
                break;
        }
        bombas = new String[filas][columnas];
        setBombas(cantBombas);
    }


}
