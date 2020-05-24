package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Dialog for client connect
 */
public class ClientConnectDialog {
    final ClientConnectDialogController controller;
    final Stage primaryStage;

    public ClientConnectDialog(Ocean ocean) {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("resources/fxml/ClientConnectDialog.fxml"));

        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        primaryStage = new Stage();
        primaryStage.setScene(new Scene(root));
        primaryStage.getIcons().setAll(new Image(getClass().getResourceAsStream("resources/images/battleship_logo.png")));
        primaryStage.setTitle("Client dialog");
        primaryStage.setResizable(false);
        primaryStage.show();
        root.requestFocus();

        controller = fxmlLoader.getController();
        controller.ocean = ocean;
    }
}
