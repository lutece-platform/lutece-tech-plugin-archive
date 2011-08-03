package fr.paris.lutece.plugins.archive.business;

import fr.paris.lutece.portal.service.plugin.Plugin;

public interface IArchiveItemDAO {

	/**
	 * return the next mail item queue id
	 * @return the next mail item queue id
	 */
	int nextArchiveItemId(Plugin plugin);

	/**
	 * Lock the  mail item
	 * @param nIdMailItemQueue the id of the mail item  to lock
	 */
	void updateState(int nIdArchiveItem, String strState, Plugin plugin);

	/**
	 * Insert a new mail item in the table.
	 * @param mailItemQueue the mail item
	 */
	int insert(ArchiveItem archiveItem, Plugin plugin);

	/**
	 * return the first mail item  in the table
	 * @param nIdMailItemQueue the id of the mail item
	 * @return the first mail item in the table
	 */
	ArchiveItem load(int nIdMailItemQueue, Plugin plugin);

	/**
	 * Delete  the mail item record in the table
	 * @param nIdMailItemQueue The indentifier of the mail item to remove
	 */
	void delete(int nIdMailItemQueue, Plugin plugin);

}