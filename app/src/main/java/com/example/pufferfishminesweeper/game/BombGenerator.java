/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.pufferfishminesweeper.game;

/**
 *
 * @author YERAY
 */
public class BombGenerator extends Thread {

    private final int minas;
    private final int filas;
    private final int columnas;

    public BombGenerator(String[][] bombas, int minas) {
        filas = bombas.length;
        columnas = bombas[1].length;
        this.minas = minas;
        this.run(bombas);
    }

    public void run(String[][] bombas) {
        generarTablero(bombas);
        generarNumeros(bombas);
        sustituirBombas(bombas);
    }


    private void generarTablero(String[][] bombas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                bombas[i][j] = "0";
            }
        }

        for (int i = 0; i < minas; i++) {
            int x = (int) (Math.random() * filas);
            int y = (int) (Math.random() * columnas);
            bombas[x][y] = "9";
        }

    }

    private void generarNumeros(String[][] bombas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (Integer.parseInt(bombas[i][j]) >= 8) {
                    if (i == 0) {
                        if (j == 0) { //arriba izq
                            bombas[i + 1][j] = cambiarNumero(Integer.parseInt(bombas[i + 1][j]));
                            bombas[i][j + 1] = cambiarNumero(Integer.parseInt(bombas[i][j + 1]));
                            bombas[i + 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j + 1]));
                        } else if (j == columnas - 1) { //arriba der
                            bombas[i + 1][j] = cambiarNumero(Integer.parseInt(bombas[i + 1][j]));
                            bombas[i][j - 1] = cambiarNumero(Integer.parseInt(bombas[i][j - 1]));
                            bombas[i + 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j - 1]));
                        } else { //fila arriba
                            bombas[i][j + 1] = cambiarNumero(Integer.parseInt(bombas[i][j + 1]));
                            bombas[i][j - 1] = cambiarNumero(Integer.parseInt(bombas[i][j - 1]));
                            bombas[i + 1][j] = cambiarNumero(Integer.parseInt(bombas[i + 1][j]));
                            bombas[i + 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j + 1]));
                            bombas[i + 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j - 1]));
                        }
                    } else if (j == 0 && i < filas - 1 && i > 0) { //columna izq + esquinas
                        bombas[i - 1][j] = cambiarNumero(Integer.parseInt(bombas[i - 1][j]));
                        bombas[i + 1][j] = cambiarNumero(Integer.parseInt(bombas[i + 1][j]));
                        bombas[i][j + 1] = cambiarNumero(Integer.parseInt(bombas[i][j + 1]));
                        bombas[i - 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j + 1]));
                        bombas[i + 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j + 1]));
                    } else if (i == filas - 1) {
                        if (j == 0) { //abajo izq
                            bombas[i - 1][j] = cambiarNumero(Integer.parseInt(bombas[i - 1][j]));
                            bombas[i][j + 1] = cambiarNumero(Integer.parseInt(bombas[i][j + 1]));
                            bombas[i - 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j + 1]));
                        } else if (j == columnas - 1) { //abajo der
                            bombas[i - 1][j] = cambiarNumero(Integer.parseInt(bombas[i - 1][j]));
                            bombas[i][j - 1] = cambiarNumero(Integer.parseInt(bombas[i][j - 1]));
                            bombas[i - 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j - 1]));
                        } else { //fila abajo
                            bombas[i][j - 1] = cambiarNumero(Integer.parseInt(bombas[i][j - 1]));
                            bombas[i][j + 1] = cambiarNumero(Integer.parseInt(bombas[i][j + 1]));
                            bombas[i - 1][j] = cambiarNumero(Integer.parseInt(bombas[i - 1][j]));
                            bombas[i - 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j - 1]));
                            bombas[i - 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j + 1]));
                        }
                    } else if (j == columnas - 1 && i < filas - 1 && i > 0) { //columna der + esquinas
                        bombas[i - 1][j] = cambiarNumero(Integer.parseInt(bombas[i - 1][j]));
                        bombas[i + 1][j] = cambiarNumero(Integer.parseInt(bombas[i + 1][j]));
                        bombas[i][j - 1] = cambiarNumero(Integer.parseInt(bombas[i][j - 1]));
                        bombas[i - 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j - 1]));
                        bombas[i + 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j - 1]));
                    } else { //resto
                        bombas[i - 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j - 1]));
                        bombas[i - 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i - 1][j + 1]));
                        bombas[i - 1][j] = cambiarNumero(Integer.parseInt(bombas[i - 1][j]));
                        bombas[i][j - 1] = cambiarNumero(Integer.parseInt(bombas[i][j - 1]));
                        bombas[i][j + 1] = cambiarNumero(Integer.parseInt(bombas[i][j + 1]));
                        bombas[i + 1][j - 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j - 1]));
                        bombas[i + 1][j + 1] = cambiarNumero(Integer.parseInt(bombas[i + 1][j + 1]));
                        bombas[i + 1][j] = cambiarNumero(Integer.parseInt(bombas[i + 1][j]));
                    }
                }
            }
        }
    }

    private String cambiarNumero(int parseInt) {
        parseInt++;
        return String.valueOf(parseInt);
    }

    private void sustituirBombas(String[][] bombas) {
        for (int i = 0; i < filas; i++) {
            for (int j = 0; j < columnas; j++) {
                if (Integer.parseInt(bombas[i][j]) > 8) {
                    bombas[i][j] = "*";
                }
            }
        }
    }

}
