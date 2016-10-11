<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-tiles" prefix="tiles" %>
<%@ include file='javaEdgeDef.jsp'%>

<tiles:insert beanName='baseDef' flush='true'>
  <tiles:put name='title' value='Todays Top Stories'/>
  <tiles:put name='content' value='/WEB-INF/jsp/tiles/homePageContent.jsp'/>
</tiles:insert>
