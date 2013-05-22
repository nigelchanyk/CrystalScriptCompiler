/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ClassOrInterfaceType extends Type {
	
	private Name name;
	
	public ClassOrInterfaceType(Name name) {
		this.name = name;
	}
	
}
