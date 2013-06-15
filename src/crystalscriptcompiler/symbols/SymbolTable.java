/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.exceptions.DuplicateDeclarationException;
import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.helpers.SaveStackIterator;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;
import crystalscriptcompiler.syntaxtree.classes.ClassDeclaration;
import crystalscriptcompiler.syntaxtree.imports.ImportItems;
import crystalscriptcompiler.syntaxtree.imports.ImportName;
import crystalscriptcompiler.syntaxtree.interfaces.InterfaceDeclaration;
import crystalscriptcompiler.syntaxtree.methods.OverloadableDeclaration;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 *
 * @author User
 */
public class SymbolTable {

	public static enum Kind {
		REGULAR,
		LOCAL_ROOT,
		INNER_CLASS_ROOT,
		OUTER_CLASS_ROOT,
		MODULE_ROOT
	}

	public static enum Scope {
		BLOCK,
		LOCAL,
		CURRENT_CLASS,
		INHERITED,
		OUTER_CLASS_ONLY, // No inheritance allowed
		OUTER_INHERITED,
		MODULAR,
		ALL
	}

	private static final HashMap<Scope, Integer> scopeMapper = new HashMap<>();
	
	static {
		// It is very tempting to use ordinal(), but its order cannot be trusted.
		
		scopeMapper.put(Scope.BLOCK, 0);
		scopeMapper.put(Scope.LOCAL, 1);
		scopeMapper.put(Scope.CURRENT_CLASS, 2);
		scopeMapper.put(Scope.INHERITED, 3);
		scopeMapper.put(Scope.OUTER_CLASS_ONLY, 4);
		scopeMapper.put(Scope.OUTER_INHERITED, 4);
		scopeMapper.put(Scope.MODULAR, 5);
		scopeMapper.put(Scope.ALL, 6);
	}
	
	private Namespace moduleNamespace;
	private SymbolTable parent; // Nullable
	private Kind kind;
	private ArrayList<SymbolTable> inheritedTables = new ArrayList<>();
	private HashMap<String, SymbolDeclaration> dependentSymbolMapper = new HashMap<>();
	private HashMap<String, SymbolDeclaration> symbolMapper = new HashMap<>();

	public SymbolTable(Namespace moduleNamespace) {
		this.moduleNamespace = moduleNamespace;
		this.kind = Kind.MODULE_ROOT;
	}

	public SymbolTable(SymbolTable parent) {
		this(parent, Kind.REGULAR);
	}

	public SymbolTable(SymbolTable parent, Kind kind) {
		this.parent = parent;
		this.moduleNamespace = parent.moduleNamespace;
		this.kind = kind;
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

	public void addSymbol(String id, OverloadableDeclaration declaration) {
		addSymbol(id, new SignatureTree(declaration));
	}

	public void addSymbol(String id, Type type, int declarationIndex, VariableSymbolDeclaration.Scope scope) {
		// Check at local scope for local variables
		if (scope == VariableSymbolDeclaration.Scope.LOCAL) {
			if (containsSymbol(id, SymbolTable.Scope.LOCAL))
				throw new DuplicateDeclarationException(id);
		}
		else {
			if (containsSymbol(id, SymbolTable.Scope.BLOCK))
				throw new DuplicateDeclarationException(id);
		}
		symbolMapper.put(id, new VariableSymbolDeclaration(type, declarationIndex, scope));
	}

	private void addSymbol(String id, SymbolDeclaration declaration) {
		if (containsSymbol(id, SymbolTable.Scope.BLOCK))
			throw new DuplicateDeclarationException(id);

		symbolMapper.put(id, declaration);
	}

	public boolean containsSymbol(String id, Scope scope) {
		return get(id, scope) != null;
	}

	public SymbolDeclaration get(String id, Scope scope) {
		if (symbolMapper.containsKey(id))
			return symbolMapper.get(id);
		
		// Scan for inherited symbols (does nothing if scope is not a class/interface)
		if (inScope(scope, Scope.INHERITED) && scope != Scope.OUTER_CLASS_ONLY) {
			for (SymbolTable table : inheritedTables) {
				SymbolDeclaration declaration = table.get(id, Scope.INHERITED);
				if (declaration != null)
					return declaration;
			}
		}

		// Scan for imported symbols (does nothing if scope is not root)
		if (inScope(scope, Scope.ALL)) {
			if (dependentSymbolMapper.containsKey(id))
				return symbolMapper.get(id);
		}

		if (!isEndOfScope(scope)) {
			// Involve parent
			SymbolDeclaration declaration = parent.get(id, scope);
			if (declaration != null)
				return declaration;
		}

		return null;
	}

	public boolean containsSymbol(Name name, Scope scope) {
		return get(name, scope) != null;
	}

	public SymbolDeclaration get(Name name, Scope scope) {
		SymbolDeclaration result = getLocal(name.iterator());

		if (result != null)
			return result;

		// Scan for inherited symbols (does nothing if scope is not a class/interface)
		if (inScope(scope, Scope.INHERITED) && scope != Scope.OUTER_CLASS_ONLY) {
			result = getInherited(name.saveStackIterator());
			if (result != null)
				return result;
		}

		// Scan for imported symbols (does nothing if scope is not root)
		if (inScope(scope, Scope.ALL)) {
			result = parent.getDependent(name);
			if (result != null)
				return result;
		}

		if (!isEndOfScope(scope)) {
			// Involve parent
			result = parent.getLocal(name.iterator());
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
		
		return get(name, Scope.BLOCK);
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
		// Only root has access to dependent tables
		if (parent != null)
			return parent.getDependent(name);
		
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

	private boolean isEndOfScope(Scope scope) {
		switch (kind) {
			case REGULAR:
				return true;
			case LOCAL_ROOT:
				return scopeMapper.get(scope) <= scopeMapper.get(Scope.LOCAL);
			case INNER_CLASS_ROOT:
				return scopeMapper.get(scope) <= scopeMapper.get(Scope.INHERITED);
			case OUTER_CLASS_ROOT:
				return scopeMapper.get(scope) <= scopeMapper.get(Scope.OUTER_INHERITED);
		}

		return true;
	}
	
}
