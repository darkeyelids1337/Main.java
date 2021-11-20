package TFDpackgae;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
public class Init {
    public static void onInit(Menu menu){
        final MenuItem menuitem = new MenuItem();
        menu.getItems().add(menuitem);
        menu.addEventHandler(Menu.ON_SHOWN, event -> menu.hide());
        menu.addEventHandler(Menu.ON_SHOWING, event -> menu.fire());
    }
    static FXMLLoader newFXMLLoader(Class<?> callerClass, String sceneName){
        return new FXMLLoader(callerClass.getResource(String.format("%s.fxml", sceneName)));
    }

}
