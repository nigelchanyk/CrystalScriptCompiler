/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.helpers;

import crystalscriptcompiler.Configurations;
import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.exceptions.ExtensionException;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.io.File;
import java.util.Iterator;

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
	
	public static File getModuleFile(Name module, Namespace globalNamespace) {
		StringBuilder sb = new StringBuilder(globalNamespace.getDirectory().getAbsolutePath());
		for (String subPath : module)
			sb.append('/').append(subPath);
		sb.append('.').append(Configurations.CRYSTALSCRIPT_EXTENSION);

		return new File(sb.toString());
	}

	public static interface SaveStackIterator<T> extends Iterator<T> {

		public abstract void saveStack();
		public abstract void restoreStack();
		
	}
	
}
