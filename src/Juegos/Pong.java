package Juegos;



import Control.ManejoPuntos;
import Control.Sistema;
import application.Menu1;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javafx.util.Duration;

public class Pong extends Application {
	private double ventanaX = 500;
	private double ventanaY = 450;
	
	private double ballCenterX = ventanaX/2;	
	private double ballSpeedX = 1.5;	
	private double ballCenterY = ventanaY/2;
	private double ballSpeedY = 1.5;
	private double ballSpeedT = 1.25;//BallSpeedTotal
	
	private double stickPosX=(ventanaX/2)-25;
	private double stickPosY = ventanaY/1.2;
	private double stickSpeed = 0;
	
	private int colisioncheck =0;
	
	public int puntos = 0;
	public int partidasj = 0;
	public int record = Sistema.getPuntos();
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene (root,ventanaX,ventanaY);
		stage.setTitle("Pong.");
		stage.setScene(scene);
		root.setBackground(new Background(new BackgroundFill(Color.BLACK, CornerRadii.EMPTY, Insets.EMPTY)));
		

		Circle ball = new Circle();
		ball.setRadius(8);
		ball.setFill(Color.ORANGE);
		ball.setCenterX(ballCenterX);
		ball.setCenterY(ballCenterY);
		
		Button b1 = new Button();
		b1.setTranslateX((ventanaX/2)-25);
		b1.setTranslateY(ventanaY/1.6);
		b1.setText("Iniciar");
		
		Rectangle rect = new Rectangle(stickPosX,stickPosY,50,8);
		rect.setFill(Color.ORANGE);
		rect.setVisible(true);
		
		
		HBox score = new HBox();
		//score.setTranslateX(ventanaX-50);
		score.setTranslateY(30);
		score.setMinWidth(ventanaX);
		score.setSpacing(150);
		
		Text pun = new Text("Puntos: "+puntos);
		pun.setFill(Color.WHITE);
		pun.setFont(Font.font(16));
		
		Text pun2 = new Text("Recórd personal: "+record);
		pun2.setFill(Color.WHITE);
		pun2.setFont(Font.font(16));
		
		score.getChildren().addAll(pun,pun2);
		
		
		
		root.getChildren().addAll(ball,b1,rect,score);
		scene.setFill(Color.BLACK);
		
		
		
		//El ciclo que mueve la bola.
		Timeline move = new Timeline(new KeyFrame(Duration.seconds(0.02),(ActionEvent ae)->{
		
		Shape s = Shape.intersect(ball, rect);	
		boolean colision = s.getBoundsInLocal().isEmpty();	
		
		stickPosX+=stickSpeed;
		if(stickPosX < 0) {
			stickPosX=0;
		}
		if(stickPosX>= ventanaX-50){
			stickPosX=ventanaX-50;
		}
		
		rect.setX(stickPosX);
			
		ball.setCenterX(ballCenterX);
		ball.setCenterY(ballCenterY);
		
		ballCenterX+=ballSpeedX;
		if(ballCenterX>=ventanaX) {
			ballSpeedX= -ballSpeedT;
			ballSpeedT=ballSpeedT+0.2;
		}
		if(ballCenterX<=0) {
			ballSpeedX= ballSpeedT;
			ballSpeedT=ballSpeedT+0.2;
		}
		ballCenterY+=ballSpeedY;
		if(ballCenterY>=ventanaY) {
			ballSpeedY= -ballSpeedT;
			ballSpeedT=ballSpeedT+0.2;
		}
		if(ballCenterY<=0) {
			ballSpeedY= ballSpeedT;
			ballSpeedT=ballSpeedT+0.2;
		}
		if(!colision) {
			ballSpeedY= -ballSpeedT;
			ballSpeedT=ballSpeedT+0.2;
			if(colisioncheck==0) {
				puntos++;
				 pun.setText("Puntos: "+String.valueOf(puntos));
				 colisioncheck=1;
			}
			
		}
		else {
			colisioncheck=0;
		}
		if(ballCenterY>stickPosY+16) {//Si la bola sobrepasa la barra.
			 ballCenterX = ventanaX/2;
			 ballCenterY = ventanaY/2;
			 
			 ballSpeedT = 1.25+0.25;
			 ballSpeedX= ballSpeedT;
			 ballSpeedY= ballSpeedT;
			 partidasj++;
			 puntos =0;
			 pun.setText("Puntos: "+String.valueOf(puntos));
		}
		if(puntos>record) {
			record=puntos;
			pun2.setText("Recórd personal: "+record);
		}
		
		}));//Fin lambda
		
		move.setCycleCount(Timeline.INDEFINITE);
		
		b1.setOnAction(e->{
			move.play();
			b1.setVisible(false);
		});
		
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {

		    switch(event.getCode()){
		    	case RIGHT:{
		    		stickSpeed = 7;
		    	}break;
		    	case LEFT:{
		    		stickSpeed = -7;
		    	}break;
		    	default:{
		    		
		    	}
		    		
		    }
		    
		    }
		});
		
		scene.setOnKeyReleased(new EventHandler<KeyEvent>() {
		    @Override
		    public void handle(KeyEvent event) {

		    }
		});
		
		stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
  	      @Override
  	      public void handle(WindowEvent event) {
  	    	if(record>Sistema.getId_puntos()) {
  	    		 ManejoPuntos mp = new ManejoPuntos();
  		    	  Sistema.setPuntos(record);
  		    	  mp.updatePuntos(Sistema.getId_puntos(), record);
  	    		
  	    	}
  	    	 
  	    	  Menu1.show();
  	    	  //Sistema.guardar();
  	    	  System.out.println("Window closed");
  	      }
  	    });
		
		
		
		
		
		stage.show();
		
		
		
	}
	
	
	
	
	
}
	