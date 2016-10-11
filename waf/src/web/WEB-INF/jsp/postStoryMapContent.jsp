<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template"%>

<BR/><BR/>
<H1><bean:message key="javaedge.poststory.text.header"/></H1>


<html:errors/>
<html:form action="postStory">

<TABLE>
        <TR>
             <TD>
                <bean:message key="javaedge.poststory.text.intro"/>

                <logic:present scope="session" name="memberVO">
                  <B><bean:write name="memberVO" scope="session" property="firstName"/>&nbsp;<bean:write name="memberVO" scope="session" property="lastName"/></B><BR/>
                </logic:present>
                <logic:notPresent scope="session" name="memberVO">
                  <B>Anonymous</B>
                </logic:notPresent>

             </TD>
             <TD>
                <BR/><BR/>&nbsp;
             </TD>
         </TR>
         <TR>
            <TD>
               <font size="2"><b><bean:message key="javaedge.poststory.form.titlelabel"/>:&nbsp;</b></font><br/>
               <html:text property="attribute(storyTitle)"/>
            </TD>
         </TR>
         <TR>
           <TD>
             <font size="2"><b><bean:message key="javaedge.poststory.form.introlabel"/>:&nbsp;</b></font><br/>
             <html:textarea property="attribute(storyIntro)" cols="80" rows="5"/>
           </TD>
         </TR>
         <TR>
           <TD>
             <font size="2"><b><bean:message key="javaedge.poststory.form.bodylabel"/>:&nbsp;</b></font><br/>
             <html:textarea  property="attribute(storyBody)" cols="80" rows="10"/>
           </TD>
         </TR>
                 <TR>
            <TD align="center">
               <html:submit property="submitButton" value="Submit"/>&nbsp;&nbsp;
               <html:cancel value="Cancel"/>
            </TD>
         </TR>
</TABLE>
</html:form>

