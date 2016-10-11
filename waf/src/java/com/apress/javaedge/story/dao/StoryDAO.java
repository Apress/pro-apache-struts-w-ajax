/*
 * StoryDAO.java
 *
 * Created on August 24, 2002, 7:01 PM
 */

package com.apress.javaedge.story.dao;

import com.apress.javaedge.common.*;
import com.apress.javaedge.story.StoryVO;

import org.apache.commons.logging.Log;
import org.apache.ojb.broker.PersistenceBroker;
import org.apache.ojb.broker.PersistenceBrokerException;
import org.apache.ojb.broker.metadata.DescriptorRepository;
import org.apache.ojb.broker.metadata.ClassDescriptor;
import org.apache.ojb.broker.query.Criteria;
import org.apache.ojb.broker.query.Query;
import org.apache.ojb.broker.query.QueryByCriteria;
import org.apache.ojb.broker.query.QueryFactory;

import java.util.Collection;
import java.util.Iterator;


/**
 *
 * StoryDAO is responsible for all CRUD logic associated with stories.
 *
 */
public class StoryDAO implements DataAccessObject {
    public static final int MAXIMUM_TOPSTORIES = 11;

	/**
	 * Commons Log for this class
	 */
	private static Log log = ServiceLocator.getInstance().getLog(StoryDAO.class);

    /**
     * Finds a single Story record by a story id passed into the method.
     * @see com.apress.javaedge.common.DataAccessObject#findByPK(java.lang.String)
     */
    public ValueObject findByPK(String primaryKey) throws DataAccessException {
        log.debug("************Entering the StoryDAO.findByPK***************");
        PersistenceBroker broker = null;
        StoryVO storyVO = null;

        try {
            broker = ServiceLocator.getInstance().findBroker();
            storyVO = new StoryVO();
            storyVO.setStoryId(new Long(primaryKey));

            Query query = new QueryByCriteria(storyVO);
            storyVO = (StoryVO) broker.getObjectByQuery(query);
        } catch (ServiceLocatorException e) {
            log.error("PersistenceBrokerException thrown in StoryDAO.findByPK()", e);
            throw new DataAccessException("Error in StoryDAO.findByPK(): " + e.toString(), e);
        } finally {
            if (broker != null)
                broker.close();
        }

        log.debug("************Done with the StoryDAO.findByPK()***************");
        return storyVO;
    }


    /**
     * Returns a collection of the top latest stories.  The number of records to
     * be returned are controlled by the MAXIMUM_TOPSTORIES constant on this
     * class.
     * @return Collection
     * @throws DataAccessException
     */
    public Collection findTopStory() throws DataAccessException {
        log.debug("************Entering with the StoryDAO.findTopStory()***************");
        PersistenceBroker broker = null;
        Collection results = null;

        Criteria criteria = new Criteria();
        criteria.addOrderByDescending("storyId");

        Query query = QueryFactory.newQuery(StoryVO.class, criteria);

        query.setStartAtIndex(0);
        query.setEndAtIndex(MAXIMUM_TOPSTORIES);

        try {
            broker = ServiceLocator.getInstance().findBroker();


            DescriptorRepository descriptorRepository = broker.getDescriptorRepository();

            Iterator iterator = descriptorRepository.iterator();


            log.debug("--------------------------------------");
            while (iterator.hasNext()){
               ClassDescriptor classDescriptor = (ClassDescriptor) iterator.next();
			   log.debug("Class Name:  " + classDescriptor.getClassNameOfObject());
			   log.debug("Table Name:  " + classDescriptor.getFullTableName());

            }
			log.debug(descriptorRepository);
			log.debug("--------------------------------------");

            results = (Collection) broker.getCollectionByQuery(query);
            
        } catch (ServiceLocatorException e) {
            log.error("PersistenceBrokerException thrown in StoryDAO.findTopStory()", e);
            throw new DataAccessException("Error in StoryDAO.findTopStory(): " + e.toString(), e);
        } finally {
            if (broker != null) broker.close();
        }

        log.debug("************Done with the StoryDAO.findTopStory()***************");
        return results;
    }

    /**
     * Inserts a single story record into the database.
     *
     * @see com.apress.javaedge.common.DataAccessObject#insert(com.apress.javaedge.common.ValueObject)
     */
    public void insert(ValueObject insertRecord) throws DataAccessException {
        log.debug("************Entering the StoryDAO.insert()***************");
        PersistenceBroker broker = null;
        try {
            StoryVO storyVO = (StoryVO) insertRecord;

            broker = ServiceLocator.getInstance().findBroker();
            broker.beginTransaction();
            broker.store(storyVO);
            broker.commitTransaction();

        } catch (PersistenceBrokerException e) {
            // if something went wrong: rollback
            broker.abortTransaction();
            log.error("PersistenceBrokerException thrown in StoryDAO.insert()", e);

            throw new DataAccessException("Error in StoryDAO.insert(): " + e.toString(), e);
        } catch (ServiceLocatorException e) {
            log.error("ServiceLocatorException thrown in StoryDAO.insert()", e);
            throw new DataAccessException("ServiceLocatorException thrown in StoryDAO.insert()", e);
        } finally {
            if (broker != null)
                broker.close();
        }

        log.debug("************Done with the StoryDAO.insert()***************");
    }

    /**
     *   Deletes a single record from the story table using OJB.
     */
    public void delete(ValueObject deleteRecord) throws DataAccessException {
        log.debug("************Entering the StoryDAO.delete()***************");
        PersistenceBroker broker = null;

        try {
            broker = ServiceLocator.getInstance().findBroker();
            StoryVO storyVO = (StoryVO) deleteRecord;

            //Begin the transaction.
            broker.beginTransaction();
            broker.delete(storyVO);
            broker.commitTransaction();
        } catch (PersistenceBrokerException e) {
            // if something went wrong: rollback
            broker.abortTransaction();
            log.error("PersistenceBrokerException thrown in StoryDAO.delete()", e);

            throw new DataAccessException("Error in StoryDAO.delete()", e);
        } catch (ServiceLocatorException e) {
            throw new DataAccessException("ServiceLocator exception in StoryDAO.delete()", e);
        } finally {
            if (broker != null) broker.close();
        }

        log.debug("************Done with the StoryDAO.delete()***************");
    }


    /**
     *   Updates a single record from the story table using OJB.
     */
    public void update(ValueObject updateRecord) throws DataAccessException {
        log.debug("************Entering the StoryDAO.update()***************");
        PersistenceBroker broker = null;

        try {
            StoryVO storyVO = (StoryVO) updateRecord;

            broker = ServiceLocator.getInstance().findBroker();
            broker.beginTransaction();
            broker.store(storyVO);
            broker.commitTransaction();
        } catch (PersistenceBrokerException e) {
            // if something went wrong: rollback
            broker.abortTransaction();
            log.error("PersistenceBrokerException thrown in StoryDAO.update()", e);
    
            throw new DataAccessException("Error in StoryDAO.update()", e);
        } catch (ServiceLocatorException e) {
            log.error("ServiceLocatorException thrown in StoryDAO.delete()", e);
            throw new DataAccessException("ServiceLocatorException error in StoryDAO.delete()", e);
        } finally {
            if (broker != null) broker.close();
        }

        log.debug("************Done with the StoryDAO.update()***************");
    }

    /**
     * Retrieves all stories in the database as a Collection.
     * Used by Search functionality.
     *
     * @return all stories in the app;
     */

    public Collection findAllStories() throws DataAccessException {
        log.debug("************Entering the StoryDAO.findAllStories()***************");
        PersistenceBroker broker = null;
        Collection results = null;

        try {
            Criteria criteria = new Criteria();
            criteria.addOrderByDescending("storyId");

			log.debug("Critiera:" + criteria.toString());

            Query query = QueryFactory.newQuery(StoryVO.class, criteria);

            query.setStartAtIndex(1);

            //JCC - Jeff why did you put this here.  I was experimenting with the cache originally.  I am going to comment it out.
            //broker.clearCache();
            broker = ServiceLocator.getInstance().findBroker();
            results = (Collection) broker.getCollectionByQuery(query);
        } catch (ServiceLocatorException e) {
            log.error("ServiceLocatorException thrown in StoryDAO.findAllStories()", e);
            throw new DataAccessException("ServiceLocatorException error in StoryDAO.findAllStories()", e);
        } finally {
            if (broker != null) broker.close();
        }

        log.debug("************Done with StoryDAO.findAllStories()***************");

        return results;

    }

}
