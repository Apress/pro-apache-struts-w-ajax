<%@ taglib uri="/taglibs/struts-tiles" prefix="tiles" %>

<tiles:definition id='baseDef' page='/WEB-INF/jsp/tiles/template.jsp' scope='request'>
  <tiles:put name='title' value='Base JavaEdge Template'/>
  <tiles:put name='header' value='/WEB-INF/jsp/tiles/header.jsp'/>
  <tiles:put name='content' value='/WEB-INF/jsp/tiles/baseContent.jsp'/>
  <tiles:put name='footer' value='/WEB-INF/jsp/tiles/footer.jsp'/>
</tiles:definition>
