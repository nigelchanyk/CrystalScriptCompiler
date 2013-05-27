/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.statements.switches;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class SwitchGroups extends SequentialCollection<SwitchGroup> {
	
	public SwitchGroups() {
	}

	public SwitchGroups(SwitchGroup group) {
		super(group);
	}

	public SwitchGroups(SwitchGroup group, SwitchGroups next, Order order) {
		super(group, next, order);
	}
	
}
