<%@ taglib uri="/taglibs/struts-tiles" prefix="tiles" %>

<tiles:insert page='/WEB-INF/jsp/tiles/template.jsp' flush='true'>
  <tiles:put name='title' value='Hello World Test'/>
  <tiles:put name='header' value='/WEB-INF/jsp/tiles/header.jsp'/>
  <tiles:put name='content' value='/WEB-INF/jsp/tiles/helloBody.jsp'/>
  <tiles:put name='footer' value='/WEB-INF/jsp/tiles/footer.jsp'/>
</tiles:insert>
