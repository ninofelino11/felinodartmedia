package com.cware.back.common;

/**
* User Exception
*
* @version 1.0, 2006/04/01
* @author kim sungtaek [webzest@commerceware.co.kr]
*/
public class StoreException extends Exception {
    private Throwable cause = null;

    public StoreException() {
        super();
    }
 
    public StoreException(String message) {
        super(message);
    }

    public StoreException(String message, Throwable cause) {
        super(message, cause);
    }

    public StoreException(Throwable cause) {
        super(cause);
    }    

    
}
