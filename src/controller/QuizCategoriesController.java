package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.ListView;
import main.Main;
import model.Category;
import service.QuizCategoriesService;

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

    private List<Category> categories = new ArrayList<Category>();
    private List<Hyperlink> hyperlinks = new ArrayList<Hyperlink>();

    public void initialize() {
        categories = new QuizCategoriesService().getCategories();
        displayCategories();
    }

    private void displayCategories() {
        categories.stream().forEach(category -> {
            Hyperlink hyperlink = new Hyperlink(category.getCategoryName());
            hyperlinkListView.getItems().add(hyperlink);
            hyperlink.setOnMouseClicked(mouseEvent -> {
                try {
                    Main.setCategory(category);
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
