package fr.paris.lutece.plugins.archive.service.archive;

import java.util.Date;

import fr.paris.lutece.portal.service.daemon.Daemon;
import fr.paris.lutece.portal.service.spring.SpringContextService;

public class DaemonArchive extends Daemon
{
	
	private IArchiveService archiveService;
	public DaemonArchive()
	{
		
		super();
		init();
		
	}
	/**
     * Process Direcory indexing
     */
    public void run(  )
    {
       
    	StringBuilder sbLogs = new StringBuilder(  );
    	sbLogs.append( new Date(  ).toString(  ) );
    	archiveService.runGenerateArchive(sbLogs);
    	sbLogs.append( "\r\nNo Archive " );
    	setLastRunLogs(sbLogs.toString());
    }
    
    
    public void init() {
    	archiveService =(IArchiveService) SpringContextService.getBean( "archive.archiveService" ) ;
	}
    
    
    
}
