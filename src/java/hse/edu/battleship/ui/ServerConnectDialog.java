package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Dialog for server connect
 */
public class ServerConnectDialog {
    final ServerConnectDialogController controller;
    final Stage primaryStage;

    public ServerConnectDialog(Ocean ocean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/ServerConnectDialog.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));
        primaryStage.setTitle("Server dialog");
        primaryStage.setResizable(false);
        primaryStage.show();
        root.requestFocus();

        controller = fxmlLoader.getController();
        controller.ocean = ocean;

        /*
        Closes server if dialog was closed by user
         */
        primaryStage.setOnCloseRequest(windowEvent -> {
            if (!primaryStage.getTitle().equals("Close"))
                System.exit(0);
        });
    }
}
