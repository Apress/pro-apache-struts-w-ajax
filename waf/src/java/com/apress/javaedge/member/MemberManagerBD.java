package com.apress.javaedge.member;

import java.util.Collection;

import org.apache.commons.logging.Log;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.member.dao.MemberDAO;

/**
 * @author jcarnell
 *
 * Business delegate to hide how business logic for a member actually occurs.
 */
public class MemberManagerBD {
	// Create Log4j category instance for logging
    private static Log log = ServiceLocator.getInstance().getLog(MemberManagerBD.class);
	
	/**
	 * Looks up whether the user exists based on their user id or password.  
	 * This method is used during user validation.
	 * 
	 * @param userId     User id of the user.
	 * @param password   Password of the user
	 * @return			 
	 * @throws ApplicationException  Generic application exception.
	 *
	 */
	public MemberVO authenticate(String userId, String password) throws ApplicationException{
		log.debug("authenticate() started");
		MemberVO memberVO = new MemberVO();
		
		//Setting the query critiera
		memberVO.setUserId(userId);
		memberVO.setPassword(password);
		
		MemberDAO memberDAO = new MemberDAO();
		
		try{
		  memberVO = (MemberVO) memberDAO.findByCriteria(memberVO);
		  
		  if(memberVO == null) {
		  	log.info("Failed Login Attempt. Username:" + userId + " Password:" + password);
		  } else {
			log.info("Successful Login Attempt for user:" + userId);
		  }
		}
		catch(DataAccessException e){
		  log.error("Error in MemberManagerBD.authenticate()",  e);
		  throw new ApplicationException("Error in MemberManagerBD.validateUserId(): " + e.toString(),e);	
		}
		
		log.debug("authenticate() finished");
		return memberVO;
	}

    /**
     * Adds a new user to the JavaEdge database.
     * @param memberVO MemberVO containing the user's data.
     * @throws ApplicationException Generic Application Exception.
     */
    public void addUser(MemberVO memberVO) throws ApplicationException{
		log.debug("addUser() started");
        try{
          MemberDAO memberDAO = new MemberDAO();
          memberDAO.insert(memberVO);
        }
        catch(DataAccessException e){
		  log.error("Error in MemberManagerBD.addUser()",  e);
          throw new ApplicationException("An application exception has been raised in SignUp.perform()",e);
        }
		log.debug("addUser() finished");
    }
    
    public Collection getAll() throws ApplicationException{
    	MemberDAO dao = new MemberDAO();
    	
    	try {
    		return dao.findAll();
    	} catch(DataAccessException e) {
			log.error("Error in MemberManagerBD.getAll()",  e);
			throw new ApplicationException("An application exception has been raised in MemberManagerBD.getAll()",e);
    	}
    }

}
