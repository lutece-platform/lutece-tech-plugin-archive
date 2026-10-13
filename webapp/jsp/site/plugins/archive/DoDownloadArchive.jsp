<%@ page import="fr.paris.lutece.plugins.archive.util.DoDownloadArchive" %><%--
--%>${ pageContext.setAttribute( 'strResult', DoDownloadArchive.doDownloadFile( pageContext.request, pageContext.response ) ) }<%--
--%>${ pageContext.response.committed ? '' : pageContext.response.sendRedirect( strResult ) }