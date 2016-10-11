/*
 * @(#)TestMemberCRUD.java
 *
 */
package com.apress.javaedge.member;
import com.apress.javaedge.member.dao.*;
import com.apress.javaedge.common.*;
import junit.framework.*;
/**
 * @version $Revision: 1.1.1.1 $
 * @author Maciej Zawadzki
 */
public class TestMemberCRUD extends TestCase {
    
    //**************************************************************************
    // CLASS
    //**************************************************************************
    public TestMemberCRUD(String name){
       super(name);
    }
    // Create Log4j category instance for logging
    static private org.apache.log4j.Category log = org.apache.log4j.Category.getInstance(TestMemberCRUD.class.getName());
    
    //--------------------------------------------------------------------------
    static public void main(String[] args) {
        junit.textui.TestRunner.run(TestMemberCRUD.class);
    }

    //**************************************************************************
    // INSTANCE
    //**************************************************************************
    private MemberDAO dao = null;
    
    //--------------------------------------------------------------------------
    public void setUp()
    throws DataAccessException {
        dao = new MemberDAO();
    }

    //--------------------------------------------------------------------------
    public void tearDown() {
        dao = null;
    }
    
    //--------------------------------------------------------------------------
    public void testCRUD() 
    throws DataAccessException {
        MemberVO member = new MemberVO();
        member.setEmail("user@somewhere.com");
        member.setFirstName("Adam");
        member.setLastName("Adamski");
        member.setUserId("AAdamski");
        member.setPassword("secret");
        
        // test the insert
        dao.insert(member);
        Long pk = member.getMemberId();
        assertNotNull("MemberCRUDTestCase insert test failed", pk);
        log.debug("Inserted member: "+member);
        
        // test the restore
        MemberVO restoredMember = (MemberVO) dao.findByPK(pk.toString());
        assertNotNull("restore returned null", restoredMember);
        assertEquals("restored different first name", member.getFirstName(), restoredMember.getFirstName());
        assertEquals("restored different last name", member.getLastName(), restoredMember.getLastName());
        assertEquals("restored different userId", member.getUserId(), restoredMember.getUserId());
        assertEquals("restored different password", member.getPassword(), restoredMember.getPassword());
        log.debug("Restored member: "+restoredMember);

        // test update
        member.setFirstName("Bill");
        member.setLastName("Smith");
        member.setUserId("bill");
        member.setPassword("snugglebunny");
        dao.update(member);

        MemberVO retrievedMemberVO = (MemberVO) dao.findByPK(pk.toString());
        assertEquals("TestMemberCrud.testUpdate() test first name failed.",member.getFirstName(), retrievedMemberVO.getFirstName());
        assertEquals("TestMemberCrud.testUpdate() test last name failed.", member.getLastName(), retrievedMemberVO.getLastName());
        assertEquals("TestMemberCrud.testUpdate() test user id failed.", member.getUserId(), retrievedMemberVO.getUserId());
        assertEquals("TestMemberCrud.testUpdate() test password failed.", member.getPassword(), retrievedMemberVO.getPassword());  

        // test delete
        MemberVO memberVO = (MemberVO) dao.findByPK(pk.toString());
    	dao.delete(memberVO);
    	memberVO = (MemberVO) dao.findByPK(pk.toString());
    	assertNull("TestMemberCrud.testDelete() failed.", memberVO);
    }
    
}
