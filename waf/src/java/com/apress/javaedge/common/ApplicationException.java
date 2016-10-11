package com.apress.javaedge.common;

import org.apache.commons.lang.exception.NestableRuntimeException;

/**
 *    This is a generic application exception that would be captured by the front-end.
 *    It is used to let the presentation tier know that some kind of exception was caught
 *    and raised.
 *
 *    @author  John Carnell
 */
public class ApplicationException extends NestableRuntimeException {
    Throwable exceptionCause = null;

    /** Creates a new instance of ApplicationException */
    public ApplicationException(String msg) {
        super(msg);
    }

    public ApplicationException(String msg, Throwable exception){
      super(msg, exception);
      exceptionCause = exception;
    }

    /**Overriding the printStackTraceMethod*/
    public void printStackTrace(){
      if (exceptionCause!=null){
        System.err.println("An exception has been caused by: " + exceptionCause.toString());
        exceptionCause.printStackTrace();
      }
    }
}

