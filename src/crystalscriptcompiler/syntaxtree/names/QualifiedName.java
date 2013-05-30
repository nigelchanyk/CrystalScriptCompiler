/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.names;

import crystalscriptcompiler.symbols.SymbolTable;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 * @author User
 */
public class QualifiedName extends Name {
	
	private Name context;

	public QualifiedName(Name context, String value) {
		super(value);
		this.context = context;
		this.context.parent = this;
	}

	@Override
	public void setSymbolTable(SymbolTable symbolTable) {
		super.setSymbolTable(symbolTable);
		context.setSymbolTable(symbolTable);
	}

	@Override
	public boolean equals(Object obj) {
		if (!super.equals(obj))
			return false;
		final QualifiedName other = (QualifiedName) obj;
		if (!Objects.equals(this.context, other.context)) {
			return false;
		}
		return true;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 67 * hash + Objects.hashCode(this.context);
		return hash;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		append(sb);
		return sb.toString();
	}

	@Override
	protected void append(StringBuilder sb) {
		context.append(sb);
		super.append(sb);
	}

	@Override
	public Iterator<String> iterator() {
		Name root = context;
		while (root instanceof QualifiedName)
			root = ((QualifiedName)root).context;
		
		return new NameIterator(root);
	}

}
