module battleship {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    opens hse.edu.battleship.gui to javafx.fxml, javafx.base, javafx.controls;
    exports hse.edu.battleship.gui;
}