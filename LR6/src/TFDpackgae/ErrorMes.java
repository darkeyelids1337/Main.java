package TFDpackgae;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public class ErrorMes {
    private static void errorShow(String message) {
        new Alert(Alert.AlertType.ERROR, message, ButtonType.OK).show();
    }
    static void errorProcession(Exception e, String message) {
        ErrorMes.errorShow(message + ": " + e.getMessage());
    }
}
