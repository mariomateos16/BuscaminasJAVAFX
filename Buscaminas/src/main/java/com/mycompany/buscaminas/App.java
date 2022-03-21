package com.mycompany.buscaminas;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;


/**
 * JavaFX App
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
    
     
        
    short tamXPantalla = 680; 
    short tamYPantalla = 480;    
    
    BorderPane paneRoot = new BorderPane();
    var scene = new Scene(paneRoot, tamXPantalla, tamYPantalla);
    stage.setScene(scene);
    stage.show(); 
    
        //Crear objeto de la lógica del juego
        Buscaminas buscaminas = new Buscaminas((short)6, (short)4);
        
        
        for(int i=0; i<1; i++) {
            int fila = buscaminas.buscarFila(3);
            boolean colocado = buscaminas.colocarPunt(3, fila, 1);
        if(colocado == true) {
            buscaminas.mostrarTableroConsola();
        } else {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setContentText("No se ha podido colocar la ficha");
            alert.showAndWait();
        }
        }
        
        Tablero tablero = new Tablero(buscaminas);
        tablero.addGridPane();
        paneRoot.setCenter(tablero);
  
    }

    public static void main(String[] args) {
        launch();
    }

}