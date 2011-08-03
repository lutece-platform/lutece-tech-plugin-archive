package fr.paris.lutece.plugins.archive.service.archive;

import java.util.Date;

import fr.paris.lutece.plugins.archive.business.ArchiveItem;
import fr.paris.lutece.plugins.archive.business.ArchiveItemHome;
import fr.paris.lutece.plugins.archive.util.ArchiveUtil;
import fr.paris.lutece.plugins.archive.util.ArchiveConstants;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.util.AppLogService;

public class ArchiveService implements IArchiveService {


	private Plugin _plugin;
	private IGenerateArchiveServiceFactory _generateArchiveServiceFactory;
	

	/**
	 * Initialize the Form service
	 * 
	 */
	public void init() {
		_plugin = PluginService.getPlugin(ArchivePlugin.PLUGIN_NAME);
	}

	public ArchiveService()
	{
		init();
		
	}
	

	/* (non-Javadoc)
	 * @see fr.paris.lutece.plugins.archive.service.archive.IArchiveService#generateArchive(java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
	public int generateArchive(String strFolderToArchive,
			String strArchiveDestination, String strArchiveName,
			String strArchiveType) {

		
		
		
		int nIdArchiveItem=-1;
		
		
		IGenerateArchiveService generateArchiveService = _generateArchiveServiceFactory
		.getGenerateArchiveService(strArchiveType);

		if(generateArchiveService!=null)
		{
		ArchiveItem archiveItem = new ArchiveItem();
		archiveItem.setFolderToArchive(strFolderToArchive);
		archiveItem.setArchiveDestination(strArchiveDestination);
		archiveItem.setArchiveName(strArchiveName);
		archiveItem.setArchiveType(strArchiveType);
		archiveItem.setState(ArchiveConstants.ARCHIVE_STATE_INITIAL);
		archiveItem.setArchiveMimeType(generateArchiveService.getMimeType());
		nIdArchiveItem = ArchiveItemHome.create(archiveItem, _plugin);
		archiveItem.setIdArchiveItem(nIdArchiveItem);
		
		}

		return nIdArchiveItem;
	}
	
	
	
	/* (non-Javadoc)
	 * @see fr.paris.lutece.plugins.archive.service.archive.IArchiveService#informationArchive(int)
	 */
	public String informationArchive(int archiveItemKey) {

		String strState = ArchiveConstants.ARCHIVE_STATE_INITIAL;
		ArchiveItem archiveItem = ArchiveItemHome.findByPrimaryKey(
				archiveItemKey, _plugin);
		if (archiveItem != null) {
			strState = archiveItem.getState();
		}

		return strState;

	}
	
	
	/* (non-Javadoc)
	 * @see fr.paris.lutece.plugins.archive.service.archive.IArchiveService#removeArchive(int)
	 */
	public void removeArchive(int archiveItemKey) {
		
		ArchiveItem archiveItem = ArchiveItemHome.findByPrimaryKey(
				archiveItemKey, _plugin);
		
		
		if (archiveItem != null) {
			
			try {
				ArchiveUtil.deleteFile(archiveItem);
			} catch (Exception e) {
				
				AppLogService.error(e);
			}
			ArchiveItemHome.delete(archiveItemKey, _plugin);
			
		}

	}
	
	

	public void runGenerateArchive(StringBuilder sbLogs) {
		
		
		
		
		ArchiveItem archiveItem=null; 
		StringBuilder newArchiveItem;
		do
		{
		
		 archiveItem = ArchiveItemHome
				.getNextMailItemQueue(_plugin);
		if (archiveItem != null) {
			
			
			newArchiveItem=new StringBuilder();
			newArchiveItem.append("New Archive Item ");
			newArchiveItem.append(ArchiveUtil.getFilePath(archiveItem));
			AppLogService.debug(newArchiveItem.toString());
			sbLogs.append(" Start treatment ");
			sbLogs.append(new Date(  ).toString(  ));
			sbLogs.append(newArchiveItem.toString());
			
			IGenerateArchiveService generateArchiveService = _generateArchiveServiceFactory
					.getGenerateArchiveService(archiveItem.getArchiveType());
			
			try {
				ArchiveItemHome.updateState(archiveItem.getIdArchiveItem(),
						ArchiveConstants.ARCHIVE_STATE_USED, _plugin);
				generateArchiveService.archiveDirectory(archiveItem
						.getFolderToArchive(), archiveItem
						.getArchiveDestination(), archiveItem.getArchiveName());
				
				sbLogs.append(" End treatment ");
				sbLogs.append(new Date(  ).toString(  ));
				sbLogs.append( "\r\n" );
				
				ArchiveItemHome.updateState(archiveItem.getIdArchiveItem(),
						ArchiveConstants.ARCHIVE_STATE_FINAL, _plugin);

			} catch (Exception e) {

				AppLogService.error(e);
				sbLogs.append(e);
				sbLogs.append(" End treatment ");
				sbLogs.append(new Date(  ).toString(  ));
				sbLogs.append( "\r\n" );
				ArchiveItemHome.updateState(archiveItem.getIdArchiveItem(),
						ArchiveConstants.ARCHIVE_STATE_ERROR, _plugin);

			}
		}
		}
		while(archiveItem!=null);

	}



	

	/* (non-Javadoc)
	 * @see fr.paris.lutece.plugins.archive.service.archive.IArchiveService#setGenerateArchiveServiceFactory(fr.paris.lutece.plugins.archive.service.archive.IGenerateArchiveServiceFactory)
	 */
	public void setGenerateArchiveServiceFactory(
			IGenerateArchiveServiceFactory _generaArchiveServiceFactory) {
		this._generateArchiveServiceFactory = _generaArchiveServiceFactory;
	}

}
