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

import java.io.Serializable;


/**
 *
 * ArchiveItem
 *
 */
public class ArchiveItem implements Serializable
{
    private static final long serialVersionUID = 7661692993251106127L;
    private int _nIdArchiveItem;
    private String _strFolderToArchive;
    private String _strArchiveDestination;
    private String _strArchiveName;
    private String _strState;
    private String _strArchiveType;
    private String _strArchiveMimeType;

    /**
     * Get the folder to archive
     * @return the folder to archive
     */
    public String getFolderToArchive(  )
    {
        return _strFolderToArchive;
    }

    /**
     * Set the folder to archive
     * @param strFolderToArchive the folder to archive
     */
    public void setFolderToArchive( String strFolderToArchive )
    {
        _strFolderToArchive = strFolderToArchive;
    }

    /**
     * Get the archive destination
     * @return the archive destination
     */
    public String getArchiveDestination(  )
    {
        return _strArchiveDestination;
    }

    /**
     * Set the archive destination
     * @param strArchiveDestination archive destination
     */
    public void setArchiveDestination( String strArchiveDestination )
    {
        _strArchiveDestination = strArchiveDestination;
    }

    /**
     * Get the archive name
     * @return the archive name
     */
    public String getArchiveName(  )
    {
        return _strArchiveName;
    }

    /**
     * Set the archive name
     * @param strArchiveName the archive name
     */
    public void setArchiveName( String strArchiveName )
    {
        _strArchiveName = strArchiveName;
    }

    /**
     * Set the id archive item
     * @param nIdArchiveItem the id archive item
     */
    public void setIdArchiveItem( int nIdArchiveItem )
    {
        _nIdArchiveItem = nIdArchiveItem;
    }

    /**
     * Get the id archive item
     * @return the id archive item
     */
    public int getIdArchiveItem(  )
    {
        return _nIdArchiveItem;
    }

    /**
     * Set the state
     * @param strState the state
     */
    public void setState( String strState )
    {
        _strState = strState;
    }

    /**
     * Get the state
     * @return the state
     */
    public String getState(  )
    {
        return _strState;
    }

    /**
     * Set the archive type
     * @param strArchiveType the archive type
     */
    public void setArchiveType( String strArchiveType )
    {
        _strArchiveType = strArchiveType;
    }

    /**
     * Get the archive type
     * @return the archive type
     */
    public String getArchiveType(  )
    {
        return _strArchiveType;
    }

    /**
     * Set the archive mime type
     * @param strArchiveMimeType the archive mime type
     */
    public void setArchiveMimeType( String strArchiveMimeType )
    {
        _strArchiveMimeType = strArchiveMimeType;
    }

    /**
     * Get the archive mime type
     * @return the archive mime type
     */
    public String getArchiveMimeType(  )
    {
        return _strArchiveMimeType;
    }
}
