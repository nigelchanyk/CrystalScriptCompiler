/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.expressions.primary.LeftValue;

/**
 *
 * @author User
 */
public class Assignment extends ParseTreeNode {
	
	public enum Operator {
		EQ,
		MULTEQ,
		DIVEQ,
		MODEQ,
		PLUSEQ,
		MINUSEQ,
		LSHIFTEQ,
		RSHIFTEQ,
		URSHIFTEQ,
		ANDEQ,
		XOREQ,
		OREQ
	}

	private LeftValue leftValue;
	private Operator operator;
	private AssignmentExpression assignmentExpression;

	public Assignment(LeftValue leftValue, Operator operator, AssignmentExpression assignmentExpression) {
		this.leftValue = leftValue;
		this.operator = operator;
		this.assignmentExpression = assignmentExpression;
	}
	
}
