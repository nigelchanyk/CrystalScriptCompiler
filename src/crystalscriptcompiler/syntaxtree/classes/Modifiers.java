/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class Modifiers extends SequentialCollection<Modifier> {
	
	public Modifiers() {
	}

	public Modifiers(Modifier modifier) {
		super(modifier);
	}

	public Modifiers(Modifier modifier, Modifiers next) {
		super(modifier, next);
	}
	
}
