module cqu.game {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;

    opens cqu.game to javafx.fxml;
    exports cqu.game;
}
