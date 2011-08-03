<%@page import="fr.paris.lutece.plugins.archive.util.DoDownloadArchive"%>
<% 
	 String strResult =  DoDownloadArchive.doDownloadFile(request,response);
 	 if (!response.isCommitted())
	{
		  response.sendRedirect(strResult);
	}
%>
