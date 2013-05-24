/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.expressions.primary.methodinvocation;

import crystalscriptcompiler.syntaxtree.expressions.ArgumentList;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ImplicitMethodInvocation extends MethodInvocation {
	
	private Name name;

	public ImplicitMethodInvocation(Name name, ArgumentList arguments) {
		super(arguments);
		this.name = name;
	}
}
