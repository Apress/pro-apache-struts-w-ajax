<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<font size="7"><bean:message key="javaedge.header.title"/></font></p>

<div align="center">
  <center>
  <html:form action="login">
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" bgcolor="#FF66FF">
    <tr>
      <logic:notEqual scope="session" name="memberVO" property="memberId" value="1">
         <td width="16%" bgcolor="#99CCFF" align="center">
            <bean:message key="javaedge.header.logout"/>
         </td>
      </logic:notEqual>

      <td width="17%" bgcolor="#99CCFF" align="center">
         <bean:message key="javaedge.header.postastory"/>
      </td>

      <td width="17%" bgcolor="#99CCFF" align="center">
          <bean:message key="javaedge.header.viewallstories"/>
       </td>
       
      <td width="17%" bgcolor="#99CCFF" align="center">
          <bean:message key="javaedge.header.search"/>
       </td>       

      <logic:equal scope="session" name="memberVO" property="memberId" value="1">
        <td width="17%" bgcolor="#99CCFF" align="center">
          <bean:message key="javaedge.header.signup"/>
        </td>
      </logic:equal>
    </tr>
    <tr>
       <logic:equal scope="session" name="memberVO" property="memberId" value="1">
        <td width="16%" bgcolor="#99CCFF" align="left" colspan="4">
           <bean:message key="javaedge.header.userid"/><input type="text" name="userId"/>
           <bean:message key="javaedge.header.password"/><input type="password" name="password"/>
           <html:submit property="submitButton" value="Submit"/>
           <html:errors property="invalid.login"/>
        </td>
      </logic:equal>
    </tr>
      
  </table>
  </html:form>
  </center>
</div>
