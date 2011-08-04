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

import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.spring.SpringContextService;


/**
 * This class provides Data Access methods for ArchiveItem objects
 */
public final class ArchiveItemHome
{
    // Static variable pointed at the DAO instance
    private static IArchiveItemDAO _dao = (IArchiveItemDAO) SpringContextService.getBean( "archive.archiveItemDAO" );

    /**
     * Creates a new ArchiveItemHome object.
     */
    private ArchiveItemHome(  )
    {
    }

    /**
     * Insert a new archive item in the database queue.
     * @param archiveItem the archive item to insert
     * @param plugin {@link Plugin}
     * @return the id of the archive item
     */
    public static int create( ArchiveItem archiveItem, Plugin plugin )
    {
        return _dao.insert( archiveItem, plugin );
    }

    /**
     * Delete  the archive item record in the table
     * @param nIdArchiveItem The indentifier of the archive item to remove
     * @param plugin {@link Plugin}
     *
     */
    public static void delete( int nIdArchiveItem, Plugin plugin )
    {
        _dao.delete( nIdArchiveItem, plugin );
    }

    /**
     * Return the first archive item in the queue
     * @param plugin {@link Plugin}
     * @return the first archive item in the queue
     */
    public static ArchiveItem getNextArchiveItemQueue( Plugin plugin )
    {
        //get the id of the next mail item queue
        int nIdArchiveItem = _dao.nextArchiveItemId( plugin );

        if ( nIdArchiveItem != -1 )
        {
            return findByPrimaryKey( nIdArchiveItem, plugin );
        }

        return null;
    }

    /**
     * update the archive state
     * @param nIdArchiveItem the id of the archive item
     * @param strState the new state
     * @param plugin {@link Plugin}
     */
    public static void updateState( int nIdArchiveItem, String strState, Plugin plugin )
    {
        _dao.updateState( nIdArchiveItem, strState, plugin );
    }

    /**
     * load the archive item wich the id is specified in parameter
     * @param nKey the archive item id
     * @param plugin {@link Plugin}
     * @return {@link ArchiveItem}
     */
    public static ArchiveItem findByPrimaryKey( int nKey, Plugin plugin )
    {
        return _dao.load( nKey, plugin );
    }
}
