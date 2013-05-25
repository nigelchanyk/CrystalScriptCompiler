/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.basic;

import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class NameExpression extends SingularExpression {
	
	private Name name;

	public NameExpression(Name name) {
		this.name = name;
	}
	
}
