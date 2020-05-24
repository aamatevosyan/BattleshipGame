package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * Controller for servers's connect dialog
 */
public class ServerConnectDialogController {

    @FXML
    public TextField nameTextField;
    @FXML
    TextField portTextField;
    @FXML
    TextField addressTextField;

    Server server;

    Ocean ocean;

    public void initialize() {
        try {
            addressTextField.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        nameTextField.setText("Server");
        portTextField.setText("765");
    }

    @FXML
    private void onCreate(ActionEvent actionEvent) {
        if (server != null && server.isRunning.get()) {
            Tools.showError("Error!!!", "Server is already running!!!");
            return;
        }

        try {
            if (nameTextField.getText().isEmpty() || portTextField.getText().isEmpty())
                throw new Exception("Entered parameters can't be empty.");

            if (!Tools.isValidPort(portTextField.getText()))
                throw new Exception("Port is not valid.");

        } catch (Exception e) {
            Tools.showError("Error!!!", e.getMessage());
        }

        final String name = nameTextField.getText();
        final int port = Integer.parseInt(portTextField.getText());

        server = new Server(port, name);
        server.ocean = ocean;
        server.stage = (Stage) nameTextField.getScene().getWindow();
        server.start();
    }
}
