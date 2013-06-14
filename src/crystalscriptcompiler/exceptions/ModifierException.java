/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.exceptions;

import crystalscriptcompiler.StringResources;
import crystalscriptcompiler.syntaxtree.classes.Modifier;

/**
 *
 * @author User
 */
public abstract class ModifierException extends CompilerException {
	
	private ModifierException(String message) {
		super(message);
	}
	
	public static class MultipleAccessModifierException extends ModifierException {

		public MultipleAccessModifierException() {
			super(StringResources.ERROR_MULTIPLE_ACCESS_MODIFIERS);
		}
	}

	public static class InvalidModifierException extends ModifierException {

		public InvalidModifierException(Modifier modifier) {
			super(StringResources.errorInvalidModifier(modifier.toString().toLowerCase()));
		}
	}

	public static class ModifierConflictException extends ModifierException {

		public ModifierConflictException(Modifier m1, Modifier m2) {
			super(StringResources.errorModifierConflict(m1.toString().toLowerCase(), m2.toString().toLowerCase()));
		}
	}
	
}
