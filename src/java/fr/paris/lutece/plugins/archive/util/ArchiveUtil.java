package fr.paris.lutece.plugins.archive.util;

import java.io.File;
import java.io.FileNotFoundException;

import fr.paris.lutece.plugins.archive.business.ArchiveItem;

public class ArchiveUtil {
	
	
	public static void deleteFile(String filePath) throws Exception {
		File file = new File(filePath);

		if (file.exists()) {
			if (!file.delete()) {
				throw new Exception("Failed to delete " + filePath);
			}
		} else {
			throw new FileNotFoundException("The file " + filePath
					+ " does not exists");
		}
	}
	
	public static String getFilePath(ArchiveItem archiveItem)
	{
	
		StringBuilder strFilePath = new StringBuilder();
		strFilePath.append(archiveItem.getArchiveDestination());
		strFilePath.append(File.separator);
		strFilePath.append(archiveItem.getArchiveName());
		return strFilePath.toString();
	}
	
	public static void deleteFile(ArchiveItem archiveItem) throws Exception {
		
		deleteFile(getFilePath(archiveItem));
	}

}
