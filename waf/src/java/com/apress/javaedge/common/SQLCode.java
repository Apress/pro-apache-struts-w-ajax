package com.apress.javaedge.common;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * The SQLCode is used to manage the SQL code being used by the application.
 * This class acts as a Singleton and prevents the gratitous creation of String
 * and StringBuffer objects.
 *
 * @author  Administrator
 * @todo     Need to remove this code.  It is dead code and not used anywhere in the book.
 */
public class SQLCode {
    private static SQLCode sqlCode = null;                 //Instance of the SQLCode object
    private static Properties sqlCache = new Properties(); //Holds all of the SQL statements
    
    static{
      sqlCode = new SQLCode();   
    }
    
    /**Returns a reference to the sqlCode attribute shown above*/
    public static SQLCode getInstance(){
      return sqlCode;   
    }
    
    /**
     *   Private constructor that loads the SQL code from the sqlcode.properties
     *   class.
     */
    private SQLCode(){
       try{
           String sqlFileName = System.getProperty("datatier.properties", "");
           sqlCache.load(new FileInputStream(sqlFileName));
       }
       catch(IOException e){
           System.out.println(e.toString());
       }
    }
    
    /**
     *    Retrieves the requested SQL Code from the cache maintained by the SQLCode
     *    object.
     */
    public String getSQLStatement(String _sqlKeyName) throws DataAccessException{
       if (sqlCache.containsKey(_sqlKeyName)){
         return (String) sqlCache.get(_sqlKeyName);   
       }
       else{
         throw new DataAccessException("Unable to locate the SQL statement requested " +
                                       "in SQLCode.getSQLCode()" + _sqlKeyName);
       }
    }
}
