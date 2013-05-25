/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.accesses;

import crystalscriptcompiler.syntaxtree.expressions.Expression;
import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;

/**
 *
 * @author User
 */
public class PrimaryArrayAccess extends ArrayAccess {
	
	private Primary primary;

	public PrimaryArrayAccess(Primary primary, Expression index) {
		super(index);
		this.primary = primary;
	}
	
}
