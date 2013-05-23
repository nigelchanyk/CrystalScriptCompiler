/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.variables.initializers;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class GenericVariableInitializers extends SequentialCollection<GenericVariableInitializer> {
	
	public GenericVariableInitializers() {
	}

	public GenericVariableInitializers(GenericVariableInitializer init) {
		super(init);
	}

	public GenericVariableInitializers(GenericVariableInitializer init, GenericVariableInitializers next) {
		super(init, next);
	}
	
}
