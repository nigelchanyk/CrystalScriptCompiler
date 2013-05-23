/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class Parameters extends SequentialCollection<Parameter> {
	
	public Parameters() {
	}

	public Parameters(Parameter parameter) {
		super(parameter);
	}

	public Parameters(Parameter parameter, Parameters next) {
		super(parameter, next);
	}
	
}
