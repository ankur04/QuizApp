package controller;

import helper.DateHelper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
import service.UpdateUserInfoService;
import service.UserDetailsService;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import static constants.PathConstants.*;
import static constants.SceneConstants.*;
import static main.Main.navigate;


public class ProfileController {

    @FXML
    private Label usernameLabel;
    @FXML
    private Label emailAddressLabel;
    @FXML
    private Label lastLoggedInLabel;
    @FXML
    private Label noOfQuizTakenLabel;
    @FXML
    private Label averageScoreLabel;

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

    public void initialize() {

        // if gender can't exist for guest so set update button to invisible
        if (Main.user.getGender() == null)
            buttonupdate.setVisible(false);

        usernameLabel.setText(Main.user.getUserName());
        emailAddressLabel.setText(Main.user.getEmailId());

        UserDetailsService userDetailsService = new UserDetailsService();
        noOfQuizTakenLabel.setText(userDetailsService.fetchNoOfQuiz() + "");
        averageScoreLabel.setText(userDetailsService.fetchAvgScore() + "%");

        if (Main.user.getImageUrl() != null) {
            FileInputStream inputStream = null;
            try {
                inputStream = new FileInputStream(Main.user.getImageUrl());
                Image imagePic = new Image(inputStream);
                profilePic.setFill(new ImagePattern(imagePic));
                profilePic.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

        if (Main.user.getLastLoggedIn() != null) {
            DateFormat dateFormat = new SimpleDateFormat(DateHelper.VALID_DATE_PATTERN);
            String strDate = dateFormat.format(Main.user.getLastLoggedIn());
            lastLoggedInLabel.setText(strDate);
        }
    }

    @FXML
    public void profilePicSelect(MouseEvent event) throws Exception {
        try {
            profilePic.setStroke(Color.SEAGREEN);
            File file = fileChooser.showOpenDialog(Main.getPrimaryStage());
            String imagePath = file.getAbsolutePath();
            if (!imagePath.isEmpty()) {
                FileInputStream inputStream = new FileInputStream(imagePath);
                Image imagePic = new Image(inputStream);
                profilePic.setFill(new ImagePattern(imagePic));
                profilePic.setEffect(new DropShadow(+25d, 0d, +2d, Color.DARKSEAGREEN));
                // https://www.javatpoint.com/storing-image-in-oracle-database
                if (new UpdateUserInfoService().updateImage(imagePath, Main.user.getUserName())) {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Uploaded successfully");
                    alert.setHeaderText(null);
                    alert.setContentText("Your picture has been uploaded successfully");
                    alert.showAndWait();
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText(null);
                    alert.setContentText("Ooops! there was an error!");
                    alert.showAndWait();
                }
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
    void rankings(ActionEvent event) throws Exception {
        navigate(RANKING_FXML_PATH, RANKING_TITLE, CSS_PATH);
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
