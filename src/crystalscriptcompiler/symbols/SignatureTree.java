/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.symbols;

import crystalscriptcompiler.exceptions.DuplicateSignatureException;
import crystalscriptcompiler.helpers.SaveStackIterator;
import crystalscriptcompiler.syntaxtree.methods.OverloadableDeclaration;
import crystalscriptcompiler.syntaxtree.methods.Parameter;
import crystalscriptcompiler.syntaxtree.types.MultipleTypes;
import crystalscriptcompiler.syntaxtree.types.Type;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/**
 *
 * @author User
 */
public class SignatureTree extends SymbolDeclaration {

	private TreeMap<Type, SignatureTree> signatureMapper = new TreeMap<>();
	private OverloadableDeclaration terminal;

	public SignatureTree(OverloadableDeclaration declaration) {
		super(Kind.METHOD);
		addMethod(declaration);
	}

	private SignatureTree(Kind kind) {
		super(kind);
	}

	public boolean isTerminal() {
		return terminal != null;
	}

	public final void addMethod(OverloadableDeclaration declaration) {
		OverloadableDeclaration existingDeclaration = getDeclaration(declaration.getParameters().getTypes());
		if (existingDeclaration != null)
			throw new DuplicateSignatureException(existingDeclaration, declaration);

		addMethod(declaration, declaration.getParameters().iterator());
	}

	private void addMethod(OverloadableDeclaration declaration, Iterator<Parameter> itr) {
		if (!itr.hasNext())
			terminal = declaration;
		
		Parameter nextParam = itr.next();
		if (signatureMapper.containsKey(nextParam.getType()))
			signatureMapper.get(nextParam.getType()).addMethod(declaration, itr);
		else {
			SignatureTree childNode = new SignatureTree(getKind());
			childNode.addMethod(declaration, itr);
			signatureMapper.put(nextParam.getType(), childNode);
		}
	}

	public boolean containsDeclaration(MultipleTypes types) {
		return getDeclaration(types) != null;
	}

	public OverloadableDeclaration getDeclaration(MultipleTypes types) {
		return getDeclaration(types.saveStackIterator());
	}

	private OverloadableDeclaration getDeclaration(SaveStackIterator<Type> itr) {
		if (!itr.hasNext())
			return terminal;

		Type currentType = itr.next();
		OverloadableDeclaration subTreeResult = null;
		for (Map.Entry<Type, SignatureTree> entry : signatureMapper.entrySet()) {
			if (currentType.equals(entry.getKey())) {
				itr.saveStack();
				OverloadableDeclaration temp = entry.getValue().getDeclaration(itr);
				if (temp != null) {
					if (subTreeResult == null)
						subTreeResult = temp;
					else
						throw new DuplicateSignatureException(subTreeResult, temp);
				}
				itr.restoreStack();
			}
		}

		return subTreeResult;
	}
}
