/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.literals;

/**
 *
 * @author User
 */
public class NumberLiteral extends Literal {
	
	private double value;

	public NumberLiteral(double value) {
		this.value = value;
	}
	
}
