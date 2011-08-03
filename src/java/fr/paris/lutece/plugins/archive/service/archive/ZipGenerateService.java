package fr.paris.lutece.plugins.archive.service.archive;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import fr.paris.lutece.plugins.archive.util.ArchiveConstants;
import fr.paris.lutece.portal.service.util.AppLogService;

public class ZipGenerateService implements IGenerateArchiveService {

	
	

	/**
	 * this Method zip a directory and all files contained in it.
	 * 
	 * @param String
	 *            strFolderToZip : the folder to zip
	 * @param String
	 *            strZipDestination : the place for the created zip
	 * @param String
	 *            strZipName : the zip name
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 *                , FileNotFoundException
	 */
	public  void archiveDirectory(String strFolderToArchive,
			String strArchiveDestination, String strArchiveName) throws IOException,
			FileNotFoundException {
		BufferedOutputStream bos = null;
		File folderToZip;
		ZipOutputStream zos = null;
		StringBuilder strDestinationFile = new StringBuilder();
		strDestinationFile.append(strArchiveDestination);
		strDestinationFile.append(File.separator);
		strDestinationFile.append(strArchiveName);

		try {

			folderToZip = new File(strFolderToArchive);
			bos = new BufferedOutputStream(new FileOutputStream(
					strDestinationFile.toString()));
			zos = new ZipOutputStream(bos);
			zipDirectory(folderToZip, zos, "");
		} catch (Exception e) {

			AppLogService.error(e);
		}

		finally {

			zos.close();
		}
	}

	/**
	 * this Method zip a directory and all files contained in it.
	 * 
	 * @param File
	 *            dir : directory to zip
	 * @param ZipOutputStream
	 *            zos : zip object
	 * @param String
	 *            path : Current path in the zip
	 * 
	 * @return void
	 * 
	 * @exception IOException
	 *                , FileNotFoundException
	 */
	private  void zipDirectory(File dir, ZipOutputStream zos, String path)
			throws IOException, FileNotFoundException {
		if (dir != null && dir.isDirectory()) {
			zipFileInDirectory(dir, zos, path);
		}
	}

	/**
	 * zip a given directory ( Recursive function )
	 * 
	 * @param File
	 *            dir : Current directory to zip
	 * @param ZipOutputStream
	 *            zos : Zip object
	 * @param String
	 *            path : Current path in the zip object
	 * 
	 * @return void
	 * 
	 * @throws IOException
	 *             , FileNotFoundException
	 */
	private  void zipFileInDirectory(File dir, ZipOutputStream zos,
			String path) throws IOException, FileNotFoundException {

		StringBuilder strEntry = null;
		String strDirectory = null;

		if (dir != null && dir.isDirectory()) {
			File[] entries = dir.listFiles();
			int sz = entries.length;
			for (int j = 0; j < sz; j++) {
				// directory
				if (entries[j].isDirectory()) {
					// add a new directory in zip
					strEntry = new StringBuilder();
					strEntry.append(path);
					strEntry.append(entries[j].getName());
					strEntry.append(File.separator);
					strDirectory = strEntry.toString();
					ZipEntry ze = new ZipEntry(strDirectory);
					zos.putNextEntry(ze);

					// call zipDirectory
					strEntry = new StringBuilder();
					strEntry.append(dir.getAbsolutePath());
					strEntry.append(File.separator);
					strEntry.append(entries[j].getName());
					File newDir = new File(strEntry.toString());
					zipDirectory(newDir, zos, strDirectory);
				}
				// file
				else {
					// read the file to zip
					FileInputStream bis = null;

					try {
						bis = new FileInputStream(entries[j].getAbsolutePath());

						// create a new entry in zip
						ZipEntry ze = new ZipEntry(path + entries[j].getName());
						zos.putNextEntry(ze);
						byte[] tab = new byte[ArchiveConstants.CONSTANTE_FILE_BUFFER];
						int read = -1;

						do {
							read = bis.read(tab);

							if (read > 0) {
								zos.write(tab, 0, read);
							}
						} while (read > 0);

						zos.closeEntry();
					}

					finally {
						bis.close();
					}

				}
			}
		}
	}

	public String getKey() {
		
		return ArchiveConstants.ARCHIVE_TYPE_ZIP;
	}

	public String getMimeType() {
		// TODO Auto-generated method stub
		return ArchiveConstants.ARCHIVE_MIME_TYPE_ZIP;
	}

}