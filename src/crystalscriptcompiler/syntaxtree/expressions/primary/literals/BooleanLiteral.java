/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.literals;

/**
 *
 * @author User
 */
public class BooleanLiteral extends Literal {
	
	private boolean value;

	public BooleanLiteral(boolean value) {
		this.value = value;
	}
}
