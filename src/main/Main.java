package main;

import helper.Navigation;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.Category;
import model.Question;
import model.Result;
import model.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import static constants.PathConstants.*;
import static constants.SceneConstants.*;

public class Main extends Application {

    private static Result result = new Result();

    private static Stage primaryStage;

    private static Stack<Navigation> navigationStack = new Stack<Navigation>();

    public static User user;
    private static Category category;

    private static List<Question> questionList = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void setCategory(Category category) {
        Main.category = category;
    }

    public static Category getCategory() {
        return Main.category;
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Main.primaryStage = primaryStage;

        Main.navigate(LOGIN_FXML_PATH, LOGIN_TITLE, CSS_PATH);

        primaryStage.show();
    }

    public static void navigate(String fxmlPath, String title, String cssPath) throws IOException {
        Parent root = FXMLLoader.load(Main.class.getResource(fxmlPath));

        Scene scene = new Scene(root, SCENE_HEIGHT, SCENE_WIDTH);
        if (cssPath != null)
            scene.getStylesheets().add(cssPath);
        Main.primaryStage.setScene(scene);

        Main.primaryStage.setTitle(title);

        navigationStack.push(new Navigation(scene, title));
    }

    public static void back() {
        navigationStack.pop();
        Navigation backNav = navigationStack.peek();
        Main.primaryStage.setScene(backNav.getScene());
        Main.primaryStage.setTitle(backNav.getTitle());
    }

    public static void home() {
        try {
            navigationStack.clear();
            Main.navigate(HOME_FXML_PATH, HOME_TITLE, null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void setQuestionsList(List<Question> questionsList) {
        Main.questionList = questionsList;
    }

    public static List<Question> getQuestionsList() {
        return Main.questionList;
    }
}
