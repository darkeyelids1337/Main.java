package TFDpackgae;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class TabulatedController implements Initializable {

    @FXML
    private TextField LeftDomaintText;

    @FXML
    private Spinner<Integer> PointsCountSpinner;

    @FXML
    private TextField RightDomainText;

    private SpinnerValueFactory.IntegerSpinnerValueFactory pointsCountValueFactory;

    private tabFDocParametres TabFDocParametres;

    public void initialize(URL url, ResourceBundle resourceBundle){
        pointsCountValueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(2, Integer.MAX_VALUE);
        PointsCountSpinner.setValueFactory(pointsCountValueFactory);
    }
   boolean OKact(){
        double leftX = Double.parseDouble(LeftDomaintText.getText());
        double rightX = Double.parseDouble(RightDomainText.getText());
        int pointscount = PointsCountSpinner.getValue();
        TabFDocParametres = new tabFDocParametres(leftX, rightX, pointscount);
        return true;
    }

    void cancelAct(){
        TabFDocParametres = null;
    }
    public tabFDocParametres getParametres(){
        return TabFDocParametres;
    }




}