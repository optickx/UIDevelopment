package ui.control;

import java.util.logging.Logger;

import clientside.controller.CustomerManager;
import clientside.controller.CustomerManagerFactory;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.scene.control.ButtonType;

public abstract class GenericController {

    protected static final Logger LOGGER = 
        Logger.getLogger("package:");

    /**
     * Maximum text fields length.
     */
    protected final int MAX_LENGTH = 255;
    /**
     * The business logic object containing all business methods.
     */
    protected CustomerManager customerManager;
    /**
     * Sets the business logic object to be used by this UI controller. 
     * @param server An object implementing {@link UsersManager} interface.
     * TODO: change the server cofiguration.
     */
    public void setUsersManager(String server){
        customerManager = 
            CustomerManagerFactory.getCustomerManager(server);
    }
    /**
     * The Stage object associated to the Scene controlled by this controller.
     * This is an utility method reference that provides quick access inside the 
     * controller to the Stage object in order to make its initialization. Note 
     * that this makes Application, Controller and Stage being tightly coupled.
     */
    protected Stage stage;
    /**
     * Gets the Stage object related to this controller.
     * @return The Stage object initialized by this controller.
     */
    public Stage getStage(){
        return stage;
    }
    /**
     * Sets the Stage object related to this controller. 
     * @param stage The Stage object to be initialized.
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
    /**
     * Shows an error message in an alert dialog.
     * @param errorMsg The error message to be shown.
     */
    protected void showErrorAlert(String errorMsg){
        //Shows error dialog.
        Alert alert =
            new Alert(Alert.AlertType.ERROR, errorMsg, ButtonType.OK);
        // alert.getDialogPane().getStylesheets().add(getClass().getResource("/javafxapplicationud3example/ui/view/customCascadeStyleSheet.css").toExternalForm());
        alert.showAndWait();
        
    }


    
}
