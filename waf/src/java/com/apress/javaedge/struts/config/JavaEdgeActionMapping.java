package com.apress.javaedge.struts.config;

import org.apache.struts.action.ActionMapping;

public class JavaEdgeActionMapping extends ActionMapping {
	
	private boolean secure = false;
	
	public JavaEdgeActionMapping() {
		super();
	}
	
	public boolean isSecure() {
		return secure;
	}
	
	public void setSecure(boolean isSecure) {
		secure = isSecure;
	}
}
