package helper;

import javafx.scene.Scene;

public class Navigation {
    private Scene scene;
    private String title;

    public Navigation(Scene scene, String title) {
        this.scene = scene;
        this.title = title;
    }

    public Scene getScene() {
        return scene;
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
