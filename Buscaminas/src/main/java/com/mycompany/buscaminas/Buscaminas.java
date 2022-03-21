/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buscaminas;

import java.util.Random;

/**
 *
 * @author msi
 */
public class Buscaminas {
    short tamXtablero;
    short tamYtablero;
    char[][] tablero;
    
    final char PUNT1 = '1';
    final char PUNT2 = '2';
    final char MINA = '.';

    //Método constructor
    public Buscaminas() {  
        tamXtablero = 7;
        tamYtablero = 6;
        tablero = new char[tamXtablero][tamYtablero];
        for (int x=0; x<tamXtablero; x++) {
            for (int y=0; y<tamYtablero; y++) {
                Random random = new Random();
                    int valor = random.nextInt(3); // Genera número entre 0 y 2
                    switch(valor) {
                        case 0:
                            tablero[x][y] = MINA;
                            break;
                        case 1:
                            tablero[x][y] = PUNT1;
                            break;
                        case 2:
                            tablero[x][y] = PUNT2;                            
                            break;                
                } 
            }
        }    
    }   
    
    public Buscaminas(short tamX, short tamY) { 
        tamXtablero = tamX;
        tamYtablero = tamY;
        tablero = new char[tamXtablero][tamYtablero];
        for (int x=0; x<tamXtablero; x++) {
            for (int y=0; y<tamYtablero; y++) {
                Random random = new Random();
                    int valor = random.nextInt(3); // Genera número entre 0 y 2
                    switch(valor) {
                        case 0:
                            tablero[x][y] = MINA;
                            break;
                        case 1:
                            tablero[x][y] = PUNT1;
                            break;
                        case 2:
                            tablero[x][y] = PUNT2;
                            break;
                    }
                 
            }
        }    
    }
    
    public void mostrarTableroConsola() {     
        for (int y=0; y<tamYtablero; y++) {
            for (int x=0; x<tamXtablero; x++) {
                System.out.print(tablero[x][y]);
            }
            System.out.println();
        }
        System.out.println();
    }
    
    public boolean colocarPunt(int columna, int puntuacion, int fila) {
        if(columna >= 0 && columna <tamXtablero) {
        switch(puntuacion) {
            case 0:
                tablero[columna][fila] = MINA;
                break;
            case 1:
                tablero[columna][fila] = PUNT1;
                break;
            case 2:
                tablero[columna][fila] = PUNT2;
                break;
        }
        return true;
    } else {
        return false;
        }
    }
    
    public int buscarFila(int columna){
        int fila = 0;
        while(tablero [columna][fila] == '.') {
            fila++;
            if (fila <= tamYtablero) {
                break;
            }
    } 
        return fila-1;
    }    
}
