package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import Control.ManejoPerfiles;
import Control.Puntuaciones;
import Juegos.Minas;
import Juegos.PPT;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class MenuPuntuaciones {
	
	public static void display() {
    	
    	
    	Stage window;
    	window = new Stage();
        window.setTitle("Puntuaciones.");
		
		TextField textField = new TextField();
        
		Button minas = new Button("Buscaminas.");
        
        minas.setOnAction(event -> {

        	window.close();
        	PuntuacionesBuscaminas.display();
			
        });
        
        
		Button ppt = new Button("P,P & T.");
		ppt.setOnAction(event -> {
			
			window.close();
			PuntuacionesPPT.display();
        
        });
		
		Button pong = new Button("Pong");
		pong.setOnAction(event -> {
			window.close();
			PuntuacionesPong.display();
        
        });
        
        Button volver = new Button("Volver");
        volver.setOnAction(event -> {
        	Menu1.show();
        	window.close();
        
        });
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(minas,ppt,pong, volver);

        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
        
        
	}
}