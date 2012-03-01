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
package fr.paris.lutece.plugins.archive.util;

import fr.paris.lutece.plugins.archive.business.ArchiveItem;

import java.io.File;
import java.io.FileNotFoundException;


/**
 *
 * @author merlinfe
 *
 */
public final class ArchiveUtil
{
    /**
     * Private constructor
     */
    private ArchiveUtil(  )
    {
    }

    /**
     * delete the file with the path parameter is provided
     * @param filePath the file path
     * @throws Exception {@link Exception}
     */
    public static void deleteFile( String filePath ) throws Exception
    {
        File file = new File( filePath );

        if ( file.exists(  ) )
        {
            if ( !file.delete(  ) )
            {
                throw new Exception( "Failed to delete " + filePath );
            }
        }
        else
        {
            throw new FileNotFoundException( "The file " + filePath + " does not exists" );
        }
    }

    /**
     * get the file path with the archiveItem parameter is provided
     * @param archiveItem archiveItem
     * @return the file path
     */
    public static String getFilePath( ArchiveItem archiveItem )
    {
        StringBuilder strFilePath = new StringBuilder(  );
        strFilePath.append( archiveItem.getArchiveDestination(  ) );
        strFilePath.append( File.separator );
        strFilePath.append( archiveItem.getArchiveName(  ) );

        return strFilePath.toString(  );
    }

    /**
     * delete the file with the archiveItem parameter is provided
     * @param archiveItem the archiveItem
     * @throws Exception {@link Exception}
     */
    public static void deleteFile( ArchiveItem archiveItem )
        throws Exception
    {
        deleteFile( getFilePath( archiveItem ) );
    }
}
