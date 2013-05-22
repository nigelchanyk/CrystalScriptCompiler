/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.primary.Primary;

/**
 *
 * @author User
 */
public class UnaryExpression extends BasicExpression {
	
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

	private Primary value;
	private Kind kind;

	public UnaryExpression(Primary value) {
		this.value = value;
		this.kind = Kind.NONE;
	}

	public UnaryExpression(Primary value, Kind kind) {
		this.value = value;
		this.kind = kind;
	}
	
}
