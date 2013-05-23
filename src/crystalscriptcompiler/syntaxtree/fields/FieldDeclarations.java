/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.fields;

import crystalscriptcompiler.syntaxtree.SequentialCollection;
import crystalscriptcompiler.syntaxtree.classes.Modifiers;
import crystalscriptcompiler.syntaxtree.types.Type;
import crystalscriptcompiler.syntaxtree.variables.VariableDeclarator;
import crystalscriptcompiler.syntaxtree.variables.VariableDeclarators;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class FieldDeclarations extends SequentialCollection<FieldDeclaration> {
	
	private static LinkedList<FieldDeclaration> buildCollection(
			Modifiers modifiers, Type type, VariableDeclarators variables) {

		LinkedList<FieldDeclaration> values = new LinkedList<>();
		for (VariableDeclarator variable : variables)
			values.addLast(new FieldDeclaration(modifiers, type, variable));

		return values;
	}
	
	public FieldDeclarations(Modifiers modifiers, Type type, VariableDeclarators variables) {
		super(buildCollection(modifiers, type, variables));
	}
	
}
