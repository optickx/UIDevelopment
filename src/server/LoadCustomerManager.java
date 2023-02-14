package server;

import java.util.ResourceBundle;

import clientside.controller.CustomerManager;
import clientside.controller.CustomerManagerFactory;

/**
 * @author dani
 */
public abstract class LoadCustomerManager {

    private final static String SERVER = 
    ResourceBundle.getBundle("resources.server")
        .getString("URL");

    public static CustomerManager getCustomerManager() {
        CustomerManager cm =  
            CustomerManagerFactory.getCustomerManager();
        cm.setServerName(SERVER);
        return cm;
    }
}
