<%@ page language="java" %>
<%@ taglib uri="/taglibs/struts-bean" prefix="bean" %>
<%@ taglib uri="/taglibs/struts-html" prefix="html" %>
<%@ taglib uri="/taglibs/struts-logic" prefix="logic" %>
<%@ taglib uri="/taglibs/struts-template" prefix="template" %>
<%@ taglib uri="/WEB-INF/veltag.tld" prefix="vel" %>
 


<BR/><BR/>
<H2><bean:message key="javaedge.search.form.text.header"/></H2> 

<html:errors/>

<html:form action="Search">
<TABLE> 
        <TR>
             <TD>
                <bean:message key="javaedge.search.form.text.intro"/>
 
             </TD>
             <TD>
                <BR/><BR/>&nbsp;
             </TD>
         </TR>
         <TR>
            <TD>
               <bean:message key="javaedge.search.form.querylabel"/>
               <html:text name="searchForm" property="query"/>
            </TD>
         </TR>
                 <TR>
            <TD align="center">
               <html:submit property="submitButton" value="Submit"/>&nbsp;&nbsp;
               <html:cancel value="Cancel"/>
            </TD>
         </TR>
</TABLE>
<P>

<vel:velocity>
	#if ($searchResults)
		#set($length = $searchResults.length)
		#set($hits = $searchResults.hits)
		#if ($length == 0)
			No Search results found for terms $searchResults.terms
		#else
			<B>$length Search Results present</B>
			#foreach ($ctr in [0..3])
				
				$hits.doc($ctr)
				
			#end
		#end
	#end
</vel:velocity>


</html:form>

