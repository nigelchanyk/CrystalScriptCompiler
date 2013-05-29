/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.exceptions.ExtensionException;
import java.io.File;

/**
 *
 * @author User
 */
public class Helper {
	
	private Helper() {
	}

	public static String getModuleName(File file) {
		return getNameWithoutExtension(file, "cry");
	}

	private static String getNameWithoutExtension(File file, String expectedExtension) {
		String fileName = file.getName();
		int extStart = fileName.lastIndexOf(".");
		if (!expectedExtension.equalsIgnoreCase(fileName.substring(extStart + 1)) || extStart == -1)
			throw new ExtensionException(file, expectedExtension);

		return file.getName().substring(0, extStart);
	}
	
}
