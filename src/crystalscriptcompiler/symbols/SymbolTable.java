/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import java.util.ArrayList;
import java.util.HashMap;

/**
 *
 * @author User
 */
public class SymbolTable {
	
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
	
}
