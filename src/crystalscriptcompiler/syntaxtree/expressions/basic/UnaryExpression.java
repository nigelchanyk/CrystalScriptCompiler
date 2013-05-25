/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

/**
 *
 * @author User
 */
public class UnaryExpression extends SingularExpression {
	
	public static enum Kind {
		NONE,
		POSTINCREMENT,
		POSTDECREMENT,
		PREINCREMENT,
		PREDECREMENT,
		PLUS,
		MINUS,
		COMPLEMENT,
		NEGATE
	}

	private SingularExpression value;
	private Kind kind;

	public UnaryExpression(SingularExpression value) {
		this.value = value;
		this.kind = Kind.NONE;
	}

	public UnaryExpression(SingularExpression value, Kind kind) {
		this.value = value;
		this.kind = kind;
	}
	
}
