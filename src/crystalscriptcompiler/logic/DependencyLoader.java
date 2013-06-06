/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package crystalscriptcompiler.logic;

import crystalscriptcompiler.Namespace;
import crystalscriptcompiler.Parser;
import crystalscriptcompiler.Scanner;
import crystalscriptcompiler.exceptions.DependencyException;
import crystalscriptcompiler.helpers.Helper;
import crystalscriptcompiler.syntaxtree.ParseTreeRoot;
import crystalscriptcompiler.syntaxtree.names.Name;
import java.io.FileInputStream;
import java.util.LinkedList;

/**
 *
 * @author User
 */
public class DependencyLoader {
	
	public void start(Namespace globalNamespace, ParseTreeRoot startingModule) {
		LinkedList<ParseTreeRoot> moduleQueue = new LinkedList<>();
		LinkedList<Exception> parserExceptions = new LinkedList<>();
		moduleQueue.addLast(startingModule);

		while (!moduleQueue.isEmpty()) {
			ParseTreeRoot module = moduleQueue.poll();
			LinkedList<Name> importList = new LinkedList<>();
			module.addDependencies(importList);

			for (Name name : importList) {
				if (globalNamespace.contains(name))
					continue;

				ParseTreeRoot root;
				try {
					root = buildSyntaxTree(name, globalNamespace);
					moduleQueue.addLast(root);
				} catch (Exception e) {
					parserExceptions.addLast(e);
					root = new ParseTreeRoot();
				}
				
				globalNamespace.add(name, root);
			}
		}

		if (!parserExceptions.isEmpty())
			throw new DependencyException(parserExceptions);
	}

	private ParseTreeRoot buildSyntaxTree(Name name, Namespace globalNamespace) throws Exception {
		Scanner scanner = new Scanner(new FileInputStream(Helper.getModuleFile(name, globalNamespace)));
		Parser parser = new Parser(scanner);
		parser.parse();
		return parser.getRoot();
	}
	
}
