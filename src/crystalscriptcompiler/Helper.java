/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.exceptions.ExtensionException;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map.Entry;

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

	public static class HashMapValueIterator<S, T> implements Iterator<T> {

		private Iterator<Entry<S, T>> iterator;
		
		public HashMapValueIterator(HashMap<S, T> moduleMapper) {
			iterator = moduleMapper.entrySet().iterator();
		}

		@Override
		public boolean hasNext() {
			return iterator.hasNext();
		}

		@Override
		public T next() {
			return iterator.next().getValue();
		}

		@Override
		public void remove() {
		}
	}
	
}
