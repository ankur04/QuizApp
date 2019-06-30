package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;

import static constants.PathConstants.*;
import static constants.SceneConstants.*;
import static main.Main.navigate;

public class HomeController {
    @FXML
    private Button accountInfo;
    @FXML
    private Button aboutus;
    @FXML
    private Button logoutButton;
    @FXML
    private Button category;
    @FXML
    private Label firstName;
    @FXML
    private Label date;
    @FXML
    private Label title;
    @FXML
    private DatePicker currentDate;

    @FXML
    private void quizCategory() {
        try {
            navigate(CATEGORIES_FXML_PATH, CATEGORIES_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void logout() {
        try {
            navigate(LOGIN_FXML_PATH, LOGIN_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void aboutUs() {
        try {
            navigate(ABOUT_US_FXML_PATH, ABOUT_US_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void accountInfoAction() {
        try {
            navigate(PROFILE_FXML_PATH, PROFILE_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
