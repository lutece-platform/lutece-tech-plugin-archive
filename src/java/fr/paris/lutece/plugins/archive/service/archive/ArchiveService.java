/*
 * Copyright (c) 2002-2017, Mairie de Paris
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
package fr.paris.lutece.plugins.archive.service.archive;

import fr.paris.lutece.plugins.archive.business.ArchiveItem;
import fr.paris.lutece.plugins.archive.business.ArchiveItemHome;
import fr.paris.lutece.plugins.archive.util.ArchiveConstants;
import fr.paris.lutece.plugins.archive.util.ArchiveUtil;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.util.AppLogService;

import java.util.Date;


/**
 * implementation of @see IArchiveService
 * @author merlinfe
 *
 */
public class ArchiveService implements IArchiveService
{
    private Plugin _plugin;
    private IGenerateArchiveServiceFactory _generateArchiveServiceFactory;

    /**
     * Constructor
     */
    public ArchiveService(  )
    {
    }

    /**
     * Initialize the Form service
     * @return the plugin
     */
    public Plugin getPlugin(  )
    {
        if ( _plugin == null )
        {
            _plugin = PluginService.getPlugin( ArchivePlugin.PLUGIN_NAME );
        }

        return _plugin;
    }

    /**
     * {@inheritDoc}
     */
    public int generateArchive( String strFolderToArchive, String strArchiveDestination, String strArchiveName,
        String strArchiveType )
    {
        int nIdArchiveItem = -1;

        IGenerateArchiveService generateArchiveService = _generateArchiveServiceFactory.getGenerateArchiveService( strArchiveType );

        if ( generateArchiveService != null )
        {
            ArchiveItem archiveItem = new ArchiveItem(  );
            archiveItem.setFolderToArchive( strFolderToArchive );
            archiveItem.setArchiveDestination( strArchiveDestination );
            archiveItem.setArchiveName( strArchiveName );
            archiveItem.setArchiveType( strArchiveType );
            archiveItem.setState( ArchiveConstants.ARCHIVE_STATE_INITIAL );
            archiveItem.setArchiveMimeType( generateArchiveService.getMimeType(  ) );
            nIdArchiveItem = ArchiveItemHome.create( archiveItem, getPlugin(  ) );
            archiveItem.setIdArchiveItem( nIdArchiveItem );
        }

        return nIdArchiveItem;
    }

    /**
     * {@inheritDoc}
     */
    public String informationArchive( int archiveItemKey )
    {
        String strState = ArchiveConstants.ARCHIVE_STATE_INITIAL;
        ArchiveItem archiveItem = ArchiveItemHome.findByPrimaryKey( archiveItemKey, getPlugin(  ) );

        if ( archiveItem != null )
        {
            strState = archiveItem.getState(  );
        }

        return strState;
    }

    /**
     * {@inheritDoc}
     */
    public void removeArchive( int archiveItemKey )
    {
        ArchiveItem archiveItem = ArchiveItemHome.findByPrimaryKey( archiveItemKey, getPlugin(  ) );

        if ( archiveItem != null )
        {
            try
            {
                ArchiveUtil.deleteFile( archiveItem );
            }
            catch ( Exception e )
            {
                AppLogService.error( e );
            }

            ArchiveItemHome.delete( archiveItemKey, getPlugin(  ) );
        }
    }

    /**
     * {@inheritDoc}
     */
    public void runGenerateArchive( StringBuilder sbLogs )
    {
        ArchiveItem archiveItem = null;
        StringBuilder newArchiveItem;

        do
        {
            archiveItem = ArchiveItemHome.getNextArchiveItemQueue( getPlugin(  ) );

            if ( archiveItem != null )
            {
                newArchiveItem = new StringBuilder(  );
                newArchiveItem.append( "New Archive Item " );
                newArchiveItem.append( ArchiveUtil.getFilePath( archiveItem ) );
                AppLogService.info( newArchiveItem.toString(  ) );
                sbLogs.append( " Start treatment " );
                sbLogs.append( new Date(  ).toString(  ) );
                sbLogs.append( newArchiveItem.toString(  ) );

                IGenerateArchiveService generateArchiveService = _generateArchiveServiceFactory.getGenerateArchiveService( archiveItem.getArchiveType(  ) );

                try
                {
                    ArchiveItemHome.updateState( archiveItem.getIdArchiveItem(  ), ArchiveConstants.ARCHIVE_STATE_USED,
                        getPlugin(  ) );
                    generateArchiveService.archiveDirectory( archiveItem.getFolderToArchive(  ),
                        archiveItem.getArchiveDestination(  ), archiveItem.getArchiveName(  ) );

                    sbLogs.append( " End treatment " );
                    sbLogs.append( new Date(  ).toString(  ) );
                    sbLogs.append( "\r\n" );

                    ArchiveItemHome.updateState( archiveItem.getIdArchiveItem(  ),
                        ArchiveConstants.ARCHIVE_STATE_FINAL, getPlugin(  ) );
                }
                catch ( Exception e )
                {
                    AppLogService.error( e );
                    sbLogs.append( e );
                    sbLogs.append( " End treatment " );
                    sbLogs.append( new Date(  ).toString(  ) );
                    sbLogs.append( "\r\n" );
                    ArchiveItemHome.updateState( archiveItem.getIdArchiveItem(  ),
                        ArchiveConstants.ARCHIVE_STATE_ERROR, getPlugin(  ) );
                }
            }
        }
        while ( archiveItem != null );
    }

    /**
     * Setter of generaArchiveServiceFactory
     * @param generaArchiveServiceFactory The generate archive service factory
     */
    public void setGenerateArchiveServiceFactory( IGenerateArchiveServiceFactory generaArchiveServiceFactory )
    {
        this._generateArchiveServiceFactory = generaArchiveServiceFactory;
    }
}
