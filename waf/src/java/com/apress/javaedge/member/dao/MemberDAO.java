/*
 * MemberDAO.java
 *
 * Created on August 24, 2004, 5:25 PM
 */

package com.apress.javaedge.member.dao;

import java.util.Collection;

import org.apache.commons.logging.Log;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;

import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.common.DataAccessObject;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.common.ServiceLocatorException;
import com.apress.javaedge.common.ValueObject;
import com.apress.javaedge.member.MemberVO;

/**
 *  This   class is responsible for handling all CRUD logic associated with the
 *  member   table in the MemberDAO database.
 *
 *
 */
public class MemberDAO implements DataAccessObject { 
    
    /**
     * Commons Log for this class
     */
	private static Log log = ServiceLocator.getInstance().getLog(MemberDAO.class);
		
	/**
     * Deletes a single member record from the JavaEdge database.
     * @see com.apress.javaedge.common.DataAccessObject#delete(com.apress.javaedge.common.ValueObject)
     */    
    public void delete(ValueObject deleteRecord) throws DataAccessException {
    	log.debug("***********************Entering MemberDAO.delete()***********************");
    	PersistenceBroker broker = null;

    			try{
    				broker = ServiceLocator.getInstance().findBroker();
    				MemberVO memberVO = (MemberVO) deleteRecord;

    				//Begin the transaction.
    				broker.beginTransaction();
    				  broker.delete(memberVO);
    				broker.commitTransaction();
    			}
    			catch (PersistenceBrokerException e){
    				// if something went wrong: rollback
    				broker.abortTransaction();
    				log.error("PersistenceBrokerException thrown in MemberDAO.delete()", e);
    		
    				throw new DataAccessException("Error in MemberVO.delete()",e);
    			}
    			catch(ServiceLocatorException e){
    			  log.error("ServiceLocatorException thrown in MemberDAO.delete()", e);	
    			  throw new DataAccessException("ServiceLocator exception in MemberVO.delete()",e);
    			}
    			finally{
    			  if (broker!=null) broker.close();
    			}
    	log.debug("***********************Leaving MemberDAO.delete()***********************");
    }
    
    
	/**
     * Finds a single member record, as specified by the primary key value
     * passed in.  If no record is found a null value is returned.
     * 
	 * @see com.apress.javaedge.common.DataAccessObject#findByPK(java.lang.String)
	 */
	public ValueObject findByPK(String primaryKey) throws DataAccessException {
		log.debug("***********************Entering MemberDAO.findByPK()***********************");
    	PersistenceBroker broker = null;
    	MemberVO memberVO   = null;
    	
    	try{
    	  broker = ServiceLocator.getInstance().findBroker();
          memberVO = new MemberVO();
          memberVO.setMemberId(new Long(primaryKey));
        
          Query query = new QueryByCriteria(memberVO);
          memberVO = (MemberVO) broker.getObjectByQuery(query);
    	}
    	catch(ServiceLocatorException e){
    		log.error("ServiceLocatorException thrown in MemberDAO.findByPK()", e);
    	   throw new DataAccessException("ServiceLocatorException in MemberDAO.findByPK()",e);	
    	}
    	finally{
    	  if (broker!=null) broker.close();	
    	}
        
		log.debug("***********************Leaving MemberDAO.findByPK()***********************");
        return memberVO;
    }
    
    public ValueObject findByCriteria(MemberVO memberVO) throws DataAccessException{
    	log.debug("***********************Entering MemberDAO.findByCriteria()***********************");
    		PersistenceBroker broker = null;
    		MemberVO returnVO   = null;

    		try{
    		  broker = ServiceLocator.getInstance().findBroker();
    		  returnVO = new MemberVO();

    		  Query query = new QueryByCriteria(memberVO);
    		  returnVO = (MemberVO) broker.getObjectByQuery(query);
    		}
    		catch(ServiceLocatorException e){
    			log.error("ServiceLocatorException thrown in MemberDAO.findByCriteria()", e);
    		   throw new DataAccessException("ServiceLocatorException in MemberDAO.findByCriteria()",e);
    		}
    		finally{
    		  if (broker!=null) broker.close();
    		}

    		log.debug("***********************Leaving MemberDAO.findByCriteria()***********************");
    		return returnVO;
    }

    /**
     * Inserts a single member into the member table.  The value object passed
     * in has to be of type MemberVO.
     * 
	 * @see com.apress.javaedge.common.DataAccessObject#insert(com.apress.javaedge.common.ValueObject)
	 */
	public void insert(ValueObject insertRecord) throws DataAccessException {
	  log.debug("***********************Entering MemberDAO.insert()***********************");	
      MemberVO memberVO = null;
      PersistenceBroker broker = null;
      
       try{
       	    broker = ServiceLocator.getInstance().findBroker();
            memberVO = (MemberVO) insertRecord; 
                       
            broker.beginTransaction();
              broker.store(memberVO);
            broker.commitTransaction();
        }
        catch(ServiceLocatorException e){
        	log.error("ServiceLocatorException thrown in MemberDAO.insert()", e);
            throw new DataAccessException("ServiceLocator in Error in MemberDAO.insert()",e);	
        }
        catch (PersistenceBrokerException e){
            // if something went wrong: rollback
            broker.abortTransaction();
              
        	log.error("PersistenceBrokerException thrown in MemberDAO.insert()", e);
            throw new DataAccessException("Error in MemberDAO.insert()",e);
        }
        finally{
          if (broker!=null) broker.close();
        }
        
		log.debug("***********************Leaving MemberDAO.insert()***********************");	
    }

    /**
     * Updates a single member record in the JavaEdge database.
	 * @see com.apress.javaedge.common.DataAccessObject#update(com.apress.javaedge.common.ValueObject)
	 */
    public void update(ValueObject updateRecord) throws DataAccessException {
    	log.debug("***********************Entering MemberDAO.update()***********************");
    	MemberVO memberVO = null;
    	PersistenceBroker broker = null;

    	 try{
    		  broker = ServiceLocator.getInstance().findBroker();
    		  memberVO = (MemberVO) updateRecord;

    		  broker.beginTransaction();
    			broker.store(memberVO);
    		  broker.commitTransaction();
    	  }
    	  catch(ServiceLocatorException e){
    	  	  log.error("ServiceLocatorException thrown in MemberDAO.update()", e);
    		  throw new DataAccessException("ServiceLocator in Error in MemberDAO.update()",e);
    	  }
    	  catch (PersistenceBrokerException e){
    		  // if something went wrong: rollback
    		  broker.abortTransaction();
    	  	  log.error("PersistenceBrokerException thrown in MemberDAO.update()", e);
    		
    		  throw new DataAccessException("Error in MemberDAO.update()",e);
    	  }
    	  finally{
    		if (broker!=null) broker.close();
    	  }
    	  
    	log.debug("***********************Leaving MemberDAO.update()***********************");
    }
    
    public Collection findAll() throws DataAccessException {
		log.debug("***********************Starting MemberDAO.findAll()***********************");   	
    	PersistenceBroker broker = null;
    	Collection result = null;
    	
    	try {
    		
    		Query query = QueryFactory.newQuery(MemberVO.class, new Criteria());
    		
    		broker = ServiceLocator.getInstance().findBroker();
    		
    		result = broker.getCollectionByQuery(query);
    		
    	} catch(ServiceLocatorException e) {
			log.error("ServiceLocatorException thrown in MemberDAO.findAll()", e);
			throw new DataAccessException("DataAccessException error in MemberDAO.findAll()", e);
    	} finally {
    		if(broker != null) broker.close();
    	}
		log.debug("***********************Leaving MemberDAO.findAll()***********************");  	
    	return result;
    }
}
