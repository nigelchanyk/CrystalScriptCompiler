/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;

/**
 *
 * @author User
 */
public abstract class FieldAccess extends Primary {
	
	private String id;
	
	public FieldAccess(String id) {
		this.id = id;
	}
	
}
