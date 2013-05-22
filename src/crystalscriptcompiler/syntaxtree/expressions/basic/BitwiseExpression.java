/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

/**
 *
 * @author User
 */
public class BitwiseExpression extends BinaryExpression {
	
	public static enum Kind {
		INHERIT,
		AND,
		XOR,
		OR
	}
	
	private Kind kind;
	
	public BitwiseExpression(BasicExpression left, BasicExpression right, Kind kind) {
		super(left, right, BinaryExpression.Kind.INHERIT);
		this.kind = kind;
	}
	
}
