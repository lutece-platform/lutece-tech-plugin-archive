/*
 * Copyright (c) 2002-2012, Mairie de Paris
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


/**
 *
 * IArchiveItemDAO
 *
 */
public interface IArchiveItemDAO
{
    /**
     * return the next archive item queue id
     * @param plugin {@link Plugin}
     * @return the next archive item queue id
     */
    int nextArchiveItemId( Plugin plugin );

    /**
     * update the archive state
    * @param nIdArchiveItem the id of the archive item
    * @param strState the new state
    * @param plugin {@link Plugin}
    */
    void updateState( int nIdArchiveItem, String strState, Plugin plugin );

    /**
     * Insert a new archive item in the database queue.
    * @param archiveItem the archive item to insert
    * @param plugin {@link Plugin}
    * @return the id of the archive item
     */
    int insert( ArchiveItem archiveItem, Plugin plugin );

    /**
     * load the archive item wich the id is specified in parameter
    * @param nIdArchiveItem the archive item id
    * @param plugin {@link Plugin}
    * @return {@link ArchiveItem}
     */
    ArchiveItem load( int nIdArchiveItem, Plugin plugin );

    /**
     * Delete  the archive item record in the table
    * @param nIdArchiveItem The indentifier of the archive item to remove
    * @param plugin {@link Plugin}
     */
    void delete( int nIdArchiveItem, Plugin plugin );
}
