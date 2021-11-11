/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pufferfishminesweeper.game;

/**
 * @author YERAY
 */
public class BombGenerator extends Thread {

    private final int mines;
    private final int rows;
    private final int columns;


    public BombGenerator(String[][] bombs, int mines) {
        rows = bombs.length;
        columns = bombs[1].length;
        this.mines = mines;
        this.run(bombs);
    }

    public void run(String[][] bombs) {
        generateBoard(bombs);
        generateNumbers(bombs);
        replace(bombs);
    }


    private void generateBoard(String[][] bombs) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                bombs[i][j] = "0";
            }
        }

        for (int i = 0; i < mines; i++) {
            int x = (int) (Math.random() * rows);
            int y = (int) (Math.random() * columns);
            bombs[x][y] = "9";
        }

    }

    private void generateNumbers(String[][] bombs) {
        for (int x = 0; x < rows; x++) {
            for (int y = 0; y < columns; y++) {
                if (Integer.parseInt(bombs[x][y]) >= 8) {
                    if (x == 0) {
                        if (y == 0) { //arriba izq
                            bombs[x + 1][y] = numberString(Integer.parseInt(bombs[x + 1][y]));
                            bombs[x][y + 1] = numberString(Integer.parseInt(bombs[x][y + 1]));
                            bombs[x + 1][y + 1] = numberString(Integer.parseInt(bombs[x + 1][y + 1]));
                        } else if (y == columns - 1) { //arriba der
                            bombs[x + 1][y] = numberString(Integer.parseInt(bombs[x + 1][y]));
                            bombs[x][y - 1] = numberString(Integer.parseInt(bombs[x][y - 1]));
                            bombs[x + 1][y - 1] = numberString(Integer.parseInt(bombs[x + 1][y - 1]));
                        } else { //fila arriba
                            bombs[x][y + 1] = numberString(Integer.parseInt(bombs[x][y + 1]));
                            bombs[x][y - 1] = numberString(Integer.parseInt(bombs[x][y - 1]));
                            bombs[x + 1][y] = numberString(Integer.parseInt(bombs[x + 1][y]));
                            bombs[x + 1][y + 1] = numberString(Integer.parseInt(bombs[x + 1][y + 1]));
                            bombs[x + 1][y - 1] = numberString(Integer.parseInt(bombs[x + 1][y - 1]));
                        }
                    } else if (y == 0 && x < rows - 1 && x > 0) { //columna izq + esquinas
                        bombs[x - 1][y] = numberString(Integer.parseInt(bombs[x - 1][y]));
                        bombs[x + 1][y] = numberString(Integer.parseInt(bombs[x + 1][y]));
                        bombs[x][y + 1] = numberString(Integer.parseInt(bombs[x][y + 1]));
                        bombs[x - 1][y + 1] = numberString(Integer.parseInt(bombs[x - 1][y + 1]));
                        bombs[x + 1][y + 1] = numberString(Integer.parseInt(bombs[x + 1][y + 1]));
                    } else if (x == rows - 1) {
                        if (y == 0) { //abajo izq
                            bombs[x - 1][y] = numberString(Integer.parseInt(bombs[x - 1][y]));
                            bombs[x][y + 1] = numberString(Integer.parseInt(bombs[x][y + 1]));
                            bombs[x - 1][y + 1] = numberString(Integer.parseInt(bombs[x - 1][y + 1]));
                        } else if (y == columns - 1) { //abajo der
                            bombs[x - 1][y] = numberString(Integer.parseInt(bombs[x - 1][y]));
                            bombs[x][y - 1] = numberString(Integer.parseInt(bombs[x][y - 1]));
                            bombs[x - 1][y - 1] = numberString(Integer.parseInt(bombs[x - 1][y - 1]));
                        } else { //fila abajo
                            bombs[x][y - 1] = numberString(Integer.parseInt(bombs[x][y - 1]));
                            bombs[x][y + 1] = numberString(Integer.parseInt(bombs[x][y + 1]));
                            bombs[x - 1][y] = numberString(Integer.parseInt(bombs[x - 1][y]));
                            bombs[x - 1][y - 1] = numberString(Integer.parseInt(bombs[x - 1][y - 1]));
                            bombs[x - 1][y + 1] = numberString(Integer.parseInt(bombs[x - 1][y + 1]));
                        }
                    } else if (y == columns - 1 && x < rows - 1 && x > 0) { //columna der + esquinas
                        bombs[x - 1][y] = numberString(Integer.parseInt(bombs[x - 1][y]));
                        bombs[x + 1][y] = numberString(Integer.parseInt(bombs[x + 1][y]));
                        bombs[x][y - 1] = numberString(Integer.parseInt(bombs[x][y - 1]));
                        bombs[x - 1][y - 1] = numberString(Integer.parseInt(bombs[x - 1][y - 1]));
                        bombs[x + 1][y - 1] = numberString(Integer.parseInt(bombs[x + 1][y - 1]));
                    } else { //resto
                        bombs[x - 1][y - 1] = numberString(Integer.parseInt(bombs[x - 1][y - 1]));
                        bombs[x - 1][y + 1] = numberString(Integer.parseInt(bombs[x - 1][y + 1]));
                        bombs[x - 1][y] = numberString(Integer.parseInt(bombs[x - 1][y]));
                        bombs[x][y - 1] = numberString(Integer.parseInt(bombs[x][y - 1]));
                        bombs[x][y + 1] = numberString(Integer.parseInt(bombs[x][y + 1]));
                        bombs[x + 1][y - 1] = numberString(Integer.parseInt(bombs[x + 1][y - 1]));
                        bombs[x + 1][y + 1] = numberString(Integer.parseInt(bombs[x + 1][y + 1]));
                        bombs[x + 1][y] = numberString(Integer.parseInt(bombs[x + 1][y]));
                    }
                }
            }
        }
    }

    private String numberString(int parseInt) {
        parseInt++;
        return String.valueOf(parseInt);
    }

    private void replace(String[][] bombs) {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (Integer.parseInt(bombs[i][j]) > 8) {
                    bombs[i][j] = "*";
                } else if (Integer.parseInt(bombs[i][j]) == 0) {
                    bombs[i][j] = "";
                }
            }
        }
    }

}
