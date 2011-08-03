package fr.paris.lutece.plugins.archive.service.archive;

public interface IArchiveService {

	int generateArchive(String strFolderToArchive,
			String strArchiveDestination, String strArchiveName,
			String strArchiveType);

	String informationArchive(int archiveItemKey);

	void removeArchive(int archiveItemKey);
	
	void runGenerateArchive(StringBuilder sbLogs);

	
}