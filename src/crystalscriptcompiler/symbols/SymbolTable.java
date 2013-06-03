/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.exceptions.DuplicateDeclarationException;
import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.helpers.Helper.SaveStackIterator;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;
import crystalscriptcompiler.syntaxtree.classes.ClassDeclaration;
import crystalscriptcompiler.syntaxtree.imports.ImportItems;
import crystalscriptcompiler.syntaxtree.imports.ImportName;
import crystalscriptcompiler.syntaxtree.interfaces.InterfaceDeclaration;
import crystalscriptcompiler.syntaxtree.methods.MethodDeclaration;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class SymbolTable {

	public static enum Scope {
		LOCAL,
		INHERITED,
		MODULAR,
		ALL
	}

	private static final HashMap<Scope, Integer> scopeMapper = new HashMap<>();
	
	static {
		// It is very tempting to use ordinal(), but its order cannot be trusted.
		
		scopeMapper.put(Scope.LOCAL, 0);
		scopeMapper.put(Scope.INHERITED, 1);
		scopeMapper.put(Scope.MODULAR, 2);
		scopeMapper.put(Scope.ALL, 3);
	}
	
	private Namespace moduleNamespace;
	private SymbolTable parent; // Nullable
	private ArrayList<SymbolTable> inheritedTables = new ArrayList<>();
	private HashMap<String, SymbolDeclaration> dependentSymbolMapper = new HashMap<>();
	private HashMap<String, SymbolDeclaration> symbolMapper = new HashMap<>();

	public SymbolTable(Namespace moduleNamespace) {
		this.moduleNamespace = moduleNamespace;
	}

	public SymbolTable(SymbolTable parent) {
		this.parent = parent;
		this.moduleNamespace = parent.moduleNamespace;
	}

	public SymbolTable getParent() {
		return parent;
	}

	public void addInheritance(SymbolTable inheritedTable) {
		inheritedTables.add(inheritedTable);
	}

	public void addDependency(ImportName importName) {
		ParseTreeRoot module = moduleNamespace.get(importName.getRealName());
		dependentSymbolMapper.put(importName.getAlias(), new ModuleSymbolDeclaration(module));
	}

	public void addDependency(ImportName importName, ImportItems items) {
		SymbolTable moduleTable = moduleNamespace.get(importName.getRealName()).getSymbolTable();
		for (ImportName itemName : items) {
			SymbolDeclaration declaration = moduleTable.get(itemName.getRealName(), Scope.MODULAR);
			if (declaration == null)
				throw new ReferenceNotFoundException(itemName.getRealName(), importName.getRealName());

			dependentSymbolMapper.put(itemName.getAlias(), declaration);
		}
	}

	public void addSymbol(String id, ClassDeclaration declaration) {
		addSymbol(id, new ClassSymbolDeclaration(declaration));
	}

	public void addSymbol(String id, InterfaceDeclaration declaration) {
		addSymbol(id, new InterfaceSymbolDeclaration(declaration));
	}

	public void addSymbol(String id, MethodDeclaration declaration) {
		addSymbol(id, new MethodSymbolDeclaration(declaration));
	}

	private void addSymbol(String id, SymbolDeclaration declaration) {
		if (hasSymbol(id, SymbolTable.Scope.LOCAL))
			throw new DuplicateDeclarationException(id);

		symbolMapper.put(id, declaration);
	}

	public boolean hasSymbol(String id, Scope scope) {
		return get(id, scope) != null;
	}

	private SymbolDeclaration get(String id, Scope scope) {
		if (symbolMapper.containsKey(id))
			return symbolMapper.get(id);

		if (inScope(scope, Scope.INHERITED)) {
			for (SymbolTable table : inheritedTables) {
				SymbolDeclaration declaration = table.get(id, Scope.INHERITED);
				if (declaration != null)
					return declaration;
			}
		}

		if (inScope(scope, Scope.MODULAR) && parent != null) {
			SymbolDeclaration declaration = parent.get(id, scope);
			if (declaration != null)
				return declaration;
		}

		if (inScope(scope, Scope.ALL)) {
			if (dependentSymbolMapper.containsKey(id))
				return symbolMapper.get(id);
		}

		return null;
	}

	public SymbolDeclaration get(Name name, Scope scope) {
		SymbolDeclaration result = getLocal(name.iterator());

		if (result != null)
			return result;
		if (inScope(scope, Scope.INHERITED)) {
			result = getInherited(name.saveStackIterator());
			if (result != null)
				return result;
		}
		if (inScope(scope, Scope.MODULAR)) {
			result = parent.getLocal(name.iterator());
			if (result != null)
				return result;
		}
		if (inScope(scope, Scope.ALL)) {
			result = parent.getDependent(name);
			if (result != null)
				return result;
		}

		return result;
	}

	private SymbolDeclaration getLocal(Iterator<String> nameIterator) {
		String name = nameIterator.next();
		if (!symbolMapper.containsKey(name))
			return null;
		if (nameIterator.hasNext()) {
			SymbolDeclaration declaration = symbolMapper.get(name);
			return declaration.hasChildDeclaration() ? declaration.getSymbolTable().getLocal(nameIterator) : null;
		}
		
		return get(name, Scope.LOCAL);
	}

	private SymbolDeclaration getInherited(SaveStackIterator<String> nameIterator) {
		nameIterator.saveStack();
		SymbolDeclaration returnVal = _getInherited(nameIterator);
		nameIterator.restoreStack();
		return returnVal;
	}

	private SymbolDeclaration _getInherited(SaveStackIterator<String> nameIterator) {
		String name = nameIterator.next();
		if (!symbolMapper.containsKey(name)) {
			// Local search failed. Try inherited search.
			for (SymbolTable inheritedTable : inheritedTables) {
				SymbolDeclaration result = inheritedTable.getInherited(nameIterator);
				if (result != null)
					return result;
			}

			// Unreachable unless no superclass has the given name
			return null;
		}
		
		// Local search succeeded.
		if (nameIterator.hasNext()) {
			SymbolDeclaration declaration = symbolMapper.get(name);
			return declaration.hasChildDeclaration() ? declaration.getSymbolTable().getInherited(nameIterator) : null;
		}
		
		return get(name, Scope.INHERITED);
	}

	private SymbolDeclaration getDependent(Name name) {
		SaveStackIterator<String> itr = name.saveStackIterator();
		String context = itr.next();
		if (!dependentSymbolMapper.containsKey(context))
			return null;

		SymbolDeclaration declaration = dependentSymbolMapper.get(context);
		if (itr.hasNext()) {
			if (declaration.hasChildDeclaration())
				return declaration.getSymbolTable().getInherited(itr);

			return null;
		}

		return declaration;
	}

	private boolean inScope(Scope scope, Scope requirement) {
		return scopeMapper.get(scope) >= scopeMapper.get(requirement);
	}
	
}
