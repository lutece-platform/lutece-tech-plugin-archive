package fr.paris.lutece.plugins.archive.service.archive;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface IGenerateArchiveService {
	
	
	
    String  getKey();
    
    String getMimeType();
	
	 void archiveDirectory(String strFolderToArchive,
			String strArchiveDestination, String strArchiveName) throws IOException,
			FileNotFoundException;

}
