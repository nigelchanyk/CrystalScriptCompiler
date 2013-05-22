/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.names;

/**
 *
 * @author User
 */
public class QualifiedName extends Name {
	
	private Name context;

	public QualifiedName(Name context, String value) {
		super(value);
		this.context = context;
	}
	
}
