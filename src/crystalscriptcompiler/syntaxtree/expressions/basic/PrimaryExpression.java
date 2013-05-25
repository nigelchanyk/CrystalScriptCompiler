/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;

/**
 *
 * @author User
 */
public class PrimaryExpression extends SingularExpression {
	
	private Primary primary;

	public PrimaryExpression(Primary primary) {
		this.primary = primary;
	}
	
}
