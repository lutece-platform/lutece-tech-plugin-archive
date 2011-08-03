package fr.paris.lutece.plugins.archive.business;

import java.io.Serializable;

public class ArchiveItem implements Serializable{
	
	/**
	 * 
	 */
	
	
	
	
	private static final long serialVersionUID = 7661692993251106127L; 
	
	private int _nIdArchiveItem;
	private String _strFolderToArchive;
	private String _strArchiveDestination;
	private String _strArchiveName;
	private String _strState;
	private String _strArchiveType;
	private String _strArchiveMimeType;

	public String getFolderToArchive() {
		return this._strFolderToArchive;
	}
	
	public void setFolderToArchive(String strFolderToArchive) {
		this._strFolderToArchive = strFolderToArchive;
	}
	public String getArchiveDestination() {
		return this._strArchiveDestination;
	}
	public void setArchiveDestination(String strArchiveDestination) {
		this._strArchiveDestination = strArchiveDestination;
	}
	public String getArchiveName() {
		return this._strArchiveName;
	}
	public void setArchiveName(String strArchiveName) {
		this._strArchiveName = strArchiveName;
	}

	public void setIdArchiveItem(int _nIdArchiveItem) {
		this._nIdArchiveItem = _nIdArchiveItem;
	}

	public int getIdArchiveItem() {
		return this._nIdArchiveItem;
	}

	public void setState(String _strState) {
		this._strState = _strState;
	}

	public String getState() {
		return _strState;
	}

	public void setArchiveType(String _strArchiveType) {
		this._strArchiveType = _strArchiveType;
	}

	public String getArchiveType() {
		return _strArchiveType;
	}

	public void setArchiveMimeType(String _strArchiveMimeType) {
		this._strArchiveMimeType = _strArchiveMimeType;
	}

	public String getArchiveMimeType() {
		return _strArchiveMimeType;
	}

}
