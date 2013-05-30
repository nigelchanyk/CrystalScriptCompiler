/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.syntaxtree.classes.ClassDeclaration;
import crystalscriptcompiler.syntaxtree.classes.MemberDeclaration;
import crystalscriptcompiler.syntaxtree.interfaces.InterfaceDeclaration;
import crystalscriptcompiler.syntaxtree.methods.MethodDeclaration;
import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class SymbolTable {

	public static enum Scope {
		LOCAL,
		INHERITED,
		ALL
	}
	
	private SymbolTable root; // Nullable
	private ArrayList<SymbolTable> inheritedTables = new ArrayList<>();
	private HashMap<String, SymbolDeclaration> symbolMapper = new HashMap<>();

	public SymbolTable() {
	}

	public SymbolTable(SymbolTable root) {
		this.root = root;
	}

	public void addInheritance(SymbolTable inheritedTable) {
		inheritedTables.add(inheritedTable);
	}

	public void addSymbol(String id, ClassDeclaration declaration) {
		symbolMapper.put(id, new ClassSymbolDeclaration(declaration));
	}

	public void addSymbol(String id, InterfaceDeclaration declaration) {
		symbolMapper.put(id, new InterfaceSymbolDeclaration(declaration));
	}

	public void addSymbol(String id, MethodDeclaration declaration) {
		symbolMapper.put(id, new MethodSymbolDeclaration(declaration));
	}

	public boolean hasSymbol(String id, Scope scope) {
		if (symbolMapper.containsKey(id))
			return true;

		if (scope == Scope.INHERITED) {
			for (SymbolTable table : inheritedTables) {
				if (table.hasSymbol(id, Scope.INHERITED))
					return true;
			}
		}

		if (scope == Scope.ALL && root != null) {
			if (root.hasSymbol(id, Scope.ALL))
				return true;
		}

		return false;
	}
	
}
