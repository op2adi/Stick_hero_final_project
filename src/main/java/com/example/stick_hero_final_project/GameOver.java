package com.example.stick_hero_final_project;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.*;

public class GameOver {
    @FXML
    public Circle getRevive() {
        return Revive;
    }

    public void setRevive(Circle revive) {
        Revive = revive;
    }

    @FXML
    private Circle Revive;

    public ImageView getRevive_image() {
        return Revive_image;
    }

    public void setRevive_image(ImageView revive_image) {
        Revive_image = revive_image;
    }

    @FXML
    private ImageView Revive_image;
    @FXML
    private ImageView restart_image1;
    @FXML
    private Rectangle restart;

    public ImageView getRestart_image1() {
        return restart_image1;
    }

    public void setRestart_image1(ImageView restart_image1) {
        this.restart_image1 = restart_image1;
    }

    public void setScore_tell(Label score_tell) {
        this.score_tell = score_tell;
    }

    @FXML
    private Rectangle exit_from_game;
    @FXML
    private Label score_tell;
    @FXML
    private Label high_score_tell;
    @FXML
    private ImageView restart_image;
    @FXML
    private ImageView exit_image;
    public void Restart(MouseEvent MouseEvent) throws IOException {

//        score_tell.setText();
        Stage stage;
        if (restart != null) {
            stage = (Stage) restart.getScene().getWindow();
        } else {
            stage = (Stage) restart_image.getScene().getWindow();
        }
        stage.setOnCloseRequest(event -> {
            // Handle the close request here
            System.out.println("Close button pressed");
            // You can perform any cleanup or other actions here before exiting
            Platform.exit(); // Exit the application
        });
        stage.close();

        stick_hero pop = new stick_hero(new Player_create(0, 0, 0, 0), 10, 0, 100, 30, 0, false, true, new Label("HI"));
        pop.back_create();
    }

    public void Exit(MouseEvent mouseEvent) {
        Stage stage;
        if (exit_from_game!=null){
            stage = (Stage) exit_from_game.getScene().getWindow();}
        else {
            stage = (Stage) exit_image.getScene().getWindow();
        }
        stage.close();
        Platform.exit();
    }

    public Rectangle getRestart() {
        return restart;
    }

    public void setRestart(Rectangle restart) {
        this.restart = restart;
    }

    public Rectangle getExit_from_game() {
        return exit_from_game;
    }

    public void setExit_from_game(Rectangle exit_from_game) {
        this.exit_from_game = exit_from_game;
    }

    public Label getScore_tell() {
        return score_tell;
    }

    public void setScore_tell(String text) {
        this.score_tell.setText(text);
    }

    public Label getHigh_score_tell() {
        return high_score_tell;
    }

    public void setHigh_score_tell(Label high_score_tell) {
        this.high_score_tell = high_score_tell;
    }

    public ImageView getRestart_image() {
        return restart_image;
    }

    public void setRestart_image(ImageView restart_image) {
        this.restart_image = restart_image;
    }

    public ImageView getExit_image() {
        return exit_image;
    }

    public void setExit_image(ImageView exit_image) {
        this.exit_image = exit_image;
    }

    public void Revive(MouseEvent mouseEvent) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("back_end_data.txt"))) {
            System.out.println("hi");
            Integer score = (Integer) inputStream.readObject();
            Integer chr = (Integer) inputStream.readObject();
            if (chr != null && score!=null){
                if (chr-5>=0){
                    System.out.println("Revived");

                    Player_create p = new Player_create(0,0,0,0);
                    Label wel = new Label();
                    Stage stage;
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("back_end_data.txt"))) {
                        System.out.println("Data to be added");
                        out.writeObject(score);
                        out.writeObject(chr-5);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("cherry_data.txt"))) {
                        System.out.println("Data to be added");
                        out.writeObject(chr-5);
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                    if (Revive!=null ){
                        stage = (Stage) Revive.getScene().getWindow();}
                    else {
                        stage = (Stage) Revive_image.getScene().getWindow();
                    }
                    stage.close();
                    stick_hero sth = new stick_hero(p,1,score,10,10,chr-5,false,false,wel);
                    sth.back_create();
                }
                else{

                    System.out.println("Cant revive");
                }
                //cherryscore.setText(String.valueOf(chr));
                //cherries = chr;
            } else {
                System.out.println("Invalid data found in the file.");
            }
        } catch (FileNotFoundException e) {
            //System.out.println("File not found. No high score recorded yet.");
            //cherryscore.setText(String.valueOf(0));
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        catch (Exception e){
            //cherryscore.setText(String.valueOf(0));
        }

    }

}