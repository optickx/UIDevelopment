package server;

import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import clientside.controller.CustomerManager;
import clientside.controller.CustomerManagerFactory;
import clientside.exception.CreateException;
import clientside.exception.ReadException;
import clientside.model.Customer;
import clientside.model.Movement;

/**
 * @author dani
 */
public abstract class LoadCustomerManager {

    private final static String SERVER = 
    ResourceBundle.getBundle("resources.server")
        .getString("URL");

    public static CustomerManager getCustomerManager() {
        /* CustomerManager cm =  
            CustomerManagerFactory.getCustomerManager();
        cm.setServerName(SERVER); */
        return null;
    }

    private class AntiServerCustomerManager implements CustomerManager {

        public List <Customer> l;

        public AntiServerCustomerManager() {
            /* l = Arrays.asList(
                new Customer().
            ); */
        }

        @Override
        public void createMovementForCustomerAccount(Movement arg0, Long arg1) throws CreateException {
            // TODO Auto-generated method stub
            
        }

        @Override
        public Customer getCustomerAccountsFullInfo(Long arg0) throws ReadException {
            // TODO Auto-generated method stub

            return null;
        }

        @Override
        public void setMediaType(String arg0) {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void setServerName(String arg0) {
            // TODO Auto-generated method stub
            
        }

    }
}
