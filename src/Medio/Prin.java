package Medio;

import Control.Sistema;
import application.Menu1;
import javafx.application.Application;
import javafx.stage.Stage;

public class Prin extends Application {
	
	public static void main(String[] args) {
	
		//Sistema.cargarP();
		launch(args);
		
	}
	@Override
	public void start(Stage primaryStage) {
       Menu1.show();    }
	

}
