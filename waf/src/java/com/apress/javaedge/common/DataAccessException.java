package com.apress.javaedge.common;

/**
 * Generic data access exception.  This exception is used by the data access tier
 * when it catches an internal exception and then wants to mask the technology specific
 * details of the exception.
 * 
 * @author  John Carnell
 * @todo     Need to Javadoc this
 */
public class DataAccessException extends ApplicationException {
    Throwable exceptionCause = null;
    
    
    /** Creates a new instance of DataAccessException */
    public DataAccessException(String exceptionMsg) {
        super(exceptionMsg);
    }
    
    public DataAccessException(String exceptionMsg, Throwable exception){
       super(exceptionMsg);   
       exceptionCause = exception;
    }
    
    public void printStackTrace(){
        if (exceptionCause!=null){
           System.err.println("An exception has been caused by: ");
           exceptionCause.printStackTrace();
        }
        
    }
    
    
    
}
