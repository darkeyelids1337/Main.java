package TFDpackgae;

import TFDpackgae.FXMLMainFormController;
import functions.Function;
import functions.basic.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;

public class Main extends Application{
        public static void main(String[] args) {
            Function exp = new Exp();
            double theoreticValue = Math.E - 1;

        }
        @Override
        public void start(Stage stage) throws Exception {

        }
}