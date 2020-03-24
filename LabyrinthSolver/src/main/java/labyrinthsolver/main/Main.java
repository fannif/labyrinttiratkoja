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
import labyrinthsolver.domain.RecursiveDivision;
import labyrinthsolver.domain.ShortestPaths;
import labyrinthsolver.domain.Sidewinder;
import labyrinthsolver.domain.WallFollower;


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
        
        Maze maze = new Maze(45);
        Sidewinder sidew = new Sidewinder();
        WallFollower wallFollower = new WallFollower();
        RecursiveDivision reDiv = new RecursiveDivision();
        ShortestPaths allShortest = new ShortestPaths();
        
        BorderPane startLayout = new BorderPane();
        startLayout.setStyle("-fx-background-color: #ffdcff");
        
        VBox startMenu = new VBox(30);
        startMenu.setPadding(new Insets(100));
        
        Label welcome = new Label("Welcome!\nWhat would you like to do?");
        welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        Button generateSW = new Button("Generate a Sidewinder maze");
        Button generateRD = new Button("Generate a Recursive Division maze");
        
        startMenu.setAlignment(Pos.CENTER);
        startMenu.getChildren().add(welcome);
        startMenu.getChildren().add(generateSW);
        startMenu.getChildren().add(generateRD);
        
        startLayout.setCenter(startMenu);
        Scene startScene = new Scene(startLayout, 1000, 800);
        
        BorderPane mazeLayout = new BorderPane();
        mazeLayout.setStyle("-fx-background-color: #ffdcff");
        
        VBox mazeMenu = new VBox(30);
        mazeMenu.setPadding(new Insets(100));
        Button generateNewSW = new Button("Generate a new Sidewinder maze");
        Button generateNewRD = new Button("Generate a new Recursive division maze");
        Button solvefollower = new Button("Solve using Wall Follower");
        Button solveAllShortest = new Button("Solve all shortest paths");
        Label info = new Label("Blue dot is the start. Green dot is the goal.");
        Label routeInfo = new Label("Purple shows the places visited by solver algorithm.");
        
        GridPane mazeGrid = new GridPane();
        
        showMaze(maze, mazeGrid);
        
        mazeMenu.setAlignment(Pos.CENTER);
        mazeMenu.getChildren().add(info);
        mazeMenu.getChildren().add(mazeGrid);
        mazeMenu.getChildren().add(solvefollower);
        mazeMenu.getChildren().add(solveAllShortest);      
        mazeMenu.getChildren().add(generateNewSW);
        mazeMenu.getChildren().add(generateNewRD);
        
        Scene mazeScene = new Scene(mazeMenu, 1000, 800);
        
        generateSW.setOnAction((event) -> {
            maze.setLayout(sidew.generate(maze));
            showMaze(maze, mazeGrid);
            mazeMenu.getChildren().clear();
            mazeMenu.getChildren().add(info);
            mazeMenu.getChildren().add(routeInfo);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvefollower);
            mazeMenu.getChildren().add(solveAllShortest);
            mazeMenu.getChildren().add(generateNewSW);
            mazeMenu.getChildren().add(generateNewRD);
            primaryStage.setScene(mazeScene);
        });
        
        generateRD.setOnAction((event) -> {
            maze.setLayout(reDiv.generate(maze));
            showMaze(maze, mazeGrid);
            mazeMenu.getChildren().clear();
            mazeMenu.getChildren().add(info);
            mazeMenu.getChildren().add(routeInfo);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvefollower);
            mazeMenu.getChildren().add(solveAllShortest);
            mazeMenu.getChildren().add(generateNewSW);
            mazeMenu.getChildren().add(generateNewRD);
            primaryStage.setScene(mazeScene);
        });
        
        solvefollower.setOnAction((event) -> {
            Maze solvedMaze = new Maze(maze.getSize());
            solvedMaze.setLayout(wallFollower.solve(maze));
            showMaze(solvedMaze, mazeGrid);
            mazeMenu.getChildren().clear();
            mazeMenu.getChildren().add(info);
            mazeMenu.getChildren().add(routeInfo);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvefollower);
            mazeMenu.getChildren().add(solveAllShortest);
            mazeMenu.getChildren().add(generateNewSW);
            mazeMenu.getChildren().add(generateNewRD);
        });
        
        solveAllShortest.setOnAction((event) -> {
            Maze solvedMaze = new Maze(maze.getSize());
            solvedMaze.setLayout(allShortest.solve(maze));
            showMaze(solvedMaze, mazeGrid);
            mazeMenu.getChildren().clear();
            mazeMenu.getChildren().add(info);
            mazeMenu.getChildren().add(routeInfo);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvefollower);
            mazeMenu.getChildren().add(solveAllShortest);
            mazeMenu.getChildren().add(generateNewSW);
            mazeMenu.getChildren().add(generateNewRD);
        });
        
        generateNewSW.setOnAction((event) -> {
            maze.setLayout(sidew.generate(maze));
            showMaze(maze, mazeGrid);
            mazeMenu.getChildren().clear();
            mazeMenu.getChildren().add(info);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvefollower);
            mazeMenu.getChildren().add(solveAllShortest);
            mazeMenu.getChildren().add(generateNewSW);
            mazeMenu.getChildren().add(generateNewRD);
        });
        
        generateNewRD.setOnAction((event) -> {
            maze.setLayout(reDiv.generate(maze));
            showMaze(maze, mazeGrid);
            mazeMenu.getChildren().clear();
            mazeMenu.getChildren().add(info);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvefollower);
            mazeMenu.getChildren().add(solveAllShortest);
            mazeMenu.getChildren().add(generateNewSW);
            mazeMenu.getChildren().add(generateNewRD);
        });
        
        primaryStage.setScene(startScene);
        primaryStage.setTitle("Maze Solver");
        primaryStage.show();
    }
    
    /**
     * Luo visuaalisen esityksen sokkelolle
     * @param maze Käsiteltävä sokkelo
     * @param mazeGrid Visuaalinen pohja, jolla sokkelo esitetään
     */
    public void showMaze(Maze maze, GridPane mazeGrid) {
        mazeGrid.getChildren().clear();
        for (int i = 0; i < maze.getSize(); i++) {
            for (int j = 0; j < maze.getSize(); j++) {
                Label square = new Label(" ");
                square.setMaxSize(10, 10);
                square.setMinSize(10, 10);
                
                if (maze.getFromCoordinates(i, j) == 1) {
                    square.setStyle("-fx-background-color: #000000");
                } else if (i == 1 && j == 1) {
                    square.setStyle("-fx-background-color: #0000ff");
                } else if (i == maze.getSize() - 2 && j == maze.getSize() - 2) {
                    square.setStyle("-fx-background-color: #00ff00");
                } else if (maze.getFromCoordinates(i, j) == 2) {
                    square.setStyle("-fx-background-color: #ff22ff");
                }
                
                mazeGrid.add(square, i, j);
            }
        }
    }
    
}