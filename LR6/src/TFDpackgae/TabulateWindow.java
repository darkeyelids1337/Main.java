package TFDpackgae;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;

import java.io.IOException;
public class TabulateWindow extends Dialog<tabFDocParametres> {
    TabulateWindow(){
        try{
            FXMLLoader loader = Init.newFXMLLoader(getClass(), "FXMLTabulated");
            Parent root = loader.load();
            DialogPane dialogPane = getDialogPane();
            dialogPane.setContent(root);
            dialogPane.getButtonTypes().addAll(ButtonType.CANCEL, ButtonType.OK);
            TabulatedController controller = loader.getController();
            Button OKButton = (Button) dialogPane.lookupButton(ButtonType.OK);
            OKButton.setOnAction(event -> controller.OKact());
            OKButton.addEventFilter(
                    ActionEvent.ACTION, event -> {
                        if (!controller.OKact()){
                            event.consume();
                        }
                    }
            );
            Button cancelButton = (Button) dialogPane.lookupButton(ButtonType.CANCEL);
            cancelButton.setOnAction(event -> controller.cancelAct());
            setResultConverter(buttonType -> controller.getParametres());
            setOnCloseRequest(event -> hide());
        } catch (IOException e){
            e.printStackTrace();
        }
    }

}
