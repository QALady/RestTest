/**
 * 
 */
package com.qaforum.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author cdacr
 *
 */
public final class FileUtility {

	private static final Logger LOGGER = Logger.getLogger(FileUtility.class
			.getName());

	/**
	 * 
	 */
	private FileUtility() {
	}

	/**
	 * 
	 * @param inStream 
	 * @param targetFileName 
	 * @throws IOException 
	 */
	public static void copyFile(final InputStream inStream,
			final String targetFileName) throws IOException {
		final int bufSize = 1024;
		try (OutputStream outpuStream = new FileOutputStream(new File(
				targetFileName))) {
			int read = 0;
			final byte[] bytes = new byte[bufSize];

			while ((read = inStream.read(bytes)) != -1) {
				outpuStream.write(bytes, 0, read);
			}
			outpuStream.flush();
		}
		LOGGER.log(Level.INFO, "File copied successfully to: " + targetFileName);
	}

	/**
	 * 
	 * @param fileName 
	 */
	public static void deleteFile(final String fileName) {
		final File file = new File(fileName);

		if (file.exists() && file.isFile() && file.canWrite()) {
			file.delete();
		}
	}

}
