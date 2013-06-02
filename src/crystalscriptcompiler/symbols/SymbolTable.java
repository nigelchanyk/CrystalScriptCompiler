/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.exceptions.DuplicateDeclarationException;
import crystalscriptcompiler.exceptions.InheritanceException;
import crystalscriptcompiler.exceptions.InterfaceException;
import crystalscriptcompiler.exceptions.ReferenceNotFoundException;
import crystalscriptcompiler.syntaxtree.classes.ClassDeclaration;
import crystalscriptcompiler.syntaxtree.interfaces.InterfaceDeclaration;
import crystalscriptcompiler.syntaxtree.methods.MethodDeclaration;
import crystalscriptcompiler.syntaxtree.names.Name;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;
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
	private SymbolTable root; // Nullable
	private ArrayList<SymbolTable> inheritedTables = new ArrayList<>();
	private ArrayList<SymbolTable> dependentTables = new ArrayList<>();
	private HashMap<String, SymbolDeclaration> symbolMapper = new HashMap<>();

	public SymbolTable(Namespace moduleNamespace) {
		this.moduleNamespace = moduleNamespace;
	}

	public SymbolTable(SymbolTable root) {
		this.root = root;
		this.moduleNamespace = root.moduleNamespace;
	}

	public void addInheritance(SymbolTable inheritedTable) {
		System.out.println(inheritedTable.moduleNamespace.toString());
		inheritedTables.add(inheritedTable);
	}

	public void addClassInheritance(ClassOrInterfaceType superClassType) {
		SymbolDeclaration symbol = getNestedSymbolDeclaration(superClassType);
		if (!(symbol instanceof ClassSymbolDeclaration))
			throw new InheritanceException(superClassType, InheritanceException.ExpectedKind.CLASS);

		addInheritance(((ClassSymbolDeclaration)symbol).getDeclaration().getSymbolTable());
	}

	public void addInterfaceImplementation(ClassOrInterfaceType interfaceType) {
		SymbolDeclaration symbol = getNestedSymbolDeclaration(interfaceType);
		if (!(symbol instanceof InterfaceSymbolDeclaration))
			throw new InterfaceException(interfaceType);

		addInheritance(((InterfaceSymbolDeclaration)symbol).getDeclaration().getSymbolTable());
	}

	public void addInterfaceInheritance(ClassOrInterfaceType superInterfaceType) {
		SymbolDeclaration symbol = getNestedSymbolDeclaration(superInterfaceType);
		if (!(symbol instanceof InterfaceSymbolDeclaration))
			throw new InheritanceException(superInterfaceType, InheritanceException.ExpectedKind.INTERFACE);

		addInheritance(((InterfaceSymbolDeclaration)symbol).getDeclaration().getSymbolTable());
	}

	private SymbolDeclaration getNestedSymbolDeclaration(ClassOrInterfaceType type) {
		SymbolDeclaration symbol = get(type.getName(), Scope.ALL);
		if (symbol == null)
			throw new ReferenceNotFoundException(type.getName());

		return symbol;
	}

	public void addDependency(SymbolTable dependentTable) {
		dependentTables.add(dependentTable);
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
		if (symbolMapper.containsKey(id))
			return true;

		if (inScope(scope, Scope.INHERITED)) {
			for (SymbolTable table : inheritedTables) {
				if (table.hasSymbol(id, Scope.INHERITED))
					return true;
			}
		}

		if (inScope(scope, Scope.MODULAR) && root != null) {
			if (root.hasSymbol(id, Scope.ALL))
				return true;
		}

		if (inScope(scope, Scope.ALL)) {
			for (SymbolTable table : dependentTables) {
				if (table.hasSymbol(id, Scope.MODULAR))
					return true;
			}
		}

		return false;
	}

	public SymbolDeclaration get(Name name, Scope scope) {
		SymbolDeclaration declaration = get(name.iterator(), scope);
		
		if (declaration != null)
			return declaration;
		if (scope == Scope.ALL) {
			Iterator<String> itr = name.iterator();
			SymbolTable table = moduleNamespace.getRoot().getModuleSymbolTable(itr);

			if (table == null)
				return null;

			return table.get(itr, Scope.MODULAR);
		}

		return null;
	}

	public SymbolDeclaration get(Iterator<String> itr, Scope scope) {
		SymbolDeclaration declaration = get(itr.next(), scope);

		if (itr.hasNext()) {
			if (declaration == null)
				return null;

			if (declaration instanceof ClassSymbolDeclaration)
				return ((ClassSymbolDeclaration)declaration).getDeclaration().getSymbolTable().get(itr, scope);
			if (declaration instanceof InterfaceSymbolDeclaration)
				return ((InterfaceSymbolDeclaration)declaration).getDeclaration().getSymbolTable().get(itr, scope);

			return null;
		}
		
		return declaration;
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

		if (inScope(scope, Scope.MODULAR) && root != null) {
			SymbolDeclaration declaration = root.get(id, scope);
			if (declaration != null)
				return declaration;
		}

		if (inScope(scope, Scope.ALL)) {
			for (SymbolTable table : dependentTables) {
				SymbolDeclaration declaration = table.get(id, Scope.MODULAR);
				if (declaration != null)
					return declaration;
			}
		}

		return null;
	}

	private boolean inScope(Scope scope, Scope requirement) {
		return scopeMapper.get(scope) >= scopeMapper.get(requirement);
	}
	
}
