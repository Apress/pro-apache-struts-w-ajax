/*
 * Created on 25-Nov-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.apress.javaedge.struts.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.struts.action.ActionMapping;

import com.apress.javaedge.common.DataAccessException;
import com.apress.javaedge.common.MemberFilter;
import com.apress.javaedge.common.ServiceLocator;
import com.apress.javaedge.member.MemberVO;
import com.apress.javaedge.member.dao.MemberDAO;
import com.apress.javaedge.struts.config.JavaEdgeActionMapping;

public class RequestProcessorHelper {

	//	Commons Log for this class
	private static Log log =
		ServiceLocator.getInstance().getLog(MemberFilter.class);

	public boolean checkMember(
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		HttpSession session = request.getSession();

		MemberVO memberVO = (MemberVO) session.getAttribute("memberVO");
		memberVO = null;

		/*If a session can not be found, give the user one.*/
		if (memberVO == null) {
			try {
				MemberDAO memberDAO = new MemberDAO();
				memberVO = (MemberVO) memberDAO.findByPK("1");

			} catch (DataAccessException e) {
				log.error(
					"DataAccessException thrown in RequestProcessorHelper.checkMember(): "
						+ e.toString(),
					e);

				RequestDispatcher rd =
					request.getRequestDispatcher(
						"/WEB-INF/jsp/systemError.jsp");
				rd.forward(request, response);

				return false;
			}

			session.setAttribute("memberVO", memberVO);
		}

		return true;
	}

	public boolean checkHost(
		HttpServletRequest request,
		HttpServletResponse response,
		ActionMapping mapping) throws IOException, ServletException {

		if (mapping instanceof JavaEdgeActionMapping) {

			JavaEdgeActionMapping jeMapping = (JavaEdgeActionMapping) mapping;

			if (jeMapping.isSecure()) {

				String hostAddress = request.getRemoteHost();

				if (!hostAddress.equals("localhost")) {

					RequestDispatcher rd =
						request.getRequestDispatcher(
							"/WEB-INF/jsp/accessDenied.jsp");
					rd.forward(request, response);

					// secure action and different host
					// deny access
					return false;
				} else {
					// host address matches allow access
					return true;
				}
			} else {
				// not a secure action allow access
				return true;
			}

		} else {
			// not a secure action allow access
			return true;
		}
	}
}
