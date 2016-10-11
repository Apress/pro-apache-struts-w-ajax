<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<font size="7"><bean:message key="javaedge.header.title"/></font></p>
<img src="/WEB-INF/jsp"/>

<div align="center">
  <center>
  <table border="0" cellpadding="0" cellspacing="0" style="border-collapse: collapse" bordercolor="#111111" width="100%" id="AutoNumber1" bgcolor="#FF66FF">
    <tr>
      <logic:notPresent scope="session" name="memberVO" >
        <td width="16%" bgcolor="#99CCFF" align="center">
           <bean:message key="javaedge.header.login"/>
        </td>
      </logic:notPresent>
<logic:present scope="session" name="memberVO">
         <td width="16%" bgcolor="#99CCFF" align="center">
            <bean:message key="javaedge.header.logout"/>
         </td>
      </logic:present>
      <logic:present scope="session" name="memberVO">
         <td width="17%" bgcolor="#99CCFF" align="center">
            <bean:message key="javaedge.header.myaccount"/>
         </td>
      </logic:present>

      <td width="17%" bgcolor="#99CCFF" align="center">
          <bean:message key="javaedge.header.viewallstories"/>
       </td>

      <logic:notPresent scope="session" name="memberVO"/>
        <td width="17%" bgcolor="#99CCFF" align="center">
          <bean:message key="javaedge.header.signup"/>
        </td>
      </logic:notPresent>
    </tr>
      
  </table>
  </center>
</div>
