package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import main.Main;
import service.RegisterService;
import service.UpgradeService;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpgradeController {

    private static final String TEXT_FIELD_ERROR_CLASSNAME = "text-box-error";
    private static final Pattern VALID_CARD_NUMBER_REGEX = Pattern.compile("\\d{16}", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_MONTH_YEAR_REGEX = Pattern.compile("\\d{2}", Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_CVV_REGEX = Pattern.compile("\\d{3}", Pattern.CASE_INSENSITIVE);

    @FXML
    private TextField textFieldName;
    @FXML
    private TextField textFieldCardNumber;
    @FXML
    private TextField textFieldMonth;
    @FXML
    private TextField textFieldYear;
    @FXML
    private TextField textFieldCVV;

    @FXML
    private void back() {
        Main.back();
    }

    @FXML
    private void confirmPayment(){
        if (validateName(textFieldName)
                & validateCardNumber(textFieldCardNumber)
                & validateMonthYear(textFieldMonth)
                & validateMonthYear(textFieldYear)
                & validateCVV(textFieldCVV)) {
            if (new UpgradeService().upgrade(Main.user.getUserName())) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Upgraded successfully");
                alert.setHeaderText(null);
                alert.setContentText("Thank you " + Main.user.getUserName() + "!\nYou have upgraded to pro version successfully");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Error");
                alert.setHeaderText(null);
                alert.setContentText("Ooops! there was an error!");
                alert.showAndWait();
            }
        }
    }

    private boolean validateName(TextField textField) {
        if (textField.getText().isEmpty()) {
            addErrorToTextField(textField);
            return false;
        }
        removeErrorFromTextField(textField);
        return true;
    }

    private boolean validateCardNumber(TextField textField) {
        Matcher matcher = VALID_CARD_NUMBER_REGEX.matcher(textField.getText());
        if (matcher.matches()) {
            removeErrorFromTextField(textField);
            return true;
        } else {
            addErrorToTextField(textField);
            return false;
        }
    }

    private boolean validateMonthYear(TextField textField) {
        Matcher matcher = VALID_MONTH_YEAR_REGEX.matcher(textField.getText());
        if (matcher.matches()) {
            removeErrorFromTextField(textField);
            return true;
        } else {
            addErrorToTextField(textField);
            return false;
        }
    }

    private boolean validateCVV(TextField textField) {
        Matcher matcher = VALID_CVV_REGEX.matcher(textField.getText());
        if (matcher.matches()) {
            removeErrorFromTextField(textField);
            return true;
        } else {
            addErrorToTextField(textField);
            return false;
        }
    }

    private void addErrorToTextField(TextField textField) {
        ObservableList<String> styleClass = textField.getStyleClass();
        if (!styleClass.contains(TEXT_FIELD_ERROR_CLASSNAME))
            styleClass.add(TEXT_FIELD_ERROR_CLASSNAME);
    }

    private void removeErrorFromTextField(TextField textField) {
        ObservableList<String> styleClass = textField.getStyleClass();
        styleClass.remove(TEXT_FIELD_ERROR_CLASSNAME);
    }
}
