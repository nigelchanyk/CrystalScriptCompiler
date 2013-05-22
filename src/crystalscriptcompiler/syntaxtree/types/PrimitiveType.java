/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

/**
 *
 * @author User
 */
public class PrimitiveType extends Type {
	
	public static enum Kind {
		ARRAY,
		BOOLEAN,
		NUMBER,
		OBJECT,
		STRING
	}
	
	private Kind kind;

	public PrimitiveType(Kind kind) {
		this.kind = kind;
	}
	
}
