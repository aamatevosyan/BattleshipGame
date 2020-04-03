module battleship {
    requires javafx.base;
    requires javafx.controls;
    requires javafx.fxml;

    opens hse.edu.battleship.gui.main to javafx.fxml;
    //opens hse.edu.battleship.gui to javafx.fxml, battleship.core;
    exports hse.edu.battleship.gui.main;
}