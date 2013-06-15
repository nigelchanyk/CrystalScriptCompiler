/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

import crystalscriptcompiler.helpers.SaveStackIterator;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author User
 */
public class MultipleTypes extends Type implements Iterable {

	private ArrayList<Type> types = new ArrayList<>();

	public void addType(Type type) {
		types.add(type);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final MultipleTypes other = (MultipleTypes) obj;
		if (other.types.size() != types.size())
			return false;

		for (int i = 0; i < types.size(); ++i) {
			if (!types.get(i).equals(other.types.get(i)))
				return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 19 * hash + Objects.hashCode(this.types);
		return hash;
	}

	@Override
	public String toString() {
		if (types.isEmpty())
			return "";
		StringBuilder sb = new StringBuilder(types.get(0).toString());
		for (int i = 0; i < types.size(); ++i)
			sb.append(", ").append(types.get(i).toString());

		return sb.toString();
	}

	@Override
	public Iterator iterator() {
		return types.iterator();
	}

	public SaveStackIterator<Type> saveStackIterator() {
		return new SaveStackIterator.ArrayListIterator<>(types);
	}

	@Override
	public boolean isAssignableTo(Type type) {
		if (!(type instanceof MultipleTypes))
			return false;
		MultipleTypes other = (MultipleTypes) type;
		if (other.types.size() != types.size())
			return false;

		for (int i = 0; i < types.size(); ++i) {
			if (!types.get(i).isAssignableTo(other.types.get(i)))
				return false;
		}

		return true;
	}
	
}
