/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements;

/**
 *
 * @author User
 */
public class ControlStatement extends BlockStatement {
	
	public static enum Kind {
		BREAK,
		CONTINUE
	}

	private Kind kind;
	private String labelName;
	
	public ControlStatement(Kind kind) {
		this.kind = kind;
	}

	public ControlStatement(Kind kind, String labelName) {
		this.kind = kind;
		this.labelName = labelName;
	}
	
}
