/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.names;

import crystalscriptcompiler.syntaxtree.expressions.primary.LeftValue;

/**
 *
 * @author User
 */
public class Name extends LeftValue {
	
	private String value;
	
	public Name(String value) {
		this.value = value;
	}
	
}
