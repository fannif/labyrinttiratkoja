package labyrinthsolver.main;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import labyrinthsolver.domain.algorithms.ChainSolver;
import labyrinthsolver.domain.algorithms.Kruskal;
import labyrinthsolver.domain.utils.Maze;
import labyrinthsolver.domain.algorithms.RecursiveDivision;
import labyrinthsolver.domain.algorithms.ShortestPaths;
import labyrinthsolver.domain.algorithms.Sidewinder;
import labyrinthsolver.domain.algorithms.WallFollower;
import labyrinthsolver.domain.utils.Tester;

public class Main extends Application {
    
    private boolean small;
    
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
        WallFollower wallFollower = new WallFollower();
        Kruskal kruskal = new Kruskal();
        RecursiveDivision reDiv = new RecursiveDivision();
        ShortestPaths allShortest = new ShortestPaths();
        ChainSolver chainSolver = new ChainSolver();
        small = true;
        
        BorderPane startLayout = new BorderPane();
        startLayout.setStyle("-fx-background-color: #ffdcff");
        
        VBox startMenu = new VBox(30);
        startMenu.setPadding(new Insets(100));
        
        Label welcome = new Label("Welcome!\nWhat would you like to do?"
                + "\n You can generate a small maze (maze image visible) "
                + "\n or a big maze (no maze image visible).");
        welcome.setFont(Font.font("Verdana", FontWeight.BOLD, 20));
        
        Button generateSmall = new Button("Generate a 51-by-51 maze");
        Button generateBig = new Button("Generate a 213-by-213 maze");
        Button testPerformance = new Button("Run performance tests (Duration: Approx. 9 mins)");
        
        Button generateSW = new Button("Generate a Sidewinder maze");
        Button generateRD = new Button("Generate a Recursive Division maze");
        Button generateKruskal = new Button("Generate a Kruskal maze");
        
        startMenu.setAlignment(Pos.CENTER);
        startMenu.getChildren().add(welcome);
        startMenu.getChildren().add(generateSmall);
        startMenu.getChildren().add(generateBig);
        startMenu.getChildren().add(testPerformance);
        startMenu.getChildren().add(new Label("The results and progress for performance tests are \nshown in the console window, instead of the UI window.\nRunning the tests will take approximately 9 minutes.\nWhile the tests are running, the program cannot do anything else."));
        
        startLayout.setCenter(startMenu);
        Scene startScene = new Scene(startLayout, 1000, 720);
        
        BorderPane mazeLayout = new BorderPane();
        
        VBox mazeMenu = new VBox(20);
        mazeMenu.setPadding(new Insets(20, 100, 20, 100));
        HBox generators = new HBox();
        HBox solvers = new HBox();

        Button generateNewSW = new Button("Generate a new Sidewinder maze");
        Button generateNewRD = new Button("Generate a new Recursive division maze");
        Button generateNewKruskal = new Button("Generate a new Kruskal maze");
        Button solvefollower = new Button("Solve using Wall Follower");
        Button solveAllShortest = new Button("Solve all shortest paths");
        Button solveChain = new Button("Solve using chain algorithm");
        Label info = new Label("Blue dot is the start. Green dot is the goal.");
        Label routeInfo = new Label("Purple shows the route generated by solver algorithm.");
        Button back = new Button("Return to start menu");
        
        solvers.getChildren().add(solvefollower);
        solvers.getChildren().add(solveAllShortest); 
        solvers.getChildren().add(solveChain); 
        generators.getChildren().add(generateNewSW);
        generators.getChildren().add(generateNewRD);
        generators.getChildren().add(generateNewKruskal);
        
        GridPane mazeGrid = new GridPane();
        
        showMaze(maze, mazeGrid);
        
        HBox generatedLast = new HBox();
        
        mazeMenu.setAlignment(Pos.CENTER);
        mazeMenu.getChildren().add(back);
        mazeMenu.getChildren().add(info);
        mazeMenu.getChildren().add(mazeGrid);
        mazeMenu.getChildren().add(solvers);
        mazeMenu.getChildren().add(generators);
        
        mazeLayout.setCenter(mazeMenu);
        Scene mazeScene = new Scene(mazeLayout, 1000, 720);
        
        generateSmall.setOnAction((event) -> {
            maze.initialize(51);
            small = true;
            startMenu.getChildren().clear();
            startMenu.getChildren().add(generateSW);
            startMenu.getChildren().add(generateRD);
            startMenu.getChildren().add(generateKruskal);
            startLayout.setCenter(startMenu);
        });
        
        generateBig.setOnAction((event) -> {
            maze.initialize(213);
            small = false;
            startMenu.getChildren().clear();
            startMenu.getChildren().add(generateSW);
            startMenu.getChildren().add(generateRD);
            startMenu.getChildren().add(generateKruskal);
            startLayout.setCenter(startMenu);
        });
        
        generateSW.setOnAction((event) -> {
            maze.setLayout(sidew.generate(maze));
            if (small) {
                showMaze(maze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
            }
            mazeMenu.getChildren().add(new Label("Time to generate: " + sidew.getTime() + " ns" + " (=" + sidew.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
            primaryStage.setScene(mazeScene);
            generatedLast.getChildren().clear();
            generatedLast.getChildren().add(new Label("Time to generate: " + sidew.getTime() + " ns" + " (=" + sidew.getTime() / 1000000 + " ms)"));
        });
        
        generateRD.setOnAction((event) -> {
            maze.setLayout(reDiv.generate(maze));
            if (small) {
                showMaze(maze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
            }
            mazeMenu.getChildren().add(new Label("Time to generate: " + reDiv.getTime() + " ns" + " (=" + reDiv.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
            primaryStage.setScene(mazeScene);
            generatedLast.getChildren().clear();
            generatedLast.getChildren().add(new Label("Time to generate: " + reDiv.getTime() + " ns" + " (=" + reDiv.getTime() / 1000000 + " ms)"));
        });
        
        generateKruskal.setOnAction((event) -> {
            maze.setLayout(kruskal.generate(maze));
            if (small) {
                showMaze(maze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
            }
            mazeMenu.getChildren().add(new Label("Time to generate: " + kruskal.getTime() + " ns" + " (=" + kruskal.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
            primaryStage.setScene(mazeScene);
            generatedLast.getChildren().clear();
            generatedLast.getChildren().add(new Label("Time to generate: " + kruskal.getTime() + " ns" + " (=" + kruskal.getTime() / 1000000 + " ms)"));
        });
        
        solvefollower.setOnAction((event) -> {
            Maze solvedMaze = new Maze(maze.getSize());
            solvedMaze.setLayout(wallFollower.solve(maze));
            if (small) {
                showMaze(solvedMaze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(new Label("Length of solution route: " + wallFollower.solutionLength()));
            }
            mazeMenu.getChildren().add(new Label("Time to solve: " + wallFollower.getTime() + " ns" + " (=" + wallFollower.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(generatedLast);
            generatedLast.setAlignment(Pos.CENTER);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
        });
        
        solveAllShortest.setOnAction((event) -> {
            Maze solvedMaze = new Maze(maze.getSize());
            solvedMaze.setLayout(allShortest.solve(maze));
            if (small) {
                showMaze(solvedMaze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(new Label("Length of solution route: " + allShortest.solutionLength()));
            }
            mazeMenu.getChildren().add(new Label("Time to solve: " + allShortest.getTime() + " ns" + " (=" + allShortest.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(generatedLast);
            generatedLast.setAlignment(Pos.CENTER);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
        });
        
        solveChain.setOnAction((event) -> {
            Maze solvedMaze = new Maze(maze.getSize());
            solvedMaze.setLayout(chainSolver.solve(maze));
            if (small) {
                showMaze(solvedMaze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(new Label("Length of solution route: " + chainSolver.solutionLength()));
            }
            mazeMenu.getChildren().add(new Label("Time to solve: " + chainSolver.getTime() + " ns" + " (=" + chainSolver.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(generatedLast);
            generatedLast.setAlignment(Pos.CENTER);
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
        });
        
        generateNewSW.setOnAction((event) -> {
            maze.setLayout(sidew.generate(maze));
            if (small) {
                showMaze(maze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
            }
            mazeMenu.getChildren().add(new Label("Time to generate: " + sidew.getTime() + " ns" + " (=" + sidew.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
            generatedLast.getChildren().clear();
            generatedLast.getChildren().add(new Label("Time to generate: " + sidew.getTime() + " ns" + " (=" + sidew.getTime() / 1000000 + " ms)"));
        });
        
        generateNewRD.setOnAction((event) -> {
            maze.setLayout(reDiv.generate(maze));
            if (small) {
                showMaze(maze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
            }
            mazeMenu.getChildren().add(new Label("Time to generate: " + reDiv.getTime() + " ns" + " (=" + reDiv.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
            generatedLast.getChildren().clear();
            generatedLast.getChildren().add(new Label("Time to generate: " + reDiv.getTime() + " ns" + " (=" + reDiv.getTime() / 1000000 + " ms)"));
        });
        
        generateNewKruskal.setOnAction((event) -> {
            maze.setLayout(kruskal.generate(maze));
            if (small) {
                showMaze(maze, mazeGrid);
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
                mazeMenu.getChildren().add(info);
                mazeMenu.getChildren().add(routeInfo);
            } else {
                mazeGrid.getChildren().clear();
                mazeMenu.getChildren().clear();
                mazeMenu.getChildren().add(back);
            }
            mazeMenu.getChildren().add(new Label("Time to generate: " + kruskal.getTime() + " ns" + " (=" + kruskal.getTime() / 1000000 + " ms)"));
            mazeMenu.getChildren().add(mazeGrid);
            mazeMenu.getChildren().add(solvers);
            mazeMenu.getChildren().add(generators);
            generatedLast.getChildren().clear();
            generatedLast.getChildren().add(new Label("Time to generate: " + kruskal.getTime() + " ns" + " (=" + kruskal.getTime() / 1000000 + " ms)"));
        });
        
        back.setOnAction((event) -> {
            startMenu.getChildren().clear();
            startMenu.setAlignment(Pos.CENTER);
            startMenu.getChildren().add(welcome);
            startMenu.getChildren().add(generateSmall);
            startMenu.getChildren().add(generateBig);
            startMenu.getChildren().add(testPerformance);
            startMenu.getChildren().add(new Label("The results and progress for performance tests are \nshown in the console window, instead of the UI window.\nRunning the tests will take approximately 9 minutes.\nWhile the tests are running, the program cannot do anything else."));
            primaryStage.setScene(startScene);
        });
        
        testPerformance.setOnAction((event) -> {
            runPerformanceTests();
            startMenu.getChildren().clear();
            startMenu.setAlignment(Pos.CENTER);
            startMenu.getChildren().add(welcome);
            startMenu.getChildren().add(generateSmall);
            startMenu.getChildren().add(generateBig);
            startMenu.getChildren().add(testPerformance);
            startMenu.getChildren().add(new Label("The results and progress for performance tests are \nshown in the console window, instead of the UI window.\nRunning the tests will take approximately 9 minutes.\nWhile the tests are running, the program cannot do anything else."));
            primaryStage.setScene(startScene);
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
        int n = maze.getSize();
        int[] currentMaze = maze.getLayout();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Label square = new Label(" ");
                square.setMaxSize(8, 8);
                square.setMinSize(8, 8);
                
                if (currentMaze[i * n + j] == 1) {
                    square.setStyle("-fx-background-color: #000000");
                } else if (i == 1 && j == 1) {
                    square.setStyle("-fx-background-color: #0000ff");
                } else if (i == n - 2 && j == n - 2) {
                    square.setStyle("-fx-background-color: #00ff00");
                } else if (currentMaze[i * n + j] == 2) {
                    square.setStyle("-fx-background-color: #ff22ff");
                }
                
                mazeGrid.add(square, i, j);
            }
        }
    }
    
    /**
     * Ajaa automatisoidut suorituskykytestit.
     */
    public void runPerformanceTests() {
        Tester tester = new Tester();
        tester.runAll();
    }
    
}