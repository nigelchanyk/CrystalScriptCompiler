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
public class SuperMethodInvocation extends MethodInvocation {

	private Name superclassName; // Nullable
	private String methodId;

	public SuperMethodInvocation(String methodId, ArgumentList arguments) {
		this(null, methodId, arguments);
	}
	
	public SuperMethodInvocation(Name superclassName, String methodId, ArgumentList arguments) {
		super(arguments);
		this.methodId = methodId;
		this.superclassName = superclassName;
	}

}
