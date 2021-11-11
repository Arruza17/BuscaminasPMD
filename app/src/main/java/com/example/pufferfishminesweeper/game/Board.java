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

    private int rows;
    private int columns;
    private static String[][] bombs;
    private int bombCount;

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public void setBombas(int cantBombas) {
        new BombGenerator(bombs, cantBombas);
    }

    public String[][] getBombas() {
        return bombs;
    }

    public int getBombCount() {
        return bombCount;
    }

    public Board(int difficulty) {
         bombCount = 0;
        switch (difficulty) {
            case 1:
                rows = 5;
                columns = 5;
                bombCount = 3;
                break;
            case 2:
                rows = 8;
                columns = 8;
                bombCount = 8;
                break;
            case 3:
                rows = 10;
                columns = 10;
                bombCount = 12;
                break;
            case 4:
                rows = 12;
                columns = 12;
                bombCount = 30;
                break;
        }
        bombs = new String[rows][columns];
        setBombas(bombCount);
    }


}
