package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import main.Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static constants.PathConstants.*;
import static constants.SceneConstants.*;
import static main.Main.navigate;


public class ProfileController {
    @FXML
    private Circle profilePic;

    @FXML
    private Button buttonupdate;

    @FXML
    private Button buttonCumulativeScore;

    @FXML
    private Button buttonProVersion;

    @FXML
    private Button buttonRankings;

    @FXML
    private Label imageurl;
    final FileChooser fileChooser = new FileChooser();

    @FXML
    public void profilePicSelect(MouseEvent event) throws Exception {
        try {
            profilePic.setStroke(Color.SEAGREEN);
            File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
            String imagePath = file.getAbsolutePath();
            if (!imagePath.isEmpty()) {
                System.out.println(imagePath);
                FileInputStream inputstream = new FileInputStream(imagePath);
                Image imagePic = new Image(inputstream);
                profilePic.setFill(new ImagePattern(imagePic));
                profilePic.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    @FXML
    private void proVersionAction(ActionEvent event) {
        try {
            navigate(PRO_FXML_PATH, PRO_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void rankings(ActionEvent event) {

    }

    @FXML
    void updateUserInformation(ActionEvent event) {
        try {
            Main.navigate(UPDATE_INFO_FXML_PATH, UPDATE_INFO_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void viewCumulativeScore(ActionEvent event) {
        try {
            Main.navigate(CUMULATIVE_FXML_PATH, CUMULATIVE_QUIZ_RESULTS_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    void back() {
        Main.back();
    }
}
