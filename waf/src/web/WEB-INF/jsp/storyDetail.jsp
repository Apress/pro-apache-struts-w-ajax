<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<template:insert template='/WEB-INF/jsp/template.jsp'>
   <template:put name='title' content='View a specific story' direct='true'/>
   <template:put name='header' content='/WEB-INF/jsp/header.jsp'/>
   <template:put name='content' content='/WEB-INF/jsp/storyDetailContent.jsp'/>
   <template:put name='footer' content='/WEB-INF/jsp/footer.jsp'/>
</template:insert>
