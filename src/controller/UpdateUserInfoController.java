package controller;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import main.Main;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UpdateUserInfoController {
    private static final String TEXT_FIELD_ERROR_CLASSNAME = "text-box-error";
    private static final String DATE_PICKER_ERROR_CLASSNAME = "date-picker-error";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$",
            Pattern.CASE_INSENSITIVE);
    private static final Pattern VALID_PHONE_NUMBER_REGEX = Pattern.compile("\\d{10}", Pattern.CASE_INSENSITIVE);
    private static final String VALID_DATE_PATTERN = "MM/dd/yyyy";

    @FXML
    TextField textFieldUsername;
    @FXML
    TextField textFieldEmail;
    @FXML
    TextField textFieldPassword;
    @FXML
    TextField textFieldConfirmPassword;
    @FXML
    TextField textFieldPhoneNumber;

    @FXML
    ToggleGroup toggleGroupGender;
    @FXML
    RadioButton radioButtonMale;
    @FXML
    RadioButton radioButtonFemale;
    @FXML
    RadioButton radioButtonDefault;

    @FXML
    DatePicker datePickerDOB;

    @FXML
    Button buttonBack;

    public void initialize() {
        textFieldUsername.setText("Dharmesh Patel");
        textFieldEmail.setText("95dharmesh@gmail.com");
        textFieldPhoneNumber.setText("6476124010");
        radioButtonMale.setSelected(true);
        datePickerDOB.getEditor().setText("01/28/1995");
    }

    @FXML
    private void back() {
        reset();
        Main.back();
    }

    @FXML
    private void reset() {
        textFieldUsername.clear();
        textFieldEmail.clear();
        textFieldPassword.clear();
        textFieldConfirmPassword.clear();
        textFieldPhoneNumber.clear();
        radioButtonDefault.setSelected(true);
        datePickerDOB.getEditor().clear();

        removeErrorFromTextField(textFieldUsername);
        removeErrorFromTextField(textFieldEmail);
        removeErrorFromTextField(textFieldPassword);
        removeErrorFromTextField(textFieldConfirmPassword);
        removeErrorFromTextField(textFieldPhoneNumber);
        removeErrorFromDatePicker(datePickerDOB);
    }

    @FXML
    private void register() {
        if (validateUsername(textFieldUsername)
                & validateEmail(textFieldEmail)
                & validatePasswords(textFieldPassword, textFieldConfirmPassword)
                & validatePhoneNumber(textFieldPhoneNumber)
                & validateDOB(datePickerDOB)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.show();
        }
    }

    private boolean validateDOB(DatePicker datePicker) {
        TextField datePickerTextField = datePicker.getEditor();
        if (datePickerTextField != null)
            try {
                DateFormat df = new SimpleDateFormat(VALID_DATE_PATTERN);
                df.setLenient(false);
                df.parse(datePickerTextField.getText());
                removeErrorFromDatePicker(datePicker);
                return true;
            } catch (ParseException e) {
                addErrorToDatePicker(datePicker);
                return false;
            }
        else {
            addErrorToDatePicker(datePicker);
            return false;
        }
    }

    private boolean validatePhoneNumber(TextField textField) {
        Matcher matcher = VALID_PHONE_NUMBER_REGEX.matcher(textField.getText());
        if (matcher.matches()) {
            removeErrorFromTextField(textField);
            return true;
        } else {
            addErrorToTextField(textField);
            return false;
        }
    }

    private boolean validateUsername(TextField textField) {
        if (textField.getText().isEmpty()) {
            addErrorToTextField(textField);
            return false;
        }
        removeErrorFromTextField(textField);
        return true;
    }

    private boolean validateEmail(TextField textField) {
        if (textField.getText().isEmpty()) {
            addErrorToTextField(textField);
            return false;
        } else {
            Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(textField.getText());
            if (matcher.matches()) {
                removeErrorFromTextField(textField);
                return true;
            } else {
                addErrorToTextField(textField);
                return false;
            }
        }
    }

    private boolean validatePasswords(TextField textField1, TextField textField2) {
        if (textField1.getText().isEmpty() || textField2.getText().isEmpty()) {
            addErrorToTextField(textField1);
            addErrorToTextField(textField2);
            return false;
        } else {
            if (textField1.getText().length() >= 8 && textField1.getText().equals(textField2.getText())) {
                removeErrorFromTextField(textField1);
                removeErrorFromTextField(textField2);
                return true;
            }
            addErrorToTextField(textField1);
            addErrorToTextField(textField2);
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

    private void addErrorToDatePicker(DatePicker datePicker) {
        ObservableList<String> styleClass = datePicker.getStyleClass();
        if (!styleClass.contains(DATE_PICKER_ERROR_CLASSNAME))
            datePicker.getStyleClass().add(DATE_PICKER_ERROR_CLASSNAME);
    }

    private void removeErrorFromDatePicker(DatePicker datePicker) {
        ObservableList<String> styleClass = datePicker.getStyleClass();
        styleClass.remove(DATE_PICKER_ERROR_CLASSNAME);
    }
}
