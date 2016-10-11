

package com.apress.javaedge.common;

/**
 * Generic interface that is used by all DAO's to ensure that they implement the basic
 * CRUD interface.
 * 
 * @author jcarnell
 *
 *
 */
public interface DataAccessObject {
    /**
     * Used to retrieve a single record by its primary key value.
	 * @param primaryKey Primary key to retrieve the record by.
	 * @return
	 * @throws DataAccessException
	 */
	public  ValueObject findByPK(String primaryKey) throws DataAccessException;
    /**
     * Used to add a single record based on its primary key.
	 * @param insertRecord
	 * @throws DataAccessException
	 */
	public  void insert(ValueObject insertRecord) throws DataAccessException;
    /**
     * Used to update a single record.
	 * @param updateRecord
	 * @throws DataAccessException
	 */
	public  void update(ValueObject updateRecord) throws DataAccessException;
    /**
     * Used to delete a single record from the database.
	 * @param deleteRecord
	 * @throws DataAccessException
	 */
	public  void delete(ValueObject deleteRecord) throws DataAccessException;
}
