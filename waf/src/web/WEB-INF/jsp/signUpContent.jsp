<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<BR/><BR/>
<H1><bean:message key="javaedge.signup.text.header"/></H1> 

<html:errors/>

<html:form action="signUp">
<TABLE> 
         <TR>
            <TD>
               <bean:message key="javaedge.signup.form.firstnamelabel"/>
               <html:text name="signUpForm" property="memberVO.firstName"/>
            </TD>
         </TR>
         <TR>
           <TD>
             <bean:message key="javaedge.signup.form.lastnamelabel"/>
             <html:text name="signUpForm" property="memberVO.lastName"/>
           </TD>
         </TR>
         <TR>
           <TD>
             <bean:message key="javaedge.signup.form.emaillabel"/>
             <html:text name="signUpForm" property="memberVO.email"/>
           </TD>
         </TR>
         <TR>
           <TD>
             <bean:message key="javaedge.signup.form.useridlabel"/>
             <html:text name="signUpForm" property="memberVO.userId"/>
           </TD>
         </TR>
         <TR>
           <TD>
             <bean:message key="javaedge.signup.form.passwordlabel"/>
             <html:password name="signUpForm" property="memberVO.password"/>
           </TD>
         </TR>
         <TR>
           <TD>
             <bean:message key="javaedge.signup.form.confirmpasswordlabel"/>
             <html:password name="signUpForm" property="confirmPassword"/>
           </TD>
         </TR>
         <TR>
            <TD align="center">
               <html:submit property="submitButton" value="Submit"/>&nbsp;&nbsp;
            </TD>
         </TR>
</TABLE>
</html:form>

