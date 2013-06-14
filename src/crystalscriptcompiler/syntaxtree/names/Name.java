/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.names;

import crystalscriptcompiler.helpers.SaveStackIterator;
import crystalscriptcompiler.symbols.SymbolTable;
import crystalscriptcompiler.syntaxtree.expressions.primary.LeftValue;
import java.util.Iterator;
import java.util.Objects;
import java.util.Stack;

/**
 *
 * @author User
 */
public class Name extends LeftValue implements Iterable<String> {
	
	protected String value;
	protected QualifiedName parent; // Nullable
	
	public Name(String value) {
		this.value = value;
	}

	public String getLeftMostName() {
		return value;
	}

	public String getRightMostName() {
		return value;
	}

	protected Name getRoot() {
		return this;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Name other = (Name) obj;
		if (!Objects.equals(this.value, other.value)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 7;
		hash = 97 * hash + Objects.hashCode(this.value);
		return hash;
	}

	@Override
	public String toString() {
		return value;
	}

	protected void append(StringBuilder sb) {
		if (sb.length() > 0)
			sb.append('.');
		sb.append(value);
	}

	@Override
	public Iterator<String> iterator() {
		return saveStackIterator();
	}

	public SaveStackIterator<String> saveStackIterator() {
		return new NameIterator(getRoot());
	}

	protected static class NameIterator implements SaveStackIterator<String> {

		public Name root;
		public boolean iterated = false;

		private Stack<Name> positionStack = new Stack<>();
		
		public NameIterator(Name root) {
			this.root = root;
		}
		
		@Override
		public boolean hasNext() {
			return root != null;
		}

		@Override
		public String next() {
			if (!hasNext())
				return null;

			String value = root.value;
			root = root.parent;
			return value;
		}

		@Override
		public void remove() {
		}

		@Override
		public void saveStack() {
			positionStack.add(root);
		}

		@Override
		public void restoreStack() {
			root = positionStack.pop();
		}
		
	}

}
