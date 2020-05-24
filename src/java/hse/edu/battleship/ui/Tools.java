package hse.edu.battleship.ui;

import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.util.regex.Pattern;

/**
 * Tools
 */
public class Tools {
    private static final String IPv4_REGEX =
            "^(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})\\.(\\d{1,3})$";
    private static final Pattern IPv4_PATTERN = Pattern.compile(IPv4_REGEX);

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
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);

        alert.showAndWait();
    }

    /**
     * Shows info window
     *
     * @param header  header message
     * @param content content
     */
    static void showInfo(String header, String content) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.initStyle(StageStyle.UNDECORATED);
        alert.setTitle("Error");
        alert.setHeaderText(header);
        alert.setContentText(content);
        ((Stage) alert.getDialogPane().getScene().getWindow()).setAlwaysOnTop(true);

        alert.showAndWait();
    }

    /**
     * Checks if IP adress is valid
     *
     * @param ip IP address
     * @return true if IP address is valid, and false otherwise
     */
    public static boolean isValidAddress(String ip) {

        if (ip == null) {
            return false;
        }

        if (!IPv4_PATTERN.matcher(ip).matches())
            return false;

        String[] parts = ip.split("\\.");

        // verify that each of the four subgroups of IPv4 address is legal
        try {
            for (String segment : parts) {
                // x.0.x.x is accepted but x.01.x.x is not
                if (Integer.parseInt(segment) > 255 ||
                        (segment.length() > 1 && segment.startsWith("0"))) {
                    return false;
                }
            }
        } catch (NumberFormatException e) {
            return false;
        }

        return true;
    }

    /**
     * Checks if port is valid
     *
     * @param port port
     * @return true if port is valid, and false otherwise
     */
    public static boolean isValidPort(String port) {
        int res;
        try {
            res = Integer.parseInt(port);
        } catch (NumberFormatException e) {
            return false;
        }

        return res >= 0 && res <= 65535;
    }
}
