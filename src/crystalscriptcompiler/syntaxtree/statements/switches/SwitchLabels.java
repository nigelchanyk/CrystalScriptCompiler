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
public class SwitchLabels extends SequentialCollection<SwitchLabel> {
	
	public SwitchLabels() {
	}

	public SwitchLabels(SwitchLabel label) {
		super(label);
	}

	public SwitchLabels(SwitchLabel label, SwitchLabels next) {
		super(label, next);
	}

	public boolean isDefault() {
		return size() == 0;
	}
	
}
