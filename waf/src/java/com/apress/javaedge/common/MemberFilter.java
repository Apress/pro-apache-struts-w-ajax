package com.apress.javaedge.common;

import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.member.dao.MemberDAO;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;

import java.io.IOException;

/**
 * This MemberFilter will check to see if the user is currently logged in.  If
 * they are not logged in the user will automatically be set up with an the
 * anonymous log in.  This log in is defined as the first record in the
 * database.
 * 
 * Filter information
 * 
 * @author John Carnell
 * 
 * -----------------------XDoclet Tags -------------------------------
 * @web.filter name="MemberFilter" 
 *             description="Filter for determining who the JavaEdge user is."
 *             display-name="Member Login Filter"
 * @web.filter-mapping name="MemberFilter"
 *                     url-pattern="/execute/*"
 * -----------------------XDoclet Tags -------------------------------
 */
public class MemberFilter implements Filter{

  FilterConfig config;
  
  // Commons Log for this class
  private static Log log = ServiceLocator.getInstance().getLog(MemberFilter.class);

  
/**
 * Simple Filter init that can be used to initialize the filter the first time
 * it is loaded.
 * 
 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
 */
 public void init(FilterConfig config) throws ServletException{
     this.config = config;    	
  }
  
  
   /**
    * Checks to see if the user is logged in (i.e. they have a member object in
    * session.
 * 
 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
 */
public void doFilter(ServletRequest request,
                        ServletResponse response,
                        FilterChain chain)  throws IOException, ServletException{
     HttpServletRequest servletRequest = (HttpServletRequest) request;
     HttpSession session = servletRequest.getSession();
     
     MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");

     /*If a session can not be found, give the user one.*/
     if (memberVO == null){
        try{
          MemberDAO memberDAO = new MemberDAO();
          memberVO = (MemberVO) memberDAO.findByPK("1");
        }
        catch(DataAccessException e){
          log.error("DataAccessException thrown in PostStorySetupAction.perform(): " + e.toString(),e );
          
          RequestDispatcher rd = servletRequest.getRequestDispatcher("/WEB-INF/jsp/systemError.jsp");
          rd.forward(request, response);
        }

        session.setAttribute("memberVO", memberVO);
     } 
                        	
     chain.doFilter(request, response);
  }
  
  /**
   * Not implemented.
 * @see javax.servlet.Filter#destroy()
 */
public void destroy(){}
                        

}


