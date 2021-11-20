package TFDpackgae;

import functions.Function;
import functions.FunctionPoint;
import functions.InappropriateFunctionPointException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.json.simple.parser.ParseException;

public class FXMLMainFormController implements Initializable {
    @FXML
    private MenuItem ExitItem;

    @FXML
    private MenuItem NewFileItem;

    @FXML
    private MenuItem OpenFileItem;

    @FXML
    private MenuItem SaveFIleAsItem;

    @FXML
    private MenuItem SaveFileItem;

    @FXML
    private TextField edX;

    @FXML
    private TextField edY;

    @FXML
    private Label numOfPoint;

    @FXML
    private Label pointX;

    @FXML
    private Label pointY;

    @FXML
    private Button addButton;

    @FXML
    private TableView<FunctionPointT> table;

    @FXML
    private TableColumn<FunctionPointT, Double> xColumn;

    private FileChooser fileChooser;

    private TabulateWindow tw;
    private Loader loader;
    @FXML
    private Menu TabulateMenuButton;

    @FXML
    private TableColumn<FunctionPointT, Double> yColumn;

    public void initialize(URL location, ResourceBundle resources) {
        tw = new TabulateWindow();
        fileChooser = new FileChooser();
        loader = new Loader();
        xColumn.setCellValueFactory(new PropertyValueFactory<FunctionPointT,Double>("X"));
        yColumn.setCellValueFactory(new PropertyValueFactory<FunctionPointT,Double>("Y"));
        numOfPoint.setText("Number of point: 0 of " + Main.tabFDoc.getPointsCount());
        initializeMenu();
    }

    private void initializeMenu() {
        NewFileItem.setOnAction( event -> {
            try {
                if (cancelBecauseNotSaved()) return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            Optional<tabFDocParametres> params = tw.showAndWait();
            params.ifPresent(TabFDocParametres -> {
                Main.tabFDoc.newFunction(
                        TabFDocParametres.leftX,
                        TabFDocParametres.rightX,
                        TabFDocParametres.pointscount
                );
                redraw();
            });
        });
        SaveFileItem.setOnAction(event -> {
            try {
                Main.tabFDoc.saveFunction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        SaveFIleAsItem.setOnAction(event -> {
            try {
                saveFileAsAction();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        OpenFileItem.setOnAction(event -> {
            try {
                if (cancelBecauseNotSaved()) return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            File selectedFile = fileChooser.showOpenDialog(getWindow());
            if (selectedFile != null) {
                try {
                    Main.tabFDoc.loadFunction(selectedFile.getAbsolutePath());
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ParseException e) {
                    e.printStackTrace();
                } catch (InappropriateFunctionPointException e) {
                    e.printStackTrace();
                }
                redraw();
            }
        });
        ExitItem.setOnAction(event -> getWindow().close());
        TabulateMenuButton.setOnAction(event -> {
            try {
                if(cancelBecauseNotSaved()) return;
            } catch (IOException e) {
                e.printStackTrace();
            }
            File selectedFile = fileChooser.showOpenDialog(getWindow());
            if(selectedFile != null){
                try{
                    Function function = loader.loadFunction(selectedFile);
                    Optional<tabFDocParametres> parametres = tw.showAndWait();
                    parametres.ifPresent(tabFDocParametres -> {
                        try{
                            Main.tabFDoc.tabulateFunction(
                                    function, tabFDocParametres.leftX, tabFDocParametres.rightX, tabFDocParametres.pointscount
                            );
                            redraw();
                        } catch (IllegalArgumentException e){
                            ErrorMes.errorProcession(e, "Tabulate error");
                        }
                    });

                } catch (InstantiationException | IllegalArgumentException | ClassNotFoundException | NoSuchMethodException | InvocationTargetException | IllegalAccessException e){
                    ErrorMes.errorProcession(e, "Load error");
                }
            }
        });
        Init.onInit(TabulateMenuButton);

    }

    public void redraw() {
       table.getItems().clear();
       for (int i = 0; i < Main.tabFDoc.getPointsCount(); i++){
           table.getItems().add(new FunctionPointT(Main.tabFDoc.getPointX(i), Main.tabFDoc.getPointY(i) ));
       }
       numOfPoint.setText("Number of point:" + " " + (table.getSelectionModel().getSelectedIndex() + 1) + " of " + Main.tabFDoc.getPointsCount());
    }
    @FXML
    public void btNewCLick(ActionEvent event) {
        FunctionPointT functionPointT = new FunctionPointT(Double.parseDouble(edX.getText()), Double.parseDouble(edY.getText()));
        Main.tabFDoc.addPoint(new FunctionPoint(Double.parseDouble(edX.getText()), Double.parseDouble(edY.getText())));
        edX.clear();
        edY.clear();
    }

    public void btNewClick1(ActionEvent event) {
        int i = table.getSelectionModel().getSelectedIndex();
        Main.tabFDoc.deletePoint(i);
    }

    public void btNewClick2(MouseEvent mouseEvent) {
        int index = table.getSelectionModel().getSelectedIndex();
        numOfPoint.setText("Number of point:" + " " + (index + 1) + " of " + " " + Main.tabFDoc.getPointsCount());
    }
    private Stage getWindow() {
        return (Stage) addButton.getScene().getWindow();
    }
    private void saveFileAsAction() throws IOException {
        File saveFile = fileChooser.showSaveDialog(getWindow());
        Main.tabFDoc.saveFunctionAs(saveFile.getAbsolutePath());

    }
    private boolean cancelBecauseNotSaved() throws IOException {
        if (Main.tabFDoc.isModified()) {
            Optional<ButtonType> result = new Alert(
                    Alert.AlertType.CONFIRMATION,
                    "Не сохранено, сохранить?",
                    ButtonType.CANCEL, ButtonType.NO, ButtonType.YES
            ).showAndWait();
            if (result.isPresent()) {
                ButtonType buttonType = result.get();

                if (ButtonType.CANCEL == buttonType) return true;

                if (ButtonType.YES == buttonType) {
                    Main.tabFDoc.saveFunction();
                }
            } else {
                return true;
            }
        }
        return false;
    }

}