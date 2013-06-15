/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.types;

/**
 *
 * @author User
 */
public class VarType extends Type {

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		return obj instanceof Type;
	}

	@Override
	public boolean isAssignableTo(Type type) {
		return true;
	}

	@Override
	public String toString() {
		return "var";
	}

	@Override
	public int hashCode() {
		int hash = 7;
		return hash;
	}
	
}
