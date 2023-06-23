package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Control.Puntuaciones;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ControlCargaUsuarios {

public static void display() {
    	
    	
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
		
		List<Label> labeles = new ArrayList<Label>();
		Label label = new Label();
		Label label1 = new Label();
		
		label = new Label("No se admite el nombre de usuario.");
		label1 = new Label("Introducir el Id de Usuario");

		labeles.add(label);
		labeles.add(label1);
		
        Button volver = new Button("Volver");
        volver.setOnAction(event -> {
        	MenuP.display();
        	window.close();
        
        });
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(labeles);
        root.getChildren().add(volver);

        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
        
        
	}

}
