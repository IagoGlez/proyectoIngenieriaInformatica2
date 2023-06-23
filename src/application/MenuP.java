package application;

import java.util.Vector;

import Control.ManejoPerfiles;
import Control.Sistema;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
/*
 * Conjunto de menús que permiten buscar y cargar un perfil. Surgen ventanas correspondientes si tiene éxito
 * o no la carga. También permite crear y guardar uno nuevo.
 */
public class MenuP  {
	
	
    public static void display() {
    	
    	
    	Stage window;
    	window = new Stage();
        window.setTitle("Cargar.");
		
		TextField textField = new TextField();
        
		Button cargar = new Button("Cargar");
       
        cargar.setOnAction(event -> {
        	ManejoPerfiles mp = new ManejoPerfiles();
        	String n = textField.getText();
        	Vector<String> v = mp.cargar(n);
        	
        	
        	if(v.firstElement()==null) {
            	
            	MenuP.display3();
            	System.out.println("NO cargado.");
            }
        	else if(!n.matches("-?\\d+")){
        		ControlCargaUsuarios ccu = new ControlCargaUsuarios();
        		ccu.display();
        	}
            else {
            	Sistema.setNombre(v.lastElement());
            	Sistema.setId_jugador(Integer.valueOf(v.firstElement()));
            	MenuP.display2();
            	
            	System.out.println(n+"Cargado.");
            }
        
        });
        
        
        TextField textField2 = new TextField();
        
		Button guardar = new Button("Guardar Nuevo Perfil.");
		guardar.setOnAction(event -> {
				ManejoPerfiles mp = new ManejoPerfiles();
				String n = textField2.getText();
				Vector<String> v = mp.crear(n);
				Sistema.setNombre(v.lastElement());
            	Sistema.setId_jugador(Integer.valueOf(v.firstElement()));
				//MenuP.display4();
            	System.out.println("PERFIL GUARDADO Y CARGADO.");
            	ControlGuardarUsuarios cgu = new ControlGuardarUsuarios();
            	cgu.display(Integer.valueOf(v.firstElement()));
        
        });

        
        Button volver = new Button("Volver");
        volver.setOnAction(event -> {
        	Menu1.show();
        	window.close();
        
        });
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(textField, cargar,textField2,guardar, volver);

        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
        
        /*primaryStage.setTitle("Menú");
        primaryStage.setScene(scene);
        primaryStage.show();*/
        
        
	}

    /*public static void main(String[] args) {
        launch(args);
    }*/

    public static void display2() {
    	Stage window;
    	window = new Stage();
        window.setTitle("Éxito.");
        window.initModality(Modality.APPLICATION_MODAL);
        
        Button aceptar = new Button("Aceptar.");
        aceptar.setOnAction(event -> {
        	window.close();
        });
        Label label = new Label("Perfil cargado con éxito.");
        
        
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(label,aceptar);
        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
    }
    
    public static void display3() {
    	Stage window;
    	window = new Stage();
        window.setTitle("Error.");
        window.initModality(Modality.APPLICATION_MODAL);
        
        Button aceptar = new Button("Aceptar.");
        aceptar.setOnAction(event -> {
        	window.close();
        });
        Label label = new Label("Error al cargar el perfil.");
        
        
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(label,aceptar);
        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
    }
    
    public static void display4() {
    	Stage window;
    	window = new Stage();
        window.setTitle("Estas cosillas se leen?.");
        window.initModality(Modality.APPLICATION_MODAL);
        
        Button aceptar = new Button("Aceptar.");
        aceptar.setOnAction(event -> {
        	window.close();
        });
        Label label = new Label("Perfil guardado y cargado con éxito.");
        
        
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(label,aceptar);
        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
    }
    public static void display5() {
    	Stage window;
    	window = new Stage();
        window.setTitle("By the nine divines! Error, error!");
        window.initModality(Modality.APPLICATION_MODAL);
        
        Button aceptar = new Button("Aceptar.");
        aceptar.setOnAction(event -> {
        	window.close();
        });
        Label label = new Label("Nombre de perfil Inválido.");
        
        
        
        VBox root = new VBox();
        root.setPadding(new Insets(10, 10, 10, 10));
        root.setSpacing(10);
        root.getChildren().addAll(label,aceptar);
        Scene scene = new Scene(root, 300, 250);
        
        window.setScene(scene);
        window.show();
    }
	
}//Fin de clase
