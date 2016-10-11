<%@ taglib uri='/taglibs/struts-tiles' prefix='tiles' %>

<html>
<head>
   <title><tiles:getAsString name='title'/></title>
</head>

  <body>
  <p>
  <tiles:insert attribute='header'/>
  <tiles:insert attribute='adone' ignore='true'/>
  <tiles:insert attribute='content'/>
  <tiles:insert attribute='footer'/>
</html>
