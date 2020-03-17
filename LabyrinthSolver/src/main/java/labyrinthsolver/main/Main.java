package labyrinthsolver.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import labyrinthsolver.domain.Maze;
import labyrinthsolver.domain.Sidewinder;


public class Main extends Application {
    
    
    /**
     * Main-metodi, joka käynnistää ohjelman
     * @param args 
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Metodi luo ja näyttää graafisen käyttöliittymän
     * @param primaryStage On ikkuna johon näkymät laitetaan
     * @throws Exception Heittää mahdolliset poikkeukset
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        
        Maze maze = new Maze(51);
        Sidewinder sidew = new Sidewinder();
        maze.setLayout(sidew.generate(maze.getSize()));
        
        BorderPane startLayout = new BorderPane();
        startLayout.setStyle("-fx-background-color: #ffdcff");
        
        VBox startMenu = new VBox(30);
        startMenu.setPadding(new Insets(100));
        
        Label welcome = new Label("Welcome!\nWhat would you like to do?");
        welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        Button generate = new Button("Generate a maze");
        
        startMenu.setAlignment(Pos.CENTER);
        startMenu.getChildren().add(welcome);
        startMenu.getChildren().add(generate);
        
        startLayout.setCenter(startMenu);
        Scene startScene = new Scene(startLayout, 1000, 800);
        
        BorderPane mazeLayout = new BorderPane();
        mazeLayout.setStyle("-fx-background-color: #ffdcff");
        
        VBox mazeMenu = new VBox(30);
        mazeMenu.setPadding(new Insets(100));
        Button generateNew = new Button("Generate a new maze");
        
        GridPane mazeGrid = new GridPane();
        
        for (int i = 0; i < maze.getSize(); i++) {
            for (int j = 0; j < maze.getSize(); j++) {
                Label square = new Label(" ");
                square.setMaxSize(10, 10);
                square.setMinSize(10, 10);
                
                if (maze.getFromCoordinates(i, j) == 1) {
                    square.setStyle("-fx-background-color: #000000");
                } else if (i == 1 && j == 1) {
                    square.setStyle("-fx-background-color: #0000ff");
                } else if (i == maze.getSize() && j == maze.getSize() - 2) {
                    square.setStyle("-fx-background-color: #00ff00");
                }
                
                mazeGrid.add(square, i, j);
            }
        }
        
        mazeMenu.setAlignment(Pos.CENTER);
        mazeMenu.getChildren().add(mazeGrid);
        mazeMenu.getChildren().add(generateNew);
        
        Scene mazeScene = new Scene(mazeMenu, 1000, 800);
        
        generate.setOnAction((event) -> {
            primaryStage.setScene(mazeScene);
        });
        
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Maze Solver");
        primaryStage.show();
    }
    
}