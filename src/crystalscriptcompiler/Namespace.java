/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler;

import crystalscriptcompiler.exceptions.NamespaceException;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.io.File;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class Namespace {
	
	private File directory;
	private Namespace parent; // Nullable
	private HashMap<String, Namespace> subNamespaceMapper = new HashMap<>();
	private HashMap<String, ParseTreeRoot> moduleMapper = new HashMap<>();

	public Namespace(File directory) {
		this(directory, null);
	}

	private Namespace(File directory, Namespace parent) {
		if (!directory.exists())
			throw new NamespaceException(directory);

		this.directory = directory;
		this.parent = parent;
	}

	public void add(String name, ParseTreeRoot module) {
		moduleMapper.put(name, module);
	}

	public void add(Name moduleName, ParseTreeRoot moduleTree) {
		if (parent != null) {
			parent.add(moduleName, moduleTree);
			return;
		}

		add(moduleName.iterator(), moduleTree);
	}

	private void add(Iterator<String> namespaceIterator, ParseTreeRoot moduleTree) {
		String name = namespaceIterator.next();
		if (namespaceIterator.hasNext()) {
			// Current name is a directory
			if (subNamespaceMapper.containsKey(name))
				subNamespaceMapper.get(name).add(namespaceIterator, moduleTree);
			else {
				Namespace subNamespace = new Namespace(new File(directory.getAbsolutePath() + "/" + name), this);
				subNamespaceMapper.put(name, subNamespace);
				subNamespace.add(namespaceIterator, moduleTree);
			}
		}
		else {
			// Current name is a module
			if (!moduleMapper.containsKey(name))
				moduleMapper.put(name, moduleTree);
		}
	}
	
	public boolean contains(Name moduleName) {
		if (parent != null)
			return parent.contains(moduleName);

		return contains(moduleName.iterator());
	}

	private boolean contains(Iterator<String> namespaceIterator) {
		String name = namespaceIterator.next();
		if (namespaceIterator.hasNext()) {
			// Current name is a directory
			if (subNamespaceMapper.containsKey(name))
				return subNamespaceMapper.get(name).contains(namespaceIterator);

			return false;
		}
		// Current name is a module
		return moduleMapper.containsKey(name);
	}

	public File getDirectory() {
		return directory;
	}

}
