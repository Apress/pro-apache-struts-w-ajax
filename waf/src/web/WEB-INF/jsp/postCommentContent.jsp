<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<BR/><BR/>
<H1><bean:message key="javaedge.postcomment.text.header"/></H1> 

<html:errors/>

<html:form action="postComment">
	<TABLE> 
	        <TR>
	             <TD>
	                <bean:message key="javaedge.postcomment.text.intro"/>
	                <B><bean:write name="postCommentForm" scope="request" property="storyVO.storyAuthor.firstName"/>&nbsp;<bean:write name="postCommentForm" scope="request" property="storyVO.storyAuthor.lastName"/></B><BR/>                
	             </TD>
	             <TD>
	                <BR/><BR/>&nbsp;
	             </TD>
	         </TR>
	         <TR>
	             <TD>
	                <bean:message key="javaedge.postcomment.form.titlelabel"/>
	                <bean:write name="postCommentForm" scope="request" property="storyVO.storyTitle"/><BR/>                
	             </TD>
	             <TD>
	                <BR/><BR/>&nbsp;
	             </TD>
	         </TR>
	         <TR>
	             <TD>
	                <bean:message key="javaedge.postcomment.form.storybodylabel"/>
	                <bean:write name="postCommentForm" scope="request" property="storyVO.storyBody"/><BR/>                
	             </TD>
	             <TD>
	                <BR/><BR/>&nbsp;
	             </TD>
	         </TR>
	         <TR>
	            <TD>
	               <bean:message key="javaedge.postcomment.form.commentbodylabel"/>
	               <html:textarea name="postCommentForm" property="storyCommentVO.commentBody" rows="10" cols="80"/>
	            </TD>
	         </TR>
	         <TR>
	            <TD align="center">
	               <html:submit property="submitButton" value="Submit"/>&nbsp;&nbsp;
	            </TD>
	         </TR>
	</TABLE>
  <html:hidden name="postCommentForm" property="storyVO.storyId"/>  
</html:form>

