package application;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Control.Puntuaciones;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlGuardarUsuarios {
public static void display(int idUsuario) {
    	
    	
    	Stage window;
    	window = new Stage();
        window.setTitle("Puntuaciones Buscaminas.");

    	Connection conn = null;
		Statement stmt = null;
		ResultSet rs= null;
		ResultSet rs1 = null;
		 
		String NombreUsuario = "";
		int Puntuacion = 0;
		int Id_Usuario = 0;
		 
		Puntuaciones puntuacion = new Puntuaciones(NombreUsuario, Id_Usuario, Id_Usuario);
		List<Puntuaciones> puntuaciones = new ArrayList<Puntuaciones>();
		
		Label label = new Label();
		
		label = new Label("Su Id de usuario es el: " + idUsuario);
		
        Button volver = new Button("Volver");
        volver.setOnAction(event -> {
        	MenuP.display4();
        	window.close();
        
        });
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().add(label);
        root.getChildren().add(volver);

        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
        
        
	}


}
