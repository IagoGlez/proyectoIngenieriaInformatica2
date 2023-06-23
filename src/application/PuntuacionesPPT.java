package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Control.ManejoPerfiles;
import Control.Puntuaciones;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class PuntuacionesPPT {
	public static void display() {
    	
    	
    	Stage window;
    	window = new Stage();
        window.setTitle("Puntuaciones Piedra, Papel, Tijera.");

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
		 
		try {
			 Class.forName("org.sqlite.JDBC");
			 conn = DriverManager.getConnection("jdbc:sqlite:MiniRetroBBDD.db");
			 stmt = conn.createStatement();
			 rs = stmt.executeQuery("SELECT Puntuaciones.Id_Usuario, Puntuacion, Usuarios.Nombre FROM Puntuaciones LEFT JOIN Usuarios On Puntuaciones.Id_Usuario == Usuarios.ID_Usuario where Id_juego = 2 order by Puntuacion desc LIMIT 5"); //consulta (select, udpate, delete, insert)
			 while(rs.next()) { 
				 puntuacion = new Puntuaciones(NombreUsuario, Id_Usuario, Id_Usuario);
				 Puntuacion = rs.getInt("Puntuacion"); 
				 Id_Usuario = rs.getInt("Id_Usuario");
				 NombreUsuario = rs.getString("Nombre");
				 
				 puntuacion.setNombreUsuario(NombreUsuario);
				 puntuacion.setId_usuario(Id_Usuario);
				 puntuacion.setPuntuacion(Puntuacion);
				 puntuaciones.add(puntuacion);
				 
		 }
		 for (Puntuaciones elemento : puntuaciones) {
			 label = new Label(elemento.getNombreUsuario() + " - " + elemento.getPuntuacion());
			 labeles.add(label);
		 }
		 //Cerramos conexiones abiertas
		 rs.close();
		 stmt.close();
		 conn.close();
		 
		}catch(Exception e){
		 System.out.println("Error: "+ e);
		}

        Button volver = new Button("Volver");
        volver.setOnAction(event -> {
        	MenuPuntuaciones.display();
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
