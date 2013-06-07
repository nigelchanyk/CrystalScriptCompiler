/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.exceptions.DuplicateSignatureException;
import crystalscriptcompiler.syntaxtree.methods.MethodDeclaration;
import crystalscriptcompiler.syntaxtree.methods.Parameter;
import crystalscriptcompiler.syntaxtree.types.Type;
import java.util.Iterator;
import java.util.TreeMap;

/**
 *
 * @author User
 */
public class SignatureTree extends SymbolDeclaration {

	private TreeMap<Type, SignatureTree> signatureMapper = new TreeMap<>();
	private MethodDeclaration terminal;

	public SignatureTree() {
		super(Kind.METHOD);
	}

	public SignatureTree(MethodDeclaration declaration) {
		super(Kind.METHOD);
		addMethod(declaration);
	}

	public boolean isTerminal() {
		return terminal != null;
	}

	public final void addMethod(MethodDeclaration declaration) {
		addMethod(declaration, declaration.getParameters().iterator());
	}

	private void addMethod(MethodDeclaration declaration, Iterator<Parameter> itr) {
		if (!itr.hasNext())
			setTerminal(declaration);
		
		Parameter nextParam = itr.next();
		if (signatureMapper.containsKey(nextParam.getType()))
			signatureMapper.get(nextParam.getType()).addMethod(declaration, itr);
		else {
			SignatureTree childNode = new SignatureTree();
			childNode.addMethod(declaration, itr);
			signatureMapper.put(nextParam.getType(), childNode);
		}
	}

	private void setTerminal(MethodDeclaration declaration) {
		if (terminal == null)
			terminal = declaration;
		else
			throw new DuplicateSignatureException(declaration);
	}

}
