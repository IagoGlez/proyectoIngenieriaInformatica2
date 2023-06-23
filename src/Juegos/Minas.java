package Juegos;



import java.io.Console;
import java.util.ArrayList;
import java.util.List;

import Control.ManejoPuntos;
import Control.Sistema;
import application.Menu1;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;


public class Minas extends Application {

    private static final int dimensionesCuadrado = 100;
    private static final int ancho = 800;
    private static final int alto = 600;

    private static final int ejeX_Cuadrado = ancho / dimensionesCuadrado;
    private static final int ejeY_Cuadrado = alto / dimensionesCuadrado;

    private Casilla[][] malla = new Casilla[ejeX_Cuadrado][ejeY_Cuadrado];
    private Scene escena;
    
    Button b = new Button();
    VBox root = new VBox();
   
    
    private int casillasAbiertas = 0;
    int numeroCasillas = (ancho/dimensionesCuadrado) * (alto/dimensionesCuadrado);
    int numeroBombas = 0;
    int partidasGanadas = 0;
    int partidasPerdidas = 0;
    
    private Parent programa() {
    	int numeroCasillas = (ancho/dimensionesCuadrado) * (alto/dimensionesCuadrado);
        Pane raiz = new Pane();
        raiz.setPrefSize(ancho, alto);
        
        for (int y = 0; y < ejeY_Cuadrado; y++) {
            for (int x = 0; x < ejeX_Cuadrado; x++) {
            	Casilla cuadrado = new Casilla(x, y, Math.random() < 0.15);
            	
            	if(cuadrado.hayBomba == true) {
            		++numeroBombas;
            	}
                malla[x][y] = cuadrado;
                raiz.getChildren().add(cuadrado);
            }
        }
        
        System.out.println(numeroCasillas);
        System.out.println(numeroBombas);
        
        for (int y = 0; y < ejeY_Cuadrado; y++) {
            for (int x = 0; x < ejeX_Cuadrado; x++) {
            	Casilla cuadrado = malla[x][y];

                if (cuadrado.hayBomba)
                    continue;

                long bombas = vecinos(cuadrado).stream().filter(t -> t.hayBomba).count();

                if (bombas > 0)
                	cuadrado.texto.setText(String.valueOf(bombas));
                
            }
        }

        return raiz;
    }

    private List<Casilla> vecinos(Casilla tile) {
        List<Casilla> vecinos = new ArrayList<>();

        int[] puntos = new int[] {
        	//ejeX, ejeY,
              -1, -1,
              -1, 0,
              -1, 1,
              0, -1,
              0, 1,
              1, -1,
              1, 0,
              1, 1
        };

        for (int i = 0; i < puntos.length; i++) {
            int dx = puntos[i];
            int dy = puntos[++i];

            int nuevoX = tile.x + dx;
            int nuevoY = tile.y + dy;

            if (nuevoX >= 0 && nuevoX < ejeX_Cuadrado
                    && nuevoY >= 0 && nuevoY < ejeY_Cuadrado) {
            	vecinos.add(malla[nuevoX][nuevoY]);
            }
        }

        return vecinos;
    }

    private class Casilla extends StackPane {
        private int x, y;
        private boolean hayBomba;
        private boolean abierto = false;

        private Rectangle borde = new Rectangle(dimensionesCuadrado - 2, dimensionesCuadrado - 2);
        private Text texto = new Text();

        public Casilla(int x, int y, boolean hayBomba) {
            this.x = x;
            this.y = y;
            this.hayBomba = hayBomba;
            

            borde.setStroke(Color.GRAY);
            borde.setFill(Color.GRAY);

            texto.setFont(Font.font(22));
            texto.setText(hayBomba ? "X" : "");
            texto.setVisible(false);

            getChildren().addAll(borde, texto);
            

            setTranslateX(x * dimensionesCuadrado);
            setTranslateY(y * dimensionesCuadrado);
            
            setOnMouseClicked(e -> abrirCasilla());
            
        }

        public void abrirCasilla() {
        	casillasAbiertas++;
            if (abierto)
                return;

            if (hayBomba) {
            	numeroBombas = 0;
            	casillasAbiertas = 0;
            	partidasPerdidas++;
                System.out.println("Ha explotado la bomba");
                escena.setRoot(programa());
                return;
            }
            int casillasRestantes = numeroCasillas - casillasAbiertas - numeroBombas;
            System.out.println(numeroCasillas + "-" + casillasAbiertas + "-" + numeroBombas + "-" + partidasGanadas + "-" + partidasPerdidas);
            if (casillasRestantes == 0) {
            	numeroBombas = 0;
            	casillasAbiertas = 0;
            	System.out.println("Has ganado, el resto del tablero son minas.");
            	partidasGanadas++;
            	
            	//Enviar un 1 o un 0 al menu
            	escena.setRoot(programa());
            	return;
			}
            abierto = true;
            texto.setVisible(true);
            borde.setFill(null);
        }
    }

    @Override
    public void start(Stage stage) throws Exception {
    	escena = new Scene(programa());
    	
    	//stage.setOnCloseRequest(null);
    	stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
    	      @Override
    	      public void handle(WindowEvent event) {
    	    	  ManejoPuntos mp = new ManejoPuntos();
    	    	  Sistema.setPuntos(Sistema.getPuntos()+partidasGanadas);
    	    	  mp.updatePuntos(Sistema.getId_puntos(), Sistema.getPuntos());
    	    	  Menu1.show();
    	    	  //Sistema.guardar();
    	    	  System.out.println("Window closed");
    	    	  //Menu1.show();
    	      }
    	    });
        
    	
    	stage.setScene(escena);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
    
   
}
