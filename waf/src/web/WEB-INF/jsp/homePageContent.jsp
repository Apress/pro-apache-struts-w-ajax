<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>

<BR/><BR/>
<H1>Today's Top Stories</H1>
<TABLE>
      <logic:iterate id="story" name="topStories" scope="request" type="com.apress.javaedge.story.StoryVO">
        <TR bgcolor="#99CCFF">
             <TD>

               <bean:write name="story" scope="page" property="storyTitle"/><BR/>
               <FONT size="1">
                  Posted By: <bean:write name="story" property="storyAuthor.firstName"/>
                  <bean:write name="story" property="storyAuthor.lastName"/>
                  on <bean:write name="story" property="submissionDate"/>
               </FONT>
             </TD>
           </TR>
           <TR>
             <TD>
               <bean:write name="story" property="storyIntro"/>
             </TD>
           </TR>
           <TR>
             <TD align="right">
                <a href='/JavaEdge/execute/storyDetailSetup?storyId=<bean:write name="story" property="storyId"/>'>Full Story</a><BR/><BR/>
             </TD>
           </TR>
       </logic:iterate>
   </TABLE>
