/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.expressions.primary.LeftValue;

/**
 *
 * @author User
 */
public abstract class ArrayAccess extends LeftValue {
	
	private Expression index;

	public ArrayAccess(Expression index) {
		this.index = index;
	}
	
}