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

import java.io.Serializable;


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

    public String getFolderToArchive(  )
    {
        return this._strFolderToArchive;
    }

    public void setFolderToArchive( String strFolderToArchive )
    {
        this._strFolderToArchive = strFolderToArchive;
    }

    public String getArchiveDestination(  )
    {
        return this._strArchiveDestination;
    }

    public void setArchiveDestination( String strArchiveDestination )
    {
        this._strArchiveDestination = strArchiveDestination;
    }

    public String getArchiveName(  )
    {
        return this._strArchiveName;
    }

    public void setArchiveName( String strArchiveName )
    {
        this._strArchiveName = strArchiveName;
    }

    public void setIdArchiveItem( int _nIdArchiveItem )
    {
        this._nIdArchiveItem = _nIdArchiveItem;
    }

    public int getIdArchiveItem(  )
    {
        return this._nIdArchiveItem;
    }

    public void setState( String _strState )
    {
        this._strState = _strState;
    }

    public String getState(  )
    {
        return _strState;
    }

    public void setArchiveType( String _strArchiveType )
    {
        this._strArchiveType = _strArchiveType;
    }

    public String getArchiveType(  )
    {
        return _strArchiveType;
    }

    public void setArchiveMimeType( String _strArchiveMimeType )
    {
        this._strArchiveMimeType = _strArchiveMimeType;
    }

    public String getArchiveMimeType(  )
    {
        return _strArchiveMimeType;
    }
}
