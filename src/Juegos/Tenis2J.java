package Juegos;

import java.util.Random;

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

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
public class Tenis2J extends Application {
	private double ventanaX = 500;
	private double ventanaY = 450;
	
	private double ballCenterX = ventanaX/2;	
	private double ballSpeedX = 1.6;	
	private double ballCenterY = ventanaY/2;
	private double ballSpeedY = 1.6;
	private double ballSpeedT = 1.25;//BallSpeedTotal. Es como una aceleración.
	
	private double paloancho= 10;
	private double paloalto= 60;
	
	private double stickPosX=(ventanaX-ventanaX)+15;//No se porque el lado izquierod parece quedar algo más separado.
	private double stickPosY = ventanaY/2;
	private double stickSpeed = 0;
	
	private double stickPosX2=ventanaX-20;
	private double stickPosY2 = ventanaY/2;
	private double stickSpeed2 = 0;
	
	private int colisioncheck =0;
	
	public int recordJ1 = 0;
	public int recordJ2 = 0;
	
	@Override
	public void start(Stage stage) throws Exception {
		Pane root = new Pane();
		Scene scene = new Scene (root,ventanaX,ventanaY);
		stage.setTitle("Tenis.");
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
		
		Rectangle rect = new Rectangle(stickPosX,stickPosY,paloancho,paloalto);
		rect.setFill(Color.RED);
		rect.setVisible(true);
		
		Rectangle rect2 = new Rectangle(stickPosX2,stickPosY2,paloancho,paloalto);
		rect2.setFill(Color.VIOLET);
		rect2.setVisible(true);
		
		
		HBox score = new HBox();
		score.setSpacing(ventanaX-200);
		score.setTranslateY(30);
		score.setMinWidth(ventanaX);
		//score.setSpacing(150);
		
		Text pun = new Text("Victorias J1: "+recordJ1);
		pun.setFill(Color.WHITE);
		pun.setFont(Font.font(16));
		
		Text pun2 = new Text("Victorias J2: "+recordJ2);
		pun2.setFill(Color.WHITE);
		pun2.setFont(Font.font(16));
		
		score.getChildren().add(pun);
		score.getChildren().add(pun2);
		
		
		root.getChildren().addAll(ball,b1,rect,rect2,score);
		scene.setFill(Color.BLACK);
		
		
		
		//El ciclo que mueve la bola.
		Timeline move = new Timeline(new KeyFrame(Duration.seconds(0.02),(ActionEvent ae)->{
		
		Shape s = Shape.intersect(ball, rect);	
		Shape s2 = Shape.intersect(ball, rect2);
		boolean colision = s.getBoundsInLocal().isEmpty();
		boolean colision2 = s2.getBoundsInLocal().isEmpty();	
		
		stickPosY+=stickSpeed;
		stickPosY2+=stickSpeed2;
		if(stickPosY < 0) {
			stickPosY=0;
		}
		if(stickPosY>= ventanaY-paloalto){
			stickPosY=ventanaY-paloalto;
		}
		
		if(stickPosY2 < 0) {//Bordes del stick 2.
			stickPosY2=0;
		}
		if(stickPosY2>= ventanaY-paloalto){
			stickPosY2=ventanaY-paloalto;
		}
		
		rect.setY(stickPosY);
		rect2.setY(stickPosY2);	
		
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
		if(!colision||!colision2) {
			if(!colision) {
				ballSpeedX= ballSpeedT;
				ballSpeedT=ballSpeedT+0.2;
			}
			if(!colision2) {
				ballSpeedX= -ballSpeedT;
				ballSpeedT=ballSpeedT+0.2;
			}
			
			
			
		}
		else {
			colisioncheck=0;
		}
		if(ballCenterX<stickPosX+4) {//Si la bola sobrepasa la barra IZQUIERDA.
			 ballCenterX = ventanaX/2;
			 ballCenterY = ventanaY/2;
			 Random rand = new Random();
			 double num1 = rand.nextDouble() *  2.5 - 1.25;
			 double num2 = rand.nextDouble() * 3.0 - 1.5;
			 recordJ2++;
			 pun2.setText("Victorias J2: "+String.valueOf(recordJ2));
			 saquerng(num1,num2);//Para hacer el saque aleatorio uso esta función.
			 
		}
		
		if(ballCenterX>stickPosX2+4) {//Si la bola sobrepasa la barra DERECHA.
			 ballCenterX = ventanaX/2;
			 ballCenterY = ventanaY/2;
			 Random rand = new Random();
			 double num1 = rand.nextDouble() * 3.0 - 1.5;
			 double num2 = rand.nextDouble() * 3.0 - 1.5;
			 //ballSpeedT = 1.25+0.25;
			 recordJ1++;
			 pun.setText("Victorias J1: "+String.valueOf(recordJ1));
			 saquerng(num1,num2);
			 
			 
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
		    	case UP:{
		    		stickSpeed = 7;
		    	}break;
		    	case DOWN:{
		    		stickSpeed = -7;
		    	}break;
		    	
		    	case W:{
		    		stickSpeed2= -7;
		    	}break;
		    	case S:{
		    		stickSpeed2= 7;
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
		
		
		
		
		
		stage.show();
		
		
		
	}
	
	private void saquerng(double num1, double num2) {
		 if(num1<=1.25&&num1>=-1.25) {//Por si los números son muy pequeños, evitar casos en los que la bola va demasiado lenta.
			 if(num1>0) {
				 ballSpeedX= num1+1.40;
			 }
			 else {
				 ballSpeedX= num2-1.40;
			 }
			 
			
		 
		 }
		 else {
			 if(num1>0) {
				 ballSpeedX= num1+0.15;
			 }
			 else {
				 ballSpeedX= num1-0.15;
			 }
			 
		 }//Fin if X.
		 if(num2<=1.25&&num2>=-1.25) {
			 if(num2>0) {
				 ballSpeedY= num2+1.40;
			 }
			 else {
				 ballSpeedY= num2-1.40;
			 }
			 
			 
			
		 }
		 else {
			 if(num2>0) {
				 ballSpeedY= num2+0.15;
			 }
			 else {
				 ballSpeedY= num2-0.15;
			 }
			 
			 
		 }
		 
	}//Fin funcion saquerng.
	

}//Fin de la clase.
