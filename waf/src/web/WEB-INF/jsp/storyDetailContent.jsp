<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<TABLE>
  <TR>
     <TD>
         <a href="/JavaEdge/execute/homePageSetup">Home</a> | <html:link action="/printFriendly" paramId="storyId" paramName="storyVO" paramProperty="storyId">Printer Friendly</html:link>
     </TD>
  </TR>
  <TR bgcolor="#99CCFF">
     <TD>
       <bean:write name="storyVO" scope="request" property="storyTitle"/><BR/>
       <FONT size="1">
         Posted By: <bean:write name="storyVO" scope="request" property="storyAuthor.firstName"/>
         <bean:write name="storyVO" scope="request" property="storyAuthor.lastName"/>
         on <bean:write name="storyVO" scope="request" property="submissionDate"/>
       </FONT>
     </TD>	
  </TR>
  <TR>
     <TD>
       <bean:write name="storyVO" scope="request" property="storyBody"/>
     </TD>
   </TR>
   <TR align="right">
     <TD>
        <a href='/JavaEdge/execute/postCommentSetup?storyVO.storyId=<bean:write name="storyVO" property="storyId"/>'>Add a comment</A>
     </TD>
   </TR>
   <TR>
     <TD>
        <BR/>
     </TD>
   </TR>

   <logic:iterate id="comment" name="comments" scope="request" type="com.apress.javaedge.story.StoryCommentVO">
      <TR bgcolor="#FFCCFF">
         <TD>
            <B>Posted by:</B> <bean:write name="comment" property="commentAuthor.firstName"/>
                              <bean:write name="comment" property="commentAuthor.lastName"/>
            on <bean:write name="comment" property="submissionDate"/><BR/><HR/>
            <bean:write name="comment" property="commentBody"/><br/>
         </TD>
       </TR>
       <TR>
          <TD><BR/></TD>
       </TR>
   </logic:iterate>
</TABLE>
