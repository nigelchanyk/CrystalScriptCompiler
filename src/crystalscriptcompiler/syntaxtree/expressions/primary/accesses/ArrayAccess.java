/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.expressions.primary.LeftValue;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ArrayAccess extends LeftValue {
	
	private Name name;
	private Expression index;

	public ArrayAccess(Name name, Expression index) {
		this.name = name;
		this.index = index;
	}
	
}
