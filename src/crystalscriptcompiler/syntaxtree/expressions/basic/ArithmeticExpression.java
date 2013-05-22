/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

/**
 *
 * @author User
 */
public class ArithmeticExpression extends ComparativeExpression {
	
	public static enum Kind {
		ADD,
		MINUS,
		MULTIPLY,
		DIVIDE,
		MODULO,
		LEFT_SHIFT,
		RIGHT_SHIFT,
		UNSIGNED_RIGHT_SHIFT
	}

	private Kind kind;

	public ArithmeticExpression(BasicExpression left, BasicExpression right, Kind kind) {
		super(left, right, ComparativeExpression.Kind.INHERIT);

		this.kind = kind;
	}
	
}
