/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.interfaces;

import crystalscriptcompiler.syntaxtree.SequentialCollection;
import crystalscriptcompiler.syntaxtree.types.ClassOrInterfaceType;

/**
 *
 * @author User
 */
public class Interfaces extends SequentialCollection<ClassOrInterfaceType> {
	
	public Interfaces() {
	}

	public Interfaces(ClassOrInterfaceType interfaceType) {
		super(interfaceType);
	}

	public Interfaces(ClassOrInterfaceType interfaceType, Interfaces next, Order order) {
		super(interfaceType, next, order);
	}
	
}
