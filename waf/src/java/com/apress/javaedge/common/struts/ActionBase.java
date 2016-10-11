/*
 * Created on 24-Nov-2003
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package com.apress.javaedge.common.struts;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.apress.javaedge.common.ApplicationException;
import com.apress.javaedge.member.MemberVO;

/**
 * @author robh
 *
 * To change the template for this generated type comment go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
public class ActionBase extends Action {

	private static final String MEMBER_VO_KEY = "memberVO";

	private HttpServletRequest _request = null;
	private HttpServletResponse _response = null;

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response)
		throws Exception {

		_request = request;
		_response = response;

		return super.execute(mapping, form, request, response);
	}

	protected void setMember(MemberVO member) {
		_request.getSession().setAttribute(MEMBER_VO_KEY, member);
	}

	protected MemberVO getMember() {
		HttpSession session = _request.getSession();

		if (session.getAttribute(MEMBER_VO_KEY) == null) {
			throw new ApplicationException("Not Logged In");
		} else if(!(session.getAttribute(MEMBER_VO_KEY) instanceof MemberVO)) {
			throw new ApplicationException("Session Corrupted");
		} else {
			return (MemberVO) session.getAttribute(MEMBER_VO_KEY);
		}
	}
}
