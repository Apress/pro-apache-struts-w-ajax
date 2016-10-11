package com.apress.javaedge.common;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ojb.broker.PBFactoryException;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerFactory;

import javax.ejb.EJBHome;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.rmi.PortableRemoteObject;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

public class ServiceLocator{
  private static ServiceLocator serviceLocatorRef = null;
  private static Hashtable      ejbHomeCache      = null;
  private static Hashtable      dataSourceCache   = null;

  /*Enumerating the different services available from the Service Locator*/
  public static final int STORYMANAGER        = 0;
  public static final int JAVAEDGEDB          = 1;

  /*The JNDI Names used to lookup a service*/
  private static final String STORYMANAGER_JNDINAME = 
                              "storyManager/StoryManager";

  private static final String JAVAEDGEDB_JNDINAME="java:/MySQLDS";

  /*References to each of the different EJB Home Interfaces*/
  //private static final Class STORYMANAGERCLASSREF = StoryManagerHome.class
  private static final Class STORYMANAGERCLASSREF = null;
  
  static {
    serviceLocatorRef = new ServiceLocator();
  }

  /*Private Constructor for the ServiceLocator*/
  private ServiceLocator(){
    ejbHomeCache    = new Hashtable();
    dataSourceCache = new Hashtable();
  }

  /*
   * The ServiceLocator is implemented as a Singleton.  The getInstance()
   * method will return the static reference to the ServiceLocator stored
   * inside of the ServiceLocator Class.
   */
  public static ServiceLocator getInstance(){
    return serviceLocatorRef;
  }

  /*
   * The getServiceName will retrieve the JNDI name for a requested
   * service.  The service is indicated by the ServiceId passed into
   * the method.
   */
  static private String getServiceName(int pServiceId)
    throws ServiceLocatorException{
    String serviceName = null;
    switch (pServiceId){
      case STORYMANAGER:     serviceName = STORYMANAGER_JNDINAME;
                             break; 
      case JAVAEDGEDB:       serviceName = JAVAEDGEDB_JNDINAME;
                             break;
      default:               throw new ServiceLocatorException(
                             "Unable to locate the service requested in " +
                             "ServiceLocator.getServiceName() method.  ");
    }
    return serviceName;
  }

  /*
   * Returns the EJBHome Class reference for a requested service.
   * If the method cannot make a match, it will throw a    
   * ServiceLocatorException.
   */
  static private Class getEJBHomeRef(int pServiceId) 
    throws ServiceLocatorException{
    Class homeRef = null;
    switch (pServiceId){
      case STORYMANAGER:     homeRef = STORYMANAGERCLASSREF;
                             break; 
      default:               throw new ServiceLocatorException(
                             "Unable to locate the service requested in " +
                             "ServiceLocator.getEJBHomeRef() method.  ");
    }
    return homeRef;
  }

  /*
   * The getEJBHome method will return an EJBHome interface for a requested
   * service.  If it cannot find the requested EJB, it will throw a
   * servicelocator exception.
   *
   * The getEJBHome interface caches a requested EJBHome so that the first 
   * time an EJB is requested, a home interface will be retrieved but then
   * be placed into a cache.
   *
   */
  public EJBHome getEJBHome(int pServiceId)
    throws ServiceLocatorException{
   
      /*Trying to find the JNDI Name for the requested service*/
       String serviceName = getServiceName(pServiceId);
       EJBHome ejbHome    = null;

    try {
      /*Checking to see if I can find the EJBHome interface in cache*/
      if (ejbHomeCache.containsKey(serviceName)) {
        ejbHome = (EJBHome) ejbHomeCache.get(serviceName);
        return ejbHome;
      } else {
       /*
        * If I could not find the EJBHome interface in the cache, look it
        * up and then cache it.
        * */
      Context ctx = new InitialContext();
      Object jndiRef     = ctx.lookup(serviceName);

      Object portableObj = 
        PortableRemoteObject.narrow(jndiRef, getEJBHomeRef(pServiceId));

      ejbHome = (EJBHome) portableObj;

      ejbHomeCache.put(serviceName, ejbHome);
      return ejbHome;
    }
  } catch(NamingException e) {
      throw new ServiceLocatorException("Naming exception error in ServiceLocator.getEJBHome()" ,e);
    } catch(Exception e) {
      throw new ServiceLocatorException("General exception in ServiceLocator.getEJBHome",e);
    }
  
  }

  /*
   * The getDBConn() method will create a JDBC connection for the
   * requested database.  It too uses a cachin algorithm to minimize
   * the number of JNDI hits that it must perform.
   */
  public Connection getDBConn(int pServiceId)
    throws ServiceLocatorException{
     /*Getting the JNDI Service Name*/
    String     serviceName = getServiceName(pServiceId);
    Connection conn        = null;
    try {
          /*Checking to see if the requested DataSource is in the Cache*/ 
      if (dataSourceCache.containsKey(serviceName)) {
        DataSource ds = (DataSource) dataSourceCache.get(serviceName);
         conn = ((DataSource)ds).getConnection();

    return conn;
      } else {
     /*
      * The DataSource was not in the cache.  Retrieve it from JNDI
      * and put it in the cache.
      */
      Context ctx = new InitialContext();
        DataSource newDataSource = (DataSource) ctx.lookup(serviceName);
        dataSourceCache.put(serviceName, newDataSource);
        conn = newDataSource.getConnection();
        return conn;
      }
    } catch(SQLException e) {
      throw new ServiceLocatorException("A SQL error has occurred in " +
                                        "ServiceLocator.getDBConn()", e);
    } catch(NamingException e) {
      throw new ServiceLocatorException("A JNDI Naming exception has "+
                                        "occurred in "+
                                        "ServiceLocator.getDBConn()" , e);
    } catch(Exception e) {
     throw new ServiceLocatorException("An exception has occurred "+
                                       "in ServiceLocator.getDBConn()"  ,e);
    }
  }

  /**
   *    Retrieves an OJB persistence broker.
   * 
   *    @todo Need to fix this method when I upgrade the application to OJB 1.1
   */
  public PersistenceBroker findBroker() throws ServiceLocatorException{
    PersistenceBroker broker = null;
    try{
      broker = PersistenceBrokerFactory.defaultPersistenceBroker();
    }
    catch(PBFactoryException e) {
    e.printStackTrace();
    throw new ServiceLocatorException("PBFactoryException error " +
           "occurred while parsing the repository.xml file in " +  
           "ServiceLocator constructor",e);
  }

 
    return broker;
  }
  
  /**
   * Interface to Commons Logging. Use this method to
   * retreive a Log instance for a particular class
   * @param aClass An instance of Class. Should be the class you wish to write log messages for.
   * @return A Commons Logging Log instance
   */
  public Log getLog(Class aClass) {
  	return LogFactory.getLog(aClass);
  }
}
