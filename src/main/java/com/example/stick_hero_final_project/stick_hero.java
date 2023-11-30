package com.example.stick_hero_final_project;

import javafx.animation.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.io.Serializable;

import static java.lang.Thread.sleep;

public class stick_hero extends Thread implements score_interface,cherries,points,Serializable{
    public int getStick_speed_fllag() {
        return stick_speed_fllag;
    }

    public void setStick_speed_fllag(int stick_speed_fllag) {
        this.stick_speed_fllag = stick_speed_fllag;
    }
    private int postion_face = 0; // 0 means up and 1 means down
    private int stick_speed_fllag = 0;
    private String start_of_game_sound= "D:\\Stick_Hero_Final_Project\\src\\main\\java\\com\\example\\stick_hero_final_project\\Sounds\\Start_of_Game.mp4";

    public String getStart_of_game() {
        return start_of_game_sound;
    }
    private CountDownLatch latch = new CountDownLatch(0);
    private Scene newScene;
    stick s = new stick(10, 10, 5, 0);

    public void setStart_of_game(String start_of_game_sound) {
        this.start_of_game_sound = start_of_game_sound;
    }

    public CountDownLatch getLatch() {
        return latch;
    }

    public void setLatch() {
        latch = new CountDownLatch(1);
    }

    public void setTest(int test) {
        this.test.set(test);
    }

    private AtomicInteger test = new AtomicInteger();

    public Media getSound() {
        return sound;
    }

    public void setSound(Media sound) {
        this.sound = sound;
    }

    private Media sound = new Media(new File(start_of_game_sound).toURI().toString());

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

    public String getStart_of_game_sound() {
        return start_of_game_sound;
    }

    public void setStart_of_game_sound(String start_of_game_sound) {
        this.start_of_game_sound = start_of_game_sound;
    }

    public void setLatch(CountDownLatch latch) {
        this.latch = latch;
    }

    public Scene getNewScene() {
        return newScene;
    }

    public void setNewScene(Scene newScene) {
        this.newScene = newScene;
    }

    public stick getS() {
        return s;
    }

    public void setS(stick s) {
        this.s = s;
    }

    public AtomicInteger getTest() {
        return test;
    }

    public void setTest(AtomicInteger test) {
        this.test = test;
    }

    public Stage getNewStage() {
        return newStage;
    }

    public void setNewStage(Stage newStage) {
        this.newStage = newStage;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public void setTimeline(Timeline timeline) {
        this.timeline = timeline;
    }

    public Parent getRoot() {
        return root;
    }

    public void setRoot(Parent root) {
        this.root = root;
    }

    public void setAdi_flag(int adi_flag) {
        this.adi_flag = adi_flag;
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

    public void start_song(){
        MediaPlayer mediaPlayer = new MediaPlayer(this.getSound());
        mediaPlayer.play();
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
        setTest(1);
    }

    private final int width = 800;
    private final int stick_hero_height = 50;
    private int speed = 1;
    private Stage newStage = new Stage();
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
    private Timeline timeline;
    public void setAhead_pillar(Pillar ahead_pillar) {
        this.ahead_pillar = ahead_pillar;
    }
    private int adi_flag=100; //yeh flag prevent for any code to get run more than 1 time if not needed
    private int  pillar_length = 100;
    private int getPillar_width = 30;
    private int cherries=0;
    private boolean keyIsPressed = false;
    private Parent root;
    private boolean click_flag = true; // will make it True once key is pressed
    @FXML
    private Label welcomeText;

    public int getScore_view() {
        return score;
    }

    public void setScore_view(int scor) {
        score_view.setText("Score" + String.valueOf(scor));
    }

    private Label score_view;

    @FXML
    protected void onHelloButtonClick() {
        welcomeText.setText("Welcome to Game");
    }


    public int getAdi_flag() {
        return adi_flag;
    }
    public void back_create() throws IOException {
        score_view = new Label("Score"+String.valueOf(score));

        FXMLLoader loader = new FXMLLoader(getClass().getResource("game.fxml"));
        //Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        player = cr_pl();
        // Create a new stage
        newStage.setTitle("Stick_hero_game"); // Set the title of the new window
        // Set up the scene with the loaded FXML content
        newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.setWidth(600);
        newStage.setHeight(800);
        newStage.setMaxWidth(600);
        newStage.setMaxHeight(730);
        Image backgroundImage = null;
        String srt = getRandomImage();
        backgroundImage = new Image(srt);
        BackgroundImage background = new BackgroundImage(
                backgroundImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT
        );
        Background backgroundWithImage = new Background(background);
        ImageView backgroundImageView = new ImageView(backgroundImage);
        System.out.println(srt); //debug statement for background
// Create a Background with the BackgroundImage
// Set the background for the Pane
        backgroundImageView.setFitWidth(600);
        backgroundImageView.setFitHeight(700);
        score_view.setStyle("-fx-font-size: 40px; -fx-font-weight: bold;");
        ((Pane) root).getChildren().add(backgroundImageView);
        ((Pane) root).getChildren().add(score_view);
        score_view.setLayoutX(300); // Position from the right
        score_view.setLayoutY(100);
//        initialize1((Pane) root);
        // Show the new stage
        ((Pane) root).getChildren().add(player.getNode());
        player.getNode().setX(0);
        player.getNode().setY(500);
        start_song();
        newStage.show();
//        while (newScene==null){}
        init();
    }

    public int getPostion_face() {
        return postion_face;
    }

    public void setPostion_face(int postion_face) {
        this.postion_face = postion_face;
    }

    public void init() throws IOException {
        setScore_view(score);
        setClick_flag(true);
        keyIsPressed = true;
        if (ahead_pillar!=null && ((Pane) root).getChildren().contains(ahead_pillar.getRedblock())){
            ((Pane) root).getChildren().remove(ahead_pillar.getRedblock());
        }
        current_pillar = getAhead_pillar();
        createRandomPillar((Pane) root);
// Create a BackgroundImage



        newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
                    System.out.println(click_flag);
                    try {
                        latch.await();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    if (click_flag) {
                        keyIsPressed = true;
                        double x = event.getSceneX();
                        double y = event.getSceneY();

                        s = new stick(0, 0, 5, 0);
                        if (!((Pane) root).getChildren().contains(s.getStick())) {
                            ((Pane) root).getChildren().add(s.getStick());
                        }

                        System.out.println("Start and end " + player.getNode().getX() + "    " + player.getNode().getY());
                        s.getStick().setLayoutX(player.getNode().getX() + 40);
                        s.getStick().setLayoutY(player.getNode().getY() + 30);
                        AtomicReference<Double> ext = new AtomicReference<>((double) 5);
                        timeline = new Timeline( //thoda pdhna padega timeline ke baare me
                                new KeyFrame(Duration.millis(100), e -> {
                                    if (keyIsPressed && click_flag) {
                                        s.extend(speed);
//                            latch.countDown();
                                        ext.updateAndGet(v -> new Double((double) (v * 0.9)));
//                            System.out.println("HI"); Debug statement
                                    }
                                })
                        );
                        timeline.setCycleCount(timeline.INDEFINITE);
                        timeline.play();
                        timeline.setOnFinished(actionEvent -> {
                            timeline.stop();
                            timeline.setCycleCount(0);
                        });
                    }

        });
        newScene.addEventFilter(MouseEvent.MOUSE_PRESSED, event -> {
            double x = event.getSceneX();
            double y = event.getSceneY();
            System.out.println("X: " + x + ", Y: " + y);
        });
        newScene.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.SPACE && !click_flag){
                if (postion_face==0) {
                    player.getNode().setScaleY(-1);
                    System.out.println("GUIII");
                    // Simulating very fast movement and scaling using Timeline
                    player.getNode().setLayoutY(player.getNode().getLayoutY() + 20);
                    System.out.println(player.getNode().getY());
                    // Scale the player vertically

                    postion_face = 1;

                }
                else{
                    player.getNode().setLayoutY(player.getNode().getLayoutY()-20);
                    System.out.println("BY");
                    // Scale the player vertically
                    player.getNode().setScaleY(1);// Start the animation
                    postion_face = 0;

                }
            }
        });

        newScene.addEventFilter(MouseEvent.MOUSE_RELEASED, event -> {
            if (keyIsPressed==true){
                //timeline.stop();
            System.out.println(ahead_pillar.getPillar().getX());
            //CountDownLatch latch = new CountDownLatch(1);
            keyIsPressed = false;
            click_flag = false;
            System.out.println(click_flag);
            setTest(0);
            newScene.getRoot().setDisable(true);
            root.setDisable(true);
            Runnable st = () -> {
                try {
                    s.fallHorizontally(s, player, ahead_pillar, (Pane) root, newScene, current_pillar, newStage, this); // Start the rotation animation
                    //latch.countDown();
                    System.out.println(test);

                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            };

            Runnable bt = () -> {
                //t1.join(); // Wait for t1 to complete
                System.out.println("HUHU");
                //latch.await();

                //click_flag = true;


            };

            new Thread(st).start(); // Start thread t1
            new Thread(bt).start();
            //t1.join();
            //t2.start();
            //t2.join();
            test.set(0);
//                Thread.sleep(1000);
//                newScene.getRoot().setDisable(false);


            // Start thread t2}
        }
        });


        //Yha pr pillar ko dalna hai
        if (adi_flag==100){
            createmainPillar((Pane) root);
            adi_flag=101; // setting this so also not providing any setter function such that by any chance it cant re do and edit this intentionally not providing setter
        }
    }
    public void createmainPillar(Pane root){
        //will come on start Ninja jispr khara rhega
        current_pillar = new Pillar(player.getNode().getX(),530,40,500);
        root.getChildren().add(current_pillar.getNode());
        return;
    }
    public void createRandomPillar(Pane root) {
        try {

            Random random = new Random();
            int width = Math.abs(random.nextInt()) % 50 + 20; // width is random setting as likha tha assignment me
            double height =player.getNode().getY()-50; // yha pr basically uski hieght acc to ninja niklegi wese to yeh ek constant as uska y axis will not change never
            int distance = Math.abs(random.nextInt()) % 400 + 100;
            ahead_pillar = new Pillar(player.getNode().getX()+200, 530, width, height);

            // Set pillar position at a random distance
            ahead_pillar.getPillar().setX(distance);
            Rectangle rd = new Rectangle(5, 2, Color.RED);
            rd.setY(ahead_pillar.getPillar().getY());
            rd.setFill(Color.RED);
            rd.setX((ahead_pillar.getPillar().getX()+width/2));
            System.out.println(rd.getX());
            ahead_pillar.setRedblock(rd);

            // Add the pillar to the root pane
            ((Pane) root).getChildren().addAll(ahead_pillar.getNode(), ahead_pillar.getRedblock());
            return;
        }
        catch (Exception e){ //thodi error handling
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
        background back_handler = new background();
        Random random = new Random();
        int index = random.nextInt(back_handler.getBackgroundImages().size());
        return back_handler.getBackgroundImages().get(index);
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
