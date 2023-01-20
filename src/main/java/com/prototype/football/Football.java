package com.prototype.football;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Football extends Application {
    private static final int FIELD_WIDTH = 300;
    private static final int FIELD_HEIGHT = 300;
    private static final int BALL_RADIUS = 25;
    private static final int UPDATE_DELAY = 5;

    private int ballX = 0;
    private int ballY = 0;
    private int ballXDirection = 1;
    private int ballYDirection = 1;
    Circle ball = new Circle(BALL_RADIUS, Color.BLACK);

    Timeline timeline = new Timeline(new KeyFrame(Duration.millis(UPDATE_DELAY), event -> {
        ballX += ballXDirection;
        ballY += ballYDirection;
        // other code here
        ball.setLayoutX(ballX - BALL_RADIUS);
        ball.setLayoutY(ballY - BALL_RADIUS);
    }));
    
    private List<Player> players = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        Scene scene = new Scene(root, FIELD_WIDTH, FIELD_HEIGHT);

        MenuBar menuBar = new MenuBar();
        Menu fileMenu = new Menu("File");
        MenuItem createPlayerMenuItem = new MenuItem("Create Player...");
        createPlayerMenuItem.setOnAction(event -> showCreatePlayerDialog());
        fileMenu.getItems().add(createPlayerMenuItem);
        MenuItem showPlayersMenuItem = new MenuItem("Show Players...");
        showPlayersMenuItem.setOnAction(event -> showPlayers());
        fileMenu.getItems().add(showPlayersMenuItem);
        menuBar.getMenus().add(fileMenu);
        root.setTop(menuBar);

        Group ballGroup = new Group();
        root.setCenter(ballGroup);

        Circle ball = new Circle(BALL_RADIUS, Color.BLACK);
        ballGroup.getChildren().add(ball);

        scene.setOnKeyPressed(event -> {
            KeyCode keyCode = event.getCode();
            if (keyCode == KeyCode.UP) {
                ballYDirection = -1;
            } else if (keyCode == KeyCode.DOWN) {
                ballYDirection = 1;
            } else if (keyCode == KeyCode.LEFT) {
                ballXDirection = -1;
            } else if (keyCode == KeyCode.RIGHT)
                ballXDirection = 1;
        });
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
        ballGroup.getChildren().add(ball);
        ballGroup.requestFocus();


        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void showCreatePlayerDialog() {
        TextInputDialog nameDialog = new TextInputDialog();
        nameDialog.setTitle("Create Player");
        nameDialog.setHeaderText("Enter player name:");
        Optional<String> nameResult = nameDialog.showAndWait();
        if (nameResult.isEmpty()) {
            return;
        }
        String name = nameResult.get();

        ChoiceDialog<Integer> ageDialog = new ChoiceDialog<>(20, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30);
        ageDialog.setTitle("Create Player");
        ageDialog.setHeaderText("Select player age:");
        Optional<Integer> ageResult = ageDialog.showAndWait();
        if (ageResult.isEmpty()) {
            return;
        }
        int age = ageResult.get();

        TextInputDialog heightDialog = new TextInputDialog();
        heightDialog.setTitle("Create Player");
        heightDialog.setHeaderText("Enter player height (in meters):");
        Optional<String> heightResult = heightDialog.showAndWait();
        if (heightResult.isEmpty()) {
            return;
        }
        float height = Float.parseFloat(heightResult.get());

        TextInputDialog weightDialog = new TextInputDialog();
        weightDialog.setTitle("Create Player");
        weightDialog.setHeaderText("Enter player weight (in kilograms):");
        Optional<String> weightResult = weightDialog.showAndWait();
        if (weightResult.isEmpty()) {
            return;
        }
        float weight = Float.parseFloat(weightResult.get());

        ChoiceDialog<String> nationalityDialog = new ChoiceDialog<>("Argentinian", "Argentinian", "Brazilian", "Chilean", "Colombian", "Ecuadorian", "Paraguayan", "Peruvian", "Uruguayan", "Venezuelan");
        nationalityDialog.setTitle("Create Player");
        nationalityDialog.setHeaderText("Select player nationality:");
        Optional<String> nationalityResult = nationalityDialog.showAndWait();
        if (nationalityResult.isEmpty()) {
            return;
        }
        String nationality = nationalityResult.get();

        ChoiceDialog<String> leagueDialog = new ChoiceDialog<>("La Liga", "La Liga", "Premier League", "Bundesliga", "Serie A", "Ligue 1", "Eredivisie");
        leagueDialog.setTitle("Create Player");
        leagueDialog.setHeaderText("Select player league:");
        Optional<String> leagueResult = leagueDialog.showAndWait();
        if (leagueResult.isEmpty()) {
            return;
        }
        String league = leagueResult.get();

        ChoiceDialog<String> positionDialog = new ChoiceDialog<>("Forward", "Forward", "Midfielder", "Defender", "Goalkeeper");
        positionDialog.setTitle("Create Player");
        positionDialog.setHeaderText("Select player position:");
        Optional<String> positionResult = positionDialog.showAndWait();
        if (positionResult.isEmpty()) {
            return;
        }
        String position = positionResult.get();
        TextInputDialog teamDialog = new TextInputDialog();
        teamDialog.setTitle("Create Player");
        teamDialog.setHeaderText("Enter player team:");
        Optional<String> teamResult = teamDialog.showAndWait();
        if (teamResult.isEmpty()) {
            return;
        }
        String team = teamResult.get();

        Player player = new Player(name, age, height, weight, nationality, league, position, team);
        players.add(player);
    }

    private void showPlayers() {
        VBox root = new VBox();
        Scene scene = new Scene(root);

        for (Player player : players) {
            Label label = new Label(player.getName());
            root.getChildren().add(label);
        }

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }
    private static class Player {
        private final String name;
        private final int age;
        private final float height;
        private final float weight;
        private final String nationality;
        private final String league;
        private final String position;
        private final String team;

        public Player(String name, int age, float height, float weight, String nationality, String league, String position, String team) {
            this.name = name;
            this.age = age;
            this.height = height;
            this.weight = weight;
            this.nationality = nationality;
            this.league = league;
            this.position = position;
            this.team = team;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        public float getHeight() {
            return height;
        }

        public float getWeight() {
            return weight;
        }

        public String getNationality() {
            return nationality;
        }

        public String getLeague() {
            return league;
        }

        public String getPosition() {
            return position;
        }

        public String getTeam() {
            return team;
        }
    }

}

