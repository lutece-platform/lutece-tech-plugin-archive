/*
 * Copyright (c) 2002-2011, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.archive.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.paris.lutece.plugins.archive.business.ArchiveItem;
import fr.paris.lutece.plugins.archive.business.ArchiveItemHome;
import fr.paris.lutece.plugins.archive.service.archive.ArchivePlugin;
import fr.paris.lutece.portal.service.message.AdminMessage;
import fr.paris.lutece.portal.service.message.AdminMessageService;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.util.AppLogService;


/**
 *
 *  class DoDownloadGraph
 *
 */
public class DoDownloadArchive
{
    
	
    /**
     * Write in the http response the file to upload
     * @param request the http request
     * @param response The http response
     * @return Error Message
     *
     */
    public static String doDownloadFile( HttpServletRequest request, HttpServletResponse response )
    {
        Plugin plugin = PluginService.getPlugin( ArchivePlugin.PLUGIN_NAME );
        String strArchiveItemKey = request.getParameter( ArchiveConstants.PARAM_ARCHIVE_ITEM_KEY );
        int nArchiveItemKey  = -1;
        
        try
        {
        	nArchiveItemKey=Integer.parseInt(strArchiveItemKey);	
        }
        catch (NumberFormatException ne) {
        	AppLogService.error(ne);
        }
        
                
         	ArchiveItem archiveItem = ArchiveItemHome.findByPrimaryKey(
         			nArchiveItemKey, plugin);
    		if (archiveItem != null) {
        {
            
        	OutputStream os=null;
        	FileInputStream bis=null;
        	
            	try {
            		
            	    
                    response.setHeader( "Content-Disposition", "attachment ;filename=\"" + archiveItem.getArchiveName() + "\"" );
                    response.setHeader( "Pragma", "public" );
                    response.setHeader( "Expires", "0" );
                    response.setHeader( "Cache-Control", "must-revalidate,post-check=0,pre-check=0" );

            		response.setContentType( archiveItem.getArchiveMimeType(  ) );
                    
            		os = response.getOutputStream(  );
                    bis = new FileInputStream(ArchiveUtil.getFilePath(archiveItem));

					
					byte[] tab = new byte[ArchiveConstants.CONSTANTE_FILE_BUFFER];
					int read = -1;

					do {
						read = bis.read(tab);

						if (read > 0) {
							os.write(tab, 0, read);
						}
					} while (read > 0);

					
				}
            	 catch ( IOException e )
                 {
                     AppLogService.error( e );
                 }

				finally {
					try {
						bis.close();
						os.close(  );
					} catch (IOException e) {
					
						AppLogService.error(e);
					}
					
	            	}
				}  
           
        }

        return AdminMessageService.getMessageUrl( request, ArchiveConstants.MESSAGE_ERROR_DURING_DOWNLOAD_FILE, AdminMessage.TYPE_STOP );
    }
}
