/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;

/**
 *
 * @author User
 */
public class Type extends ParseTreeNode {

	@Override
	public boolean equals(Object obj) {
		return getClass() == obj.getClass();
	}

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return getClass().getName();
	}
	
}
