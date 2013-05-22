/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

/**
 *
 * @author User
 */
public class BinaryExpression extends BasicExpression {
	
	public static enum Kind {
		INHERIT,
		AND,
		OR
	}

	protected BasicExpression left;
	protected BasicExpression right;
	private Kind kind;
	
	public BinaryExpression(BasicExpression left, BasicExpression right, Kind kind) {
		this.left = left;
		this.right = right;
		this.kind = kind;
	}
	
}
