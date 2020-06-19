package com.internshala.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.awt.*;

public class Main extends Application {

	private Controller controller;
	@Override
	public void start(Stage primaryStage) throws Exception {

		FXMLLoader loader=new FXMLLoader(getClass().getResource("app_layout.fxml"));
		GridPane rootGridPane=loader.load();

		controller = loader.getController();
		controller.createPlayground();

		MenuBar menuBar=createMenu();
		menuBar.prefWidthProperty().bind(primaryStage.widthProperty());
		Pane menuPane= (Pane) rootGridPane.getChildren().get(0);
		menuPane.getChildren().add(menuBar);

		Scene scene = new Scene(rootGridPane);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Game");
		primaryStage.setResizable(false);
		primaryStage.show();

	}

	private MenuBar createMenu() {

		MenuBar menuBar = new MenuBar();
		
		Menu file = new Menu("File");
		MenuItem newGame = new MenuItem("New Game");
		SeparatorMenuItem separatorMenuItem = new SeparatorMenuItem();
		MenuItem exit = new MenuItem("Exit");
		file.getItems().addAll(newGame,separatorMenuItem,exit);

		Menu help  = new Menu("Help");
		MenuItem about = new MenuItem("About");
		help.getItems().add(about);
		
		menuBar.getMenus().addAll(file,help);
		
		newGame.setOnAction(event -> newGame());
		exit.setOnAction(event -> exit());
		about.setOnAction(event -> about());
		
		return menuBar;

	}

	private void about() {
		Alert alert = new Alert(Alert.AlertType.INFORMATION);
		alert.setTitle("About Game");
		alert.setHeaderText("Just a little game to revive your childhood");
		alert.setContentText("Developed by Pradeep Kumar while learning JavaFX\nENJOY!!");
		alert.show();
	}

	private void exit() {

		Platform.exit();
		System.exit(0);

	}

	private void newGame() {
		controller.resetGame();


	}
}
