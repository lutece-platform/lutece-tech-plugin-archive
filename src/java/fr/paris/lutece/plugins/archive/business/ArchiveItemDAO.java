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
package fr.paris.lutece.plugins.archive.business;

import fr.paris.lutece.plugins.archive.util.ArchiveConstants;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.util.sql.DAOUtil;


/**
 * This class provides Data Access methods for MailItemQueue objects
 */
public class ArchiveItemDAO implements IArchiveItemDAO
{
    private static final String SQL_QUERY_NEW_PK = "SELECT max(id_archive_item) FROM archive_item";
    private static final String SQL_QUERY_SELECT_NEXT_MAIL_ITEM_QUEUE_ID = "SELECT min(id_archive_item) FROM archive_item WHERE state='" +
        ArchiveConstants.ARCHIVE_STATE_INITIAL + "'";
    private static final String SQL_QUERY_LOAD_ARCHIVE_ITEM = "SELECT id_archive_item,folder_to_archive,archive_destination,archive_name,archive_type,archive_mime_type,state FROM archive_item WHERE id_archive_item = ? ";
    private static final String SQL_QUERY_INSERT = " INSERT INTO archive_item( id_archive_item,folder_to_archive,archive_destination,archive_name,archive_type,archive_mime_type,state) VALUES( ? ,? ,? ,? ,?,?,?) ";
    private static final String SQL_QUERY_UPDATE_STATE = " UPDATE archive_item SET state=? WHERE id_archive_item = ? ";
    private static final String SQL_QUERY_DELETE_ARCHIVE_ITEM = " DELETE FROM archive_item WHERE id_archive_item = ?";

    /**
     * Generates a new primary key
     * @param plugin the {@link Plugin}
     * @return The new primary key
     */
    private int newPrimaryKey( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_NEW_PK, plugin );
        daoUtil.executeQuery(  );

        int nKey;

        if ( !daoUtil.next(  ) )
        {
            // if the table is empty
            nKey = 1;
        }

        nKey = daoUtil.getInt( 1 ) + 1;

        daoUtil.free(  );

        return nKey;
    }

    /**
     * {@inheritDoc}
     */
    public int nextArchiveItemId( Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_SELECT_NEXT_MAIL_ITEM_QUEUE_ID );

        daoUtil.executeQuery(  );

        int nIdMailItemQueue = -1;

        if ( daoUtil.next(  ) )
        {
            nIdMailItemQueue = daoUtil.getInt( 1 );
        }

        daoUtil.free(  );

        return nIdMailItemQueue;
    }

    /**
     * {@inheritDoc}
     */
    public void updateState( int nIdArchiveItem, String strState, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_UPDATE_STATE, plugin );
        daoUtil.setString( 1, strState );
        daoUtil.setInt( 2, nIdArchiveItem );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }

    /**
     * {@inheritDoc}
     */
    public synchronized int insert( ArchiveItem archiveItem, Plugin plugin )
    {
        int nIdPrimaryKey = newPrimaryKey( plugin );
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_INSERT, plugin );

        daoUtil.setInt( 1, nIdPrimaryKey );
        daoUtil.setString( 2, archiveItem.getFolderToArchive(  ) );
        daoUtil.setString( 3, archiveItem.getArchiveDestination(  ) );
        daoUtil.setString( 4, archiveItem.getArchiveName(  ) );
        daoUtil.setString( 5, archiveItem.getArchiveType(  ) );
        daoUtil.setString( 6, archiveItem.getArchiveMimeType(  ) );
        daoUtil.setString( 7, archiveItem.getState(  ) );

        daoUtil.executeUpdate(  );
        daoUtil.free(  );

        return nIdPrimaryKey;
    }

    /**
     * {@inheritDoc}
     */
    public ArchiveItem load( int nIdMailItemQueue, Plugin plugin )
    {
        ArchiveItem archiveItem = null;

        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_LOAD_ARCHIVE_ITEM, plugin );
        daoUtil.setInt( 1, nIdMailItemQueue );
        daoUtil.executeQuery(  );

        if ( daoUtil.next(  ) )
        {
            archiveItem = new ArchiveItem(  );
            archiveItem.setIdArchiveItem( daoUtil.getInt( 1 ) );
            archiveItem.setFolderToArchive( daoUtil.getString( 2 ) );
            archiveItem.setArchiveDestination( daoUtil.getString( 3 ) );
            archiveItem.setArchiveName( daoUtil.getString( 4 ) );
            archiveItem.setArchiveType( daoUtil.getString( 5 ) );
            archiveItem.setArchiveMimeType( daoUtil.getString( 6 ) );
            archiveItem.setState( daoUtil.getString( 7 ) );
        }

        daoUtil.free(  );

        return archiveItem;
    }

    /**
     * {@inheritDoc}
     */
    public void delete( int nIdMailItemQueue, Plugin plugin )
    {
        DAOUtil daoUtil = new DAOUtil( SQL_QUERY_DELETE_ARCHIVE_ITEM, plugin );
        daoUtil.setInt( 1, nIdMailItemQueue );
        daoUtil.executeUpdate(  );
        daoUtil.free(  );
    }
}
