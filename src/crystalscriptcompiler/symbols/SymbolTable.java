/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import java.util.HashMap;

/**
 *
 * @author User
 */
public class SymbolTable {
	
	private SymbolTable root;
	private HashMap<String, SymbolDeclaration> symbolMapper = new HashMap<>();
	
}
