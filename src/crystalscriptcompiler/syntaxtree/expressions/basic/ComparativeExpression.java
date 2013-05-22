/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

/**
 *
 * @author User
 */
public class ComparativeExpression extends EqualityExpression {
	
	public static enum Kind {
		INHERIT,
		LESS_THAN,
		GREATER_THAN,
		LESS_THAN_EQUAL,
		GREATER_THAN_EQUAL
	}

	private Kind kind;
	
	public ComparativeExpression(BasicExpression left, BasicExpression right, Kind kind) {
		super(left, right, EqualityExpression.Kind.INHERIT);
		this.kind = kind;
	}
	
}
