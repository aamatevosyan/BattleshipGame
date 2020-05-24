package hse.edu.battleship.ui;

import hse.edu.battleship.core.Ocean;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.Random;

/**
 * Controller for client's connect dialog
 */
public class ClientConnectDialogController {

    @FXML
    TextField nameTextField;
    @FXML
    TextField portTextField;
    @FXML
    TextField addressTextField;

    Ocean ocean;

    Client client;

    /**
     * Generates random string
     *
     * @param n length of string
     * @return random string
     */
    static String getAlphaNumericString(int n) {

        // length is bounded by 256 Character
        byte[] array = new byte[256];
        new Random().nextBytes(array);

        String randomString
                = new String(array, StandardCharsets.UTF_8);

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer();

        // Append first 20 alphanumeric characters
        // from the generated random String into the result
        for (int k = 0; k < randomString.length(); k++) {

            char ch = randomString.charAt(k);

            if (((ch >= 'a' && ch <= 'z')
                    || (ch >= 'A' && ch <= 'Z')
                    || (ch >= '0' && ch <= '9'))
                    && (n > 0)) {

                r.append(ch);
                n--;
            }
        }

        // return the resultant string
        return r.toString();
    }

    public void initialize() {
        nameTextField.setText("Client-" + getAlphaNumericString(4));
        portTextField.setText("765");
        try {
            addressTextField.setText(InetAddress.getLocalHost().getHostAddress());
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void onConnect(ActionEvent actionEvent) {
        try {
            /*
            Checking for correct parameters
             */
            if (nameTextField.getText().isEmpty() || addressTextField.getText().isEmpty() || portTextField.getText().isEmpty())
                throw new Exception("Entered parameters can't be empty.");

            if (!Tools.isValidAddress(addressTextField.getText()))
                throw new Exception("IP address is not valid.");

            if (!Tools.isValidPort(portTextField.getText()))
                throw new Exception("Port is not valid.");

            final String name = nameTextField.getText();
            final String ip = addressTextField.getText();
            final int port = Integer.parseInt(portTextField.getText());

            client = new Client(ip, port, name);
            client.ocean = ocean;
            client.stage = (Stage) ((Button) actionEvent.getSource()).getScene().getWindow();
            client.start();

        } catch (Exception e) {
            Tools.showError("Error!!!", e.getMessage());
        }
    }
}
