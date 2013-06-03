/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.imports;

import crystalscriptcompiler.syntaxtree.ParseTreeNode;
import crystalscriptcompiler.syntaxtree.names.Name;

/**
 *
 * @author User
 */
public class ImportName extends ParseTreeNode {
	
	private Name realName;
	private String alias;

	public ImportName(Name realName) {
		this(realName, realName.getRightMostName());
	}

	public ImportName(Name realName, String alias) {
		this.realName = realName;
		this.alias = alias;
	}

	public String getAlias() {
		return alias;
	}

	public Name getRealName() {
		return realName;
	}
	
}
