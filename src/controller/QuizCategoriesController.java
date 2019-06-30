package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import main.Main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static constants.PathConstants.APPEAR_QUIZ_FXML_PATH;
import static constants.PathConstants.CSS_PATH;
import static constants.SceneConstants.APPEAR_FOR_QUIZ_TITLE;

public class QuizCategoriesController {

    @FXML
    ListView<Hyperlink> hyperlinkListView;

    private List<Hyperlink> hyperlinks = new ArrayList<Hyperlink>(Arrays.asList(
            new Hyperlink("Category 1"),
            new Hyperlink("Category 2"),
            new Hyperlink("Category 3"),
            new Hyperlink("Category 4"),
            new Hyperlink("Category 5")
    ));

    public void initialize() {
        displayDummyCategories();
    }

    private void displayDummyCategories() {

        hyperlinks.stream().forEach(hyperlink -> {
            hyperlinkListView.getItems().add(hyperlink);
            hyperlink.setOnMouseClicked(mouseEvent -> {
                try {
                    Main.navigate(APPEAR_QUIZ_FXML_PATH, APPEAR_FOR_QUIZ_TITLE, CSS_PATH);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        });
    }

    @FXML
    private void back() {
        Main.back();
    }
}
