package hse.edu.battleship.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("resources/fxml/Main.fxml"));
        Scene scene = new Scene(root, 380, 330);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Battleship Game - Arrr!!!");
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));

        Font.loadFont(getClass().getResource("resources/fonts/Lobster.otf").toExternalForm(), 10);
        Font.loadFont(getClass().getResource("resources/fonts/Roboto-Regular.ttf").toExternalForm(), 10);

        scene.getStylesheets().add(getClass().getResource("resources/css/Main.css").toExternalForm());

        primaryStage.show();
    }
}
