/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.buscaminas;

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 *
 * @author msi
 */
public class Tablero extends GridPane {

    GridPane grid = new GridPane();   
    Buscaminas buscaminas;
       
    //Variable de las dimensiones del rectángulo
    double ANCHURA_RECT = 100;
    double ALTURA_RECT = 100;
    
    //Varibale del contador
    int contador;
    
    //Variable vidas
    int vidas = 3;
    
    //Creación array de rectángulos
    Rectangle [][] rectangulos;
    
    public Tablero (Buscaminas buscaminas) {
        
        this.buscaminas = buscaminas;
         
        //Creación array rectangulos
        rectangulos = new Rectangle [buscaminas.tamXtablero][buscaminas.tamYtablero];
        
        //For para que las imágenes se generen en la posición que le corresponde
        for (int x=0; x<buscaminas.tamXtablero; x++) {
            for (int y=0; y<buscaminas.tamYtablero; y++) {
                
                //Donde haya una MINA en el tablero se pone la imagen de mina                
                if (buscaminas.tablero[x][y] == buscaminas.MINA) {
                    Image mina = new Image(getClass().getResourceAsStream("/images/mina.jpg"));
                    ImageView imgView3 = new ImageView(mina);
                    grid.add(imgView3, buscaminas.tamXtablero, buscaminas.tamYtablero);
                    this.add(imgView3, x, y);
                }
                  
                //Donde haya PUNT1 en el tablero se pone la imagen de numero1 que simboliza un punto              
                if (buscaminas.tablero[x][y] == buscaminas.PUNT1) {
                    Image numero1 = new Image(getClass().getResourceAsStream("/images/numero1.jpg"));
                    ImageView imgView1 = new ImageView(numero1);
                    grid.add(imgView1, buscaminas.tamXtablero, buscaminas.tamYtablero);
                    this.add(imgView1, x, y);
                }

                //Donde haya PUNT2 en el tablero se pone la imagen de numero2 que simboliza dos puntos  
                if (buscaminas.tablero[x][y] == buscaminas.PUNT2) {
                    Image numero2 = new Image(getClass().getResourceAsStream("/images/numero2.jpg"));
                    ImageView imgView2 = new ImageView(numero2);
                    grid.add(imgView2, buscaminas.tamXtablero, buscaminas.tamYtablero);
                    this.add(imgView2, x, y);
                }
                
            }    
        }    
                //For para que se generen los rectangulos que tapan las imágenes
                for(int x=0; x<buscaminas.tamXtablero; x++) {
                    for(int y=0; y<buscaminas.tamYtablero; y++) {            
                        Rectangle rect = new Rectangle(buscaminas.tamXtablero, buscaminas.tamYtablero, ANCHURA_RECT, ALTURA_RECT);
                        this.add(rect, x, y);
                        
                        // Añade los rectangulos al array de rectangulos
                        rectangulos[x][y] = rect;
                    
        }     
    }
                   
        
            this.setOnMouseClicked((eventConsola) -> {             
                    int fila = (int)(eventConsola.getX() / (ANCHURA_RECT + 15));
                    int columna = (int)(eventConsola.getY() / (ALTURA_RECT + 15));

                    //Si cuando cliqueo detecta que hay una mina resta vida
                    if (buscaminas.tablero[fila][columna] == buscaminas.MINA)  {     
                        
                        vidas --;
                        
                        //Cuando falles y te queden dos vidas te salta un alert que te muestra las vidas que te quedan
                    // y un recordatorio para que recuerdes donde estaban las bombas.
                    if(vidas == 2) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("BOMBA!!    Te quedan 2 vidas.   Recuerda donde estaban los puntos y las bombas");
                        alert.showAndWait();
                        this.resetGame();
                    }

                    //Cuando falles y te quede una vida te salta un alert que te muestra las vidas que te quedan
                    // y un recordatorio para que recuerdes donde estaban las bombas.
                    if(vidas == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("BOMBA!!    Te queda 1 vida.   Recuerda donde estaban los puntos y las bombas");
                        alert.showAndWait();
                        this.resetGame();
                    }

                    //Cuando llegas a cero vidas te salta un alert que te dice que has perdido.
                    if(vidas == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("HAS PERDIDO");
                        alert.showAndWait();
                        System.exit(0); 
                    }
                    
                    }
                       
                    //Cuando consigues un punto se te suma un punto al contador
                    if (buscaminas.tablero[fila][columna] == buscaminas.PUNT1) {
                        contador = contador + 1; 
                    }
                    
                    //Cuando consigues dos puntos se te suman dos puntos al contador
                    if (buscaminas.tablero[fila][columna] == buscaminas.PUNT2) {
                        contador = contador + 2;
                    }
                    
                    //Si consigues 15 puntos ganas.
                    if (contador >= 15) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("HAS GANADO!!");
                        alert.showAndWait();
                        System.exit(0);    
                    }

                    System.out.println(contador);

                    //Hacer invisible el rectangulo que este en el array en la posicion fila y columna
                    rectangulos[fila][columna].setFill(Color.TRANSPARENT);           
                });
    
}
    //Reinicio del juego
    public void resetGame() {
        
        ANCHURA_RECT = 100;
        ALTURA_RECT = 100;                
        contador = 0;
        
        this.buscaminas = buscaminas;
                 
        //Creación array rectangulos
        rectangulos = new Rectangle [buscaminas.tamXtablero][buscaminas.tamYtablero];
        
        //For para que las imágenes se generen en la posición que le corresponde
        for (int x=0; x<buscaminas.tamXtablero; x++) {
            for (int y=0; y<buscaminas.tamYtablero; y++) {
                
                //Donde haya una MINA en el tablero se pone la imagen de mina                
                if (buscaminas.tablero[x][y] == buscaminas.MINA) {
                    Image mina = new Image(getClass().getResourceAsStream("/images/mina.jpg"));
                    ImageView imgView3 = new ImageView(mina);
                    grid.add(imgView3, buscaminas.tamXtablero, buscaminas.tamYtablero);
                    this.add(imgView3, x, y);
                }
                  
                //Donde haya PUNT1 en el tablero se pone la imagen de numero1 que simboliza un punto              
                if (buscaminas.tablero[x][y] == buscaminas.PUNT1) {
                    Image numero1 = new Image(getClass().getResourceAsStream("/images/numero1.jpg"));
                    ImageView imgView1 = new ImageView(numero1);
                    grid.add(imgView1, buscaminas.tamXtablero, buscaminas.tamYtablero);
                    this.add(imgView1, x, y);
                }

                //Donde haya PUNT2 en el tablero se pone la imagen de numero2 que simboliza dos puntos  
                if (buscaminas.tablero[x][y] == buscaminas.PUNT2) {
                    Image numero2 = new Image(getClass().getResourceAsStream("/images/numero2.jpg"));
                    ImageView imgView2 = new ImageView(numero2);
                    grid.add(imgView2, buscaminas.tamXtablero, buscaminas.tamYtablero);
                    this.add(imgView2, x, y);
                }
                
            }    
        }    
                //For para que se generen los rectangulos que tapan las imágenes
                for(int x=0; x<buscaminas.tamXtablero; x++) {
                    for(int y=0; y<buscaminas.tamYtablero; y++) {            
                        Rectangle rect = new Rectangle(buscaminas.tamXtablero, buscaminas.tamYtablero, ANCHURA_RECT, ALTURA_RECT);
                        this.add(rect, x, y);
                        
                        // Añade los rectangulos al array de rectangulos
                        rectangulos[x][y] = rect;
                    
        }     
    }
                   
        
            this.setOnMouseClicked((eventConsola) -> {             
                    int fila = (int)(eventConsola.getX() / (ANCHURA_RECT + 15));
                    int columna = (int)(eventConsola.getY() / (ALTURA_RECT + 15));

                    //Si cuando cliqueo detecta que hay una mina resta vida
                    if (buscaminas.tablero[fila][columna] == buscaminas.MINA)  {     
                        
                        vidas --;
                        
                        //                    Cuando falles y te queden dos vidas te salta un alert que te muestra las vidas que te quedan
//                     y un recordatorio para que recuerdes donde estaban las bombas.
                    if(vidas == 2) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("BOMBA!!    Te quedan 2 vidas.   Recuerda donde estaban los puntos y las bombas");
                        alert.showAndWait();
                        this.resetGame();
                    }

//                    Cuando falles y te quede una vida te salta un alert que te muestra las vidas que te quedan
//                     y un recordatorio para que recuerdes donde estaban las bombas.
                    if(vidas == 1) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("BOMBA!!    Te queda 1 vida.   Recuerda donde estaban los puntos y las bombas");
                        alert.showAndWait();
                        this.resetGame();
                    }

                    //Cuando llegas a cero vidas te salta un alert que te dice que has perdido.
                    if(vidas == 0) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("HAS PERDIDO");
                        alert.showAndWait();
                        System.exit(0); 
                    }
                    
                    }

                    //Cuando consigues un punto se te suma un punto al contador
                    if (buscaminas.tablero[fila][columna] == buscaminas.PUNT1) {
                        contador = contador + 1; 
                    }
                    
                    //Cuando consigues dos puntos se te suman dos puntos al contador
                    if (buscaminas.tablero[fila][columna] == buscaminas.PUNT2) {
                        contador = contador + 2;
                    }
                    
                    //Si consigues 15 puntos ganas.
                    if (contador >= 15) {
                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                        alert.setHeaderText(null);
                        alert.setTitle("Info");
                        alert.setContentText("HAS GANADO!!");
                        alert.showAndWait();
                        System.exit(0);    
                    }

                    System.out.println(contador);

                    //Hacer invisible el rectangulo que este en el array en la posicion fila y columna
                    rectangulos[fila][columna].setFill(Color.TRANSPARENT);           
                });
        
    } 
    
    //Márgenes para los rectangulos
    public void addGridPane() {
          
    this.setHgap(10);
    this.setVgap(10);    
    this.setPadding(new Insets(15, 15, 15, 15));

    }
}  


