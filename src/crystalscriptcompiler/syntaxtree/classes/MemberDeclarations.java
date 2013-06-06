/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.syntaxtree.classes;

import crystalscriptcompiler.syntaxtree.SequentialCollection;

/**
 *
 * @author User
 */
public class MemberDeclarations extends SequentialCollection<MemberDeclaration> {
	
	public MemberDeclarations() {
	}

	public MemberDeclarations(MemberDeclaration member) {
		super(member);
	}

	public MemberDeclarations(MemberDeclarations members) {
		super(members);
	}

	public MemberDeclarations(MemberDeclaration member, MemberDeclarations next) {
		super(member, next);
	}

	public MemberDeclarations(MemberDeclarations members, MemberDeclarations next) {
		super(members, next);
	}

	public MemberDeclarations(MemberDeclaration member, MemberDeclarations next, Order order) {
		super(member, next, order);
	}

	public MemberDeclarations(MemberDeclarations members, MemberDeclarations next, Order order) {
		super(members, next, order);
	}

}
