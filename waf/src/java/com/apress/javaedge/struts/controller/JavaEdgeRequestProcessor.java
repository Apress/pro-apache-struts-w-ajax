package com.apress.javaedge.struts.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class JavaEdgeRequestProcessor
	extends org.apache.struts.action.RequestProcessor {

	private RequestProcessorHelper helper = null;

	public JavaEdgeRequestProcessor() {
		super();
		helper = new RequestProcessorHelper();
	}

	public void process(
		HttpServletRequest request,
		HttpServletResponse response)
		throws IOException, ServletException {

		if (helper.checkMember(request, response)) {
			super.process(request, response);
		}
	}

	protected ActionForward processActionPerform(
		HttpServletRequest request,
		HttpServletResponse response,
		Action action,
		ActionForm form,
		ActionMapping mapping)
		throws IOException, ServletException {

		if (helper.checkHost(request, response, mapping)) {
			return super.processActionPerform(
				request,
				response,
				action,
				form,
				mapping);
		} else {
			return null;
		}
	}

	protected boolean processForward(
		HttpServletRequest request,
		HttpServletResponse response,
		ActionMapping mapping)
		throws IOException, ServletException {

		if (helper.checkHost(request, response, mapping)) {
			return super.processForward(request, response, mapping);
		} else {
			return false;
		}
	}

	protected boolean processInclude(
		HttpServletRequest request,
		HttpServletResponse response,
		ActionMapping mapping)
		throws IOException, ServletException {

		if (helper.checkHost(request, response, mapping)) {
			return super.processInclude(request, response, mapping);
		} else {
			return false;
		}
	}

}
