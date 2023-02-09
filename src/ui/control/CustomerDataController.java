package ui.control;

import java.util.logging.Logger;

import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * @author dani
 */
public class CustomerDataController {
    
    @FXML
    private Button b;

    private Stage stage;

    private static final Logger LOGGER = 
        Logger.getLogger("package:");

    private static final String WINDOW_NAME = 
        "Customer's accounts and balances";
        

    public void initStage(Parent root) {
       LOGGER.info("Initialazing " + WINDOW_NAME + " window."); 
       Scene scene = new Scene(root);
       stage.setScene(scene);

       stage.setScene(scene);

        // Set properties
        stage.setTitle(WINDOW_NAME);
        stage.setResizable(false);
        stage.setOnShowing(this::handlerWindowShowing);

        stage.show();
    }

    /**
     * Prepare the stage for a change of scene
     * @param stage where the window shows
     */

     public void setStage(Stage stage) {
        this.stage = stage;
    }

    private void handlerWindowShowing(WindowEvent event) {
        LOGGER.info("Initializing UserWindowController::handlerWindowShowing");
    }
}
