package labyrinthSolver.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;


public class Main extends Application {
    
    
    /**
     * Main-metodi, joka käynnistää ohjelman
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        
        BorderPane startLayout = new BorderPane();
        startLayout.setStyle("-fx-background-color: #ffdcff");
        
        VBox startMenu = new VBox(30);
        startMenu.setPadding(new Insets(100));
        
        Label welcome = new Label("Welcome!\nWhat would you like to do?");
        welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        startMenu.setAlignment(Pos.CENTER);
        startMenu.getChildren().add(welcome);
        
        startLayout.setCenter(startMenu);
        Scene startScene = new Scene(startLayout, 800, 600);
        
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Maze Solver");
        primaryStage.show();
    }
    
}