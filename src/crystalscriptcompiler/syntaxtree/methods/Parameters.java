/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.methods;

import crystalscriptcompiler.syntaxtree.SequentialCollection;
import crystalscriptcompiler.syntaxtree.types.MultipleTypes;

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

	public Parameters(Parameter parameter, Parameters next, Order order) {
		super(parameter, next, order);
	}

	public MultipleTypes getTypes() {
		MultipleTypes types = new MultipleTypes();
		for (Parameter parameter : this)
			types.addType(parameter.getType());

		return types;
	}
	
}
