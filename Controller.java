package com.internshala.game;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class Controller implements Initializable {

	@FXML
	public GridPane rootGridPane;
	@FXML
	public Pane menuPane;
	@FXML
	public Pane bodyPane;
	@FXML
	public Pane infoPane;

	int count=0;

	Shape[][] shapes = new Shape[3][3];

	private boolean isPlayerOneTurn=true;


	public void createPlayground(){


		Rectangle r1 = new Rectangle(350,1);
		r1.setTranslateX(35);
		r1.setTranslateY(126);
		Rectangle r2 = new Rectangle(350,1);
		r2.setTranslateX(35);
		r2.setTranslateY(242);

		Rectangle c1 = new Rectangle(1,350);
		c1.setTranslateX(151);
		c1.setTranslateY(10);
		Rectangle c2 = new Rectangle(1,350);
		c2.setTranslateX(267);
		c2.setTranslateY(10);



		bodyPane.getChildren().addAll(r1,c1,r2,c2);

		int x,y;

		for(int i=0;i<9;i++){

			Rectangle s = new Rectangle(116,116);
			s.setFill(Color.TRANSPARENT);
			if(i<3){
				y=10;
				x=(i * 116) + 35;

			}
			else if(i<6){
				y=127;
				x=(i-3)*116 + 35;

			}
			else{
				y=244;
				x=(i-6)*116+35;

			}
			s.setTranslateY(y);
			s.setTranslateX(x);
			s.setOnMouseEntered(event -> s.setFill(Color.valueOf("#eeeeee86")));
			s.setOnMouseExited(event -> s.setFill(Color.TRANSPARENT));
			int C=i%3;
			int R;
			if(i<3) R=0;
			else if(i<6) R=1;
			else R=2;
			int finalY = y;
			int finalX = x;
			bodyPane.getChildren().add(s);
			s.setOnMouseClicked(event ->{
				try {
					shapes[R][C] = mark(finalX, finalY);
					System.out.println("Shape["+R+"]["+C+"]="+shapes[R][C]);
				}catch (ArrayIndexOutOfBoundsException exception){

				}


				check(R,C);


			});





		}



	}

	private Shape mark(int x,int y) {
		if(isPlayerOneTurn){
			return createCircle(x,y);

		}
		else{
			return createCross(x,y);

		}

	}

	private Circle createCircle(int x, int y) {
		Circle circle = new Circle(48);
		circle.setTranslateX(x+56);
		circle.setTranslateY(y+56);
		bodyPane.getChildren().add(circle);
		isPlayerOneTurn = !isPlayerOneTurn;
		return circle;
	}

	private void check(int r, int c) {

		//horizontal

			try {
				count++;
				if((shapes[r][0] instanceof Circle && shapes[r][1] instanceof Circle && shapes[r][2] instanceof Circle)||
						(shapes[r][0] instanceof Rectangle && shapes[r][1] instanceof Rectangle && shapes[r][2] instanceof Rectangle))
					gameOver(r,c,0);
				else if((shapes[0][c] instanceof Circle && shapes[1][c] instanceof Circle && shapes[2][c] instanceof Circle)||
						(shapes[0][c] instanceof Rectangle && shapes[1][c] instanceof Rectangle && shapes[2][c] instanceof Rectangle))
					gameOver(r,c,1);
				else if((shapes[0][0] instanceof Circle && shapes[1][1] instanceof Circle && shapes[2][2] instanceof Circle)||
						(shapes[0][0] instanceof Rectangle && shapes[1][1] instanceof Rectangle && shapes[2][2] instanceof Rectangle))
					gameOver(r,c,11);
				else if((shapes[0][2] instanceof Circle && shapes[1][1] instanceof Circle && shapes[2][0] instanceof Circle)||
						(shapes[0][2] instanceof Rectangle && shapes[1][1] instanceof Rectangle && shapes[2][0] instanceof Rectangle))
					gameOver(r,c,10);
				else if (count==9)
					gameOver(0);
			}
			catch (ArrayIndexOutOfBoundsException ex){

			}


		

	}

	private void gameOver(int i) {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Tie");
		alert.setHeaderText("It's a Tie");
		alert.setContentText("You can try again");
		alert.show();
		Rectangle rectangle = new Rectangle(420,420);
		rectangle.setFill(Color.TRANSPARENT);
		bodyPane.getChildren().add(rectangle);
	}

	private void gameOver(int r,int c,int l) {

		Rectangle rec = new Rectangle();
		if(l==0){
			rec = new Rectangle(348,2);
			rec.setTranslateX(35);
			rec.setTranslateY(10+ (2*r+1)*58);
		}
		if(l==1){
			rec = new Rectangle(2,348);
			rec.setTranslateY(10);
			rec.setTranslateX(35+(2*c+1)*58);
		}
		if(l==11){
			rec = new Rectangle(2,494);
			rec.setRotate(-45);
			rec.setTranslateX(207);
			rec.setTranslateY(-64);
		}
		if(l==10){
			rec = new Rectangle(2,494);
			rec.setRotate(45);
			rec.setTranslateX(210);
			rec.setTranslateY(-64);
		}

		rec.setFill(Color.YELLOW);
		bodyPane.getChildren().add(rec);
		String win=isPlayerOneTurn?"Player Two":"Player One";
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("Winner");
		alert.setHeaderText("Winner is "+win);
		alert.setContentText("Nice Game");
		alert.show();
		Rectangle rectangle = new Rectangle(420,420);
		rectangle.setFill(Color.TRANSPARENT);
		bodyPane.getChildren().add(rectangle);


	}

	public void resetGame(){

		for(int i=0;i<3;i++)
			for (int j=0;j<3;j++){
				shapes[i][j]=null;
			//	System.out.println(shapes[i][j]);
			}


		bodyPane.getChildren().clear();
			isPlayerOneTurn=true;
		createPlayground();

	}

	private Rectangle createCross(int x, int y) {
		Rectangle r1 = new Rectangle(5,150);
		Rectangle r2 = new Rectangle(5,150);
		r1.setRotate(45);
		r2.setRotate(-45);

		r1.setTranslateX(x+56);
		r1.setTranslateY(y-18);

		r2.setTranslateX(x+56);
		r2.setTranslateY(y-18);



		bodyPane.getChildren().addAll(r1,r2);
		isPlayerOneTurn=!isPlayerOneTurn;
		return r1;
	}


	@Override
	public void initialize(URL location, ResourceBundle resources) {

	}
}
