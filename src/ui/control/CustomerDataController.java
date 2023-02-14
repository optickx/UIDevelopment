package ui.control;

import clientside.controller.CustomerManager;
import clientside.model.Account;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author dani
 */
public class CustomerDataController extends GenericController {
    
    @FXML
    private Button btnSearch, btnExit;

    @FXML
    private Text customerIDText, accountsTable, totalBalanceText;

    @FXML
    private TableView <Account> accountsTableView;

    private Stage stage;

    private static final String WINDOW_NAME = 
        "Customer's accounts and balances";
    
    private CustomerManager customerManager;
        

    public void initStage(Parent root) {
       LOGGER.info("Initialazing " + WINDOW_NAME + " window."); 
       Scene scene = new Scene(root);
       stage.setScene(scene);
       stage.setScene(scene);
        // Set properties
        stage.setTitle(WINDOW_NAME);
        stage.setResizable(false);
        stage.setOnShowing(this::handlerWindowShowing);
        // add property change listeners
        stage.show();
    }
    /**
     * Prepare the stage for a change of scene
     * @param stage where the window shows
     */
     public void setStage(Stage stage) {
        this.stage = stage;
    }
    /**
     * sets the value of the object that manages and
     * gets all the information from the server.
     * @param customerManager object that implementates the interface
     */
    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
    /**
     * 
     * @param event
     */
    private void handlerWindowShowing(WindowEvent event) {
        LOGGER.info("Initializing UserWindowController::handlerWindowShowing");
    }
}
