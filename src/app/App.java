package app;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.stage.Stage;
import server.LoadCustomerManager;
import ui.control.CustomerDataController;

/**
 * @author dani
 */
public class App extends Application {

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader loader = 
                new FXMLLoader(getClass().getClassLoader().getResource("ui/view/CustomerData.fxml"));
            Parent root = (Parent) loader.load();
            //Obtain the Sign In window controller
            CustomerDataController controller = 
                CustomerDataController.class
                    .cast(loader.getController());
            controller.setCustomerManager(LoadCustomerManager.getCustomerManager());
            controller.setStage(stage);
            controller.initStage(root);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
