package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import service.LoginService;

import java.io.IOException;

import static constants.PathConstants.*;
import static constants.SceneConstants.HOME_TITLE;
import static constants.SceneConstants.REGISTER_TITLE;
import static main.Main.navigate;

public class LoginController {

    @FXML
    private TextField textFieldEmail;
    @FXML
    private TextField textFieldPassword;
    @FXML
    private Label errorEmail;
    @FXML
    private Label errorPassword;

    @FXML
    public void login(ActionEvent actionEvent) {
        if (textFieldEmail.getText().isEmpty()) {
            errorEmail.setText("Invalid UserName or Email id found");
        } else if (textFieldPassword.getText().isEmpty() ||
                !textFieldPassword.getText().matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$")) {
            errorPassword.setText("Invalid Password found");
        } else {
            errorEmail.setText("");
            errorPassword.setText("");
            try {
                LoginService loginService = new LoginService();
                if (loginService.checkPassword(textFieldEmail.getText(), textFieldPassword.getText())) {
                    navigate(HOME_FXML_PATH, HOME_TITLE, CSS_PATH);
                } else {
                    errorEmail.setText("Username or password is incorrect. Please try again");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    @FXML
    public void register() {
        try {
            navigate(REGISTER_FXML_PATH, REGISTER_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void playAsGuest() {
        try {
            navigate(HOME_FXML_PATH, HOME_TITLE, CSS_PATH);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
