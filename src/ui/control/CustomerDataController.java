package ui.control;

import java.text.NumberFormat;
import java.util.List;
import java.util.Optional;

import clientside.controller.CustomerManager;
import clientside.exception.ReadException;
import clientside.model.Account;
import clientside.model.Customer;
import exceptions.NoAccountsException;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
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
    private Text txtAccountsTable;

    @FXML
    private TextField txtFieldCustomerID, txtFieldTotalBalance;

    @FXML
    private TableColumn
        colAccountNumber, colType,
        colDescription, colBalance;

    @FXML
    private TableView <Account> accountsTableView;

    private Stage stage;

    private static final String WINDOW_NAME = 
        "Customer's accounts and balances";
    
    private CustomerManager customerManager;
    /**
     * sets the value of the object that manages and
     * gets all the information from the server.
     * @param customerManager object that implementates the interface
     */
    public void setCustomerManager(CustomerManager customerManager) {
        this.customerManager = customerManager;
    }
    /**
     * Sets the Stage object related to this controller. 
     * @param stage The Stage object to be initialized.
     */
    public void setStage(Stage stage){
        this.stage=stage;
    }
    /**
     * method that initiates the stage and sets/prepares the values
     * inside of it.
     * @param root
     */
    public void initStage(Parent root) {
       LOGGER.info("Initialazing " + WINDOW_NAME + " window."); 
       Scene scene = new Scene(root);
       stage.setScene(scene);
        // Set properties
        // se establece el nombre de la ventana
        stage.setTitle(WINDOW_NAME);
        // se establece la ventana como no redimensionable
        stage.setResizable(false);
        // se establecen las celdas de las tablas
        colAccountNumber.setCellValueFactory(
            new PropertyValueFactory<>("id"));
        colType.setCellValueFactory(
            new PropertyValueFactory<>("type"));
        colDescription.setCellValueFactory(
            new PropertyValueFactory<>("description"));
        colBalance.setCellValueFactory(
            new PropertyValueFactory<>("balance"));

        // alineamos los elementos
        colAccountNumber.cellValueFactoryProperty().setValue(Pos.CENTER_RIGHT);
        colType.cellValueFactoryProperty().setValue(Pos.CENTER_RIGHT);    
        txtFieldTotalBalance.alignmentProperty().setValue(Pos.CENTER_RIGHT);
        // deshabilitar el campo total balance
        txtFieldTotalBalance.setDisable(true);
        // se habilita el foco en el campo customer ID
        txtFieldCustomerID.requestFocus();
        stage.setOnShowing(this::handlerWindowShowing);
        // add property change listeners
        txtFieldCustomerID.textProperty().addListener(this::textPropertyChange);
        // set elements status
        

        // por si acaso se pide hacer algo con teclas
        /* stage.addEventHandler(KeyEvent.KEY_RELEASED, (KeyEvent event) -> { // Adds an event handler that records every time the escape key is pressed
            if (KeyCode.ESCAPE == event.getCode()) 
                closeRequest();
        }); */

        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                closeRequest();
                // by consuming the event we prevent it from closing the window
                we.consume();
            }
        });  
        stage.show();
        LOGGER.info("Window opened.");
    }
    /**
     * 
     * @param event
     */
    private void handlerWindowShowing(WindowEvent event) {
        LOGGER.info("Initializing UserWindowController::handlerWindowShowing");
    }
    /**
     * Text change event handler for login and name text fields.
     * It enables or disables create and modify buttons depending on those fields state.
     * @param observable the property being observed: TextProperty of TextField.
     * @param oldValue   old String value for the property.
     * @param newValue   new String value for the property.
     */
    private void textPropertyChange(
    ObservableValue observable,
    Object oldValue,
    Object newValue) {
        accountsTableView.getItems().clear();
        txtFieldTotalBalance.setText("");
    }

    public void handleSearchButtonAction() {
        String customerID = txtFieldCustomerID.getText();
        if (customerID.length() > this.MAX_LENGTH) {
            showErrorAlert("La longitud máxima es de " + this.MAX_LENGTH + " caracteres.");
            btnSearch.setDisable(true);
        }

        else if (customerID.trim().isEmpty()) 
            btnSearch.setDisable(true);
        
        else { // sabemos que es texto valido
            try {
                long ID = Long.valueOf(customerID);
                if (ID < 0) {
                    btnSearch.setDisable(true);
                    throw new NumberFormatException();
                }
                else {
                    // si es valido obtener los datos.
                    Customer clientCustomer = customerManager.getCustomerAccountsFullInfo(ID);
                    if (clientCustomer.getAccounts().isEmpty()) 
                        throw new NoAccountsException("Este cliente no tiene cuentas.");
                    
                    /*Si dicha lista no es nula, cargar la tabla 
                    con los datos de las cuentas del usuario y 
                    mostrar en el campo Total Balance la suma de 
                    los saldos de todas las cuentas del cliente.*/
                    List <Account> accounts = clientCustomer.getAccounts();
                    accountsTableView.setItems(
                        FXCollections.observableArrayList(accounts));
                    accountsTableView.refresh();
                
                    /*Mostrar los datos del cliente en los campos 
                    correspondientes de la ventana.*/
                    
                    /*Si se produce alguna excepción atraparla y
                    mostrar un mensaje de alerta con el mensaje de 
                    la excepción y enfocar el campo CustomerID.*/
                }
            } catch (NumberFormatException | NoAccountsException | ReadException e) {
                LOGGER.severe(CustomerDataController.class.getName() + e);   
                if (NumberFormatException.class.isInstance(e))
                    showErrorAlert("Debe tratarse de un valor numerico positivo.");
                else if (NoAccountsException.class.isInstance(e))
                    showErrorAlert(e.getMessage());
                else if (ReadException.class.isInstance(e))
                    showErrorAlert("Error connecting to the server.");
            } catch (Exception e) {
                showErrorAlert("There was an error.");
            }
        }
    }
    /**
     * button that does the very same thing as the 
     * close button on top of any window
     */
    public void handleExitButtonAction() {
        closeRequest();
    }
    /**
     * action that will be executed when the 
     * user tries to close the application.
     */
    public void closeRequest() {
        Optional<ButtonType> action = 
            new Alert(Alert.AlertType.CONFIRMATION, 
            "Are you sure you want to exit the application?").showAndWait();
        if (action.get() == ButtonType.OK) 
            stage.close();
    }
    
}
