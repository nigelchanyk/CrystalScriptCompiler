/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

/**
 *
 * @author User
 */
public class EqualityExpression extends BitwiseExpression {
	
	public static enum Kind {
		INHERIT,
		EQUAL,
		NOT_EQUAL,
		IS,
		ISNT
	}
	
	private Kind kind;
	
	public EqualityExpression(BasicExpression left, BasicExpression right, Kind kind) {
		super(left, right, BitwiseExpression.Kind.INHERIT);
		this.kind = kind;
	}
	
}
