package com.apress.javaedge.story.ejb;

import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.struts.poststory.PostStoryForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author jcarnell
 *
 * Bad example of the PostStory class.  The example below shows a concern slush
 * anti=pattern where there is no clear separation of the framework, business logic and
 * data access code.
 */
public class PostStory extends Action {

    /* (non-Javadoc)
     * @see org.apache.struts.action.Action#execute(org.apache.struts.action.ActionMapping, org.apache.struts.action.ActionForm, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public ActionForward execute(
            ActionMapping mapping,
            ActionForm form,
            HttpServletRequest request,
            HttpServletResponse response) {

        PostStoryForm postStoryForm = (PostStoryForm) form;
        HttpSession session = request.getSession();

        MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

        if (this.isCancelled(request)) {
            return (mapping.findForward("poststory.success"));
        }

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/MySQLDS");
            conn = ds.getConnection();
            conn.setAutoCommit(false);

            StringBuffer insertSQL = new StringBuffer();

            /*
              Please note that this code is only an example. The SQL code assumes
              that the story table is using an auto-generated key.  However, in
              the JavaEdge application we use Object-Relational Bridges Sequence
              capabilities to generate a key.  This code will not work unless you
              modify the story table to use an auto-generated key for the story_id
              column*/
            insertSQL.append("INSERT INTO story(           ");
            insertSQL.append("  member_id             ,    ");
            insertSQL.append("  story_title           ,    ");
            insertSQL.append("  story_into            ,    ");
            insertSQL.append("  story_body            ,    ");
            insertSQL.append("  submission_date            ");
            insertSQL.append(")                            ");
            insertSQL.append("VALUES(                     ");
            insertSQL.append("  ?                    ,    ");
            insertSQL.append("  ?                    ,    ");
            insertSQL.append("  ?                    ,    ");
            insertSQL.append("  ?                    ,    ");
            insertSQL.append("  CURDATE()              )    ");

            ps = conn.prepareStatement(insertSQL.toString());

            ps.setLong(1, memberVO.getMemberId().longValue());
            ps.setString(2, postStoryForm.getStoryTitle());
            ps.setString(3, postStoryForm.getStoryIntro());
            ps.setString(4, postStoryForm.getStoryBody());

            ps.execute();
            conn.commit();

            checkStoryCount(memberVO);
        } catch (SQLException e) {
            try {
                if (conn != null)
                    conn.rollback();

            } catch (SQLException ex) {
            }

            System.err.println(
                    "A SQL exception has been raised in "
                    + "PostStory.perform(): "
                    + e.toString());

            return (mapping.findForward("system.failure"));
        } catch (NamingException e) {
            System.err.println(
                    "A Naming exception has been raised in "
                    + "PostStory.perform(): "
                    + e.toString());
            return (mapping.findForward("system.failure"));
        } finally {
            try {
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }

        }

        return (mapping.findForward("poststory.success"));
    }

    /**
     * Checks to see the number of stories the user has submitted.
     *
     * @param memberVO  Member id
     * @throws SQLException
     * @throws NamingException
     */
    private void checkStoryCount(MemberVO memberVO)
            throws SQLException, NamingException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Context ctx = new InitialContext();
            DataSource ds = (DataSource) ctx.lookup("java:/MySQLDS");
            conn = ds.getConnection();

            StringBuffer selectSQL = new StringBuffer();

            selectSQL.append("SELECT                     ");
            selectSQL.append("  count(*) total_count     ");
            selectSQL.append("FROM                       ");
            selectSQL.append("  story where member_id=?  ");

            ps = conn.prepareStatement(selectSQL.toString());
            ps.setLong(1, memberVO.getMemberId().longValue());

            rs = ps.executeQuery();
            int totalCount = 0;

            if (rs.next()) {
                totalCount = rs.getInt("total_count");
            }

            boolean TOTAL_COUNT_EQUAL_1000 = (totalCount == 1000);
            boolean TOTAL_COUNT_EQUAL_5000 = (totalCount == 5000);

            if (TOTAL_COUNT_EQUAL_1000 || TOTAL_COUNT_EQUAL_5000) {
                //Notify Prize Manager
                PrizeManager prizeManager = new PrizeManager();
                prizeManager.notifyMarketing(memberVO, totalCount);
            }
        } catch (SQLException e) {
            System.err.println(
                    "A SQL exception has been raised in "
                    + " PostStory.checkStoryCount(): "
                    + e.toString());

            throw e;
        } catch (NamingException e) {
            System.err.println(
                    "A Naming exception has been raised in "
                    + " PostStory.checkStoryCount(): "
                    + e.toString());
            throw e;
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (conn != null)
                    conn.close();
            } catch (SQLException e) {
            }
        }
    }
}
