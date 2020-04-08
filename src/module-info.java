module battleship {
    requires javafx.controls;
    requires javafx.fxml;

    opens hse.edu.battleship.gui to javafx.fxml;
    exports hse.edu.battleship.core;
    exports hse.edu.battleship.gui;
}