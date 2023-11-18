package com.example.stick_hero_final_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Random;

import static java.lang.Thread.sleep;

public class stick_hero extends background implements score_interface,cherries,points{
    private Player_create player;
    private final int height = 80;

    public Player_create getPlayer() {
        return player;
    }

    public void setPlayer(Player_create player) {
        this.player = player;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getStick_hero_height() {
        return stick_hero_height;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getPillar_length() {
        return pillar_length;
    }

    public void setPillar_length(int pillar_length) {
        this.pillar_length = pillar_length;
    }

    public int getGetPillar_width() {
        return getPillar_width;
    }

    public void setGetPillar_width(int getPillar_width) {
        this.getPillar_width = getPillar_width;
    }

    public int getCherries() {
        return cherries;
    }

    public void setCherries(int cherries) {
        this.cherries = cherries;
    }

    public boolean isKeyIsPressed() {
        return keyIsPressed;
    }

    public void setKeyIsPressed(boolean keyIsPressed) {
        this.keyIsPressed = keyIsPressed;
    }

    public boolean isClick_flag() {
        return click_flag;
    }

    public void setClick_flag(boolean click_flag) {
        this.click_flag = click_flag;
    }

    public Label getWelcomeText() {
        return welcomeText;
    }

    public void setWelcomeText(Label welcomeText) {
        this.welcomeText = welcomeText;
    }

    public stick_hero(Player_create player, int speed, int score, int pillar_length, int getPillar_width, int cherries, boolean keyIsPressed, boolean click_flag, Label welcomeText) {
        this.player = null;
        this.speed = speed;
        this.score = score;
        this.pillar_length = pillar_length;
        this.getPillar_width = getPillar_width;
        this.cherries = cherries;
        this.keyIsPressed = keyIsPressed;
        this.click_flag = click_flag;
        this.welcomeText = welcomeText;
    }

    private final int width = 800;
    private final int stick_hero_height = 50;
    private int speed = 1;
    private int score = 0;
    private Pillar current_pillar;
    private Pillar ahead_pillar;

    public Pillar getCurrent_pillar() {
        return current_pillar;
    }

    public void setCurrent_pillar(Pillar current_pillar) {
        this.current_pillar = current_pillar;
    }

    public Pillar getAhead_pillar() {
        return ahead_pillar;
    }

    public void setAhead_pillar(Pillar ahead_pillar) {
        this.ahead_pillar = ahead_pillar;
    }

    private int  pillar_length = 100;
    private int getPillar_width = 30;
    private int cherries=0;
    private boolean keyIsPressed = false;

    private boolean click_flag = true; // will make it True once key is pressed
    @FXML
    private Label welcomeText;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Game");
    }


    public void init() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = loader.load();
        player = cr_pl();
        // Create a new stage
        Stage newStage = new Stage();
        newStage.setTitle("Stick_hero_game"); // Set the title of the new window
        // Set up the scene with the loaded FXML content
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        String srt = getRandomImage();
        Image backgroundImage = new Image(srt);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        System.out.println(srt);
        newStage.setWidth(600);
        newStage.setHeight(800);
        newStage.setMaxWidth(600);
        newStage.setMaxHeight(730);

// Create a BackgroundImage
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

// Create a Background with the BackgroundImage
        Background backgroundWithImage = new Background(background);

// Set the background for the Pane
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(700);
        ((Pane) root).getChildren().add(backgroundImageView);
//        initialize1((Pane) root);
        // Show the new stage
        ((Pane) root).getChildren().add(player.getNode());
        player.getNode().setX(0);
        player.getNode().setY(500);


        newScene.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            keyIsPressed = false;
        });

        newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            keyIsPressed = true;
            double x = event.getSceneX();
            double y = event.getSceneY();

            stick s = new stick(10, 10, 5, 0);
            ((Pane) root).getChildren().add(s.getStick());

            s.getStick().setLayoutX(player.getNode().getX() + 40);
            s.getStick().setLayoutY(player.getNode().getY() + 30);

            Timeline timeline = new Timeline( //thoda pdhna padega timeline ke baare me
                    new KeyFrame(Duration.millis(100), e -> {
                        if (keyIsPressed && click_flag==true) {
                            s.extend(10);

//                            System.out.println("HI"); Debug statement
                        }
                    })
            );
            timeline.setCycleCount(Timeline.INDEFINITE);
            timeline.play();
        });

        newScene.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            keyIsPressed = false;
            click_flag=false;
        });

        //Yha pr pillar ko dalna hai
        Random random = new Random();
        int width = Math.abs(random.nextInt()) % 100 + 100;
        int height = Math.abs(random.nextInt()) % 300 + 100;
        int distance = Math.abs(random.nextInt()) % 400 + 100;

        createRandomPillar((Pane) root);

        newStage.show();
    }

    private void createRandomPillar(Pane root) {
        try {

            Random random = new Random();
            int width = Math.abs(random.nextInt()) % 100 + 30;
            int height = Math.abs(random.nextInt()) % 300 + 30;

            ahead_pillar = new Pillar(400, 400, width, height);
            int distance = Math.abs(random.nextInt()) % 400 + 100;

            // Set pillar position at a random distance
            ahead_pillar.getNode().setLayoutX(distance);

            // Add the pillar to the root pane
            ((Pane) root).getChildren().add(ahead_pillar.getNode());
            return;
        }
        catch (Exception e){
            System.out.println("HI");
            e.printStackTrace();
            System.exit(-1);
        }
    }
        public Node cr_pl_get_nd(){
        return (new Player_create(0,0,0,0)).getNode();
    }

    public Player_create cr_pl(){
        return (new Player_create(0,0,0,0));
    }

    public void initialize1(Pane mainPane) {
        // Set the background image
        Image backgroundImage = new Image(getRandomImage());
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );

        mainPane.setBackground(new Background(background));
    }

    private String getRandomImage() {
        Random random = new Random();
        int index = random.nextInt(getBackgroundImages().size());
        return getBackgroundImages().get(index);
    }
    @FXML
    private void onMouseDragged(MouseEvent event) {
        double xCoordinate = event.getX();
        double yCoordinate = event.getY();
        System.out.println("Mouse dragged at coordinates: (" + xCoordinate + ", " + yCoordinate + ")");

        // You can perform any action or logic based on these coordinates here
    }

    @Override
    public void viewscore() {

    }

    @Override
    public void setscore() {

    }

    @Override
    public void increase_score() {

    }

    @Override
    public void set_cherries() {

    }

    @Override
    public void inc_cherries() {

    }

    @Override
    public void view_cherries() {

    }

    @Override
    public void revive_cherries() {

    }
    @Override
    public void display() {

    }

    @Override
    public void set_score() {

    }

    @Override
    public void increment() {

    }

    @Override
    public void perfect_increment() {

    }
}
