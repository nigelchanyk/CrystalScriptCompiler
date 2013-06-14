/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.exceptions.ModifierException;
import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import java.util.HashSet;

/**
 *
 * @author User
 */
public class Modifiers extends ParseTreeNode {

	private HashSet<Modifier> modifierSet;
	private Modifier[] forbiddenModifiers; // Nullable
	
	public Modifiers() {
		modifierSet = new HashSet<>();
	}

	public Modifiers(Modifier modifier) {
		modifierSet = new HashSet<>();
		modifierSet.add(modifier);
	}

	public Modifiers(Modifier modifier, Modifiers next) {
		this.modifierSet = next.modifierSet;
		modifierSet.add(modifier);
	}

	public void setForbiddenModifiers(Modifier ... forbiddenModifiers) {
		this.forbiddenModifiers = forbiddenModifiers;
	}

	public boolean contains(Modifier modifier) {
		return modifierSet.contains(modifier);
	}

	public void setDefaultAccessModifier(Modifier defaultModifier) {
		if (getAccessModifier() == null)
			modifierSet.add(defaultModifier);
	}

	public Modifier getAccessModifier() {
		if (modifierSet.contains(Modifier.PRIVATE))
			return Modifier.PRIVATE;
		if (modifierSet.contains(Modifier.PROTECTED))
			return Modifier.PROTECTED;
		if (modifierSet.contains(Modifier.PUBLIC))
			return Modifier.PUBLIC;

		return null;
	}

	@Override
	public void validateModifiers() {
		int accessModifierCount = 0;
		if (modifierSet.contains(Modifier.PUBLIC))
			accessModifierCount++;
		if (modifierSet.contains(Modifier.PROTECTED))
			accessModifierCount++;
		if (modifierSet.contains(Modifier.PRIVATE))
			accessModifierCount++;

		if (accessModifierCount >= 2)
			throw new ModifierException.MultipleAccessModifierException();

		if (forbiddenModifiers != null) {
			for (Modifier modifier : forbiddenModifiers) {
				if (modifierSet.contains(modifier))
					throw new ModifierException.InvalidModifierException(modifier);
			}
		}
	}
	
}
