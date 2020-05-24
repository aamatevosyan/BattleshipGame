package hse.edu.battleship.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class Main extends Application {

    public static void main(String[] args) {
        try {
            if (args.length != 1)
                throw new IllegalArgumentException("Invalid count of arguments.");

            if (!args[0].equals("Server") && !args[0].equals("Client") && !args[0].equals("Solo"))
                throw new IllegalArgumentException("Invalid argument.");

            launch(args);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        Font.loadFont(getClass().getResource("resources/fonts/Lobster-Regular.ttf").toExternalForm(), 14);
        Font.loadFont(getClass().getResource("resources/fonts/Roboto-Regular.ttf").toExternalForm(), 14);

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/Main.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Scene scene = new Scene(root, 380, 380);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setTitle("Battleship Game - Arrr!!!");
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));

        scene.getStylesheets().add(getClass().getResource("resources/css/Main.css").toExternalForm());

        ((Controller) fxmlLoader.getController()).gameMode = GameMode.valueOf(getParameters().getRaw().get(0));

        primaryStage.show();
    }
}
