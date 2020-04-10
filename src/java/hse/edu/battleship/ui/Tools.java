package hse.edu.battleship.ui;

import javafx.scene.control.Alert;
import javafx.stage.StageStyle;

/**
 * Tools
 */
public class Tools {
    /**
     * Shows error window
     *
     * @param header  header message
     * @param content content
     */
    static void showError(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
